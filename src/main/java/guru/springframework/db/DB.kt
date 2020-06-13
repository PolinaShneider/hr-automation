package guru.springframework.db

import guru.springframework.domain.entities.Position
import java.sql.*
import java.util.Properties

/**
 * Program to list databases in MySQL using Kotlin
 */
object DB {
    internal var conn: Connection? = null
    internal var username = "root" // provide the username
    internal var password = "hello_world" // provide the corresponding password

    fun executeMySQLQuery(query: String): MutableList<Position> {
        var stmt: Statement? = null
        val positions = mutableListOf<Position>()
        var resultset: ResultSet? = null
        try {
            stmt = conn!!.createStatement()
            resultset = stmt!!.executeQuery(query)
            if (stmt.execute(query)) {
                resultset = stmt.resultSet
            }

            while (resultset!!.next()) {
                val id = resultset.getString("id")
                val isOpened = resultset.getString("is_opened")
                val requirements = resultset.getString("requirements")
                val teamId = resultset.getString("team_id")
                val title = resultset.getString("title")

                val res = Position()
                res.title = title
                res.teamId = teamId.toInt()
                res.requirements = requirements
                res.id = id.toInt()
                res.isOpened = isOpened.toInt() == 1

                positions.add(res)
            }

            return positions
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {

            // release resources
            if (resultset != null) {
                try {
                    // resultset.close()
                } catch (sqlEx: SQLException) {

                }
                resultset = null
            }
            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }
                stmt = null
            }
            if (conn != null) {
                try {
                    conn!!.close()
                } catch (sqlEx: SQLException) {
                }
                conn = null
            }
        }

        return positions
    }
    /**
     * This method makes a connection to MySQL Server
     * In this example, MySQL Server is running in the local host (so 127.0.0.1)
     * at the standard port 3306
     */
    fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("user", username)
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            conn = DriverManager.getConnection(
                    "jdbc:" + "mysql" + "://" +
                            "127.0.0.1" +
                            ":" + "3306" + "/springbootdb" +
                            "",
                    connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }
}
