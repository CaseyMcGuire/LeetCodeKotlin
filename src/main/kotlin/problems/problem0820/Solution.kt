package problems.problem0820

class Solution {
  fun minimumLengthEncoding(words: Array<String>): Int {
    val wordSet = words.toMutableSet()
    val trie = Trie()
    for (word in words) {
      trie.insert(word.reversed())
    }

    for (word in words) {
      if (trie.isSubstring(word.reversed())) {
        wordSet.remove(word)
      }
    }

    return wordSet.map { it.length }.sum() + wordSet.size
  }
}

class Trie {
  private val root = TrieNode()

  fun insert(word: String) {
    var cur = root
    for (c in word) {
      cur = cur.children.getOrPut(c) { TrieNode() }
    }
  }

  fun isSubstring(word: String): Boolean {
    var cur = root
    for (c in word) {
      cur = cur.children[c] ?: error("No entry for $word")
    }
    return cur.children.isNotEmpty()
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
}