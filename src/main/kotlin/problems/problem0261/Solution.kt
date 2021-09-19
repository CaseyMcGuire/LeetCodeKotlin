package problems.problem0261

class Solution {
  fun validTree(n: Int, edges: Array<IntArray>): Boolean {
    if (n == 0) {
      return false
    }
    val nodes = mutableMapOf<Int, Node>()
    for (num in 0 until n) {
      nodes[num] = Node(num)
    }
    for (edge in edges) {
      val first = edge[0]
      val second = edge[1]
      val firstNode = nodes[first]!!
      val secondNode = nodes[second]!!
      firstNode.edges.add(second)
      secondNode.edges.add(first)
    }
    val visited = mutableSetOf<Int>()
    fun recurse(num: Int, previous: Int?): Boolean {
      if (!visited.add(num)) {
        return false
      }
      val node = nodes[num]!!
      for (edge in node.edges) {
        if (edge == previous) {
          continue
        }
        if (!recurse(edge, num)) {
          return false
        }
      }
      return true
    }
    val succeeded = recurse(0, null)
    return visited.size == n && succeeded
  }
}

data class Node(val num: Int, val edges: MutableList<Int> = mutableListOf())

fun main(args: Array<String>) {
  println(Solution().validTree(3, arrayOf(intArrayOf(0,1), intArrayOf(0,2), intArrayOf(1,2))))
}