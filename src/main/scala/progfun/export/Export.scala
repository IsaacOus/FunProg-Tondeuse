package progfun.`export`

import progfun.tondeuse.TondeuseState

trait Export {
  def export(path: String, mapSize: (Int, Int), tondeuses: List[(TondeuseState, TondeuseState)], commands: List[String]): Unit
}
