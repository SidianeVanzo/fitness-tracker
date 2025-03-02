package com.app.fitnesstracker

import com.app.fitnesstracker.database.{FoodEntity, MealEntity}
import com.app.fitnesstracker.repository.NutritionRepository
import com.app.fitnesstracker.utils.NutritionMapper.mealEntityToMealDTO
import org.mockito.ArgumentMatchers.any
import org.mockito.MockitoSugar
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.sql.Date
import scala.concurrent.{ExecutionContext, Future}

class NutritionServiceTest
  extends AnyWordSpec
    with Matchers
    with ScalaFutures
    with MockitoSugar {

  implicit val ec: ExecutionContext = ExecutionContext.global

  "NutritionRepository" should {
    "successfully insert a meal entry" in {
      val repositoryMock = mock[NutritionRepository]
      val service = new NutritionService(repositoryMock)

      val mealId = "MealID"
      val meal = MealEntity(mealId, "Lunch", Date.valueOf("2024-02-27"))
      val foods = List(FoodEntity("FoodID", mealId, "Egg", "uni", 2))

      val req = mealEntityToMealDTO(meal, foods)
      when(repositoryMock.insert(
        any[MealEntity], any[List[FoodEntity]]
      )).thenReturn(Future.successful("The request has been fulfilled and resulted in a new resource being created."))

      val result = service.createNutrition(req)

      whenReady(result) { id =>
        println(id)
        id shouldNot be(null)
      }

      verify(repositoryMock, times(1)).insert(any[MealEntity], any[List[FoodEntity]])
    }
  }
}