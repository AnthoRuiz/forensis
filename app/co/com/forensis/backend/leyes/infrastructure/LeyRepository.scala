package co.com.forensis.backend.leyes.infrastructure

import co.com.forensis.backend.leyes.domain.Ley
import com.google.inject.{ Inject, Singleton }

import scala.concurrent.Future

/**
 * Created by jarvis on 01/05/2016.
 */
@Singleton
final class LeyRepository @Inject() ( dao: LeyDAO ) {

  def getLey: Future[ Seq[ LeyRecord ] ] = {
    dao.getLey
  }

  def insertLey( ley: Ley ): Future[ Int ] = {
    dao.insertLey( LeyRecord( None, ley.titulo.toUpperCase, ley.capitulo.toUpperCase, ley.articulo.toUpperCase ) )
  }

  def updateLey( ley: Ley ): Future[ Int ] = {
    dao.updateLey( LeyRecord( Some( ley.id ), ley.titulo.toUpperCase, ley.capitulo.toUpperCase, ley.articulo.toUpperCase ) )
  }

  def deleteLey( id: Int ): Future[ Int ] = {
    dao.deleteLey( id )
  }

}
