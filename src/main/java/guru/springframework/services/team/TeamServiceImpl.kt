package guru.springframework.services.team

import guru.springframework.domain.model.Team
import guru.springframework.repositories.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TeamServiceImpl : TeamService {
    private var teamRepository: TeamRepository? = null

    @Autowired
    fun setProductRepository(teamRepository: TeamRepository) {
        this.teamRepository = teamRepository
    }

    override fun listAllTeams(): Iterable<Team> {
        return teamRepository!!.findAll()
    }

    override fun getTeamById(id: Int?): Team {
        return teamRepository!!.findOne(id)
    }

    override fun saveTeam(team: Team): Team {
        return teamRepository!!.save(team)
    }
}
