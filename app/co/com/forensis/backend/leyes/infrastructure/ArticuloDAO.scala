package co.com.forensis.backend.leyes.infrastructure

/**
 * Created by Usuario on 10/05/2016.
 */
import com.google.inject.Inject
import play.api.db.slick.{ DatabaseConfigProvider, HasDatabaseConfigProvider }
import play.db.NamedDatabase
import slick.driver.JdbcProfile

import scala.concurrent.Future

final class ArticuloDAO @Inject() ( @NamedDatabase( "default" ) protected val dbConfigProvider:DatabaseConfigProvider )
    extends HasDatabaseConfigProvider[ JdbcProfile ] {

  import driver.api._

  private val articulos = TableQuery[ ArticuloTable ]

  def getArticulo: Future[ Seq[ ArticuloRecord ] ] = {
    val query = articulos.map( x => x )
    db.run( query.result )
  }

  def insertArticulo( articulo: ArticuloRecord ): Future[ Option[ Int ] ] = {
    val insert = articulos returning articulos.map( _.id ) += articulo
    db.run( insert )
  }

  def updateCapitulo( articulo: ArticuloRecord ): Future[ Int ] = {
    val update = articulos.filter( _.id === articulo.id ).update( articulo )
    db.run( update )
  }

  def deleteCapitulo( id: Int ): Future[ Int ] = {
    val delete = articulos.filter( _.id === id ).delete
    db.run( delete )
  }

  def getArticulosByCapitulo( capituloId: Int, leyId: Int ): Future[ Seq[ ArticuloRecord ] ] = {
    val query = articulos.filter( x => x.capituloId === capituloId && x.leyId === leyId )
    db.run( query.result )
  }
}