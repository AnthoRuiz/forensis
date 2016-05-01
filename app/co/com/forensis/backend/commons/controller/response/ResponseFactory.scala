package co.com.forensis.backend.commons.controller.response

import play.api.libs.json.{ Json, Writes }
import play.api.mvc.Result
import play.api.mvc.Results._

trait ResponseFactory {

  implicit val messageResponseWrites: Writes[ MessageResponse ] = Json.writes[ MessageResponse ]

  implicit val DateTimeResponseWrites: Writes[ DateTimeResponse ] = Json.writes[ DateTimeResponse ]

  def createBadRequest( message: String ): Result = BadRequest( Json.toJson( MessageResponse( 400, message ) ) )

  def createOk( message: String ): Result = Ok( Json.toJson( MessageResponse( 200, message ) ) )

  def createOk( dateTime: DateTimeResponse ): Result = Ok( Json.toJson( dateTime ) )

  def createInternalServerError( message: String ): Result = InternalServerError( Json.toJson( MessageResponse( 500, message ) ) )

  def createNotFound( message: String ): Result = NotFound( Json.toJson( MessageResponse( 404, message ) ) )

}

object ResponseFactory extends ResponseFactory