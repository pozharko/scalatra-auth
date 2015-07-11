package org.scalatra.example

import org.scalatra.auth.strategy.{BasicAuthStrategy, BasicAuthSupport}
import org.scalatra.auth.{ScentrySupport, ScentryConfig}
import org.scalatra.example.models.User
import org.scalatra.{ScalatraBase}
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}


class OurBasicAuthStrategy(protected override val app: ScalatraBase, realm: String) extends BasicAuthStrategy[User](app, realm) {

  protected def validate(userName: String, password: String)(implicit request: HttpServletRequest, response: HttpServletResponse): Option[User] = {
    DB.getUserByLogin(userName).filter(_.password == password)
  }

  protected def getUserId(user: User)(implicit request: HttpServletRequest, response: HttpServletResponse): String = user.id.toString
}

trait AuthenticationSupport extends ScentrySupport[User] with BasicAuthSupport[User] {
  self: ScalatraBase =>

  val realm = "Scalatra Basic Auth Example"

  protected def fromSession = {
    //???????????
    case id: String => DB.getUserById(id.toInt).get
  }

  protected def toSession = {
    case usr: User => usr.id.toString
  }

  protected val scentryConfig = new ScentryConfig {}.asInstanceOf[ScentryConfiguration]


  override protected def configureScentry() = {
    scentry.unauthenticated {
      scentry.strategies("Basic").unauthenticated()
    }
  }

  override protected def registerAuthStrategies() = {
    scentry.register("Basic", app => new OurBasicAuthStrategy(app, realm))
  }

}
