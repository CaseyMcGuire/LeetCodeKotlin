package problems.problem0743

class Solution {
  fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    val graph = createGraph(times, n)
    val timeToReachNode = mutableMapOf<Int, Int>()
    timeToReachNode[k] = 0
    fun dfs(node: Int, currentTime: Int, visitedInPath: MutableSet<Int>) {
      if (!visitedInPath.add(node)) {
        return
      }
      val neighbors = graph[node]!!
      var maxTime = currentTime
      for (neighbor in neighbors) {
        val currentMaxTime = timeToReachNode[neighbor.node]
        val currentTime = neighbor.time + currentTime
        if (currentMaxTime == null || currentMaxTime > currentTime) {
          timeToReachNode[neighbor.node] = currentTime
          dfs(neighbor.node, currentTime, visitedInPath)
        }
      }
      visitedInPath.remove(node)
    }
    val maxTime = dfs(k, 0, mutableSetOf())
    if (timeToReachNode.size != n) {
      return -1
    }
    return timeToReachNode.values.max()!!
  }

  private fun createGraph(times: Array<IntArray>, n: Int): MutableMap<Int, MutableSet<Destination>> {
    val graph = mutableMapOf<Int, MutableSet<Destination>>()
    for (i in 1..n) {
      graph[i] = mutableSetOf()
    }

    for (time in times) {
      val from = time[0]
      val to = time[1]
      val timeTaken = time[2]
      val destination = Destination(to, timeTaken)
      graph[from]!!.add(destination)
    }
    return graph
  }
}

data class Destination(val node: Int, val time: Int)