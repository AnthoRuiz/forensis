package co.com.forensis.backend.usuario.controller.request

/**
 * Created by Usuario on 27/04/2016.
 */

import org.joda.time.DateTime

import co.com.forensis.backend.commons.controller.response.Response

final case class CreateUsuarioRequest( nombre: String, documento: String, sexo: String, fechaNac: DateTime, edad: Int,
                                       direccionDom: String, correo: String, telefono: String, celular: String,
                                       userInsert: String, modificacion: DateTime, gcmid: String, rol: String, clave: String )

final case class UpdateUsuarioRequest( id: Int, nombre: String, documento: String, sexo: String, fechaNac: DateTime, edad: Int,
                                       direccionDom: String, correo: String, telefono: String, celular: String,
                                       userInsert: String, modificacion: DateTime, gcmid: String, rol: String, clave: String )

final case class AuthenticateUsuarioRequest( correo: String, clave: String )