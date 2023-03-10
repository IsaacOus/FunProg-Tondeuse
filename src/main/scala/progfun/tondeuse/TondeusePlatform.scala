package progfun.tondeuse

import progfun.{Avancer, Command, Droite, Gauche}
import progfun.direction._
import progfun.position.Position

object TondeusePlatform {

  private val mapWidth: Int = 5
  private val mapHeight: Int = 5
  type EitherState = Either[DonneesIncorectesException, TondeuseState]
  def initializeTondeuse(
      coords: (Int, Int),
      direction: Char
  ): EitherState = {
    for {
      direction <- toDirection(direction)
      position  <- buildPosition(coords)
    } yield TondeuseState(position, direction)
  }

  def executeCommands(
      tondeuseState: EitherState,
      commands: List[Char]
  ): EitherState = {
    commands.foldLeft(tondeuseState)(
      (state, command) => executeCommand(state, command)
    )
  }

  private def executeCommand(state: EitherState, command: Char): EitherState = {
    toCommand(command) match {
      case Right(command) => parseCommand(state, command)
      case Left(error)    => Left(error)
    }
  }

  private def toCommand(
      command: Char
  ): Either[DonneesIncorectesException, Command] = {
    command match {
      case 'A' => Right(Avancer)
      case 'D' => Right(Droite)
      case 'G' => Right(Gauche)
      case _   => Left(DonneesIncorectesException("Invalid command"))
    }
  }

  private def parseCommand(
      state: EitherState,
      command: Command
  ): EitherState = {
    command match {
      case Avancer =>
        state.flatMap { s =>
          if (isTondeuseAtEdge(s.position, s.direction)) Right(s)
          else avancer(state)
        }
      case Droite =>
        state.map(s => TondeuseState(s.position, s.direction.tournerDroite))
      case Gauche =>
        state.map(s => TondeuseState(s.position, s.direction.tournerGauche))
    }
  }

  private def isTondeuseAtEdge(
      position: Position,
      direction: Direction
  ): Boolean = {
    (position.x == 0 && direction == WEST) ||
    (position.x == mapWidth && direction == EAST) ||
    (position.y == 0 && direction == SOUTH) ||
    (position.y == mapHeight && direction == NORTH)
  }

  private def avancer(state: EitherState): EitherState = {
    state.map { s =>
      val newPosition = s.direction match {
        case NORTH => s.position.copy((s.position.x, s.position.y + 1))
        case SOUTH => s.position.copy((s.position.x, s.position.y - 1))
        case EAST  => s.position.copy((s.position.x + 1, s.position.y))
        case WEST  => s.position.copy((s.position.x - 1, s.position.y))
      }
      TondeuseState(newPosition, s.direction)
    }
  }

  private def buildPosition(
      coords: (Int, Int)
  ): Either[DonneesIncorectesException, Position] = {
    val (x, y) = coords
    if (x < 0 || x > mapWidth || y < 0 || y > mapHeight)
      Left(DonneesIncorectesException("Invalid starting point"))
    else Right(Position(coords))
  }

  private def toDirection(
      direction: Char
  ): Either[DonneesIncorectesException, Direction] = {
    direction match {
      case 'N' => Right(NORTH)
      case 'S' => Right(SOUTH)
      case 'E' => Right(EAST)
      case 'W' => Right(WEST)
      case _   => Left(DonneesIncorectesException("Invalid direction"))
    }
  }
}
