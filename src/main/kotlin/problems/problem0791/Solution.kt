package problems.problem0791

class Solution {
  fun customSortString(order: String, s: String): String {
    val frequencyMap = mutableMapOf<Char, Int>()
    for (c in s) {
      frequencyMap.increment(c)
    }

    val reorderedString = mutableListOf<Char>()
    for (c in order) {
      val frequency = frequencyMap[c]
      if (frequency != null) {
        for (i in 0 until frequency) {
          reorderedString.add(c)
        }
        frequencyMap.remove(c)
      }
    }

    for (entry in frequencyMap.entries) {
      for (i in 0 until entry.value) {
        reorderedString.add(entry.key)
      }
    }

    return reorderedString.joinToString("")
  }

  private fun MutableMap<Char, Int>.increment(c: Char) {
    this.merge(c, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<Char, Int>.decrement(c: Char) {
    val currentFrequency = this[c] ?: return
    if (currentFrequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = currentFrequency - 1
    }
  }
}