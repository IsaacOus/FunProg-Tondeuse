package progfun

import better.files.File
import progfun.parsing.TondeuseParser
import progfun.tondeuse.TondeusePlatform.{
  executeCommands,
  initializeTondeuse,
  EitherState
}

object Main extends App {
  val file: File = File("src/main/resources/sample_input.txt")

  val parser = new TondeuseParser(file)
  val mapSize: (Int, Int) = parser.extractMapSize()
  val tondeuses = parser.extractTondeusePosition()
  val commands = parser.extractCommands()

  val tondeusesStates: List[EitherState] = tondeuses.map(tondeuse => {
    initializeTondeuse((tondeuse._1, tondeuse._2), tondeuse._3.toCharArray()(0))
  })

  val tondeusesStatesAfterCommands: List[EitherState] =
    tondeusesStates
      .zip(commands)
      .map(tondeuse => {
        executeCommands(tondeuse._1, tondeuse._2.toCharArray.toList)
      })

  println(
    "Output:" + tondeusesStatesAfterCommands.map(_.toString).mkString("\n")
  )
}
