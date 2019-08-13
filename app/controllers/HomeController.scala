package controllers

import javax.inject.Inject
import play.api.mvc._
import services.TokenServices

import scala.concurrent.{ ExecutionContext, Future }

class HomeController @Inject() (tokenServices: TokenServices, controllerComponents: ControllerComponents)(implicit executionContext: ExecutionContext) extends AbstractController(controllerComponents) {

  def someAsyncApi: Future[Int] = Future {
    1
  }

  def longRunningApi: Future[Int] = Future {
    Thread.sleep(5000L)
    2
  }

  def getAsync(x: Int): Action[AnyContent] = Action.async {
    if (x == 1) {
      val asyncApiResponse: Future[Int] = longRunningApi
      asyncApiResponse.map(value => Ok("Api Result: " + value))
    } else {
      val asyncApiResponse: Future[Int] = someAsyncApi
      asyncApiResponse.map(value => Ok("Api Result: " + value))
    }
  }

  def dbGet: Action[AnyContent] = Action.async {
    tokenServices.all.map { case (t) => Ok(views.html.dbGet(t)) }
  }

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
}
