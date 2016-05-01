package co.com.forensis.backend.usuario.controller

import co.com.forensis.backend.commons.service.{ OperationError, OperationFailed }
import co.com.forensis.backend.usuario.controller.request.{ AuthenticateUsuarioRequest, CreateUsuarioRequest, UpdateUsuarioRequest, UsuarioDeserializer }
import co.com.forensis.backend.usuario.controller.response.{ UsuarioAutenticadoResponse, UsuarioResponse, UsuarioResponseFactory }
import co.com.forensis.backend.usuario.service.UsuarioService
import com.google.inject.{ Inject, Singleton }
import play.api.Logger
import play.api.mvc.{ Action, Controller }

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

@Singleton
final class UsuarioController @Inject() ( service: UsuarioService ) extends Controller {

  import UsuarioDeserializer._

  lazy val logger: Logger = Logger( "UsuarioService" )

  def autentica = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ AuthenticateUsuarioRequest ].fold(
      error => {
        Future.successful( UsuarioResponseFactory.createBadRequest( "los campos suministrados son invalidos" ) )
      },
      user =>
        service.authenticateUsuario( user ).map { resultado =>
          resultado.fold(
            error => {
              error match {
                case OperationFailed( _ ) => UsuarioResponseFactory.createUnauthorized()
              }
            },
            success => UsuarioResponseFactory.createOk( success )
          )
        }
    )

    //Simulando la autenticacion de un usuario
    //Future.successful( UsuarioResponseFactory.createUnauthorized()); //no autenticado
    //Future.successful( UsuarioResponseFactory.createOk( UsuarioAutenticadoResponse( "carlosreyes", "INSPECTOR" ) ) ); //autenticado
  }

  def updateUsuario = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ UpdateUsuarioRequest ].fold(
      error => {
        Future.successful( UsuarioResponseFactory.createBadRequest( "los campos suministrados son invalidos" ) )
      },
      user =>
        service.updateUsuario( user ).map { resultado =>
          resultado.fold(
            error => {
              error match {
                case OperationFailed( _ ) => UsuarioResponseFactory.createNotFound( "no se pudo actualizar el usuario con el parametro suministrado" )
                case OperationError( _ ) => UsuarioResponseFactory.
                  createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
              }
            },
            success => UsuarioResponseFactory.createOk( "usuario actualizado exitosamente" )
          )
        }
    )
  }

  def getUsuario = Action.async { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    service.getUsuario.map { result =>
      result.fold(
        error => {
          error match {
            case OperationFailed( _ ) => UsuarioResponseFactory.createNotFound( "no se pudo consultar los usuarios" )
            case OperationError( _ )  => UsuarioResponseFactory.createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
          }
        },
        clases => UsuarioResponseFactory.createOk( clases.map( user => UsuarioResponse( user.id, user.nombre,
          user.documento, user.sexo, user.fechaNac, user.edad, user.direccionDom, user.correo, user.telefono,
          user.celular, user.userInsert, user.modificacion, user.gcmid, user.rol ) ) )
      )
    }
  }

  def createUsuario = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ CreateUsuarioRequest ].fold(
      error => {
        Future.successful( UsuarioResponseFactory.createBadRequest( "los campos suministrados son invalidos" ) )
      },
      clase =>
        service.createUsuario( clase ).map { resultado =>
          resultado.fold(
            error => {
              error match {
                case OperationFailed( _ ) => UsuarioResponseFactory.createNotFound( "no se pudo crear el usuario con el parametro suministrado" )
                case OperationError( x )  => UsuarioResponseFactory.createBadRequest( x )
              }
              //UsuarioResponseFactory.createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
            },
            success => UsuarioResponseFactory.createOk( "usuario creado exitosamente" )
          )
        }
    )
  }

  def deleteUsuario( id: Int ) = Action.async { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    service.deleteUsuario( id ).map { result =>
      result.fold(
        error => {
          error match {
            case OperationFailed( _ ) => UsuarioResponseFactory.createNotFound( "no se pudo eliminar el usuario con el parametro suministrado" )
            case OperationError( _ )  => UsuarioResponseFactory.createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
          }
        },
        success => UsuarioResponseFactory.createOk( "usuario eliminado exitosamente" )
      )
    }
  }

}
