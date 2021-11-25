package problems.problem1135

class Solution {
  fun minimumCost(n: Int, connections: Array<IntArray>): Int {
    if (n == 1) {
      return 0
    }
    val cityToUnion = mutableMapOf<Int, Union>()
    for (i in 1..n) {
      cityToUnion[i] = Union(i)
    }
    val edges = connections.map { Edge(it[0], it[1], it[2]) }
    val pq = PriorityQueue<Edge>(compareBy { it.weight })
    pq.addAll(edges)
    var totalWeight = 0
    while (pq.isNotEmpty()) {
      val nextEdge = pq.poll()
      val firstUnion = cityToUnion[nextEdge.firstCity]!!
      val secondUnion = cityToUnion[nextEdge.secondCity]!!
      if (firstUnion.isUnited(secondUnion)) {
        continue
      }
      totalWeight += nextEdge.weight
      val size = firstUnion.union(secondUnion)
      if (size == n) {
        return totalWeight
      }
    }
    return -1
  }
}

data class Edge(val firstCity: Int, val secondCity: Int, val weight: Int)

class Union(val num: Int) {
  var parent: Union? = null
  var size = 1

  fun isUnited(other: Union): Boolean {
    return this.getRoot().num == other.getRoot().num
  }

  fun union(other: Union): Int {
    var otherRoot = other.getRoot()
    var thisRoot = this.getRoot()
    thisRoot.size = thisRoot.size + otherRoot.size
    otherRoot.parent = thisRoot
    return thisRoot.size
  }

  fun getRoot(): Union {
    var curRoot = this
    while (curRoot.parent != null) {
      curRoot = curRoot.parent!!
    }
    return curRoot
  }
}
