package com.app.fitnesstracker

import com.app.fitnesstracker.data.NutritionModel.Meal
import com.app.fitnesstracker.database.{FoodEntity, MealEntity}
import com.app.fitnesstracker.repository.NutritionRepository
import com.app.fitnesstracker.utils.DateFormatter.stringToDate
import com.app.fitnesstracker.utils.NutritionMapper.mealEntityToMealDTO
import org.slf4j.LoggerFactory

import java.sql.Date
import java.time.LocalDate
import java.util.UUID
import scala.concurrent.{ExecutionContext, Future}

class NutritionService(repository: NutritionRepository)(implicit ec: ExecutionContext) {
  private val log = LoggerFactory.getLogger(getClass)

  def getDailyNutrition(dateOpt: Option[String]): Future[Meal] = {
    log.info(s"Trying do get all the information of nutrition $dateOpt")
    val date = dateOpt.map(stringToDate).getOrElse(Date.valueOf(LocalDate.now()))

    repository.getByDate(date)
      .map(r => mealEntityToMealDTO(r._1, r._2))
  }

  def createNutrition(req: Meal): Future[Unit] = {
    log.info("Trying do create a new meal")

    val mealId: String = UUID.randomUUID().toString
    val date = req.date.map(stringToDate).getOrElse(Date.valueOf(LocalDate.now()))

    val mealEntity = MealEntity(mealId, req.name, date)
    val foodEntityList = req.foods.map { food =>
      FoodEntity(UUID.randomUUID().toString, mealId, food.name, food.measurementUnit, food.quantity)
    }

    repository.insert(mealEntity, foodEntityList)
  }
}
