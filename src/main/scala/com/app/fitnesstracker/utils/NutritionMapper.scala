package com.app.fitnesstracker.utils

import com.app.fitnesstracker.data.NutritionModel.{Food, Nutrition}
import com.app.fitnesstracker.database.{FoodEntity, NutritionEntity}

object NutritionMapper {

  def nutritionEntityToNutritionDTO(
                                     nutEntity: NutritionEntity,
                                     foods: List[FoodEntity]
                                   ): Nutrition = {
    Nutrition(
      meal = nutEntity.meal, foods = foods.map(foodEntityToFoodDTO)
    )
  }

  def foodEntityToFoodDTO(entity: FoodEntity): Food = {
    Food(
      name = entity.name, measurementUnit = entity.measurementunit, quantity = entity.quantity.toInt
    )
  }

}
