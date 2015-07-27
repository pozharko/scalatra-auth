package org.scalatra.example

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.example.db.models.User
import org.scalatra.example.db.tables.Users
import org.scalatra.json.JacksonJsonSupport

import scala.concurrent.ExecutionContext

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
class UserController extends ScalatraServlet with JacksonJsonSupport with FutureSupport {

  protected implicit def executor: ExecutionContext = ExecutionContext.Implicits.global

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    Users.getAll
  }

  get("/:id") {
    Users.find(id)
  }

  //TODO catch require errors (password/login length)
  //TODO correct response body (put/post/delete)
  put("/") {
    val user = User(None, params("login"),  params("password"))
    Users.insert(user)
  }

  post("/:id") {
    val user = User(id, params("login"),  params("password"))
    Users.update(user)
  }

  delete("/:id") {
    Users.delete(id)
    "some response"
  }

  private def id: Int = {
    //TODO catch errors
    params("id").toInt
  }


}
