package problems.problem1209

class Solution {
  fun removeDuplicates(s: String, k: Int): String {
    val elements = mutableListOf<Char>()
    val window = MultiSet()
    for (i in s.indices) {
      val c = s[i]
      elements.add(c)
      window.increment(c)
      if (window.size == k && window.numKeys() == 1) {
        elements.removeLastElements(k)
        window.clear()
        val lastElements = elements.getLastElements(k - 1)
        lastElements.forEach { window.increment(it) }
      }
      else if (window.size == k) {
        val charToRemove = elements[elements.size - k]
        window.decrement(charToRemove)
      }
    }
    return elements.joinToString("")
  }

  private fun MutableList<Char>.removeLastElements(numElements: Int) {
    var i = 0
    while (this.size > 0 && i < numElements) {
      this.removeAt(this.size - 1)
      i++
    }
  }

  private fun MutableList<Char>.getLastElements(numElements: Int): List<Char> {
    val elements = mutableListOf<Char>()
    for (i in this.size - 1 downTo this.size - numElements) {
      if (i < 0) {
        break
      }
      elements.add(this[i])
    }
    return elements
  }
}

class MultiSet {
  val map = mutableMapOf<Char, Int>()
  var size = 0

  fun increment(c: Char) {
    map.merge(c, 1) { cur, acc -> cur + acc }
    size++
  }

  fun decrement(c: Char) {
    val curNum = map[c] ?: return
    if (curNum == 1) {
      map.remove(c)
    }
    else {
      map[c] = curNum - 1
    }
    size--
  }

  fun numKeys(): Int {
    return map.size
  }

  fun clear() {
    map.clear()
    size = 0
  }

  override fun toString(): String {
    return "[map: $map size: $size]"
  }

}