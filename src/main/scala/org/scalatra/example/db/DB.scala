package org.scalatra.example.db

import org.scalatra.example.db.models.User
import org.scalatra.example.db.tables.Users
import slick.driver.H2Driver.api._

import scala.concurrent.Future

/**
 * @author Dmitry Meshkov
 * @since 11.07.2015
 */
object DB extends DBSupport {

  lazy val db = Database.forConfig("h2mem1")


  def createSchema(): Unit = {
    val setupAction: DBIO[Unit] = DBIO.seq(
      users.schema.create,

      users ++= Seq(
        User(1, "scalatra", "scalatra"),
        User(2, "admin", "admin")
      ))

    run(setupAction)
  }

  def test(): Unit = {
    users.result
  }

  def closeConnection(): Unit = {
    db.close()
  }
}

trait DBSupport {
  lazy val users: TableQuery[Users] = TableQuery[Users]
  implicit val context =  scala.concurrent.ExecutionContext.Implicits.global

  def run[A](action: DBIO[A]): Future[A] = {
    DB.db.run(action)
  }
}
