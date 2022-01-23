package problems.problem0093

import java.util.*

class Solution {
  fun restoreIpAddresses(s: String): List<String> {
    val addresses = mutableSetOf<String>()

    val curAddress = LinkedList<String>()
    fun backtrack(i: Int) {
      if (curAddress.size == 3) {
        if (s.length - i > 3) {
          return
        }
        val element = s.substring(i, s.length)
        curAddress.addLast(element)
        if (isValidIpAddress(curAddress)) {
          addresses.add(curAddress.joinToString("."))
        }
        curAddress.removeLast()
        return
      }

      for (j in 1..3) {
        val end = i + j
        if (end > s.length) {
          break
        }
        val curComponent = s.substring(i, end)
        curAddress.addLast(curComponent)
        backtrack(end)
        curAddress.removeLast()
      }
    }
    backtrack(0)
    return addresses.toList()
  }

  private fun isValidIpAddress(address: List<String>): Boolean {
    for (elem in address) {

      if (elem.isEmpty() || elem.startsWith("0") && elem != "0" || elem.toInt() !in 0..255) {
        return false
      }
    }
    return true
  }
}