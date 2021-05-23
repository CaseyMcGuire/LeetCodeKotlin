package problems.problem0904

class Solution {
  fun totalFruit(tree: IntArray): Int {
    var currentIndex = 0
    var currentMax = 0
    var runningCount = 0
    var currentRunningFruit = CurrentFruit(0, tree[0])
    var fruits = mutableSetOf<Int>()
    fruits.add(tree[0])
    while (currentIndex < tree.size) {
      val currentFruit = tree[currentIndex]
      val containsCurrentFruit = fruits.contains(currentFruit)
      // we found a new fruit
      if (fruits.size == 2 && !containsCurrentFruit) {
        if (currentMax < runningCount) {
          currentMax = runningCount
        }
        runningCount = 0
        currentIndex = currentRunningFruit.startingIndex
        fruits = mutableSetOf(currentRunningFruit.fruit, currentFruit)
      }
      else {
        if (currentRunningFruit.fruit != currentFruit) {
          currentRunningFruit = CurrentFruit(currentIndex, currentFruit)
        }
        runningCount++
        fruits.add(currentFruit)
        currentIndex++
      }
    }
    return if (runningCount > currentMax) {
      runningCount
    } else {
      currentMax
    }
  }
}

data class CurrentFruit(val startingIndex: Int, val fruit: Int)

fun main(args: Array<String>) {
  println(Solution().totalFruit(intArrayOf(0,1,2,2)))
}