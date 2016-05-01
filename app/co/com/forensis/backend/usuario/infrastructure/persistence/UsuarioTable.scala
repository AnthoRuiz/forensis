package co.com.forensis.backend.usuario.infrastructure.persistence

import org.joda.time.DateTime
import slick.driver.PostgresDriver.api._
import slick.lifted.ProvenShape
import com.github.tototoshi.slick.PostgresJodaSupport._

/**
 * Created by Usuario on 27/04/2016.
 */

final case class UsuarioRecord( id: Option[ Int ], nombre: String, documento: String,
                                sexo: String, fechaNac: DateTime, edad: Int, direccionDom: String, correo: String,
                                telefono: String, celular: String, userInsert: String, modificacion: DateTime, gcmid: String, rol: String, clave: String )

final class UsuarioTable( tag: Tag ) extends Table[ UsuarioRecord ]( tag, "USUARIO" ) {

  def id = column[ Option[ Int ] ]( "ID", O.PrimaryKey, O.AutoInc )

  def nombre = column[ String ]( "NOMBRE" )

  def documento = column[ String ]( "DOCUMENTO" )

  def sexo = column[ String ]( "SEXO" )

  def fechaNac = column[ DateTime ]( "FECHANAC" )

  def edad = column[ Int ]( "EDAD" )

  def direccionDom = column[ String ]( "DIRECCIONDOM" )

  def correo = column[ String ]( "CORREO" )

  def telefono = column[ String ]( "TELEFONO" )

  def celular = column[ String ]( "CELULAR" )

  def userInsert = column[ String ]( "USERINSERT" )

  def modificacion = column[ DateTime ]( "MODIFICACION" )

  def gcmid = column[ String ]( "GCMID" )

  def rol = column[ String ]( "ROL" )

  def clave = column[ String ]( "CLAVE" )

  override def * : ProvenShape[ UsuarioRecord ] = ( id, nombre, documento, sexo, fechaNac, edad, direccionDom, correo, telefono, celular, userInsert, modificacion, gcmid, rol, clave ) <> ( UsuarioRecord.tupled, UsuarioRecord.unapply )
}
