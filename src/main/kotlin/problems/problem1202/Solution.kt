package problems.problem1202

class Solution {
  fun smallestStringWithSwaps(s: String, pairs: List<List<Int>>): String {
    val indexToNode = mutableMapOf<Int, UnionNode>()
    for (i in s.indices) {
      indexToNode[i] = UnionNode(i)
    }

    for (pair in pairs) {
      val first = pair[0]
      val second = pair[1]
      val firstRoot = indexToNode[first]!!.getRoot()
      val secondRoot = indexToNode[second]!!.getRoot()

      firstRoot.setParent(secondRoot)
    }

    val rootToIndices = mutableMapOf<UnionNode, MutableSet<Int>>()
    for (i in s.indices) {
      val root = indexToNode[i]!!.getRoot()
      val indices = rootToIndices[root] ?: mutableSetOf()
      indices.add(i)
      rootToIndices[root] = indices
    }

    val strArr = CharArray(s.length)
    for (indices in rootToIndices.values) {
      val pairs = indices.map { CharPair(s[it], it) }
      val sortedPairs = pairs.sortedBy { it.c }
      val indices = pairs.map { it.index }
      for (i in sortedPairs.indices) {
        strArr[indices[i]] = sortedPairs[i].c
      }
    }

    return strArr.joinToString("")
  }
}

class UnionNode(val index: Int) {
  var parentNode: UnionNode? = null

  fun getRoot(): UnionNode {
    var cur = this
    var intermediate = mutableListOf<UnionNode>()
    while (cur.parentNode != null) {
      cur = cur.parentNode!!
      intermediate.add(cur)
    }

    for (node in intermediate) {
      node.setParent(cur)
    }
    return cur
  }

  fun setParent(node: UnionNode) {
    if (node != this) {
      parentNode = node
    }
  }
}

data class CharPair(val c: Char, val index: Int)