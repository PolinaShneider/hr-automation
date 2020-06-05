package guru.springframework.bootstrap

import guru.springframework.domain.entities.Candidate
import guru.springframework.domain.entities.Interviewer
import guru.springframework.domain.entities.Position
import guru.springframework.domain.entities.Team
import guru.springframework.repositories.CandidateRepository
import guru.springframework.repositories.InterviewersRepository
import guru.springframework.repositories.PositionRepository
import guru.springframework.repositories.TeamRepository
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

    @Autowired
    fun setRepository(
        candidateRepository: CandidateRepository,
        positionRepository: PositionRepository,
        teamRepository: TeamRepository,
        interviewersRepository: InterviewersRepository
    ) {
        this.candidateRepository = candidateRepository
        this.positionRepository = positionRepository
        this.teamRepository = teamRepository
        this.interviewersRepository = interviewersRepository
    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val candidate = Candidate("Pedro", "")
        candidateRepository!!.save(candidate)

        val interviewer = Interviewer("Mike")
        interviewersRepository!!.save(interviewer)

        val teams = listOf(
            Team("Blender"),
            Team("Sunrise"),
            Team("Ginger")
        )

        teamRepository!!.save(teams)

        val position = Position()
        position.requirements = "Be awesome"
        position.teamId = 1

        positionRepository!!.save(position)
    }
}
