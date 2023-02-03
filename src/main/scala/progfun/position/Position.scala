package progfun.position

case class Position(coords: (Int, Int)) {
  val x: Int = coords._1
  val y: Int = coords._2
}
