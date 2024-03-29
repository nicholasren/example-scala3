import sbt.Keys.libraryDependencies

val scala3Version = "3.3.0"
lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := scala3Version,
  libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "example-scala3",
    commonSettings
  )

lazy val robot = project
  .in(file("robot"))
  .settings(
    name := "robot",
    commonSettings
  )

lazy val fpinscala = project
  .in(file("fpinscala"))
  .settings(
    name := "fpinscala",
    version := "0.1.0",
    commonSettings
  )

lazy val water_pouring = project
  .in(file("water-pouring"))
  .settings(
    name := "water-pouring",
    version := "0.1.0",
    commonSettings
  )

lazy val algorithm = project
  .in(file("algorithm"))
  .settings(
    name := "algorithm",
    version := "0.1.0",
    commonSettings
  )