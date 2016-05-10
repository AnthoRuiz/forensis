package co.com.forensis.backend.usuario.service.domain

import org.joda.time.DateTime

/**
 * Created by Usuario on 27/04/2016.
 */
final case class Usuario( id: Int, nombre: String, apellido: String, cedula: String,
                          correo: String, celular: String, clave: String, activo: Boolean )

trait UsuarioDomainOperation

case class UsuarioCreated( s: String ) extends UsuarioDomainOperation

case class UsuarioUpdated( s: String ) extends UsuarioDomainOperation

case class UsuarioDeleted( s: String ) extends UsuarioDomainOperation