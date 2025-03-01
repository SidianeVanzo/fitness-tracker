package com.app.fitnesstracker

import com.app.fitnesstracker.config.DatabaseConfig.db
import com.app.fitnesstracker.data.NutritionModel.Meal
import com.app.fitnesstracker.database.{FoodEntity, MealEntity, Tables}
import com.app.fitnesstracker.utils.DateFormatter.stringToDate
import com.app.fitnesstracker.utils.NutritionMapper.mealEntityToMealDTO
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile.api._

import java.sql.Date
import java.time.LocalDate
import scala.concurrent.{ExecutionContext, Future}

class NutritionService(implicit ec: ExecutionContext) {
  private val log = LoggerFactory.getLogger(getClass)

  def getDailyNutrition(dateOpt: Option[String]): Future[Meal] = {
    log.info(s"Trying do get all the information of nutrition $dateOpt")
    val date = dateOpt.map(stringToDate).getOrElse(Date.valueOf(LocalDate.now()))

    val query = for {
      (n, f) <- Tables.meal joinLeft Tables.food on (_.id === _.mealId)
      if n.date === date
    } yield (n, f)

    db.run(query.result).map { rows =>
      rows.groupBy(_._1).map {
          case (nutrition, foods) => mealEntityToMealDTO(nutrition, foods.flatMap(_._2).toList)
        }
        .headOption
        .getOrElse(throw new NoSuchElementException(s"Nutrition entry with for $date not found"))
    }
  }

  def createNutrition(req: Meal): Future[Unit] = {
    log.info("Trying do create a new meal")

    val date = req.date.map(stringToDate).getOrElse(Date.valueOf(LocalDate.now()))

    val query = for {
      nutritionId <- (Tables.meal returning Tables.meal.map(_.id)) += MealEntity(None, req.name, date)
      _ <- Tables.food ++= req.foods.map { food =>
        FoodEntity(None, nutritionId, food.name, food.measurementUnit, food.quantity)
      }
    } yield ()

    db.run(query.transactionally)
  }
}
