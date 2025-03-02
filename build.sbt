ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.16"

ThisBuild / resolvers += "Akka library repository".at("https://repo.akka.io/maven")

val akkaVersion = "2.10.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka"          %% "akka-http"                         % "10.7.0",
  "com.typesafe.akka"          %% "akka-actor-typed"                  % akkaVersion,
  "com.typesafe.akka"          %% "akka-stream"                       % akkaVersion,
  "com.typesafe.akka"          %% "akka-http-spray-json"              % "10.7.0",
  "com.typesafe.akka"          %% "akka-slf4j"                        % akkaVersion,
  // database
  "org.postgresql"              % "postgresql"                        % "42.7.4",
  "com.typesafe.slick"         %% "slick"                             % "3.5.2",
  "com.typesafe.slick"         %% "slick-hikaricp"                    % "3.5.2",
  //log libs
  "ch.qos.logback"              % "logback-classic"                   % "1.5.11",
  "net.logstash.logback"        % "logstash-logback-encoder"          % "8.0",
  //unit test
  "org.scalatest"     %% "scalatest"  % "3.2.19"  % Test,
  "org.mockito"       %% "mockito-scala" % "1.17.37" % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "fitness-tracker"
  )
