package com.app.fitnesstracker

import com.app.fitnesstracker.config.DatabaseConfig.db
import com.app.fitnesstracker.data.NutritionModel.Nutrition
import com.app.fitnesstracker.database.Tables
import com.app.fitnesstracker.utils.NutritionMapper.nutritionEntityToNutritionDTO
import org.slf4j.LoggerFactory
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class NutritionService(implicit ec: ExecutionContext) {
  val log = LoggerFactory.getLogger(getClass)

  def getNutritions(): Future[List[Nutrition]] = {
    log.info("Trying do get all nutritions from the database")
    val query = for {
      (n, f) <- Tables.nutrition joinLeft Tables.food on (_.id === _.nutritionId)
    } yield (n, f)

    db.run(query.result).map { rows =>
      rows.groupBy(_._1).map {
        case (nutrition, foods) => nutritionEntityToNutritionDTO(nutrition, foods.flatMap(_._2).toList)
      }.toList
    }
  }

  def getNutrition(id: Int): Future[Nutrition] = {
    log.info(s"Trying do get all the information of nutrition $id")
    val query = for {
      (n, f) <- Tables.nutrition joinLeft Tables.food on (_.id === _.nutritionId)
      if n.id === id
    } yield (n, f)

    db.run(query.result).map { rows =>
      rows.groupBy(_._1).map {
          case (nutrition, foods) => nutritionEntityToNutritionDTO(nutrition, foods.flatMap(_._2).toList)
        }
        .headOption
        .getOrElse(throw new NoSuchElementException(s"Nutrition entry with ID $id not found"))
    }
  }

  def createNutrition(req: Nutrition) = {
    log.info("Trying do create a new nutrition")
    Future.successful(
      s"""
         |Nutrition:
         |Meal: ${req.meal}
         |Foods: ${req.foods.mkString(", ")}
         |""".stripMargin)
  }

}
