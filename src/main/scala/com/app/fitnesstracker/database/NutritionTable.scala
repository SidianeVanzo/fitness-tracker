package com.app.fitnesstracker.database

import slick.jdbc.PostgresProfile.api._

case class NutritionEntity(id: Option[Int] = None, meal: String)
case class FoodEntity(id: Option[Int] = None, nutritionId: Int, name: String, measurementunit: String, quantity: String)

class NutritionTable(tag: Tag) extends Table[NutritionEntity](tag, "nutrition") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def meal = column[String]("meal")

  def * =
    (id.?, meal) <> (NutritionEntity.tupled, NutritionEntity.unapply)
}

class FoodTable(tag: Tag) extends Table[FoodEntity](tag, "food") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def nutritionId = column[Int]("nutrition_id")
  def name = column[String]("name")
  def measurementunit = column[String]("measurementunit")
  def quantity = column[String]("quantity")

  def nutritionFK = foreignKey("nutrition_fk", nutritionId, TableQuery[NutritionTable])(_.id, onDelete = ForeignKeyAction.Cascade)

  def * = (id.?, nutritionId, name, measurementunit, quantity) <> (FoodEntity.tupled, FoodEntity.unapply)
}

object Tables {
  val nutrition = TableQuery[NutritionTable]
  val food = TableQuery[FoodTable]
}
