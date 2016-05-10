package co.com.forensis.backend.leyes.infrastructure

import slick.driver.PostgresDriver.api._
import slick.lifted.{ ProvenShape, Tag }

/**
 * Created by jarvis on 01/05/2016.
 */

final case class LeyRecord( id: Option[ Int ], nombre: String )

final class LeyTable( tag: Tag ) extends Table[ LeyRecord ]( tag, "leyes" ) {

  def id = column[ Option[ Int ] ]( "id", O.PrimaryKey, O.AutoInc )

  def nombre = column[ String ]( "nombre" )

  override def * : ProvenShape[ LeyRecord ] = ( id, nombre ) <> ( LeyRecord.tupled, LeyRecord.unapply )
}

