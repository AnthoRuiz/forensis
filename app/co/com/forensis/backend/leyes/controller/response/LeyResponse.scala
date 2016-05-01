package co.com.forensis.backend.leyes.controller.response

import co.com.forensis.backend.commons.controller.response.Response
import org.joda.time.DateTime

/**
 * Created by jarvis on 01/05/2016.
 */
final case class LeyResponse( id: Int, titulo: String, capitulo: String, articulo: String ) extends Response
