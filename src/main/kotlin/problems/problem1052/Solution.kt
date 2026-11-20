package problems.problem1052

class Solution {
  fun maxSatisfied(customers: IntArray, grumpy: IntArray, minutes: Int): Int {
    var sum = 0
    val grumpyMinutes = IntArray(customers.size) { 0 }
    for (i in customers.indices) {
      if (grumpy[i] != 1) {
        sum += customers[i]
      }
      else {
        grumpyMinutes[i] = customers[i]
      }
    }

    var maxSoFar = sum
    var i = 0
    var j = 0
    var notGrumpyTotal = 0
    while (true) {
      if (j - i > minutes) {
        notGrumpyTotal -= grumpyMinutes[i]
        i++
      }
      else {
        maxSoFar = Math.max(maxSoFar, notGrumpyTotal + sum)
        if (j == grumpy.size) {
          break
        }
        notGrumpyTotal += grumpyMinutes[j]
        j++
      }
    }
    return maxSoFar
  }
}