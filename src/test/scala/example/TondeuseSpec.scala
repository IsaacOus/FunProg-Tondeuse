package example

import org.scalatest.funsuite.AnyFunSuite
import progfun.TondeusePlatform._
import progfun.{NORTH, Position, TondeusePlatform, TondeuseState}

class TondeuseSpec extends AnyFunSuite {

  private val InitialCoords = TondeusePlatform.InitialCoords

  test("initialize with a valid starting point and direction") {
    val tondeuseState = initializeTondeuse(InitialCoords);
    tondeuseState match {
      case Right(TondeuseState(Position(InitialCoords), NORTH)) =>
      case _ =>
        fail("Tondeuse should be initialized with valid coords and direction")
    }
  }

}
