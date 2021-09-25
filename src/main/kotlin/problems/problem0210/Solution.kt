package problems.problem0210

import java.util.*

class Solution {
  fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val graph = mutableMapOf<Int, MutableList<Int>>()
    for (i in  0 until numCourses) {
      graph[i] = mutableListOf<Int>()
    }
    for (prereq in prerequisites) {
      val takeFirst = prereq[1]
      val takeSecond = prereq[0]
      graph.merge(takeFirst, mutableListOf<Int>(takeSecond)) {cur, acc -> (cur + acc).toMutableList() }
    }

    val courses = LinkedList<Int>()
    val temporary = mutableSetOf<Int>()
    val permanent = mutableSetOf<Int>()
    var foundCycle = false
    fun recurse(courseNum: Int) {
      if (permanent.contains(courseNum)) {
        return
      }
      if (temporary.contains(courseNum)) {
        foundCycle = true
        return
      }

      temporary.add(courseNum)
      for (course in graph[courseNum]!!) {
        recurse(course)
      }
      temporary.remove(courseNum)
      permanent.add(courseNum)
      courses.addFirst(courseNum)
    }
    for (course in graph.keys) {
      recurse(course)
    }
    if (foundCycle) {
      return intArrayOf()
    }
    return courses.toIntArray()
  }
}