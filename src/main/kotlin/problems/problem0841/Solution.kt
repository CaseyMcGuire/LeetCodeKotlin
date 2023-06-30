package problems.problem0841

class Solution {
  fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
    val graph = mutableMapOf<Int, Set<Int>>()
    for (i in rooms.indices) {
      graph[i] = rooms[i].toSet()
    }
    val visited = mutableSetOf<Int>()
    fun recurse(i: Int) {
      if (!visited.add(i)) {
        return
      }
      for (key in graph[i]!!) {
        recurse(key)
      }
    }
    recurse(0)
    return visited.size == rooms.size
  }
}