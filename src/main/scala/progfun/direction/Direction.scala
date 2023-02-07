package progfun.direction

sealed trait Direction {
  def toString: String
  def tournerDroite: Direction
  def tournerGauche: Direction
}
case object NORTH extends Direction {
  override def toString: String = "N"
  override def tournerDroite: Direction = EAST
  override def tournerGauche: Direction = WEST
}

case object SOUTH extends Direction {
  override def toString: String = "S"
  override def tournerDroite: Direction = WEST
  override def tournerGauche: Direction = EAST
}

case object EAST extends Direction {
  override def toString: String = "E"
  override def tournerDroite: Direction = SOUTH
  override def tournerGauche: Direction = NORTH
}

case object WEST extends Direction {
  override def toString: String = "W"
  override def tournerDroite: Direction = NORTH
  override def tournerGauche: Direction = SOUTH
}
