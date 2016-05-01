package co.com.forensis.backend.usuario.infrastructure.persistence

import co.com.forensis.backend.usuario.controller.request.AuthenticateUsuarioRequest
import co.com.forensis.backend.usuario.controller.response.UsuarioAutenticadoResponse
import co.com.forensis.backend.usuario.service.domain.Usuario
import com.google.inject.{ Inject, Singleton }

import scala.concurrent.Future

@Singleton
final class UsuarioRepository @Inject() ( dao: UsuarioDAO ) {

  def getUsuario: Future[ Seq[ UsuarioRecord ] ] = {
    dao.getUsuario
  }

  def insertUsuario( user: Usuario ): Future[ Int ] = {
    dao.insertUsuario( UsuarioRecord( None, user.nombre.toUpperCase, user.documento.toUpperCase, user.sexo.toUpperCase,
      user.fechaNac, user.edad, user.direccionDom.toUpperCase, user.correo.toUpperCase, user.telefono.toUpperCase,
      user.celular.toUpperCase, user.userInsert.toUpperCase, user.modificacion, user.gcmid, user.rol, user.clave ) )
  }

  def updateUsuario( user: Usuario ): Future[ Int ] = {
    dao.updateUsuario( UsuarioRecord(
      Some( user.id ), user.nombre.toUpperCase, user.documento.toUpperCase, user.sexo.toUpperCase,
      user.fechaNac, user.edad, user.direccionDom.toUpperCase, user.correo.toUpperCase, user.telefono.toUpperCase,
      user.celular.toUpperCase, user.userInsert.toUpperCase, user.modificacion, user.gcmid, user.rol, user.clave
    ) )
  }

  def deleteUsuario( id: Int ): Future[ Int ] = {
    dao.deleteUsuario( id )
  }

  def authenticatedUsuario( user: AuthenticateUsuarioRequest ): Future[ Option[ UsuarioRecord ] ] = {
    dao.authenticateUsuario( user )
  }

}
