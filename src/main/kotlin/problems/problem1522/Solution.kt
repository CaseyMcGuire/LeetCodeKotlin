package problems.problem1522

class Node(var `val`: Int) {
  var children: List<Node?> = listOf()
}

class Solution {
  fun diameter(root: Node?): Int {
    var maxSoFar = 0
    fun recurse(node: Node?): Int {
      if (node == null) {
        return 0
      }
      if (node.children.isEmpty()) {
        return 1
      }

      val pathLengths = node.children.map { recurse(it) }
      var longest: Int? = null
      var secondLongest: Int? = null
      for (l in pathLengths) {
        if (longest == null) {
          longest = l
        }
        else if (secondLongest == null) {
          secondLongest = l
        }
        else if (longest < l) {
          longest = l
        }
        else if (secondLongest < l) {
          secondLongest = l
        }
      }
      val currentPath = (longest ?: 0) + (secondLongest ?: 0)
      if (currentPath > maxSoFar) {
        maxSoFar = currentPath
      }
      return 1 + Math.max(longest ?: 0, secondLongest ?: 0)
    }
    recurse(root)
    return maxSoFar
  }
}