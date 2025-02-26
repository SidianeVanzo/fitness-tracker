package com.app.fitnesstracker

import akka.actor.ActorSystem
import akka.http.scaladsl.Http

import scala.concurrent.ExecutionContextExecutor

object Server extends App {

  implicit val system: ActorSystem = ActorSystem("FitnessTracker")
  implicit val ec: ExecutionContextExecutor = system.dispatcher
  val router = new Router()

  val server = Http().newServerAt("localhost", 9090).bind(router.allRoutes)
  server.map { _ =>
    println("Successfully started on localhost:9090 ")
  } recover { case ex =>
    println("Failed to start the server due to: " + ex.getMessage)
  }
}
