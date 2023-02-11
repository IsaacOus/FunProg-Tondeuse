package progfun

import better.files.File
import progfun.parsing.Parser

object Main extends App {
  val file: File = File("src/main/resources/sample_input.txt")

  val parser = new Parser(file)
  val mapSize: (Int, Int) = parser.extractMapSize()
  val tondeuses = parser.extractTondeusePosition()
  val commands = parser.extractCommands()

  println(mapSize)
  println(tondeuses)
  println(commands)
}
