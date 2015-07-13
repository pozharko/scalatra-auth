package org.scalatra.example

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json.JacksonJsonSupport

import scala.util.{Failure, Success, Try}

class UserController extends ScalatraServlet with AuthenticationSupport with JacksonJsonSupport {

  protected implicit lazy val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
    basicAuth()
  }

  get("/") {
    DB.getAllUsers
    halt(400)
  }

  get("/:id") {
    Try {
      params("id").toInt
    } match {
      case Success(id) => DB.getUserById(id)
      case Failure(ex) => pass()
    }
  }

}
