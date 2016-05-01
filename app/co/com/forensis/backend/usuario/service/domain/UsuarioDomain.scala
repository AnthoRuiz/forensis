package co.com.forensis.backend.usuario.service.domain

import org.joda.time.DateTime

/**
 * Created by Usuario on 27/04/2016.
 */
final case class Usuario( id: Int, nombre: String, documento: String,
                          sexo: String, fechaNac: DateTime, edad: Int, direccionDom: String, correo: String,
                          telefono: String, celular: String, userInsert: String, modificacion: DateTime, gcmid: String, rol: String, clave: String )

trait UsuarioDomainOperation

case class UsuarioCreated( s: String ) extends UsuarioDomainOperation

case class UsuarioUpdated( s: String ) extends UsuarioDomainOperation

case class UsuarioDeleted( s: String ) extends UsuarioDomainOperation