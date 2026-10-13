package problems.problem0966

class Solution {
  fun spellchecker(wordlist: Array<String>, queries: Array<String>): Array<String> {
    val trie = Trie()
    for (word in wordlist) {
      trie.addWord(word)
    }

    val correctedStrings = Array<String>(queries.size) { "" }
    for (i in queries.indices) {
      correctedStrings[i] = trie.getWord(queries[i]) ?: ""
    }
    return correctedStrings
  }
}

class Trie {

  companion object {
    val VOWELS = "aeiou".toList().sorted()
  }

  val root = TrieNode()
  var size = 0
  private val wordToIndex = mutableMapOf<String, Int>()

  fun addWord(word: String) {
    if (wordToIndex.containsKey(word)) {
      return
    }

    var currentNode = root

    for (c in word) {
      currentNode = currentNode.children.getOrPut(c) { TrieNode() }
    }

    currentNode.words.add(word)
    wordToIndex[word] = size
    size++
  }

  fun getWord(word: String): String? {

    val matches = mutableSetOf<String>()

    fun recurse(node: TrieNode, i: Int) {

      if (i == word.length) {
        matches.addAll(node.words)
        return
      }

      val c = word[i]
      val candidates = listOf(c, c.toOppositeCase()) +
          if (c.isVowel()) VOWELS else emptyList()

      for (candidate in candidates) {
        val next = node.children[candidate]
        if (next != null) {
          recurse(next, i + 1)
        }
      }
    }

    recurse(root, 0)

    if (matches.contains(word)) {
      return word
    }
    val (lowerCase, vowelChanges) = matches.partition { it.lowercase() == word.lowercase() }

    if (lowerCase.isNotEmpty()) {
      return lowerCase.minBy { wordToIndex[it]!! }
    }
    else if (vowelChanges.isNotEmpty()) {
      return vowelChanges.minBy { wordToIndex[it]!! }
    }
    else {
      return null
    }
  }

  private fun Char.isVowel(): Boolean {
    return this.lowercaseChar() in VOWELS
  }

  private fun Char.toOppositeCase(): Char {
    if (this == this.lowercaseChar()) {
      return this.uppercaseChar()
    }
    else {
      return this.lowercaseChar()
    }
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  val words = LinkedHashSet<String>()

  fun getFirst(): String? {
    for (word in words) {
      return word
    }
    return null
  }

  override fun toString(): String {
    return "{words: $words children: $children}"
  }
}