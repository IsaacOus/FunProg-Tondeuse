package progfun.tondeuse

import progfun.direction._
import progfun.position.Position

object TondeusePlatform {

  val InitialCoords: (Int, Int) = (1, 1)

  def initializeTondeuse(
      coords: (Int, Int),
      direction: Char
  ): Either[TondeuseError, TondeuseState] = {
    for {
      direction <- toDirection(direction)
    } yield TondeuseState(Position(coords), direction)
  }

  private def toDirection(
      direction: Char
  ): Either[TondeuseError, Direction] = {
    direction match {
      case 'N' => Right(NORTH)
      case 'S' => Right(SOUTH)
      case 'E' => Right(EST)
      case 'W' => Right(WEST)
      case _   => Left(TondeuseError("Invalid direction"))
    }
  }
}
