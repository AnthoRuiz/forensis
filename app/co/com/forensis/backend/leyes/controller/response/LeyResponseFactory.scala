package co.com.forensis.backend.leyes.controller.response

import co.com.forensis.backend.commons.controller.response.{ MessageResponse, ResponseFactory }
import co.com.forensis.backend.usuario.controller.response.{ UsuarioAutenticadoResponse, UsuarioResponse, UsuarioSerializer }
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results._

/**
 * Created by jarvis on 01/05/2016.
 */
object LeyResponseFactory extends ResponseFactory {

  import LeySerializer._

  def createOk( lista: List[ LeyResponse ] ): Result = Ok( Json.toJson( lista ) )

}
