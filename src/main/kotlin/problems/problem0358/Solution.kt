package problems.problem0358

import java.util.*

class Solution {
  fun rearrangeString(s: String, k: Int): String {
    if (k == 0) {
      return s
    }
    val possibleChars = getPriorityQueue(s)
    val window = mutableMapOf<Char, CharFrequency>()
    val builder = StringBuilder()
    for (i in s.indices) {
      val toRemove = i - k

      if (toRemove >= 0) {
        val c = builder[toRemove]
        val charFreq = window[c]
        if (charFreq != null) {
          possibleChars.add(charFreq)
          window.remove(c)
        }
      }

      if (possibleChars.isEmpty()) {
        return ""
      }

      val cur = possibleChars.poll()
      if (window.contains(cur.c)) {
        return ""
      }
      builder.append(cur.c)
      cur.frequency--
      if (cur.frequency > 0) {
        window[cur.c] = cur
      }
    }
    return builder.toString()
  }

  private fun getPriorityQueue(s: String): PriorityQueue<CharFrequency> {
    val pq = PriorityQueue<CharFrequency>(compareBy({ -it.frequency }))

    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in s) {
      frequencyMap.merge(c, 1) { cur, acc -> cur + acc }
    }

    for (entry in frequencyMap.entries) {
      pq.add(CharFrequency(entry.key, entry.value))
    }

    return pq
  }
}

data class CharFrequency(val c: Char, var frequency: Int)