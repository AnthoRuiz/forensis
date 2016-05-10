package co.com.forensis.backend.leyes.controller.response

import play.api.libs.json.{ Json, Writes }

/**
 * Created by Usuario on 10/05/2016.
 */
object ArticuloSerializer {

  implicit val ArticuloWrites: Writes[ ArticuloResponse ] = Json.writes[ ArticuloResponse ]

}
