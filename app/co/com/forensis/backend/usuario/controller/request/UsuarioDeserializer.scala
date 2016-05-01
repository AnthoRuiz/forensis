package co.com.forensis.backend.usuario.controller.request

/**
 * Created by Usuario on 27/04/2016.
 */
import play.api.libs.json.Json

object UsuarioDeserializer {

  implicit val createUsuarioReads = Json.reads[ CreateUsuarioRequest ]

  implicit val updateUsuarioReads = Json.reads[ UpdateUsuarioRequest ]

  implicit val authenticateUsuarioReads = Json.reads[ AuthenticateUsuarioRequest ]
}