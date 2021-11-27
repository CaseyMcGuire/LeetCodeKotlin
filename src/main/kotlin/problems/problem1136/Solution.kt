package problems.problem1136

class Solution {
  fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
    val graph = mutableMapOf<Int, MutableSet<Int>>()
    val fromTo = mutableMapOf<Int, MutableSet<Int>>()
    for (edge in relations) {
      val from = edge[0]
      val to = edge[1]
      graph.addEdge(from, to)
      fromTo.addEdge(to, from)
    }

    val temporary = mutableSetOf<Int>()
    val permanent = mutableSetOf<Int>()
    fun recurse(node: Int): Boolean {
      // has cycle
      if (temporary.contains(node)) {
        return false
      }

      if (permanent.contains(node)) {
        return true
      }

      temporary.add(node)
      for (edge in graph[node] ?: mutableSetOf()) {
        if (!recurse(edge)) {
          return false
        }
      }
      temporary.remove(node)
      permanent.add(node)
      return true
    }
    val allNodes = mutableSetOf<Int>()
    for (i in 1..n) {
      if (!recurse(i)) {
        return -1
      }
      temporary.clear()
      allNodes.add(i)
    }

    val edges = graph.values
    val allEdges = mutableSetOf<Int>()
    for (edge in edges) {
      allEdges.addAll(edge)
    }

    var currentSemester = mutableListOf<Int>()
    val startingNodes = allNodes subtract allEdges
    currentSemester.addAll(startingNodes)

    val prereqsTaken = mutableMapOf<Int, MutableSet<Int>>()
    var semesters = 0
    while (currentSemester.isNotEmpty()) {
      semesters++
      val nextSemester = mutableListOf<Int>()
      for (course in currentSemester) {
        val edges = graph[course] ?: mutableSetOf()
        for (edge in edges) {
          prereqsTaken.addEdge(edge, course)
          val takenCourses = prereqsTaken[edge]
          val allPrereqs = fromTo[edge]
          if (takenCourses == allPrereqs) {
            nextSemester.add(edge)
          }
        }
      }
      currentSemester = nextSemester
    }
    return semesters
  }

  private fun MutableMap<Int, MutableSet<Int>>.addEdge(from: Int, to: Int) {
    val set = this[from] ?: mutableSetOf<Int>()
    set.add(to)
    this[from] = set
  }
}