package problems.problem0211

class WordDictionary() {

  val trie = TrieNode()

  fun addWord(word: String) {
    var currentNode = trie
    for (c in word) {
      var nextNode = currentNode.children[c]
      if (nextNode == null) {
        nextNode = TrieNode()
        currentNode.children[c] = nextNode
      }
      currentNode = nextNode
    }
    currentNode.isTerminal = true
  }

  fun search(word: String): Boolean {
    return search(word, trie, 0)
  }

  private fun search(word: String, node: TrieNode, index: Int): Boolean {
    if (word.length == index) {
      return node.isTerminal
    }
    val c = word[index]
    if (c == '.') {
      val values = node.children.values
      for (value in values) {
        if (search(word, value, index + 1)) {
          return true
        }
      }
    }
    else {
      val child = node.children[c]
      if (child == null) {
        return false
      }
      return search(word, child, index + 1)
    }
    return false
  }

}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var isTerminal = false
}