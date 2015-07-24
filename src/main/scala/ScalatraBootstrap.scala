import javax.servlet.ServletContext

import org.scalatra._
import org.scalatra.example.UserController

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {
    println("!!!!!!!!!!!!!!!!!!!")
    context.mount(new UserController, "/boot/*")
  }
}
