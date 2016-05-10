package co.com.forensis.backend.leyes.controller.request

/**
 * Created by Usuario on 10/05/2016.
 */
import play.api.libs.json.Json

object ArticuloDeserializer {

  implicit val getArticulosReads = Json.reads[ GetArticulosRequest ]
  // implicit val createLeyReads = Json.reads[ CreateLeyRequest ]

  // implicit val updateLeyReads = Json.reads[ UpdateLeyRequest ]

}