package progfun.`export`
import better.files.File
import progfun.tondeuse.TondeuseState

class ExportCSV extends Export{
  override def export(path: String, mapSize: (Int, Int), tondeuses: List[(TondeuseState, TondeuseState)], commands: List[String]): Unit = {
    val file = File(path + ".csv")
    val result = file.createIfNotExists().overwrite("numéro;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions\n")
    val lines = tondeuses.zipWithIndex.map {
      case ((tondeuseIntial, tondeuseEnd), index) =>
        s"${(index + 1).toString};${tondeuseIntial.position._1.toString};${tondeuseIntial.position._2.toString};${tondeuseIntial.direction.toString};${tondeuseEnd.position._1.toString};${tondeuseEnd.position._2.toString};${tondeuseEnd.direction.toString};${commands(index)}\n"
    }
    print(result.appendLines(lines.mkString))
  }

}
