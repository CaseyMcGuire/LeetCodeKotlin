package problems.problem0675

import java.util.*

class Solution {
  fun cutOffTree(forest: List<List<Int>>): Int {
    val pq = PriorityQueue<Tree>(compareBy({ it.height }))
    for (i in forest.indices) {
      for (j in forest[i].indices) {
        if (forest[i][j] != 0) {
          pq.add(Tree(forest[i][j], Coordinate(i, j)))
        }
      }
    }
    var curCoordinate = Coordinate(0, 0)
    var totalSteps = 0
    while (pq.isNotEmpty()) {
      val nextShortest = pq.poll()
      if (nextShortest.height == 1) {
        continue
      }
      val coordinate = nextShortest.c
      val steps = findShortestPath(forest, curCoordinate, coordinate)
      if (steps == -1) {
        return -1
      }
      totalSteps += steps
      curCoordinate = coordinate
    }
    return totalSteps
  }

  private fun findShortestPath(forest: List<List<Int>>, start: Coordinate, end: Coordinate): Int {
    val queue = LinkedList<Coordinate?>()
    var length = 0
    val visited = mutableSetOf<Coordinate>()
    queue.addLast(start)
    queue.addLast(null)
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      if (cur == null) {
        length++
        if (queue.isEmpty()) {
          return -1
        }
        queue.addLast(null)
        continue
      }
      if (cur == end) {
        return length
      }
      for (neighbor in cur.getNeighbors()) {
        if (neighbor.i !in 0 until forest.size || neighbor.j !in 0 until forest[0].size || forest[neighbor.i][neighbor.j] == 0 || !visited.add(neighbor)) {
          continue
        }
        queue.addLast(neighbor)
      }
    }
    return -1
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )
  }
}
data class Tree(val height: Int, val c: Coordinate)