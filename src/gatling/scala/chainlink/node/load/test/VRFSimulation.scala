package chainlink.node.load.test

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration.DurationInt

class VRFSimulation extends Authenticated {

  val CreateVRFEndpoint = "/v2/keys/vrf"

  setUp(
    scenario("Execute VRF")
      .exec(
        http("vrf request")
          .post(CreateVRFEndpoint)
          .header("Cookie", sessionToken)
          .requestTimeout(3.minutes)
          .check(
            status.is(200),
            jsonPath("$.data.id").findAll.exists
          )
      )
      .inject(constantUsersPerSec(numberOfUsers).during(3.minutes))
      .throttle(
        reachRps(5) in (10.seconds),
        holdFor(3.minutes)
      )
      .protocols(httpProtocol)
  )
}
