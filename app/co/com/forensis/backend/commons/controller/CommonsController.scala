package co.com.forensis.backend.commons.controller

import co.com.forensis.backend.commons.controller.response.{ DateTimeResponse, ResponseFactory }
import org.joda.time.DateTime
import play.api.mvc.{ Action, Controller }

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

final class CommonsController extends Controller {

  def getFecha = Action.async {
    Future {
      val time = DateTime.now()
      val dateTime = DateTimeResponse( time.toString( "dd/MM/yyyy" ), time.toString( "hh:mm:ss" ) )
      ResponseFactory.createOk( dateTime )
    }
  }

}
