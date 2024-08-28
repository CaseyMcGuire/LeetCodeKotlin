package problems.problem0380

import java.util.*

class RandomizedSet() {

  private val numToIndex = mutableMapOf<Int, Int>()
  private val nums = mutableListOf<Int>()

  fun insert(`val`: Int): Boolean {
    if (numToIndex.containsKey(`val`)) {
      return false
    }
    nums.add(`val`)
    numToIndex[`val`] = nums.size - 1
    return true
  }

  fun remove(`val`: Int): Boolean {
    val index = numToIndex[`val`] ?: return false
    val lastNum = nums.removeLast()
    numToIndex.remove(`val`)
    if (lastNum != `val`) {
      nums[index] = lastNum
      numToIndex[lastNum] = index
    }
    return true
  }

  fun getRandom(): Int {
    val randomIndex = (0 until nums.size).random()
    return nums[randomIndex]
  }

}

fun main(args: Array<String>) {
  val set = RandomizedSet()
  set.insert(1)
  set.remove(2)
  set.insert(2)
  set.getRandom()
  set.insert(1)
  set.remove(2)
  set.getRandom()
}