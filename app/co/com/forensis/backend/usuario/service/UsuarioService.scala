package co.com.forensis.backend.usuario.service

/**
 * Created by Usuario on 27/04/2016.
 */

import co.com.forensis.backend.commons.service.{ OperationError, OperationFailed }
import co.com.forensis.backend.usuario.controller.request.{ AuthenticateUsuarioRequest, CreateUsuarioRequest, UpdateUsuarioRequest }
import co.com.forensis.backend.usuario.controller.response.UsuarioAutenticadoResponse
import co.com.forensis.backend.usuario.infrastructure.persistence.{ UsuarioDAO, UsuarioRecord }
import co.com.forensis.backend.usuario.service.domain._
import com.google.inject.{ Inject, Singleton }
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class UsuarioService @Inject() ( dao: UsuarioDAO ) {

  import co.com.forensis.backend.commons.service.DomainOperation

  def getUsuario: Future[ Either[ DomainOperation, List[ Usuario ] ] ] = {
    dao.getUsuario.map { clases =>
      Right(
        clases.map( user => Usuario( user.id.get, user.nombre,
        user.apellido, user.cedula, user.correo, user.celular, user.clave, user.activo ) ).toList
      )
    }.recover {
      case e =>
        Logger.error( s"Error consultando los Usuario: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def createUsuario( user: CreateUsuarioRequest ): Future[ Either[ DomainOperation, UsuarioDomainOperation ] ] = {
    dao.insertUsuario( UsuarioRecord( None, user.nombre, user.apellido, user.cedula,
      user.correo, user.celular, user.clave, user.activo ) ).map { filasAfectadas =>
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

  def updateUsuario( user: UpdateUsuarioRequest ): Future[ Either[ DomainOperation, UsuarioUpdated ] ] = {
    dao.updateUsuario( UsuarioRecord( Some( user.id ), user.nombre, user.apellido, user.cedula,
      user.correo, user.celular, user.clave, user.activo ) ).map { result =>
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
    dao.deleteUsuario( id ).map { result =>
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
    dao.authenticateUsuario( user ).map { ( result: Option[ UsuarioRecord ] ) =>
      result match {
        case Some( usuario ) => Right( UsuarioAutenticadoResponse( usuario.nombre, usuario.apellido, usuario.correo ) )
        case None            => Left( OperationFailed( "Usuario no existe" ) )
      }
    }
  }

}
