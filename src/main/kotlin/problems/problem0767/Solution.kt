package problems.problem0767

import java.util.*

class Solution {
  fun reorganizeString(s: String): String {
    if (s.isEmpty()) {
      return s
    }
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in s) {
      frequencyMap.merge(c, 1) { cur, acc -> cur + acc }
    }
    val pq = PriorityQueue<Element>(compareBy({ -it.size }, { it.c }))

    for (entry in frequencyMap.entries) {
      pq.add(Element(entry.key, entry.value))
    }

    val builder = StringBuilder()
    var cur = pq.poll()
    while (true) {
      builder.append(cur.c)

      if (pq.isEmpty()) {
        if (cur.size == 1) {
          return builder.toString()
        }
        else if (cur.size > 1) {
          return ""
        }
      }

      val replacement = Element(cur.c, cur.size - 1)
      val next = pq.poll()
      if (replacement.size != 0) {
        pq.add(replacement)
      }
      cur = next!!
    }
  }
}

data class Element(val c: Char, val size: Int)