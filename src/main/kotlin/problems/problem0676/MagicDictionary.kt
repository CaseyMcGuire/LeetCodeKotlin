package problems.problem0676

class MagicDictionary() {

  val root = TrieNode()

  fun buildDict(dictionary: Array<String>) {
    for (word in dictionary) {
      insert(word)
    }
  }

  private fun insert(word: String) {
    var cur = root
    for (c in word) {
      var next = cur.children[c]
      if (next == null) {
        next = TrieNode()
        cur.children[c] = next
      }
      cur = next
    }
    cur.isWord = true
  }

  fun search(searchWord: String): Boolean {

    fun recurse(node: TrieNode, i: Int, hasChanged: Boolean): Boolean {
      if (i == searchWord.length) {
        return hasChanged && node.isWord
      }
      val next = node.children[searchWord[i]]
      if (next != null && recurse(next, i + 1, hasChanged)) {
        return true
      }

      if (!hasChanged) {
        for (childEntry in node.children.entries) {
          if (childEntry.key == searchWord[i]) {
            continue
          }
          if (recurse(childEntry.value, i + 1, true)) {
            return true
          }
        }
      }
      return false
    }
    return recurse(root, 0, false)
  }

}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var isWord = false
}
