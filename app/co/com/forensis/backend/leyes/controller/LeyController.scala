package co.com.forensis.backend.leyes.controller

import co.com.forensis.backend.commons.service.{ OperationError, OperationFailed }
import co.com.forensis.backend.leyes.controller.request.{ CreateLeyRequest, LeyDeserializer, UpdateLeyRequest }
import co.com.forensis.backend.leyes.controller.response.{ LeyResponse, LeyResponseFactory }
import co.com.forensis.backend.leyes.service.LeyService
import com.google.inject.{ Inject, Singleton }
import play.api.Logger
import play.api.mvc.{ Action, Controller }

import scala.concurrent.ExecutionContext.Implicits._
import scala.concurrent.Future

/**
 * Created by jarvis on 01/05/2016.
 */
@Singleton
final class LeyController @Inject() ( service: LeyService ) extends Controller {

  import LeyDeserializer._

  lazy val logger: Logger = Logger( "LeyService" )

  def getLey = Action.async { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    service.getLey.map { result =>
      result.fold(
        error => {
          error match {
            case OperationFailed( _ ) => LeyResponseFactory.createNotFound( "no se pudo consultar la ley" )
            case OperationError( _ )  => LeyResponseFactory.createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
          }
        },
        leyes => LeyResponseFactory.createOk( leyes.map( ley => LeyResponse( ley.id, ley.titulo, ley.capitulo, ley.articulo ) ) )
      )
    }
  }

  def createLey = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ CreateLeyRequest ].fold(
      error => {
        Future.successful( LeyResponseFactory.createBadRequest( "los campos suministrados son invalidos" ) )
      },
      leyes =>
        service.createLey( leyes ).map { resultado =>
          resultado.fold(
            error => {
              error match {
                case OperationFailed( _ ) => LeyResponseFactory.createNotFound( "no se pudo crear la ley con el parametro suministrado" )
                case OperationError( x )  => LeyResponseFactory.createBadRequest( x )
              }
              //UsuarioResponseFactory.createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
            },
            success => LeyResponseFactory.createOk( "Ley creada exitosamente" )
          )
        }
    )
  }

  def deleteLey( id: Int ) = Action.async { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    service.deleteLey( id ).map { result =>
      result.fold(
        error => {
          error match {
            case OperationFailed( _ ) => LeyResponseFactory.createNotFound( "no se pudo eliminar la ley con el parametro suministrado" )
            case OperationError( _ )  => LeyResponseFactory.createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
          }
        },
        success => LeyResponseFactory.createOk( "ley eliminada exitosamente" )
      )
    }
  }

  def updateLey = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ UpdateLeyRequest ].fold(
      error => {
        Future.successful( LeyResponseFactory.createBadRequest( "los campos suministrados son invalidos" ) )
      },
      ley => service.updateLey( ley ).map { resultado =>
        resultado.fold(
          error => {
            error match {
              case OperationFailed( _ ) => LeyResponseFactory.createNotFound( "no se pudo actualizar el usuario con el parametro suministrado" )
              case OperationError( _ ) => LeyResponseFactory.
                createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
            }
          },
          success => LeyResponseFactory.createOk( "ley actualizada exitosamente" )
        )
      }
    )
  }

}