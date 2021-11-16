package problems.problem0249

class Solution {
  fun groupStrings(strings: Array<String>): List<List<String>> {
    val stringMap = mutableMapOf<String, Int>()
    strings.forEach { stringMap.increment(it) }
    val allGroupedShifts = mutableListOf<List<String>>()
    for (s in strings.sorted()) {
      if (!stringMap.containsKey(s)) {
        continue
      }
      val groupedShifts = mutableListOf<String>()
      var shifted = s
      for (i in 0 until 26) {
        while (stringMap.containsKey(shifted)) {
          groupedShifts.add(shifted)
          stringMap.decrement(shifted)
        }
        shifted = shifted.shift()
      }
      allGroupedShifts.add(groupedShifts)
    }

    return allGroupedShifts
  }

  private fun MutableMap<String, Int>.increment(s: String) {
    this.merge(s, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<String, Int>.decrement(s: String) {
    val current = this[s]
    if (current == null) {
      return
    }

    if (current == 1) {
      this.remove(s)
    }
    else {
      this[s] = current - 1
    }
  }

  private fun String.shift(): String {
    val list = mutableListOf<Char>()
    for (c in this) {

      val next = if (c == 'z') 'a' else (c + 1).toChar()
      list.add(next)
    }
    return list.joinToString("")
  }
}