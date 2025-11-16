package problems.problem0305

class Solution {
  fun numIslands2(m: Int, n: Int, positions: Array<IntArray>): List<Int> {
    val coordinateToUnionFindNode = mutableMapOf<Coordinate, UnionFindNode>()
    val islandNums = mutableListOf<Int>()
    val islands = mutableSetOf<UnionFindNode>()
    for (position in positions) {
      val coordinate = Coordinate(position[0], position[1])
      val unionFindNode = coordinateToUnionFindNode.getOrPut(coordinate) { UnionFindNode() }
      islands.remove(unionFindNode)

      val neighbors = coordinate.getNeighbors()
      for (neighbor in neighbors) {
        val otherUnionFindNode = coordinateToUnionFindNode[neighbor]
        if (otherUnionFindNode == null) {
          continue
        }
        val path = unionFindNode.union(otherUnionFindNode)
        // some of the nodes on the path to root may be considered their own islands
        // so we need to make sure to remove those as well
        path.forEach { islands.remove(it) }
      }

      islands.add(unionFindNode.getRootPath().root)

      val numIslands = islands.size
      islandNums.add(numIslands)
    }

    return islandNums
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
class UnionFindNode {
  var parent: UnionFindNode? = null

  fun union(other: UnionFindNode): List<UnionFindNode> {
    val curRootPath = other.getRootPath()
    val otherRootPath = this.getRootPath()
    curRootPath.root.setParentNode(otherRootPath.root)
    return listOf(curRootPath.root) +
        listOf(otherRootPath.root) +
        curRootPath.path +
        otherRootPath.path
  }

  fun getRootPath(): RootPath {
    var cur = this
    var parent = this.parent
    val nodesInPath = mutableListOf<UnionFindNode>()
    while (parent != null) {
      nodesInPath.add(cur)
      cur = parent
      parent = parent.parent
    }

    for (node in nodesInPath) {
      node.setParentNode(cur)
    }

    return RootPath(cur, nodesInPath)
  }

  fun setParentNode(other: UnionFindNode) {
    if (other != this) {
      this.parent = other
    }
  }

}

data class RootPath(val root: UnionFindNode, val path: List<UnionFindNode>)