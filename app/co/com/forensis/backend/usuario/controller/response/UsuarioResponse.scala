package co.com.forensis.backend.usuario.controller.response

/**
 * Created by Usuario on 27/04/2016.
 */
import org.joda.time.DateTime

import co.com.forensis.backend.commons.controller.response.Response

final case class UsuarioResponse( id: Int, nombre: String, documento: String,
                                  sexo: String, fechaNac: DateTime, edad: Int, direccionDom: String, correo: String,
                                  telefono: String, celular: String, userInsert: String, modificacion: DateTime,
                                  gcmid: String, rol: String ) extends Response

//NOTE solo para propositos de autenticacion. En este momento representado de la forma mas simple.
final case class UsuarioAutenticadoResponse( nombre: String, rol: String ) extends Response
