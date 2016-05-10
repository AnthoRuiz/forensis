package co.com.forensis.backend.leyes.infrastructure

import com.google.inject.Inject
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import play.db.NamedDatabase
import slick.driver.JdbcProfile

import scala.concurrent.Future

final class CapituloDAO @Inject() ( @NamedDatabase( "default" ) protected val dbConfigProvider:DatabaseConfigProvider )
    extends HasDatabaseConfigProvider[ JdbcProfile ] {

  import driver.api._

  private val capitulos = TableQuery[ CapituloTable ]

  def getCapitulo: Future[ Seq[ CapituloRecord ] ] = {
    val query = capitulos.map( x => x )
    db.run( query.result )
  }

  def insertCapitulo( capitulo: CapituloRecord ): Future[ Option[ Int ] ] = {
    val insert = capitulos returning capitulos.map( _.id ) += capitulo
    db.run( insert )
  }

  def updateCapitulo( capitulo: CapituloRecord ): Future[ Int ] = {
    val update = capitulos.filter( _.id === capitulo.id ).update( capitulo )
    db.run( update )
  }

  def deleteCapitulo( id: Int ): Future[ Int ] = {
    val delete = capitulos.filter( _.id === id ).delete
    db.run( delete )
  }
}
