package com.app.fitnesstracker

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import com.app.fitnesstracker.data.NutritionModel.Meal
import com.app.fitnesstracker.utils.JsonSupport
import org.slf4j.{Logger, LoggerFactory}
import spray.json.DefaultJsonProtocol.listFormat

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class Router(implicit ec: ExecutionContext) extends JsonSupport {
  private val op = new OperationRegistry()
  val log: Logger = LoggerFactory.getLogger(getClass)

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
        entity(as[Meal]) { request =>
          onComplete(op.nutritionService.createNutrition(request)) {
            case Success(_) => complete(StatusCodes.Created)
            case Failure(ex) =>
             log.error(s"create nutrition failure: ${ex.getCause} \n ${ex.getMessage}")
              complete(StatusCodes.InternalServerError, s"Error registering a meal. Exception: ${ex.getMessage}")
          }
        }
      }
    }
  }
}
