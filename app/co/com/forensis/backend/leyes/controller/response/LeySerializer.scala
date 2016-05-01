package co.com.forensis.backend.leyes.controller.response

import co.com.forensis.backend.usuario.controller.response.{ UsuarioAutenticadoResponse, UsuarioResponse }
import play.api.libs.json.{ Json, Writes }

/**
 * Created by jarvis on 01/05/2016.
 */
object LeySerializer {

  implicit val LeyWrites: Writes[ LeyResponse ] = Json.writes[ LeyResponse ]

}
