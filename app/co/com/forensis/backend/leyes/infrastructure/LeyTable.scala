package co.com.forensis.backend.leyes.infrastructure

import slick.driver.PostgresDriver.api._
import slick.lifted.{ ProvenShape, Tag }

/**
 * Created by jarvis on 01/05/2016.
 */

final case class LeyRecord( id: Option[ Int ], titulo: String, capitulo: String, articulo: String )

final class LeyTable( tag: Tag ) extends Table[ LeyRecord ]( tag, "LEYES" ) {

  def id = column[ Option[ Int ] ]( "ID", O.PrimaryKey, O.AutoInc )

  def titulo = column[ String ]( "TITULO" )

  def capitulo = column[ String ]( "CAPITULO" )

  def articulo = column[ String ]( "ARTICULO" )

  override def * : ProvenShape[ LeyRecord ] = ( id, titulo, capitulo, articulo ) <> ( LeyRecord.tupled, LeyRecord.unapply )
}

