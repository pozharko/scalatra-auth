package org.scalatra.example.db.tables

import org.scalatra.example.db.DBSupport
import org.scalatra.example.db.models.User
import slick.driver.H2Driver.api._
import scala.concurrent.duration._

import scala.concurrent.{Await, Future}

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
class Users(tag: Tag) extends Table[User](tag, "users") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  //TODO make unique
  def login: Rep[String] = column[String]("login")

  def password: Rep[String] = column[String]("password")

  def * = (id.?, login, password) <>(User.tupled, User.unapply)
}


object Users extends DBSupport {

  //TODO return created id
  def insert(u: User) = {
    val insertActions = DBIO.seq(
      users += u
    )
    run(insertActions)
  }

  def update(u: User) = {
    run(users.filter(_.id === u.id).update(u))
  }

  lazy val users: TableQuery[Users] = TableQuery[Users]

  def getAll: Future[Seq[User]] = {
    run(users.result)
  }

  def find(id: Int): Future[Option[User]] = {
    run(users.filter(_.id === id).result.map(_.headOption))
  }

  def delete(id: Int): Future[Int] = {
    run(users.filter(_.id === id).delete)
  }

  def createSchema(): Unit = {
    val setupAction: DBIO[Unit] = DBIO.seq(
      users.schema.create,

      users ++= Seq(
        User(1, "scalatra", "scalatra"),
        User(2, "admin", "admin")
      ))

    run(setupAction)
  }


}