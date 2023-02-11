package progfun.parsing

import better.files.File
import org.scalatest.funsuite.AnyFunSuite

class TondeuseParserSpec extends AnyFunSuite {
  test("should correctly extract the map size from the input") {
    val file = File.newTemporaryFile()
    file.overwrite("5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA")
    val parser = new TondeuseParser(file)
    val mapSize = parser.extractMapSize()
    assert(mapSize == ((5, 5)))
  }

  test(
    "should correctly extract the tondeuse positions from the input"
  ) {
    val file = File.newTemporaryFile()
    file.overwrite("5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA")
    val parser = new TondeuseParser(file)
    val tondeusePositions = parser.extractTondeusePosition()
    assert(tondeusePositions == List((1, 2, "N"), (3, 3, "E")))
  }

  test("should correctly extract the commands from the input") {
    val file = File.newTemporaryFile()
    file.overwrite("5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA")
    val parser = new TondeuseParser(file)
    val commands = parser.extractCommands()
    assert(commands == List("GAGAGAGAA", "AADAADADDA"))
  }
}
