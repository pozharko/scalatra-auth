package org.scalatra.example

import org.scalatra.example.models.User

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
object DB {

  private var users = List(
    User(1, "scalatra", "scalatra"),
    User(2, "admin", "admin"))

  def getAllUsers: List[User] = users

  def getUserById(id: Int): Option[User] = users.find(_.id == id)

  def getUserByLogin(login: String): Option[User] = users.find(_.login == login)

}
