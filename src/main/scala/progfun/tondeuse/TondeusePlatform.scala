package progfun.tondeuse

import progfun.direction._
import progfun.position.Position

object TondeusePlatform {

  private val mapWidth = 10
  private val mapHeight = 10

  def initializeTondeuse(
      coords: (Int, Int),
      direction: Char
  ): Either[TondeuseError, TondeuseState] = {
    for {
      direction <- toDirection(direction)
      position  <- buildPosition(coords)
    } yield TondeuseState(position, direction)
  }

  def executeCommands(
      tondeuseState: Either[TondeuseError, TondeuseState],
      commands: List[Char]
  ): Either[TondeuseError, TondeuseState] = {
    println(commands)
   tondeuseState
  }

  private def buildPosition(
      coords: (Int, Int)
  ): Either[TondeuseError, Position] = {
    val (x, y) = coords
    if (x < 0 || x > mapWidth || y < 0 || y > mapHeight)
      Left(TondeuseError("Invalid starting point"))
    else Right(Position(coords))
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
