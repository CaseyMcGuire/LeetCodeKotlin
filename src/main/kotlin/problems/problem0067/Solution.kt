package problems.problem0067

class Solution {
  class Solution {
    fun addBinary(a: String, b: String): String {
      val sumBinary = StringBuilder()
      var i = a.length - 1
      var j = b.length - 1
      var remainder = false
      while (i >= 0 || j >= 0) {
        var curPlaceSum = 0
        if (remainder) {
          curPlaceSum++
        }
        if (i >= 0 && a[i] == '1') {
          curPlaceSum++
        }
        if (j >= 0 && b[j] == '1') {
          curPlaceSum++
        }
        when (curPlaceSum) {
          0 -> {
            sumBinary.append('0')
            remainder = false
          }
          1 -> {
            sumBinary.append('1')
            remainder = false
          }
          2 -> {
            sumBinary.append('0')
            remainder = true
          }
          3 -> {
            sumBinary.append('1')
            remainder = true
          }
          else -> throw RuntimeException()
        }
        i--
        j--
      }

      if (remainder) {
        sumBinary.append('1')
      }

      return sumBinary.reverse().toString()
    }
  }
}