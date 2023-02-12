package progfun

import better.files.File
import progfun.`export`.Export
import progfun.parsing.TondeuseParser
import progfun.tondeuse.TondeusePlatform.{EitherState, executeCommands, initializeTondeuse}
import progfun.tondeuse.{TondeuseState}

object Main extends App {
  val file: File = File("src/main/resources/input/sample_input.txt")

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

  val tondeusesIntialState: List[TondeuseState] = tondeusesStates.collect {
    case Right(tondeuse) => tondeuse
  }

  val tondeusesStatesAfterCommandsList: List[TondeuseState] = tondeusesStatesAfterCommands.collect {
     case Right(tondeuse) => tondeuse
   }


    val export = new Export()
    export.exportToJSON("src/main/resources/output-json/sample_output", mapSize, tondeusesIntialState.zip(tondeusesStatesAfterCommandsList), commands)
}
