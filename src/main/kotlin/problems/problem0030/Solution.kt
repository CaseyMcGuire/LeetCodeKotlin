package problems.problem0030

class Solution {
  fun findSubstring(s: String, words: Array<String>): List<Int> {
    val indices = mutableListOf<Int>()
    val trie = getTrie(words)
    val totalLength = words.map { it.length }.sum()

    for (i in s.indices) {
      if (i + totalLength > s.length) {
        break
      }
      if (containsIndex(s, i, trie)) {
        indices.add(i)
      }
    }
    return indices
  }

  fun containsIndex(s: String, index: Int, trie: Trie): Boolean {

    val root = trie.root
    var curNode = root
    val frequencyMap = trie.frequencyMap
    val currentMap = mutableMapOf<String, Int>()

    fun recurse(i: Int, node: TrieNode?): Boolean {
      if (node == null) {
        return false
      }
      val word = node.word
      if (word != null && node.freq > 0) {
        currentMap.increment(word)
        node.freq--
        val found = if (currentMap == frequencyMap) {
          true
        }
        else if (i < s.length && recurse(i + 1, root.children[s[i]])) {
          true
        }
        else {
          false
        }
        node.freq++
        currentMap.decrement(word)
        if (found || i == s.length) {
          return found
        }
      }

      if (i < s.length && recurse(i + 1, node.children[s[i]])) {
        return true
      }

      return false

    }
    return recurse(index, root)
  }

  fun getTrie(words: Array<String>): Trie {
    val trie = Trie()
    words.forEach { trie.insert(it) }
    return trie
  }
}

class Trie {
  val frequencyMap = mutableMapOf<String, Int>()
  val root = TrieNode()

  fun insert(str: String) {
    frequencyMap.increment(str)
    var cur = root
    for (c in str) {
      val child = cur.children[c] ?: TrieNode()
      cur.children[c] = child
      cur = child
    }
    cur.word = str
    cur.freq++
  }
}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var word: String? = null
  var freq = 0
}

fun MutableMap<String, Int>.increment(str: String) {
  this.merge(str, 1) { cur, acc -> cur + acc }
}

fun MutableMap<String, Int>.decrement(str: String) {
  val frequency = this[str] ?: return
  if (frequency == 1) {
    this.remove(str)
  }
  else {
    this[str] = frequency - 1
  }
}