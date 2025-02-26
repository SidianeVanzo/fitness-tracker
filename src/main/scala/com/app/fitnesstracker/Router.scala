package com.app.fitnesstracker

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.app.fitnesstracker.data.NutritionModel.Nutrition
import spray.json.DefaultJsonProtocol.listFormat

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class Router(implicit ec: ExecutionContext) extends JsonSupport {
  private val op = new OperationRegistry()

  lazy val allRoutes: Route = createNutrition ~ getNutritions ~ getNutritionById

  val getNutritions: Route = {
    path("api" / "nutrition") {
      get {
        onComplete(op.nutritionService.getNutritions()) {
          case Success(res) => complete(StatusCodes.OK, res)
          case Failure(ex) => complete(StatusCodes.InternalServerError, s"Error while getting a meal. Exception: ${ex.getMessage}")
        }
      }
    }
  }

  val getNutritionById: Route = {
    path("api" / "nutrition" / IntNumber) { id =>
      get {
        onComplete(op.nutritionService.getNutrition(id)) {
          case Success(res) => complete(StatusCodes.OK, res)
          case Failure(ex) => complete(StatusCodes.InternalServerError, s"Error while getting a meal. Exception: ${ex.getMessage}")
        }
      }
    }
  }

  val createNutrition: Route = {
    path("api" / "nutrition") {
      post {
        entity(as[Nutrition]) { request =>
          println("ADDING MEAL")
          onComplete(op.nutritionService.createNutrition(request)) {
            case Success(res) => complete(StatusCodes.OK, res)
            case Failure(ex) => complete(StatusCodes.InternalServerError, "Error registering a meal")
          }
        }
      }
    }
  }
}
