package problems.problem0839

class Solution {
  fun numSimilarGroups(strs: Array<String>): Int {
    val groups = mutableMapOf<String, UnionFind>()
    for (str in strs) {
      groups[str] = UnionFind(str)
    }

    for (i in strs.indices) {
      for (j in i + 1 until strs.size) {
        val similar = areSimilar(strs[i], strs[j])
        if (similar) {
          val uf1 = groups[strs[i]]!!
          val uf2 = groups[strs[j]]!!
          uf1.merge(uf2)
        }
      }
    }

    val unions = mutableSetOf<MutableSet<String>>()
    for (value in groups.values) {
      unions.add(value.getRoot().strings)
    }
    return unions.size
  }

  private fun areSimilar(first: String, second: String): Boolean {
    var differences = 0
    for (i in first.indices) {
      if (first[i] != second[i]) {
        differences++
      }
    }

    return differences <= 2
  }

}

class UnionFind(str: String) {
  var parent: UnionFind? = null
  val strings = mutableSetOf<String>(str)

  fun getRoot(): UnionFind {
    if (parent == null) {
      return this
    }
    var curParent = this.parent
    while (curParent?.parent != null) {
      curParent = curParent.parent
    }
    return curParent!!
  }

  fun merge(other: UnionFind) {
    val thisParent = this.getRoot()
    val otherParent = other.getRoot()
    if (thisParent !== otherParent) {
      thisParent.parent = otherParent
    }

    otherParent.strings.addAll(this.strings)
  }
}