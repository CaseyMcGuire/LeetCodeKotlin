package problems.problem3043

class Solution {
  fun longestCommonPrefix(arr1: IntArray, arr2: IntArray): Int {
    val trie = getTrie(arr1)
    var longestSoFar = 0
    for (num in arr2) {
      val numStr = num.toString()
      var cur = trie
      var length = 0
      for (c in numStr) {
        cur = cur.children[c] ?: break
        length++
      }
      longestSoFar = Math.max(longestSoFar, length)
    }
    return longestSoFar
  }

  private fun getTrie(arr1: IntArray): TrieNode {
    val root = TrieNode()
    for (num in arr1) {
      val numStr = num.toString()
      var cur = root
      for (c in numStr) {
        val next = cur.children[c] ?: TrieNode()
        cur.children[c] = next
        cur = next
      }
    }
    return root
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
}