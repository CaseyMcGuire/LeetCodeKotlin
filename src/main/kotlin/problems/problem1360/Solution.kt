package problems.problem1360

class Solution {
  // number of year difference
  // number of month difference
  // number of day difference
  // leap years
  // different days per month
  fun daysBetweenDates(date1: String, date2: String): Int {
    if (date1 == date2) {
      return 0
    }
    val date1Split = date1.split("-").map { it.toInt() }
    val date2Split = date2.split("-").map { it.toInt() }
    val firstDate = Date(date1Split[0], date1Split[1], date1Split[2])
    val secondDate = Date(date2Split[0], date2Split[1], date2Split[2])

    val (earlier, later) = getEarlierAndLaterDate(firstDate, secondDate)
    var numDays = 0
    var curDay = earlier.day
    var curYear = earlier.year
    var curMonth = earlier.month
    var curDate = Date(earlier.year, earlier.month, earlier.day)
    while (curDate != later) {
      numDays++
      curDate.day++
      val daysInMonth = getDaysInMonth(curDate.year, curDate.month)
      if (curDate.day > daysInMonth) {
        curDate.day = 1
        curDate.month++
      }
      if (curDate.month == 13) {
        curDate.month = 1
        curDate.year++
      }
    }
    return numDays
  }

  fun getDaysInMonth(year: Int, month: Int): Int {
    return when (month) {
      1 -> 31
      2 -> if (isLeapYear(year)) 29 else 28
      3 -> 31
      4 -> 30
      5 -> 31
      6 -> 30
      7 -> 31
      8 -> 31
      9 -> 30
      10 -> 31
      11 -> 30
      else -> 31
    }
  }

  private fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0
  }

  fun getEarlierAndLaterDate(date1: Date, date2: Date): Pair<Date, Date> {
    if (date1.year > date2.year) {
      return Pair(date2, date1)
    }
    else if (date1.year < date2.year) {
      return Pair(date1, date2)
    }
    else if (date1.month < date2.month) {
      return Pair(date1, date2)
    }
    else if (date1.month > date2.month) {
      return Pair(date2, date1)
    }
    else if (date1.day > date2.day) {
      return Pair(date2, date1)
    }
    else {
      return Pair(date1, date2)
    }
  }

}

data class Date(var year: Int, var month: Int, var day: Int)