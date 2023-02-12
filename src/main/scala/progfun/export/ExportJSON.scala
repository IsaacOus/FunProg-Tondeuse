package progfun.`export`

import better.files.File
import play.api.libs.json.Json
import progfun.tondeuse.TondeuseState

class ExportJSON extends Export {

  def export(
      path: String,
      mapSize: (Int, Int),
      tondeuses: List[(TondeuseState, TondeuseState)],
      commands: List[String]
  ): Unit = {
    val limite = Json.obj(
      "x" -> mapSize._1,
      "y" -> mapSize._2
    )

    val tondeusesJson = tondeuses.map {
      case (tondeuseIntial, tondeuseEnd) =>
        Json.obj(
          "début" -> Json.obj(
            "x"         -> tondeuseIntial.position._1,
            "y"         -> tondeuseIntial.position._2,
            "direction" -> tondeuseIntial.direction.toString
          ),
          "instructions" ->Json.toJson(
            commands(
            tondeuses.indexOf((tondeuseIntial, tondeuseEnd))
          ).toCharArray.toList.map(_.toString)),
          "fin" -> Json.obj(
            "point" -> Json.obj(
              "x" -> tondeuseEnd.position._1,
              "y" -> tondeuseEnd.position._2
            ),
            "direction" -> tondeuseEnd.direction.toString
          )
        )
    }
    val json = Json.obj("limite" -> limite, "tondeuses" -> tondeusesJson)
    val file = File(path + ".json")
    val result = file.createIfNotExists().overwrite(Json.stringify(json))
    print(result)
  }
}
