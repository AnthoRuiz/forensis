package co.com.forensis.backend.leyes.service

import co.com.forensis.backend.commons.service.{ DomainOperation, OperationError, OperationFailed }
import co.com.forensis.backend.leyes.controller.request.{ CreateLeyRequest, UpdateLeyRequest }
import co.com.forensis.backend.leyes.infrastructure.LeyRepository
import co.com.forensis.backend.leyes.domain._
import com.google.inject.{ Inject, Singleton }
import play.api.Logger

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
final class LeyService @Inject() ( repository: LeyRepository ) {

  import co.com.forensis.backend.commons.service.DomainOperation

  def getLey: Future[ Either[ DomainOperation, List[ Ley ] ] ] = {
    repository.getLey.map { leyes =>
      Right(
        leyes.map( ley => Ley( ley.id.get, ley.titulo, ley.capitulo, ley.articulo ) ).toList
      )
    }.recover {
      case e =>
        Logger.error( s"Error consultando las Leyes: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def createLey( leyRequest: CreateLeyRequest ): Future[ Either[ DomainOperation, LeyDomainOperation ] ] = {
    repository.insertLey( Ley( 0, leyRequest.titulo, leyRequest.capitulo, leyRequest.articulo ) ).map { filasAfectadas =>
      filasAfectadas match {
        case 1 => Right( LeyCreated( "created" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error actualizando la ley: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def updateLey( leyUpdate: UpdateLeyRequest ): Future[ Either[ DomainOperation, LeyUpdated ] ] = {
    repository.updateLey( Ley( leyUpdate.id, leyUpdate.titulo, leyUpdate.capitulo, leyUpdate.articulo ) ).map { result =>
      result match {
        case 1 => Right( LeyUpdated( "updated" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error la Ley: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

  def deleteLey( id: Int ): Future[ Either[ DomainOperation, LeyDeleted ] ] = {
    repository.deleteLey( id ).map { result =>
      result match {
        case 1 => Right( LeyDeleted( "deleted" ) )
        case _ => Left( OperationFailed( "failed" ) )
      }
    }.recover {
      case e =>
        Logger.error( s"Error eliminando Ley: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }

}