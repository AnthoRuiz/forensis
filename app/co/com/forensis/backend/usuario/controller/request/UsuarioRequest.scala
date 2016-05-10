package co.com.forensis.backend.usuario.controller.request

/**
 * Created by Usuario on 27/04/2016.
 */

final case class CreateUsuarioRequest( nombre: String, apellido: String, cedula: String,
                                       correo: String, celular: String, clave: String, activo: Boolean )

final case class UpdateUsuarioRequest( id: Int, nombre: String, apellido: String, cedula: String,
                                       correo: String, celular: String, clave: String, activo: Boolean )

final case class AuthenticateUsuarioRequest( correo: String, clave: String )