package problems.problem0528

class Solution(w: IntArray) {

  val sum = w.sum()
  val probabilities = DoubleArray(w.size)

  init {
    for (i in w.indices) {
      val probability = w[i].toDouble() / sum.toDouble()
      if (i == 0) {
        probabilities[i] = probability
      }
      else {
        probabilities[i] = probability + probabilities[i - 1]
      }
    }
  }

  fun pickIndex(): Int {
    val random = Math.random()
    for (i in probabilities.indices) {
      if (probabilities[i] > random) {
        return i
      }
    }
    return probabilities.size - 1
  }

}