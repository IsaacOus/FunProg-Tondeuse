package example

import org.scalatest.funsuite.AnyFunSuite
import progfun.direction.{EAST, NORTH, SOUTH, WEST}
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
    val tondeuseState =
      executeCommands(Right(TondeuseState(Position((0, 0)), NORTH)), List())

    assert(tondeuseState == Right(TondeuseState(Position((0, 0)), NORTH)))
  }

  test(
    "return a tondeuseState located at (1,2) when given 'A' as a command starting from (1,1)"
  ) {
    val tondeuseState =
      executeCommands(Right(TondeuseState(Position((1, 1)), NORTH)), List('A'))

    assert(tondeuseState == Right(TondeuseState(Position((1, 2)), NORTH)))
  }

  test(
    "return a tondeuseState located at (1,3) when given 'AA' as a command starting from (1,1)"
  ) {
    val tondeuseState = executeCommands(
      Right(TondeuseState(Position((1, 1)), NORTH)),
      List('A', 'A')
    )

    assert(tondeuseState == Right(TondeuseState(Position((1, 3)), NORTH)))
  }

  test(
    "return a tondeuseState direction EAST when given 'D' as a command starting from (1,1)"
  ) {
    val tondeuseState = executeCommands(
      Right(TondeuseState(Position((1, 1)), NORTH)),
      List('D')
    )

    assert(tondeuseState == Right(TondeuseState(Position((1, 1)), EAST)))
  }
  test(
    "return a tondeuseState direction WEST when given 'G' as a command starting from (1,1)"
  ) {
    val tondeuseState = executeCommands(
      Right(TondeuseState(Position((1, 1)), NORTH)),
      List('G')
    )

    assert(tondeuseState == Right(TondeuseState(Position((1, 1)), WEST)))
  }

  test(
    "return a tondeuseState located at (2,1) when given 'DA' as a command starting from (1,1)"
  ) {
    val tondeuseState = executeCommands(
      Right(TondeuseState(Position((1, 1)), NORTH)),
      List('D', 'A')
    )

    assert(tondeuseState == Right(TondeuseState(Position((2, 1)), EAST)))
  }

  test(
    "return a tondeuseState located at (0,1) when given 'GA' as a command starting from (1,1)"
  ) {
    val tondeuseState = executeCommands(
      Right(TondeuseState(Position((1, 1)), NORTH)),
      List('G', 'A')
    )

    assert(tondeuseState == Right(TondeuseState(Position((0, 1)), WEST)))
  }
}
