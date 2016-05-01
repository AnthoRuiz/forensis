package co.com.forensis.backend.usuario.controller.response

/**
 * Created by Usuario on 27/04/2016.
 */
import play.api.libs.json.{ Json, Writes }

object UsuarioSerializer {

  implicit val usuarioWrites: Writes[ UsuarioResponse ] = Json.writes[ UsuarioResponse ]

  implicit val usuarioAutenticadoWrites: Writes[ UsuarioAutenticadoResponse ] = Json.writes[ UsuarioAutenticadoResponse ]

}
