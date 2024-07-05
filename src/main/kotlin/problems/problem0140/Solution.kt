package problems.problem0140

import java.util.*

class Solution {

  fun wordBreak(s: String, wordDict: List<String>): List<String> {
    val root = createTrie(wordDict)
    val sentences = mutableListOf<String>()
    val queue = LinkedList<Char>()

    fun recurse(index: Int, node: TrieNode) {
      val c = s[index]
      val nextNode = node.children[c]
      if (nextNode == null) {
        return
      }
      queue.addLast(c)
      if (index == s.length - 1) {
        if (nextNode.isWord) {
          val str = queue.joinToString("")
          sentences.add(str)
        }
        queue.removeLast()
        return
      }
      if (nextNode.isWord) {
        queue.addLast(' ')
        recurse(index + 1, root)
        queue.removeLast()
      }
      recurse(index + 1, nextNode)
      queue.removeLast()
    }
    recurse(0, root)
    return sentences
  }


  private fun createTrie(wordDict: List<String>): TrieNode {
    val root = TrieNode()
    for (word in wordDict) {
      var cur = root
      for (c in word) {
        val child = cur.children[c] ?: TrieNode()
        cur.children[c] = child
        cur = child
      }
      cur.isWord = true
    }

    return root
  }
}

class TrieNode {
  var isWord = false
  val children = mutableMapOf<Char, TrieNode>()
}