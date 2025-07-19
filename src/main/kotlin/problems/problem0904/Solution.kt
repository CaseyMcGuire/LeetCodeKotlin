package problems.problem0904

class Solution {
  fun totalFruit(fruits: IntArray): Int {
    val fruitToFrequency = mutableMapOf<Int, Int>()
    var i = fruits.size - 1
    var j = fruits.size - 1
    var maxSoFar: Int? = null
    while (true) {
      if (fruitToFrequency.size > 2) {
        fruitToFrequency.decrement(fruits[j])
        j--
        continue
      }
      val curSize = j - i
      if (maxSoFar == null || maxSoFar < curSize) {
        maxSoFar = curSize
      }

      if (i < 0) {
        break
      }
      fruitToFrequency.increment(fruits[i])
      i--
    }
    return maxSoFar!!
  }

  private fun MutableMap<Int, Int>.increment(num: Int) {
    this.merge(num, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<Int, Int>.decrement(num: Int) {
    val frequency = this[num] ?: num
    if (frequency == 1) {
      this.remove(num)
    }
    else {
      this[num] = frequency - 1
    }
  }

}