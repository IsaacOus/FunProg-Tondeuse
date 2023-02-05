package progfun.tondeuse

import progfun.{Avancer, Command, Droite, Gauche}
import progfun.direction._
import progfun.position.Position

object TondeusePlatform {

  private val mapWidth = 10
  private val mapHeight = 10
  private type EitherState = Either[TondeuseError, TondeuseState]
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

  private def toCommand(command: Char): Either[TondeuseError, Command] = {
    command match {
      case 'A' => Right(Avancer)
      case 'D' => Right(Droite)
      case 'G' => Right(Gauche)
      case _   => Left(TondeuseError("Invalid command"))
    }
  }

  private def parseCommand(
      state: EitherState,
      command: Command
  ): EitherState = {
    command match {
      case Avancer => avancer(state)
      case Droite =>
        state.map(s => TondeuseState(s.position, s.direction.tournerDroite))
      case Gauche =>
        state.map(s => TondeuseState(s.position, s.direction.tournerGauche))
    }
  }

  private def avancer(state: EitherState): EitherState = {
    state.map(s => {
      val newPosition = Position((s.position.x, s.position.y + 1))
      TondeuseState(newPosition, s.direction)
    })
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
      case 'E' => Right(EAST)
      case 'W' => Right(WEST)
      case _   => Left(TondeuseError("Invalid direction"))
    }
  }
}
