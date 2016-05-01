package co.com.forensis.backend.leyes.controller.request

import play.api.libs.json.Json

object LeyDeserializer {

  implicit val createLeyReads = Json.reads[ CreateLeyRequest ]

  implicit val updateLeyReads = Json.reads[ UpdateLeyRequest ]

}