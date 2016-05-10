package co.com.forensis.backend.leyes.infrastructure

import slick.driver.PostgresDriver.api._
import slick.lifted.{ ProvenShape, Tag }

final case class CapituloRecord( id: Option[ Int ], nombre: String )

final class CapituloTable( tag: Tag ) extends Table[ CapituloRecord ]( tag, "capitulos" ) {

  def id = column[ Option[ Int ] ]( "id", O.PrimaryKey, O.AutoInc )

  def nombre = column[ String ]( "nombre" )

  override def * : ProvenShape[ CapituloRecord ] = ( id, nombre ) <> ( CapituloRecord.tupled, CapituloRecord.unapply )
}
