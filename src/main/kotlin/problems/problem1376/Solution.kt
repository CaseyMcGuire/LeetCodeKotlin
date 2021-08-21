package problems.problem1376

class Solution {
  fun numOfMinutes(n: Int, headID: Int, managers: IntArray, informTime: IntArray): Int {
    val idToSubordinates = mutableMapOf<Int, MutableList<Int>>()
    for ((index, manager) in managers.withIndex()) {
      idToSubordinates.merge(manager, mutableListOf(index)) {cur, acc ->
        cur.addAll(acc)
        cur
      }
    }

    fun recurse(employeeId: Int): Int {
      val subordinates = idToSubordinates[employeeId]
      if (subordinates.isNullOrEmpty()) {
        return 0
      }
      val informTime = informTime[employeeId]
      val max = subordinates.map { recurse(it) }.max()
      return (max ?: 0) + informTime
    }
    return recurse(headID)
  }
}