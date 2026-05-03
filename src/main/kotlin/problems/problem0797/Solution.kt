package problems.problem0797

class Solution {
  fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {
    val paths = mutableListOf<List<Int>>()
    val curPath = mutableListOf<Int>()

    fun recurse(node: Int) {
      if (node == graph.size - 1) {
        paths.add(curPath.toList())
        return
      }

      for (neighbor in graph[node]) {
        curPath.add(neighbor)
        recurse(neighbor)
        curPath.removeLast()
      }
    }
    curPath.add(0)
    recurse(0)
    return paths
  }

}