package guru.springframework.bootstrap

import guru.springframework.builder.*
import guru.springframework.domain.entities.*
import guru.springframework.repositories.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class ApplicationLoader : ApplicationListener<ContextRefreshedEvent> {
    private var candidateRepository: CandidateRepository? = null
    private var positionRepository: PositionRepository? = null
    private var teamRepository: TeamRepository? = null
    private var interviewersRepository: InterviewersRepository? = null
    private var interviewsRepository: InterviewsRepository? = null
    private var applicationsRepository: ApplicationRepository? = null

    @Autowired
    fun setRepository(
        candidateRepository: CandidateRepository,
        positionRepository: PositionRepository,
        teamRepository: TeamRepository,
        interviewersRepository: InterviewersRepository,
        applicationsRepository: ApplicationRepository,
        interviewsRepository: InterviewsRepository
    ) {
        this.candidateRepository = candidateRepository
        this.positionRepository = positionRepository
        this.teamRepository = teamRepository
        this.interviewersRepository = interviewersRepository
        this.applicationsRepository = applicationsRepository
        this.interviewsRepository = interviewsRepository
    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val applicationBuilder = ApplicationBuilder()
        val userBuilder = UserBuilder()
        val positionBuilder = PositionBuilder()
        val interviewBuilder = InterviewBuilder()
        val teamBuilder = TeamBuilder()


        val candidates = listOf(
            userBuilder.createCandidate("Pedro", "Java", false),
            userBuilder.createCandidate("Markus", "HTML, CSS", false),
            userBuilder.createCandidate("Julia", "React", false)
        )

        candidateRepository!!.save(candidates)

        val teams = listOf(
            teamBuilder.create("Hydra"),
            teamBuilder.create("Ginger"),
            teamBuilder.create("Dot.com"),
            teamBuilder.create("Iskra")
        )

        teamRepository!!.save(teams)

        val positions = listOf(
            positionBuilder.create(1, "Java", "Senior Java Developer"),
            positionBuilder.create(1, "Ruby", "Ruby Intern"),
            positionBuilder.create(2, "CPP", "Backend C++ Developer"),
            positionBuilder.create(3, "HTML, CSS, JavaScript", "Frontend JavaScript Developer"),
            positionBuilder.create(4, "Haskell", "God of Haskell")
        )

        positionRepository!!.save(positions)

        val applications = listOf(
            applicationBuilder.create(1,1, Status.PENDING),
            applicationBuilder.create(2,2, Status.PENDING)
        )

        this.applicationsRepository!!.save(applications)

        val interview = interviewBuilder.create(1,2)
        val interviewId = this.interviewsRepository!!.save(interview).id

        val interviewer = userBuilder
                .createInterviewer("Mike", interviewId)
        interviewersRepository!!.save(interviewer)
    }
}
