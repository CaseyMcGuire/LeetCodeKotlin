package problems.problem0207

class Solution {
  fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    val nodes = getNodes(prerequisites)
    val visitedNodes = mutableSetOf<Int>()
    for (node in nodes) {
      if (visitedNodes.contains(node.num)) {
        continue
      }
      val result = hasCycle(node)
      if (result.hasCycle) {
        return false
      }
      visitedNodes.addAll(result.allVisited)
    }
    return true
  }

  fun hasCycle(node: Node): Recurse {
    val allVisited = mutableSetOf<Int>()
    val visitedInCurrentPath = mutableSetOf<Int>()
    fun findCycle(currentNode: Node): Boolean {
      if (visitedInCurrentPath.contains(currentNode.num)) {
        return true
      }
      if (!allVisited.add(currentNode.num)) {
        return false
      }
      visitedInCurrentPath.add(currentNode.num)
      var foundCycle = false
      for (neighbor in currentNode.neighbors) {
        foundCycle = findCycle(neighbor)
        if (foundCycle) {
          break
        }
      }
      visitedInCurrentPath.remove(currentNode.num)
      return foundCycle
    }
    val foundCycle = findCycle(node)
    return Recurse(foundCycle, allVisited)

  }

  private fun getNodes(prerequisites: Array<IntArray>): List<Node> {
    val nodes = mutableMapOf<Int, Node>()
    for (prerequisite in prerequisites) {
      val from = Node(prerequisite[0])
      val to = Node(prerequisite[1])
      nodes[from.num] = from
      nodes[to.num] = to
    }
    for (prerequisite in prerequisites) {
      val from = prerequisite[1]
      val to = prerequisite[0]
      val existingFrom = nodes[from]!!
      val existingTo = nodes[to]!!
      existingFrom.neighbors.add(existingTo)
    }
    return nodes.values.toList()
  }
}

data class Recurse(val hasCycle: Boolean, val allVisited: MutableSet<Int>)

data class Node(val num: Int, val neighbors: MutableList<Node> = mutableListOf<Node>())
fun main(args: Array<String>) {
  println(Solution().canFinish(2, arrayOf(intArrayOf(0,1), intArrayOf(0, 2), intArrayOf(2, 0))))
}

// [[1,0],[2,0],[0,2]]
// 0 -> 1
// 0 -> 2
// 2 -> 0