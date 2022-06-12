package chainlink.node.load.test


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

trait Jobs extends Authenticated {
  val JobsEndPoint = "/v2/jobs"

  val JobIdsSessionKey = "___jobIds"

  val JobIdsFileEnvVariable = "JOB_IDS_FILE"

   def allJobIds: HttpRequestBuilder = {
    addAuthenticationHeader(
      http("jobs")
        .get(JobsEndPoint)
        .check(
          status.is(200),
          jsonPath("$.data[*].id").findAll.saveAs(JobIdsSessionKey)
        )
    )
  }
}