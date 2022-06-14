package chainlink.node.load.test


import io.gatling.core.Predef._
import io.gatling.http.Predef._

trait Authenticated extends Simulation {


  val TokenVariable = "___sessionToken"
  val ChainLinkServerURLVariable = "___chainLinkServerURL"

  val NumberOfUsersEnvVariable = "USERS_COUNT"
  val chainLinkServerURL = System.getProperty(ChainLinkServerURLVariable)

  val httpProtocol = http
    .baseUrl(chainLinkServerURL)

  val sessionToken = System.getProperty(TokenVariable)

  val numberOfUsers = sys.env.get(NumberOfUsersEnvVariable).map(_.toInt).getOrElse(1)
}
