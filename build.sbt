import sbt.Keys.libraryDependencies

val scala3Version = "3.0.1"
lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := scala3Version,
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  libraryDependencies += "org.scalameta" %% "munit" % "0.7.28" % Test,
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