package com.app.fitnesstracker

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.app.fitnesstracker.data.NutritionModel.{Food, Nutrition}
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport {

  import DefaultJsonProtocol._

  implicit val foodFormat = jsonFormat3(Food)
  implicit val nutritionFormat = jsonFormat2(Nutrition)
}
