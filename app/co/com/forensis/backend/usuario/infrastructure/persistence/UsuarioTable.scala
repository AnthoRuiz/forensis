package co.com.forensis.backend.usuario.infrastructure.persistence

import org.joda.time.DateTime
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape
import com.github.tototoshi.slick.PostgresJodaSupport._

/**
 * Created by Usuario on 27/04/2016.
 */

final case class UsuarioRecord( id: Option[ Int ], nombre: String, apellido: String, cedula: String,
                                correo: String, celular: String, clave: String, activo: Boolean )

final class UsuarioTable( tag: Tag ) extends Table[ UsuarioRecord ]( tag, "usuarios" ) {

  def id = column[ Option[ Int ] ]( "id", O.PrimaryKey, O.AutoInc )

  def nombre = column[ String ]( "nombre" )

  def apellido = column[ String ]( "apellido" )

  def cedula = column[ String ]( "cedula" )

  def correo = column[ String ]( "correo" )

  def celular = column[ String ]( "celular" )

  def clave = column[ String ]( "clave" )

  def activo = column[ Boolean ]( "activo" )

  override def * : ProvenShape[ UsuarioRecord ] = ( id, nombre, apellido, cedula, correo, celular, clave, activo ) <> ( UsuarioRecord.tupled, UsuarioRecord.unapply )
}
