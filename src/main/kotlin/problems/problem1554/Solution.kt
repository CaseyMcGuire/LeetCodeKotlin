package problems.problem1554

class Solution {
  fun differByOne(dict: Array<String>): Boolean {
    val trie = Trie()
    for (s in dict) {
      trie.insert(s)
    }

    for (s in dict) {
      if (trie.containsExceptOne(s)) {
        return true
      }
    }

    return false
  }
}

class Trie {

  val root = TrieNode()

  fun insert(s: String) {
    var curNode = root
    for (c in s) {
      val nextNode = curNode.charToNode[c] ?: TrieNode()
      curNode.charToNode[c] = nextNode
      curNode = nextNode
    }
  }

  fun containsExceptOne(s: String): Boolean {

    fun recurse(curNode: TrieNode, i: Int, hasSkipped: Boolean): Boolean {
      if (i == s.length) {
        return hasSkipped
      }

      if (hasSkipped) {
        val next = curNode.charToNode[s[i]]
        if (next == null) {
          return false
        }
        return recurse(next, i + 1, hasSkipped)
      }

      var found = false
      for (entry in curNode.charToNode.entries) {
        found = found || recurse(entry.value, i + 1, entry.key != s[i])
      }
      return found
    }

    return recurse(root, 0, false)
  }
}

class TrieNode {
  val charToNode = mutableMapOf<Char, TrieNode>()

  override fun toString(): String {
    return charToNode.toString()
  }
}