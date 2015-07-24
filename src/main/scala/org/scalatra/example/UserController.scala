package org.scalatra.example

import org.scalatra._

import scala.concurrent.{ExecutionContext, Future}

class UserController extends ScalatraServlet with FutureSupport  {

  protected implicit def executor: ExecutionContext = ExecutionContext.Implicits.global

  get("/test") {
    Future {
      "test"
    }
  }

}
