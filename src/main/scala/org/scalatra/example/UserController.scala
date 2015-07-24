package org.scalatra.example

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.example.db.models.User
import org.scalatra.json.JacksonJsonSupport

import scala.concurrent.ExecutionContext

class UserController extends ScalatraServlet with JacksonJsonSupport with FutureSupport {

  protected implicit def executor: ExecutionContext = ExecutionContext.Implicits.global

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    User.getAll
  }

  get("/:id") {
    val id = getId
    User.find(id)
  }

  put("/") {
    throw new NotImplementedError()
  }

  post("/:id") {
    throw new NotImplementedError()
  }

  delete("/:id") {
    val id = getId
    User.delete(id).map(_.toString)
  }

  private def getId: Int = {
    //TODO catch errors
    params("id").toInt
  }


}
