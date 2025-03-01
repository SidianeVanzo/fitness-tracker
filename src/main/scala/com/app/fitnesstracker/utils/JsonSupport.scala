package com.app.fitnesstracker.utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.app.fitnesstracker.data.NutritionModel.{Food, Meal}
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

trait JsonSupport extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit val foodFormat: RootJsonFormat[Food] = jsonFormat3(Food)
  implicit val nutritionFormat: RootJsonFormat[Meal] = jsonFormat3(Meal)
}
