package problems.problem0969

class Solution {
  fun pancakeSort(arr: IntArray): List<Int> {
    var currentMax = arr.size
    val flips = mutableListOf<Int>()
    while (currentMax > 1) {
      val indexOfMax = arr.indexOf(currentMax)
      val currentMaxIndex = currentMax - 1

      // don't flip if it's already in the right place
      if (indexOfMax != currentMaxIndex) {
        // get largest to the front
        if (indexOfMax != 0) {
          flips.add(indexOfMax + 1)
          arr.flip(indexOfMax)
        }
        // now flip the next max to the right place
        flips.add(currentMax)
        arr.flip(currentMaxIndex)
      }
      currentMax--
    }
    arr.forEach {print(it)}
    return flips
  }

  private fun IntArray.flip(endIndex: Int) {
    var start = 0
    var end = endIndex
    while (start < end) {
      val temp = this[start]
      this[start] = this[end]
      this[end] = temp
      start++
      end--
    }
  }
}

fun main(args: Array<String>) {
  println(Solution().pancakeSort(intArrayOf(3,2,4,1)))
}