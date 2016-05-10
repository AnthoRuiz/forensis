package co.com.forensis.backend.leyes.infrastructure

import com.google.inject.Inject
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import play.db.NamedDatabase
import slick.driver.JdbcProfile

import scala.concurrent.Future

final class LeyDAO @Inject() ( @NamedDatabase( "default" ) protected val dbConfigProvider:DatabaseConfigProvider )
    extends HasDatabaseConfigProvider[ JdbcProfile ] {

  import driver.api._

  private val leyes = TableQuery[ LeyTable ]

  def getLey: Future[ Seq[ LeyRecord ] ] = {
    val query = leyes.map( x => x )
    db.run( query.result )
  }

  def insertLey( ley: LeyRecord ): Future[ Option[ Int ] ] = {
    val insert = leyes returning leyes.map( _.id ) += ley
    db.run( insert )
  }

  def updateLey( ley: LeyRecord ): Future[ Int ] = {
    val update = leyes.filter( _.id === ley.id ).update( ley )
    db.run( update )
  }

  def deleteLey( id: Int ): Future[ Int ] = {
    val delete = leyes.filter( _.id === id ).delete
    db.run( delete )
  }
}