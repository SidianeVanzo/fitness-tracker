package com.app.fitnesstracker.database

import slick.jdbc.PostgresProfile.api._

import java.sql.Date

case class MealEntity(id: String, name: String, date: Date)
case class FoodEntity(id: String, mealId: String, name: String, measurementunit: String, quantity: Double)

class MealTable(tag: Tag) extends Table[MealEntity](tag, "meal") {
  def id = column[String]("id", O.PrimaryKey, O.Unique)
  def name = column[String]("name")
  def date = column[Date]("date")

  def * =
    (id, name, date) <> (MealEntity.tupled, MealEntity.unapply)
}

class FoodTable(tag: Tag) extends Table[FoodEntity](tag, "food") {
  def id = column[String]("id", O.PrimaryKey, O.Unique)
  def mealId = column[String]("meal_id")
  def name = column[String]("name")
  def measurementunit = column[String]("measurementunit")
  def quantity = column[Double]("quantity")

  def mealFK = foreignKey("meal_fk", mealId, TableQuery[MealTable])(_.id, onDelete = ForeignKeyAction.Cascade)

  def * = (id, mealId, name, measurementunit, quantity) <> (FoodEntity.tupled, FoodEntity.unapply)
}

object Tables {
  val meal = TableQuery[MealTable]
  val food = TableQuery[FoodTable]
}
