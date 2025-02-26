package com.app.fitnesstracker

import scala.concurrent.ExecutionContext

class OperationRegistry(implicit ec: ExecutionContext) {

  val nutritionService = new NutritionService()

}
