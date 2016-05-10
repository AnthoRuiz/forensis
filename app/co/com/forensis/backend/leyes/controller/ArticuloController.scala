package co.com.forensis.backend.leyes.controller

/**
 * Created by Usuario on 10/05/2016.
 */
import co.com.forensis.backend.commons.service.{ OperationError, OperationFailed }
import co.com.forensis.backend.leyes.controller.request._
import co.com.forensis.backend.leyes.controller.response.{ ArticuloResponse, ArticuloResponseFactory, LeyResponseFactory }
import co.com.forensis.backend.leyes.service.LeyService
import com.google.inject.{ Inject, Singleton }
import play.api.Logger
import play.api.mvc.{ Action, Controller }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class ArticuloController @Inject() ( service: LeyService ) extends Controller {

  import ArticuloDeserializer._

  lazy val logger: Logger = Logger( "LeyService" )

  def getArticulosByIds = Action.async( parse.json ) { implicit request =>
    logger.info( s"${request.remoteAddress} ${request.method} ${request.uri}" )

    request.body.validate[ GetArticulosRequest ].fold(
      error => {
        Future.successful( LeyResponseFactory.createBadRequest( "los campos suministrados son invalidos" ) )
      },
      articulo => service.getArticulosByIds( articulo.leyId, articulo.capituloId ).map { resultado =>
        resultado.fold(
          error => {
            error match {
              case OperationFailed( _ ) => ArticuloResponseFactory.createNotFound( "no se pudo obtener los articulos con el parametro suministrado" )
              case OperationError( _ ) => ArticuloResponseFactory.
                createInternalServerError( "un error inesperado ocurrio mientras se procesaba la solicitud" )
            }
          },
          articulos => ArticuloResponseFactory.createOk( articulos.map( articulo => ArticuloResponse( articulo.id, articulo.cuerpo ) ) )
        )
      }
    )
  }

  /*def getLey = Action.async { implicit request =>
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
  }*/

}
