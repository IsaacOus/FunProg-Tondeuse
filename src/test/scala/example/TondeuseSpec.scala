package example

import org.scalatest.funsuite.AnyFunSuite
import progfun.direction.{NORTH, SOUTH}
import progfun.position.Position
import progfun.tondeuse.TondeusePlatform._
import progfun.tondeuse.TondeuseState

class TondeuseSpec extends AnyFunSuite {

  test("initialize with a valid starting point and a north direction") {
    val InitialCoords = (1, 1)
    val InitialDirection = 'N'

    val tondeuseState = initializeTondeuse(InitialCoords, InitialDirection)
    tondeuseState match {
      case Right(TondeuseState(Position(InitialCoords), NORTH)) =>
      case _ =>
        fail("Tondeuse should be initialized with valid coords and direction")
    }
  }

  test("initialize with a valid starting point and a south direction") {
    val InitialCoords = (0, 0)
    val InitialDirection = 'S'

    val tondeuseState = initializeTondeuse(InitialCoords, InitialDirection)
    tondeuseState match {
      case Right(TondeuseState(Position(InitialCoords), SOUTH)) =>
      case _ =>
        fail("Tondeuse should be initialized with valid coords and direction")
    }
  }

  test("initialize with an invalid direction") {
    val tondeuseState = initializeTondeuse((0, 0), 'Z')
    tondeuseState match {
      case Left(_) =>
      case _ =>
        fail("Tondeuse should not be initialized with an invalid direction")
    }
  }

  test("initialize with an invalid starting point") {
    val tondeuseState = initializeTondeuse((-1, 0), 'N')
    tondeuseState match {
      case Left(_) =>
      case _ =>
        fail(
          "Tondeuse should not be initialized with an invalid starting point"
        )
    }
  }

}
