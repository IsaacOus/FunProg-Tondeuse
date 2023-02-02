package progfun

object TondeusePlatform {

  val InitialCoords: (Int, Int) = (1, 1);

  val InitialDirection: Direction = NORTH
  def initializeTondeuse(
      coords: (Int, Int)
  ): Either[TondeuseError, TondeuseState] = {
    Right(TondeuseState(Position(coords), InitialDirection))
  }
}
