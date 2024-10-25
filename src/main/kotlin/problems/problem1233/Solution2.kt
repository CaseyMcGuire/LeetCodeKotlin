package problems.problem1233

class Solution2 {
  fun removeSubfolders(paths: Array<String>): List<String> {
    val folders = paths.sorted()
      .map { path ->
        path.split("/")
          .filter { it.isNotEmpty() }
      }
      .filter { it.isNotEmpty() }

    val trie = Trie()
    val nonSubfolders = mutableListOf<String>()
    for (folder in folders) {
      if (!trie.isSubsetOf(folder)) {
        nonSubfolders.add("/${folder.joinToString("/")}")
      }
      trie.insert(folder)
    }
    return nonSubfolders
  }
}

class Trie {
  val root = TrieNode()

  fun insert(path: List<String>) {
    var cur = root
    for (folder in path) {
      val child = cur.folderToChildren[folder] ?: TrieNode()
      cur.folderToChildren[folder] = child
      cur = child
    }
    cur.isTerminal = true
  }

  fun isSubsetOf(path: List<String>): Boolean {
    var cur = root
    for (i in path.indices) {
      val folder = path[i]
      if (cur.isTerminal) {
        return true
      }

      val next = cur.folderToChildren[folder]
      if (next == null) {
        return false
      }
      cur = next
    }
    return true
  }
}

class TrieNode {
  val folderToChildren = mutableMapOf<String, TrieNode>()
  var isTerminal = false
}