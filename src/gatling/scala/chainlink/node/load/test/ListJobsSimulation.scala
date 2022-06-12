package chainlink.node.load.test


import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.reflect.io.File

class ListJobsSimulation extends Jobs {

  val httpProtocol = http
    .baseUrl(ChainLinkServerURL)


  val scn = scenario("getJobs")
    .exec(authenticate)
    .exec(
      allJobIds
    ).exec{ s =>
    val exportJobIdsFileOpt = Option(System.getenv(JobIdsFileEnvVariable))
    exportJobIdsFileOpt.foreach(jobIdsFile =>
      File(jobIdsFile)
        .appendAll((Seq("jobId") ++ s(JobIdsSessionKey).as[Seq[String]]).mkString("\n"))
    )
    s
  }




  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)


}