package problems.problem0212

class Solution {
  fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    val foundWords = mutableSetOf<String>()
    val numRows = board.size
    val numColumns = board[0].size
    val visited = mutableSetOf<Coordinate>()
    val trieRoot = TrieNode()
    for (word in words) {
      trieRoot.add(word)
    }

    fun recurse(node: TrieNode, coord: Coordinate) {
      val nextNode = node.children[board[coord.i][coord.j]]
      if (nextNode == null) {
        return
      }
      if (nextNode.word != null) {
        foundWords.add(nextNode.word!!)
        nextNode.word = null
      }

      visited.add(coord)
      val neighbors = coord.getNeighbors().filter {
        it.i in 0 until numRows && it.j in 0 until numColumns && !visited.contains(it)
      }
      for (neighbor in neighbors) {
        recurse(nextNode, neighbor)
      }
      visited.remove(coord)
      return
    }


    for (i in board.indices) {
      for (j in board[i].indices) {
        recurse(trieRoot, Coordinate(i, j))
      }
    }
    return foundWords.toList()
  }
}

data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )
  }
}

data class TrieNode(var word: String? = null, val children: MutableMap<Char, TrieNode> = mutableMapOf()) {
  fun add(word: String) {
    var current = this
    for (c in word.toCharArray()) {
      val existing = current.children[c]
      if (existing == null) {
        val newNode = TrieNode()
        current.children[c] = newNode
        current = newNode
      }
      else {
        current = existing
      }
    }
    current.word = word
  }
}