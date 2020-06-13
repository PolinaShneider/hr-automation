package guru.springframework.db

import guru.springframework.domain.entities.Position


object PositionData {
    fun getAllPositions(): MutableList<Position> {
        DB.getConnection()
        // execute the query via connection object
        return DB.executeMySQLQuery("SELECT * FROM position;")
    }
}