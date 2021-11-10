package problems.problem0043

import java.util.*

class Solution {
  fun multiply(num1: String, num2: String): String {
    if (num1 == "0" || num2 == "0") {
      return "0"
    }

    val (shorter, longer) = if (num1.length < num2.length) Pair(num1, num2) else Pair(num2, num1)

    var carry = 0
    val sums = mutableListOf<String>()
    for (i in shorter.indices.reversed()) {
      val shortDigit = Character.getNumericValue(shorter[i])
      var remainder = 0L
      val list = LinkedList<Long>()
      for (j in longer.indices.reversed()) {
        val longDigit = Character.getNumericValue(longer[j]).toLong()
        val curSum: Long = longDigit * shortDigit + remainder
        if (curSum >= 10) {
          remainder = curSum / 10L
          val curPlace = curSum - (10L * remainder)
          list.addFirst(curPlace)
        }
        else {
          list.addFirst(curSum)
          remainder = 0
        }
      }
      if (remainder != 0L) {
        list.addFirst(remainder)
      }

      for (i in 0 until carry) {
        list.addLast(0)
      }
      val sum = list.joinToString("")
      sums.add(sum)
      carry++
    }

    var newSum = "0"
    for (sum in sums) {
      println("sum $sum")
      println("newSum $newSum")
      newSum = add(newSum, sum)
    }

    return newSum
  }

  private fun add(s1: String, s2: String): String {
    val (shorter, longer) = if (s1.length < s2.length) Pair(s1, s2) else Pair(s2, s1)
    var remainder = 0
    val sumList = LinkedList<Int>()
    var longerIndex = longer.length - 1
    for (i in shorter.indices.reversed()) {
      val first = Character.getNumericValue(shorter[i])
      val second = Character.getNumericValue(longer[longerIndex])
      var sum = first + second + remainder
      longerIndex--
      if (sum >= 10) {
        remainder = 1
        sum -= 10
        sumList.addFirst(sum)
      }
      else {
        remainder = 0
        sumList.addFirst(sum)
      }
    }

    for (i in longerIndex downTo 0) {
      var sum = Character.getNumericValue(longer[i]) + remainder
      if (sum >= 10) {
        remainder = 1
        sum -= 10
        sumList.addFirst(sum)
      }
      else {
        remainder = 0
        sumList.addFirst(sum)
      }
    }

    if (remainder != 0) {
      sumList.addFirst(remainder)
    }
    println(sumList)
    return sumList.joinToString("")
  }
}