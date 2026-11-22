package problems.problem1268

import java.util.TreeSet

class Solution {
  fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
    val root = createTrie(products)
    val suggestedProducts = mutableListOf<List<String>>()
    val path = mutableListOf<TrieNode?>()
    var cur: TrieNode? = root
    for (c in searchWord) {
      cur = cur?.children?.get(c)
      path.add(cur)
    }

    val visited = mutableSetOf<TrieNode>()
    val curWords = TreeSet<String>()

    fun findWordsUnderNode(node: TrieNode) {
      var nodes = mutableListOf(node)
      while (nodes.isNotEmpty()) {
        val nextNodes = mutableListOf<TrieNode>()
        for (node in nodes) {
          visited.add(node)
          val word = node.word
          if (word != null) {
            curWords.add(word)
            if (curWords.size > 3) {
              curWords.pollLast()
            }
          }
          nextNodes.addAll(node.children.values.filter { !visited.contains(it) })
        }

        nodes = nextNodes
      }
    }


    for (i in path.size - 1 downTo 0) {
      val node = path[i]
      if (node == null) {
        suggestedProducts.add(emptyList())
        continue
      }
      findWordsUnderNode(node)
      suggestedProducts.add(curWords.toList())
    }

    return suggestedProducts.reversed()
  }

  private fun createTrie(products: Array<String>): TrieNode {
    val root = TrieNode()

    fun insert(word: String) {
      var cur = root
      for (c in word) {
        cur = cur.children.getOrPut(c) { TrieNode() }
      }
      cur.word = word
    }

    for (product in products) {
      insert(product)
    }

    return root
  }
}


class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var word: String? = null
}