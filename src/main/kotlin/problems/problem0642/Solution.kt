package problems.problem0642

import java.util.*

class AutocompleteSystem(sentences: Array<String>, times: IntArray) {

  val root: TrieNode = TrieNode()
  var curWord = StringBuilder()
  var iterNode: TrieNode? = root

  init {
    for (i in sentences.indices) {
      for (j in 0 until times[i]) {
        insert(sentences[i])
      }
    }

  }

  fun input(c: Char): List<String> {
    if (c == '#') {
      insert(curWord.toString())
      curWord = StringBuilder()
      iterNode = root
      return emptyList()
    }
    curWord.append(c)
    if (iterNode == null) {
      return emptyList()
    }
    iterNode = iterNode!!.children[c]
    if (iterNode == null) {
      return emptyList()
    }

    val allInputs = PriorityQueue<SentenceInput>(compareBy({ -it.timesInput }, { it.input }))
    fun recurse(node: TrieNode?) {
      if (node == null) {
        return
      }
      val input = node.input
      if (input != null) {
        allInputs.add(input)
      }
      for (value in node.children.values) {
        recurse(value)
      }
    }

    recurse(iterNode)
    val topThree = mutableListOf<String>()
    for (i in 0 until 3) {
      if (allInputs.isEmpty()) {
        break
      }
      topThree.add(allInputs.poll().input)
    }
    return topThree
  }



  private fun insert(s: String) {
    var curNode = root
    for (c in s) {
      var nextNode = curNode.children[c] ?: TrieNode()
      curNode.children[c] = nextNode
      curNode = nextNode
    }
    val input = curNode.input
    if (input == null) {
      curNode.input = SentenceInput(s, 1)
    }
    else {
      input.timesInput++
    }
  }

}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var input: SentenceInput? = null

  override fun toString(): String {
    return "children=${children.toString()},input=${input}"
  }
}


data class SentenceInput(val input: String, var timesInput: Int)