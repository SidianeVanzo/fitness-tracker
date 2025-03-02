package com.app.fitnesstracker.repository

import com.app.fitnesstracker.config.DatabaseConfig.db
import com.app.fitnesstracker.database.{FoodEntity, MealEntity, Tables}
import slick.jdbc.PostgresProfile.api._

import java.sql.Date
import scala.concurrent.{ExecutionContext, Future}

class NutritionRepository(implicit ec: ExecutionContext) {

  def insert(meal: MealEntity, foods: List[FoodEntity]): Future[Unit] = {
    val query = for {
      _ <- (Tables.meal returning Tables.meal.map(_.id)) += meal
      _ <- Tables.food ++= foods
    } yield ()

    db.run(query.transactionally)
  }

  def getByDate(date: Date): Future[(MealEntity, List[FoodEntity])] ={
    val query = for {
      (n, f) <- Tables.meal joinLeft Tables.food on (_.id === _.mealId)
      if n.date === date
    } yield (n, f)

    db.run(query.result).map { rows =>
      rows.groupBy(_._1).map {
          case (nutrition, foods) => (nutrition, foods.flatMap(_._2).toList)
        }
        .headOption
        .getOrElse(throw new NoSuchElementException(s"Nutrition entry with for $date not found"))
    }
  }
}
