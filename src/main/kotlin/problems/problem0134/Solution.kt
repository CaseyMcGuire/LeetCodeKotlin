package problems.problem0134

class Solution {
  fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {

    var startingIndex = 0
    while (true) {
      var curGas = 0
      var foundCycle = true
      for (i in 0 until gas.size) {
        var cur = startingIndex + i
        var curIndex = if (cur >= gas.size) Math.abs(cur - gas.size).toInt() else cur
        curGas += gas[curIndex] - cost[curIndex]
        if (curGas < 0) {
          val next = startingIndex + i + 1
          if (next >= gas.size) {
            return -1
          }
          val nextIndex = if (next >= gas.size) Math.abs(next - gas.size).toInt() else next
          startingIndex = nextIndex
          foundCycle = false
          break
        }
      }
      if (foundCycle) {
        return startingIndex
      }
    }

    return -1
  }
}