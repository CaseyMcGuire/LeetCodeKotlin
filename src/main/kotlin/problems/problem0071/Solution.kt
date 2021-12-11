package problems.problem0071

import java.util.*

class Solution {
  fun simplifyPath(path: String): String {
    var i = 0
    val pathSoFar = LinkedList<String>()
    while (i < path.length) {
      if (path[i] == '/') {
        while (i < path.length && path[i] == '/') {
          i++
        }
        continue
      }
      else {
        val builder = StringBuilder()
        while (i < path.length && path[i] != '/') {
          builder.append(path[i])
          i++
        }
        val file = builder.toString()
        if (file == ".") {
          continue
        }
        else if (file == "..") {
          if (pathSoFar.isNotEmpty()) {
            pathSoFar.removeLast()
          }
        }
        else {
          pathSoFar.addLast("/" + builder.toString())
        }
      }
    }
    if (pathSoFar.isEmpty()) {
      return "/"
    }
    return pathSoFar.joinToString("")
  }
}