
import net.noerd.prequel.DatabaseConfig
import net.noerd.prequel.SQLFormatterImplicits._
import net.noerd.prequel.ResultSetRowImplicits._
import dispatch._

object FrittRegnskapCore {

  val database = DatabaseConfig(
    driver = "com.mysql.jdbc.Driver",
    jdbcURL = "jdbc:mysql://localhost:3306/fr",
    username = "root")

  def addInstall = {
    database.transaction { tx =>
      tx.execute("delete from to_install")
      tx.execute("insert into to_install (secret, wikilogin) values ('test','test')")
    }

    val u = url("http://master.local.no/RegnskapServer/services/admin/admin_install.php").POST
    val withParams = u.addParameter("secret", "test").addParameter("wikilogin", "test").addParameter("phone", "98257810").
      addParameter("zipcode", "1068").addParameter("city", "Oslo").addParameter("address", "Ole Reistads vei 25B").
      addParameter("email", "knutbo@ifi.uio.no").addParameter("contact", "Knut Erik").addParameter("clubname", "Minklubb").
      addParameter("domainname", "test").addParameter("password", "passor1").addParameter("superuser", "super")

    val result = Http(withParams OK as.String)

    println("Create domain result:" + result())

  }

  def main(args: Array[String]): Unit = {
    addInstall
  }
}
