package problems.problem0957

class Solution {
  fun prisonAfterNDays(cells: IntArray, n: Int): IntArray {
    if (n == 0) {
      return cells
    }
    var days = 0
    val cellToSteps = mutableMapOf<String, Int>()
    var curState = cells
    while (days < n) {
      var curSteps = cellToSteps[curState.asString()]
      if (curSteps != null) {
        // found a cycle
        break
      }

      if (days != 0) {
        cellToSteps[curState.asString()] = days
      }

      curState = getNextState(curState)
      days++
    }
    if (days == n) {
      return curState
    }

    var remainder = n % cellToSteps.size
    if (remainder == 0) {
      remainder = cellToSteps.size
    }
    for (entry in cellToSteps.entries) {
      if (entry.value == remainder) {
        return entry.key.map { Character.getNumericValue(it) }.toIntArray()
      }
    }

    return curState
  }

  private fun IntArray.asString(): String {
    return this.joinToString("")
  }

  private fun getNextState(cells: IntArray): IntArray {
    val nextState = IntArray(cells.size)
    for (i in cells.indices) {
      if (i == 0 || i == cells.size - 1) {
        nextState[i] = 0
      }
      else if (cells[i - 1] == 1 && cells[i + 1] == 1 || cells[i - 1] == 0 && cells[i + 1] == 0){
        nextState[i] = 1
      }
    }
    return nextState
  }
}