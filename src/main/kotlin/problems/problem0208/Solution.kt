package problems.problem0208

class Trie() {

  val subNodes: MutableMap<Char, TrieNode> = mutableMapOf()

  /** Inserts a word into the trie. */
  fun insert(word: String) {
    if (word.isEmpty()) {
      return
    }
    var currentNode = subNodes[word[0]]
    if (currentNode == null) {
      currentNode = TrieNode(false, word[0])
      subNodes[word[0]] = currentNode
    }
    for (i in 1 until word.length) {
      val currentChar = word[i]
      var nextNode = currentNode!!.subNodes[currentChar]
      if (nextNode == null) {
        nextNode = TrieNode(false, currentChar)
        currentNode.subNodes[currentChar] = nextNode
      }
      currentNode = nextNode
    }
    currentNode?.isWord = true
  }

  /** Returns if the word is in the trie. */
  fun search(word: String): Boolean {
    val node = searchForNode(word)
    return node?.isWord == true
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  fun startsWith(prefix: String): Boolean {
    val node = searchForNode(prefix)
    return node != null
  }

  private fun searchForNode(s: String): TrieNode? {
    if (s.isEmpty()) {
      return null
    }
    var currentNode = subNodes[s[0]]
    if (currentNode == null) {
      return null
    }
    for (i in 1 until s.length) {
      currentNode = currentNode!!.subNodes[s[i]]
      if (currentNode == null) {
        return null
      }
    }
    return currentNode
  }

}

data class TrieNode(var isWord: Boolean, val char: Char, val subNodes: MutableMap<Char, TrieNode> = mutableMapOf())
