package org.scalatra.example.db.models

import scala.concurrent.{Await, Future}

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
case class User(id: Option[Int], login: String, password: String) {

  def this(id: Int, login: String, password: String) = this(Some(id), login, password)

  require(login.nonEmpty, "Login should not be empty")
  require(password.length > 4, "Password length should be > 4")

}

case object User extends ((Option[Int], String, String) => User) {
  def apply(id: Int, login: String, password: String) = new User(Some(id), login, password)
}