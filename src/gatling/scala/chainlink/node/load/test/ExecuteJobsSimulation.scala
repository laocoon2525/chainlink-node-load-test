package chainlink.node.load.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExecuteJobsSimulation extends Jobs {

  val jobEndPoint = "/v2/jobs/%s/runs"


  val httpProtocol = http
    .baseUrl(ChainLinkServerURL)

  val exportJobIdsFileOpt = Option(System.getenv(JobIdsFileEnvVariable))

  val feeder = exportJobIdsFileOpt.map(csv(_)).getOrElse(throw new IllegalArgumentException("No job ids specified"))

  val scn = scenario("Execute Jobs")
    .exec(authenticate)
    .feed(feeder)
    .exec(
      addAuthenticationHeader(
        http("execute job")
          .post(jobEndPoint.format("#{jobId}"))
      )
    )


  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)


}