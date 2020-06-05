package guru.springframework.services.team

import guru.springframework.domain.model.Team


interface TeamService {
    fun listAllTeams(): Iterable<Team>

    fun getTeamById(id: Int?): Team

    fun saveTeam(team: Team): Team
}
