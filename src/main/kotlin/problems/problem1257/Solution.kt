package problems.problem1257

class Solution {
  fun findSmallestRegion(regions: List<List<String>>, region1: String, region2: String): String {
    val childToParent = mutableMapOf<String, String>()
    val parentToChildren = mutableMapOf<String, MutableSet<String>>()

    for (region in regions) {
      val parent = region[0]
      val children = mutableSetOf<String>()
      for (i in 1 until region.size) {
        children.add(region[i])
      }
      parentToChildren[parent] = children

      for (child in children) {
        childToParent[child] = parent
      }
    }

    val visited = mutableSetOf<String>()
    val end = region2
    val start = region1

    fun dfs(curRegion: String): Boolean {
      if (curRegion == end) {
        return true
      }
      if (!visited.add(curRegion)) {
        return false
      }
      val children = parentToChildren[curRegion] ?: mutableSetOf()
      for (child in children) {
        if (dfs(child)) {
          return true
        }
      }
      return false
    }

    var current = start
    while (true) {
      if(dfs(current)) {
        return current
      }
      current = childToParent[current]!!
    }
    return ""
  }
}