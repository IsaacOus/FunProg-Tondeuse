package progfun.position

case class Position(coords: (Int, Int)) {
  val x: Int = coords._1
  val y: Int = coords._2

  def _1 = x
  def _2 = y

}
