package progfun

sealed trait Direction {
  def toString: String
}
case object NORTH extends Direction {
  override def toString: String = "N"
}

case object SOUTH extends Direction {
  override def toString: String = "S"
}

case object EST extends Direction {
  override def toString: String = "E"
}

case object WEST extends Direction {
  override def toString: String = "W"
}
