package example

import org.scalatest.funsuite.AnyFunSuite
import progfun.direction.{NORTH, SOUTH}
import progfun.tondeuse.TondeusePlatform._
import progfun.position.Position
import progfun.tondeuse.TondeuseState

class TondeuseSpec extends AnyFunSuite {

  test("initialize with a valid starting point and a north direction") {
    val InitialCoords = (1, 1);
    val InitialDirection = NORTH;

    val tondeuseState = initializeTondeuse(InitialCoords, InitialDirection);
    tondeuseState match {
      case Right(TondeuseState(Position(InitialCoords), InitialDirection)) =>
      case _ =>
        fail("Tondeuse should be initialized with valid coords and direction")
    }
  }
  test("initialize with a valid starting point and a south direction") {
    val InitialCoords = (0, 0);
    val InitialDirection = SOUTH;

    val tondeuseState = initializeTondeuse(InitialCoords, InitialDirection);
    tondeuseState match {
      case Right(TondeuseState(Position(InitialCoords), InitialDirection)) =>
      case _ =>
        fail("Tondeuse should be initialized with valid coords and direction")
    }
  }
}
