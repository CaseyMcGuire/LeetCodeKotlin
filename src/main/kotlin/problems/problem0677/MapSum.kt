package problems.problem0677

class MapSum() {

  private val root = TrieNode()

  fun insert(key: String, value: Int) {
    var cur = root
    for (c in key) {
      cur = cur.children.getOrPut(c) { TrieNode() }
    }
    cur.value = value
  }

  fun sum(prefix: String): Int {

    fun recurse(node: TrieNode): Int {
      var sum = node.value
      for (child in node.children.values) {
        sum += recurse(child)
      }
      return sum
    }

    var cur = root
    for (c in prefix) {
      val next = cur?.children[c]
      if (next == null) {
        return 0
      }
      cur = next
    }

    return recurse(cur)
  }

}

class TrieNode {
  val children = mutableMapOf<Char, TrieNode>()
  var value = 0
}
