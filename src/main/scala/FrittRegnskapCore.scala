
import net.noerd.prequel.DatabaseConfig
import net.noerd.prequel.SQLFormatterImplicits._
import net.noerd.prequel.ResultSetRowImplicits._
import uk.co.bigbeeconsultants.http.request.RequestBody
import uk.co.bigbeeconsultants.http._
import uk.co.bigbeeconsultants.http.response.Status

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

    val requestBody = RequestBody(Map("secret" -> "test", "wikilogin" -> "test", "phone" -> "98257810", "zipcode" -> "1068", "city" -> "Oslo", "address" -> "Ole Reistads vei 25B",
      "email" -> "knutbo@ifi.uio.no", "contact" -> "Knut Erik", "clubname" -> "Minklubb", "domainname" -> "test", "password" -> "passor1", "superuser" -> "super"))

    val url = "http://master.local.no/RegnskapServer/services/admin/admin_install.php"
    val httpClient = new HttpClient
    val response = httpClient.post(url, Some(requestBody))

    println("Create domain result:" + response.body)

  }

  def authBySecret = {
    val secret = (System.currentTimeMillis() + 100000) + "-123"
    database.transaction { tx =>
      tx.execute("update master_person set secret = '" + secret + "' where id = (select person from master_user where username = 'knubo')")
    }
    val url = "http://master.local.no/RegnskapServer/services/authenticate.php"

    val requestBody = RequestBody(Map("action" -> "secret", "secret" -> secret, "username" -> "knubo"))

    val httpClient = new HttpClient
    val response = httpClient.post(url, Some(requestBody))

    if (response.status != Status.S200_OK) {
    	throw new RuntimeException("Did not get forwarded as expected. " + response.status+ " Body was:"+response.body)
    }

    val checkBody = RequestBody(Map("action" -> "test"))
    val responseCheck = httpClient.post(url, Some(checkBody))

    assert(responseCheck.body.equals("knubo"))

  }

  def main(args: Array[String]): Unit = {
    // addInstall
    authBySecret
  }
}
