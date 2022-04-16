package problems.problem0720

class Solution {
  fun longestWord(words: Array<String>): String {
    val trie = Trie()
    for (word in words) {
      trie.add(word)
    }
    return trie.getLongestWordPath()
  }
}

class Trie {
  val root = TrieNode()

  fun add(word: String) {
    var curNode = root
    for (c in word) {
      val nextNode = curNode.children[c] ?: TrieNode()
      curNode.children[c] = nextNode
      curNode = nextNode
    }
    curNode.word = word
  }

  fun getLongestWordPath(): String {
    var longestWordsSoFar = mutableListOf<String>()

    fun recurse(node: TrieNode) {
      val word = node.word
      if (word == null) {
        return
      }

      if (longestWordsSoFar.isEmpty() || longestWordsSoFar.first().length < word.length) {
        longestWordsSoFar = mutableListOf<String>(word)
      }
      else if (longestWordsSoFar.first().length == word.length) {
        longestWordsSoFar.add(word)
      }

      for (node in node.children.values) {
        recurse(node)
      }
    }
    for (value in root.children.values) {
      recurse(value)
    }

    longestWordsSoFar.sort()
    return if (longestWordsSoFar.isEmpty()) "" else longestWordsSoFar.first()
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var word: String? = null
}