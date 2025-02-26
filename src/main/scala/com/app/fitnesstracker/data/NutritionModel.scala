package com.app.fitnesstracker.data

object NutritionModel {
  case class Nutrition(
                        meal: String,
                        foods: List[Food]
                      )

  case class Food(
                   name: String,
                   measurementUnit: String,
                   quantity: Double
                 ) {
    //todo return calories and macros based on the data provided
  }

  //todo add a formatter json for enum
  object Meal extends Enumeration {
    type Meal = Value
    val Breakfast, Lunch, Snack_1, Snack_2, Dinner = Value
  }

}

