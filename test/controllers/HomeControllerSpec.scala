package controllers

import models.Token
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test.Helpers._
import play.api.test._
import repositories.TokenRepository
import services.TokenServices

import scala.concurrent.{ ExecutionContext, Future }

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting with MockitoSugar {
  implicit val ec: ExecutionContext = ExecutionContext.global

  "HomeController GET" should {

    "render the get all tokens page from a new instance of controller" in {
      val tokenRepository = mock[TokenRepository]
      when(tokenRepository.all) thenReturn Future {
        Seq(Token(1, "xncbv"))
      }
      val svc = new TokenServices(tokenRepository)
      val controller = new HomeController(svc, stubControllerComponents())
      val home = controller.dbGet().apply(FakeRequest(GET, "/db"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("xncbv")
    }

    "render the index page from a new instance of controller" in {
      val tokenRepository = mock[TokenRepository]
      val svc = new TokenServices(tokenRepository)
      val controller = new HomeController(svc, stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }

    "render the index page from the application" in {
      val controller = inject[HomeController]
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }

    "render the index page from the router" in {
      val request = FakeRequest(GET, "/")
      val home = route(app, request).get

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")
    }
  }
}
