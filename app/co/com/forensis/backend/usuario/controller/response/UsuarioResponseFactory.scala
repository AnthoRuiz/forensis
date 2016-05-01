package co.com.forensis.backend.usuario.controller.response

import co.com.forensis.backend.commons.controller.response.{ MessageResponse, ResponseFactory }
import play.api.i18n.Messages.Message
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results._

/**
 * Created by Usuario on 27/04/2016.
 */
object UsuarioResponseFactory extends ResponseFactory {

  import UsuarioSerializer._

  val NOTAUTHORIZED = 401

  def createOk( lista: List[ UsuarioResponse ] ): Result = Ok( Json.toJson( lista ) )

  def createOk( usuario: UsuarioAutenticadoResponse ): Result = Ok( Json.toJson( usuario ) )

  def createUnauthorized(): Result = Unauthorized( Json.toJson( MessageResponse( NOTAUTHORIZED, "Usuario no Autorizado" ) ) )

}
