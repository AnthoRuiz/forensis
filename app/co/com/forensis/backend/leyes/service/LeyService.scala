package co.com.forensis.backend.leyes.service

import co.com.forensis.backend.commons.service.OperationError
import co.com.forensis.backend.leyes.domain._
import co.com.forensis.backend.leyes.infrastructure.ArticuloDAO
import com.google.inject.{ Inject, Singleton }
import play.api.Logger

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
final class LeyService @Inject() ( dao: ArticuloDAO ) {

  import co.com.forensis.backend.commons.service.DomainOperation

  def getArticulosByIds( leyId: Int, capituloId: Int ): Future[ Either[ DomainOperation, List[ ArticuloDomain ] ] ] = {
    dao.getArticulosByCapitulo( leyId, capituloId ).map { articulos =>
      Right(
        articulos.map( articulo => ArticuloDomain( articulo.id.get, articulo.cuerpo ) ).toList
      )
    }.recover {
      case e =>
        Logger.error( s"Error consultando las Leyes: ${e.getMessage}" )
        Left( OperationError( "error interno en el servidor" ) )
    }
  }
}