package com.app.fitnesstracker.utils

import com.app.fitnesstracker.data.NutritionModel.{Food, Meal}
import com.app.fitnesstracker.database.{FoodEntity, MealEntity}
import com.app.fitnesstracker.utils.DateFormatter.dateToString

object NutritionMapper {

  def mealEntityToMealDTO(
                                     nutEntity: MealEntity,
                                     foods: List[FoodEntity]
                                   ): Meal = {
    Meal(
      name = nutEntity.name,
      foods = foods.map(foodEntityToFoodDTO),
      date = Option(dateToString(nutEntity.date))
    )
  }

  def foodEntityToFoodDTO(entity: FoodEntity): Food = {
    Food(
      name = entity.name,
      measurementUnit = entity.measurementunit,
      quantity = entity.quantity
    )
  }
}
