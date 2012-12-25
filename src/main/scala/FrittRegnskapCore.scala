

import net.noerd.prequel.DatabaseConfig
import net.noerd.prequel.SQLFormatterImplicits._
import net.noerd.prequel.ResultSetRowImplicits._

class FrittRegnskapCore {

  val database = DatabaseConfig(
    driver = "org.mysql.jdbc.JDBCDriver",
    jdbcURL = "jdbc:mysql://localhost:3306/fr"
  )


}
