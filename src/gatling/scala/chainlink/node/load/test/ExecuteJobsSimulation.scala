package chainlink.node.load.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ExecuteJobsSimulation extends Jobs {

  val jobEndPoint = "/v2/jobs/%s/runs"


  val exportJobIdsFileOpt = Option(System.getenv(JobIdsFileEnvVariable))

  val feeder = exportJobIdsFileOpt.map(csv(_)).getOrElse(throw new IllegalArgumentException("No job ids specified"))

  setUp(
    scenario("Execute Jobs")
      .feed(feeder)
      .exec(
        http("execute job")
          .post(jobEndPoint.format("#{jobId}"))
          .header("Cookie", sessionToken)
      ).inject(atOnceUsers(numberOfUsers))
      .protocols(httpProtocol)
  )

}