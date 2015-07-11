val scalatraVersion = "2.4.0-RC2-2"

resolvers += "Scalaz Bintray Repo" at "https://dl.bintray.com/scalaz/releases"

lazy val root = (project in file(".")).settings(
  organization := "com.example",
  name := "scalatra-auth-example",
  version := "0.1.0-SNAPSHOT",
  scalaVersion := "2.11.6",
  scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature"),
  libraryDependencies ++= Seq(
    "org.scalatra" %% "scalatra-auth" % scalatraVersion,
    "org.scalatra" %% "scalatra" % scalatraVersion,
    "org.scalatra" %% "scalatra-json" % scalatraVersion,
    "org.scalatra" %% "scalatra-specs2" % scalatraVersion % "test",
    "org.json4s" %% "json4s-jackson" % "3.3.0.RC2"
  )
).settings(jetty(): _*)
