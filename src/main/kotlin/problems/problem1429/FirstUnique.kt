package problems.problem1429

class FirstUnique(nums: IntArray) {

  val frequencyMap = mutableMapOf<Int, Int>()
  val numsList: MutableList<Int> = mutableListOf<Int>()
  var uniqueIndex = 0

  init {
    for (num in nums) {
      add(num)
    }
  }

  fun showFirstUnique(): Int {
    if (uniqueIndex == numsList.size) {
      return -1
    }
    return numsList[uniqueIndex]
  }

  fun add(value: Int) {
    frequencyMap.merge(value, 1) { cur, acc -> cur + acc }
    numsList.add(value)
    while (uniqueIndex < numsList.size) {
      val frequency = frequencyMap[numsList[uniqueIndex]]!!
      if (frequency > 1) {
        uniqueIndex++
      }
      else {
        break
      }
    }
  }

}