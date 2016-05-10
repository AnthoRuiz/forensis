package co.com.forensis.backend.leyes.infrastructure

import slick.driver.PostgresDriver.api._
import slick.lifted.{ ProvenShape, Tag }

final case class ArticuloRecord( id: Option[ Int ], cuerpo: String, leyId: Int, capituloId: Int )

final class ArticuloTable( tag: Tag ) extends Table[ ArticuloRecord ]( tag, "articulos" ) {

  def id = column[ Option[ Int ] ]( "id", O.PrimaryKey, O.AutoInc )

  def cuerpo = column[ String ]( "cuerpo" )

  def leyId = column[ Int ]( "ley_id" )

  def capituloId = column[ Int ]( "capitulo_id" )

  override def * : ProvenShape[ ArticuloRecord ] = ( id, cuerpo, leyId, capituloId ) <> ( ArticuloRecord.tupled, ArticuloRecord.unapply )
}
