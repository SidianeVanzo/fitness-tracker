package com.app.fitnesstracker.data

object NutritionModel {
  case class Meal(
                   name: String,
                   foods: List[Food],
                   date: Option[String]
                 )

  case class Food(
                   name: String,
                   measurementUnit: String,
                   quantity: Double
                 ) {
    //todo return calories and macros based on the data provided
  }

  //todo add a formatter json for enum
  object MealEnum extends Enumeration {
    type Meal = Value
    val Breakfast, Lunch, Snack_1, Snack_2, Dinner = Value
  }

}

