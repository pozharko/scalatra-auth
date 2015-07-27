import javax.servlet.ServletContext

import org.scalatra._
import org.scalatra.example.UserController
import org.scalatra.example.db.DB
import org.scalatra.example.db.models.User
import org.scalatra.example.db.tables.Users
import org.slf4j.LoggerFactory

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
class ScalatraBootstrap extends LifeCycle {

  val logger = LoggerFactory.getLogger(getClass)

  override def init(context: ServletContext) {
    context.mount(new UserController, "/user/*")
    //TODO move to migration tool
    Users.createSchema()
  }

  override def destroy(context: ServletContext): Unit = {
    super.destroy(context)
    DB.closeConnection()
  }
}
