package guru.springframework.bootstrap

import guru.springframework.domain.model.Candidate
import guru.springframework.domain.model.Position
import guru.springframework.domain.model.Team
import guru.springframework.repositories.CandidateRepository
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

    @Autowired
    fun setProductRepository(
        candidateRepository: CandidateRepository,
        positionRepository: PositionRepository,
        teamRepository: TeamRepository
    ) {
        this.candidateRepository = candidateRepository
        this.positionRepository = positionRepository
        this.teamRepository = teamRepository
    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val candidate = Candidate("Pedro", "")
        candidateRepository!!.save(candidate)

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
