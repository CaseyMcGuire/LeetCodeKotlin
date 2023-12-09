package problems.problem0139

class Solution {
  fun wordBreak(s: String, wordDict: List<String>): Boolean {
    if (s.isEmpty()) {
      return true
    }
    val cache = mutableMapOf<CacheItem, Boolean>()
    val head = getHeadTrieNode(wordDict)
    fun recurse(i: Int, node: TrieNode): Boolean {
      val nextNode = node.get(s[i])
      val cacheItem = CacheItem(i, node)
      val cacheValue = cache[cacheItem]
      if (cacheValue != null) {
        return cacheValue
      }
      if (nextNode == null) {
        return false
      }
      if (i == s.length - 1) {
        val completes = nextNode.isWord
        cache[cacheItem] = completes
        return completes
      }
      if (nextNode.isWord) {
        val completes = recurse(i + 1, head)
        cache[cacheItem] = completes
        if (completes) {
          return true
        }
      }
      val completes = recurse(i + 1, nextNode)
      cache[cacheItem] = completes
      return completes

    }
    return recurse(0, head)
  }

  fun getHeadTrieNode(wordDict: List<String>): TrieNode {
    val head = TrieNode()
    for (word in wordDict) {
      var cur = head
      for (c in word) {
        val next = cur.map[c] ?: TrieNode()
        cur.map[c] = next
        cur = next
      }
      cur.isWord = true
    }
    return head
  }
}

class TrieNode {
  val map = mutableMapOf<Char, TrieNode>()
  var isWord = false

  fun get(c: Char): TrieNode? {
    return map[c]
  }
}

data class CacheItem(val i: Int, val node: TrieNode)