package progfun

sealed trait Command {
  def toString: String
}

case object Avancer extends Command {
  override def toString: String = "A"
}

case object Gauche extends Command {
  override def toString: String = "G"
}

case object Droite extends Command {
  override def toString: String = "D"
}
