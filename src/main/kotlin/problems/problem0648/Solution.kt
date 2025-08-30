package problems.problem0648

class Solution {
  fun replaceWords(dictionary: List<String>, sentence: String): String {
    val trie = Trie()

    for (word in dictionary) {
      trie.addWord(word)
    }

    val words = sentence.split(" ")
    val revisedSentence = mutableListOf<String>()
    for (word in words) {
      val root = trie.getRoot(word)
      if (root != null) {
        revisedSentence.add(root)
      }
      else {
        revisedSentence.add(word)
      }
    }
    return revisedSentence.joinToString(" ")
  }
}

class Trie {
  val root = TrieNode()

  fun addWord(word: String) {
    var cur = root
    for (c in word) {
      cur = cur.children.getOrPut(c) { TrieNode() }
    }
    cur.isWord = true
  }

  fun getRoot(word: String): String? {
    var cur = root
    for (i in word.indices) {
      val curChar = word[i]
      val next = cur.children[curChar]
      if (next == null) {
        return null
      }

      if (next.isWord) {
        return word.substring(0, i + 1)
      }

      cur = next
    }
    return null
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var isWord = false
}