package co.com.forensis.backend.commons.controller

import co.com.tricloud.backend.BaseSpec
import co.com.forensis.backend.commons.controller.response.ResponseFactory
import play.api.libs.json.Json

import play.api.test.Helpers._

import scala.concurrent.Future

class ResponseFactorySpec extends BaseSpec {

  "ResponseFactory" should {

    "crear un response OK" in {
      val result = ResponseFactory.createOk( "operacion exitosa" )
      val future = Future.successful( result )

      val expected = Json.parse( """{"status":200,"message":"operacion exitosa"}""" )

      status( future ) must equalTo( OK )
      contentType( future ) must beSome.which( _ == "application/json" )
      contentAsJson( future ) must equalTo( expected )
    }

    "crear un response BAD_REQUEST" in {
      val result = ResponseFactory.createBadRequest( "campos invalidos" )
      val future = Future.successful( result )

      val expected = Json.parse( """{"status":400,"message":"campos invalidos"}""" )

      status( future ) must equalTo( BAD_REQUEST )
      contentType( future ) must beSome.which( _ == "application/json" )
      contentAsJson( future ) must equalTo( expected )
    }

    "crear un response INTERNAL_SERVER_ERROR" in {
      val result = ResponseFactory.createInternalServerError( "error interno" )
      val future = Future.successful( result )

      val expected = Json.parse( """{"status":500,"message":"error interno"}""" )

      status( future ) must equalTo( INTERNAL_SERVER_ERROR )
      contentType( future ) must beSome.which( _ == "application/json" )
      contentAsJson( future ) must equalTo( expected )
    }

    "crear un response NOT_FOUND" in {
      val result = ResponseFactory.createNotFound( "recurso no encontrado" )
      val future = Future.successful( result )

      val expected = Json.parse( """{"status":404,"message":"recurso no encontrado"}""" )

      status( future ) must equalTo( NOT_FOUND )
      contentType( future ) must beSome.which( _ == "application/json" )
      contentAsJson( future ) must equalTo( expected )
    }
  }
}
