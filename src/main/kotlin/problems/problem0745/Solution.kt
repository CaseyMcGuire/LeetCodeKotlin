package problems.problem0745

class WordFilter(words: Array<String>) {

  private val suffixTrie = Trie()
  private val prefixTrie = Trie()

  private val wordToIndex = mutableMapOf<String, Int>()

  init {
    for (i in words.indices) {
      val word = words[i]
      wordToIndex[word] = i
      prefixTrie.insert(word)
      suffixTrie.insert(word.reversed())
    }
  }

  fun f(prefix: String, suffix: String): Int {
    val matchingSuffixes = suffixTrie.getWordsThatStartWith(suffix.reversed()).map { it.reversed() }
    val matchingPrefixes = prefixTrie.getWordsThatStartWith(prefix)
    val intersection = (matchingSuffixes intersect matchingPrefixes)

    var largestIndex = -1
    for (str in intersection) {
      val index = wordToIndex[str]
      if (index != null && index > largestIndex) {
        largestIndex = index
      }
    }

    return largestIndex
  }

}

class Trie {
  val root = TrieNode()

  fun insert(s: String) {
    var curNode = root
    for (c in s) {
      var node = curNode.children[c]
      if (node == null) {
        node = TrieNode()
        curNode.children[c] = node

      }
      curNode = node
    }
    curNode.isTerminal = true
  }

  fun getWordsThatStartWith(s: String): List<String> {
    var curNode = root
    val chars = mutableListOf<Char>()
    for (c in s) {
      chars.add(c)
      val nextNode = curNode.children[c]
      if (nextNode == null) {
        return emptyList()
      }
      curNode = nextNode
    }


    val words = mutableListOf<String>()
    fun recurse(node: TrieNode, list: MutableList<Char>) {
      if (node.isTerminal) {
        words.add(list.joinToString(""))
      }
      val entries = node.children.entries
      for (entry in entries) {
        list.add(entry.key)
        recurse(entry.value, list)
        list.removeLast()
      }
    }
    recurse(curNode, chars)
    return words
  }

  private fun MutableList<Char>.removeLast() {
    if (this.isEmpty()) {
      return
    }
    this.removeAt(this.size - 1)
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var isTerminal = false
}