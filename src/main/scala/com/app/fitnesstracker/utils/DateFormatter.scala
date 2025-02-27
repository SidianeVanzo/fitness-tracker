package com.app.fitnesstracker.utils

import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateFormatter {

  val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

  def stringToDate(dateStr:String): Date = {
    val localDate = LocalDate.parse(dateStr, formatter)
    Date.valueOf(localDate)
  }

  def dateToString(date:Date): String = {
    val localDate: LocalDate = date.toLocalDate
    localDate.format(formatter)
  }

}
