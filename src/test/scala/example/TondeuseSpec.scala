package example

import org.scalatest.funsuite.AnyFunSuite
import progfun.direction.{NORTH, SOUTH}
import progfun.position.Position
import progfun.tondeuse.TondeusePlatform._
import progfun.tondeuse.{TondeuseError, TondeuseState}

class TondeuseSpec extends AnyFunSuite {

  test("initialize with a valid starting point and a north direction") {
    val InitialCoords = (1, 1)
    val InitialDirection = 'N'

    val tondeuseState = initializeTondeuse(InitialCoords, InitialDirection)

    assert(
      tondeuseState == Right(TondeuseState(Position(InitialCoords), NORTH))
    )

  }

  test("initialize with a valid starting point and a south direction") {
    val InitialCoords = (0, 0)
    val InitialDirection = 'S'

    val tondeuseState = initializeTondeuse(InitialCoords, InitialDirection)
    assert(
      tondeuseState == Right(TondeuseState(Position(InitialCoords), SOUTH))
    )

  }

  test("initialize with an invalid direction") {
    val tondeuseState = initializeTondeuse((0, 0), 'Z')

    assert(tondeuseState == Left(TondeuseError("Invalid direction")))
  }

  test("initialize with an invalid starting point") {
    val tondeuseState = initializeTondeuse((-1, 0), 'N')

    assert(tondeuseState == Left(TondeuseError("Invalid starting point")))

  }

  test("return a valid state when given an empty list of commands") {
    val tondeuseState = executeCommands(Right(TondeuseState(Position((0, 0)), NORTH)), List())

    assert(tondeuseState == Right(TondeuseState(Position((0, 0)), NORTH)))
  }

}
