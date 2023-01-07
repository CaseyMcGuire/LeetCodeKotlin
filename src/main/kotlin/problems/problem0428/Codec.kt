package problems.problem0428


class Codec {
  // Encodes a tree to a single string.
  fun serialize(root: Node?): String {
    var builder = StringBuilder()

    fun recurse(node: Node, first: Boolean) {
      if (!first) {
        builder.append(',')
      }
      builder.append(node.`val`)
      val children = node.children.filterNotNull()
      if (children.isNotEmpty()) {
        builder.append('[')
        var isFirst = true
        for (child in children) {
          recurse(child, isFirst)
          isFirst = false
        }
        builder.append(']')
      }
    }
    if (root == null) {
      return ""
    }
    recurse(root, true)
    return builder.toString()
  }

  // Decodes your encoded data to tree.
  fun deserialize(data: String): Node? {
    var i = 0
    println(data)
    fun recurse(): List<Node> {
      val level = mutableListOf<Node>()
      var builder = StringBuilder()
      while (true) {
        if (i >= data.length) {
          if (builder.isNotEmpty()) {
            level.add(Node(builder.toString().toInt()))
          }
          return level
        }
        else if (data[i] == ']') {
          i++
          if (builder.isNotEmpty()) {
            level.add(Node(builder.toString().toInt()))
          }
          return level
        }
        else if (data[i] == ',') {
          i++
          if (builder.isNotEmpty()) {
            level.add(Node(builder.toString().toInt()))
          }
          builder = StringBuilder()
        }
        else if (data[i] == '[') {
          i++
          val node = Node(builder.toString().toInt())
          node.children = recurse()
          level.add(node)
          builder = StringBuilder()
        }
        else {
          builder.append(data[i])
          i++
        }
      }

    }
    if (data.isEmpty()) {
      return null
    }
    return recurse()[0]
  }
}
class Node(var `val`: Int) {
   var children: List<Node?> = listOf()
}