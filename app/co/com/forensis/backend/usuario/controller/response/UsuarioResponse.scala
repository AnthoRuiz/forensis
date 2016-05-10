package co.com.forensis.backend.usuario.controller.response

import co.com.forensis.backend.commons.controller.response.Response

final case class UsuarioResponse( id: Int, nombre: String, apellido: String, cedula: String,
                                  correo: String, celular: String, clave: String, activo: Boolean ) extends Response

final case class UsuarioAutenticadoResponse( nombre: String, apellido: String, correo: String ) extends Response
