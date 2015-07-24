package org.scalatra.example.db.models

import org.scalatra.example.db.{DBSupport, DB}

import scala.concurrent.Future
import slick.driver.H2Driver.api._

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
case class User(id: Int, login: String, password: String)


object User extends DBSupport {
  def getAll: Future[Seq[User]] = {
    run(users.result)
  }

  def find(id: Int): Future[Option[User]] = {
    run(users.filter(_.id === id).result.map(_.headOption))
  }

  def delete(id: Int): Future[Int] = {
    run(users.filter(_.id === id).delete)
  }

}