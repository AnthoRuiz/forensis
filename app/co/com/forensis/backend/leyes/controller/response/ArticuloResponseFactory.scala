package co.com.forensis.backend.leyes.controller.response

import co.com.forensis.backend.commons.controller.response.ResponseFactory
import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results._

/**
 * Created by Usuario on 10/05/2016.
 */
object ArticuloResponseFactory extends ResponseFactory {

  import ArticuloSerializer._

  def createOk( lista: List[ ArticuloResponse ] ): Result = Ok( Json.toJson( lista ) )

}