import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object MySQLDatabase {
    const val JDBC_DRIVER = "com.mysql.jdbc.Driver"
    const val DB_URL = "jdbc:mysql://localhost:3306/leftovers"
    var user = ""
    var pass = ""
    fun createConnection(): Connection? {
        var conn: Connection? = null
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        try {
            Class.forName(JDBC_DRIVER)
            conn = DriverManager.getConnection(DB_URL, user, pass)
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return conn
    }
}