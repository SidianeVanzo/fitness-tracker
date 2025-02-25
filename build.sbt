ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.5"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.7.0",
  "com.typesafe.akka" %% "akka-actor-typed" % "2.10.1",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.7.0"
)

lazy val root = (project in file("."))
  .settings(
    name := "fitness-tracker"
  )
