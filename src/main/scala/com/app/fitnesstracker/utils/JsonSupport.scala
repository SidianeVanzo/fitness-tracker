package com.app.fitnesstracker.utils

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.app.fitnesstracker.data.NutritionModel.{Food, Meal}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit val foodFormat = jsonFormat3(Food)
  implicit val nutritionFormat = jsonFormat2(Meal)
}
