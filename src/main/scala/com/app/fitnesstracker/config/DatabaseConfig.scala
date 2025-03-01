package com.app.fitnesstracker.config


import slick.jdbc.JdbcBackend.Database
import com.typesafe.config.ConfigFactory

object DatabaseConfig {
  private val config = ConfigFactory.load().getConfig("slick.dbs.default.db")

  val db: Database = Database.forURL(
    url = config.getString("url"),
    driver = config.getString("driver"),
    user = config.getString("user"),
    password = config.getString("password")
  )
}
