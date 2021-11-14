package problems.problem0582

class Solution {
  fun killProcess(pid: List<Int>, ppid: List<Int>, kill: Int): List<Int> {
    val processToChildren = mutableMapOf<Int, MutableSet<Int>>()
    for (p in pid) {
      processToChildren[p] = mutableSetOf<Int>()
    }

    for (index in ppid.indices) {
      val parent = ppid[index]
      if (parent == 0) {
        continue
      }
      val currentChild = pid[index]
      val children = processToChildren[parent]!!
      children.add(currentChild)
    }

    val deletedIds = mutableListOf<Int>()
    deletedIds.add(kill)
    var i = 0
    while (i < deletedIds.size) {
      val current = deletedIds[i]
      i++
      val children = processToChildren[current]!!
      for (child in children) {
        deletedIds.add(child)
      }
    }
    return deletedIds
  }
}