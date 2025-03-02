package com.app.fitnesstracker

import com.app.fitnesstracker.repository.NutritionRepository

import scala.concurrent.ExecutionContext

class OperationRegistry(implicit ec: ExecutionContext) {

  private val nutritionRepository = new NutritionRepository()
  val nutritionService = new NutritionService(nutritionRepository)

}
