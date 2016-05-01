package co.com.forensis.backend.usuario.service

/**
 * Created by Usuario on 27/04/2016.
 */

import co.com.forensis.backend.commons.service.{ DomainOperation, OperationError, OperationFailed }
import co.com.forensis.backend.usuario.infrastructure.persistence.{ UsuarioRecord, UsuarioRepository }
import co.com.forensis.backend.usuario.controller.request.{ AuthenticateUsuarioRequest, CreateUsuarioRequest, UpdateUsuarioRequest }
import co.com.forensis.backend.usuario.controller.response.UsuarioAutenticadoResponse
import co.com.forensis.backend.usuario.service.domain._
import com.google.inject.{ Inject, Singleton }
import play.api.Logger

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
final class UsuarioService @Inject() ( repository: UsuarioRepository ) {

  import co.com.forensis.backend.commons.service.DomainOperation

  def getUsuario: Future[ Either[ DomainOperation, List[ Usuario ] ] ] = {
    repository.getUsuario.map { clases =>
      Right(
        clases.map( user => Usuario( user.id.get, user.nombre, user.documento, user.sexo, user.fechaNac, user.edad, user.direccionDom,
        user.correo, user.telefono, user.celular, user.userInsert, user.modificacion, user.gcmid, user.rol, user.clave ) ).toList
      )
    }.recover {
      case e =>
        Logger.error( s"Error consultando los Usuario: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def createUsuario( createUsuarioRequest: CreateUsuarioRequest ): Future[ Either[ DomainOperation, UsuarioDomainOperation ] ] = {
    repository.insertUsuario( Usuario( 0, createUsuarioRequest.nombre, createUsuarioRequest.documento, createUsuarioRequest.sexo,
      createUsuarioRequest.fechaNac, createUsuarioRequest.edad, createUsuarioRequest.direccionDom, createUsuarioRequest.correo,
      createUsuarioRequest.telefono, createUsuarioRequest.celular, createUsuarioRequest.userInsert,
      createUsuarioRequest.modificacion, createUsuarioRequest.gcmid, createUsuarioRequest.rol, createUsuarioRequest.clave ) ).map { filasAfectadas =>
      filasAfectadas match {
        case 1 => Right( UsuarioCreated( "created" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error insertando usuario: ${e.getMessage}" )
        Left( OperationError( "Usuario con esa cedula ya existe" ) )
    }
  }

  def updateUsuario( updateUsuario: UpdateUsuarioRequest ): Future[ Either[ DomainOperation, UsuarioUpdated ] ] = {
    repository.updateUsuario( Usuario(
      updateUsuario.id, updateUsuario.nombre, updateUsuario.documento, updateUsuario.sexo, updateUsuario.fechaNac,
      updateUsuario.edad, updateUsuario.direccionDom, updateUsuario.correo, updateUsuario.telefono, updateUsuario.celular,
      updateUsuario.userInsert, updateUsuario.modificacion, updateUsuario.gcmid, updateUsuario.rol, updateUsuario.clave
    ) ).map { result =>
      result match {
        case 1 => Right( UsuarioUpdated( "updated" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error actualizando el usuario: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def deleteUsuario( id: Int ): Future[ Either[ DomainOperation, UsuarioDeleted ] ] = {
    repository.deleteUsuario( id ).map { result =>
      result match {
        case 1 => Right( UsuarioDeleted( "deleted" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error eliminando usuario: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def authenticateUsuario( user: AuthenticateUsuarioRequest ): Future[ Either[ DomainOperation, UsuarioAutenticadoResponse ] ] = {
    repository.authenticatedUsuario( user ).map { ( result: Option[ UsuarioRecord ] ) =>
      result match {
        case Some( usuario ) => Right( UsuarioAutenticadoResponse( usuario.nombre, usuario.rol ) )
        case None            => Left( OperationFailed( "Usuario no existe" ) )
      }
    }
  }

}
