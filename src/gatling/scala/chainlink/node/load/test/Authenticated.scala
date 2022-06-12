package chainlink.node.load.test


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

trait Authenticated extends Simulation {


  val tokenVariable= "___sessionToken"
  val ChainLinkHost = "localhost"
  val ChainLinkPort = 6688
  val ChainLinkServerURL = s"""http://${ChainLinkHost}:${ChainLinkPort}"""
  val sessionPath = "/sessions"


  def authenticate: HttpRequestBuilder = {
    http("authenticate")
      .post(sessionPath)
      .body(StringBody(s"""{"email":"${System.getenv("ADMIN_USER")}","password":"${System.getenv("ADMIN_PASSWORD")}"}"""))
      .check(
        header("Set-Cookie").saveAs(tokenVariable)
      )
  }

  def addAuthenticationHeader(http: HttpRequestBuilder): HttpRequestBuilder = {
    http.header("Cookie", s"#{${tokenVariable}}")
  }

}
