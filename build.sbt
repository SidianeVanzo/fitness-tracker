ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

ThisBuild / resolvers += "Akka library repository".at("https://repo.akka.io/maven")

libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-http"            % "10.6.3",
  "com.typesafe.akka"  %% "akka-actor-typed"     % "2.9.3",
  "com.typesafe.akka"  %% "akka-stream"          % "2.9.3",
  "com.typesafe.akka"  %% "akka-http-spray-json" % "10.6.3",
  "org.postgresql"      % "postgresql"           % "42.7.4",
  "com.typesafe.slick" %% "slick"                % "3.5.2",
  "com.typesafe.slick" %% "slick-hikaricp"       % "3.5.2"
)

lazy val root = (project in file("."))
  .settings(
    name := "fitness-tracker"
  )
