package progfun.parsing

import better.files._

class TondeuseParser(file: File) {

  private val input = extractLines(this.file)

  private def extractLines(file: File) = {
    val lines = file.contentAsString
    lines
  }

  def extractMapSize(): (Int, Int) = {
    val lines = input.split("\n")
    val sizeLine = lines.find(line => line.count(_.isDigit) >= 2)
    sizeLine match {
      case Some(line) => {
        val parts = line.split(" ")
        (parts(0).toInt, parts(1).toInt)
      }
      case None => (0, 0)
    }
  }
  def extractTondeusePosition(): List[(Int, Int, String)] = {
    val lines = input.split("\n").filter(line => line.count(_.isDigit) >= 2)
    lines
      .flatMap(line => {
        val parts = line.split(" ")
        if (parts.length == 3) Some((parts(0).toInt, parts(1).toInt, parts(2)))
        else None
      })
      .toList
  }
  def extractCommands(): List[String] = {
    val lines = input.split("\n").filter(line => line.count(_.isDigit) == 0)
    lines.toList
  }
}
