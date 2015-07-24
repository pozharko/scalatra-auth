package org.scalatra.example.db.tables

import org.scalatra.example.db.models.User
import slick.driver.H2Driver.api._

class Users(tag: Tag) extends Table[User](tag, "USERS") {

  def id: Rep[Int] = column[Int]("ID", O.PrimaryKey)

  def login: Rep[String] = column[String]("LOGIN")

  def password: Rep[String] = column[String]("PASSWORD")

  def * = (id, login, password) <>((User.apply _).tupled, User.unapply)
}
