package problems.problem1088

class Solution {
  fun confusingNumberII(n: Int): Int {
    val nList = mutableListOf<Int>()

    for (c in n.toString()) {
      nList.add(Character.getNumericValue(c))
    }

    var numConfusingNumbers = 0
    fun backtrack(cur: MutableList<Int>) {
      if (cur.isNotEmpty()) {
        if (!nList.isGreaterThan(cur) || cur.isZeroes()) {
          return
        }
        if (isConfusing(cur)) {
          numConfusingNumbers++
        }
      }

      for (num in listOf(0, 1, 6, 8, 9)) {
        cur.add(num)
        backtrack(cur)
        cur.removeLast()
      }
    }
    backtrack(mutableListOf())
    return numConfusingNumbers
  }

  private fun isConfusing(cur: MutableList<Int>): Boolean {
    var i = 0
    var j = cur.size - 1
    while (i < cur.size) {
      if (cur[i] != getFlipped(cur[j])) {
        return true
      }
      i++
      j--
    }
    return false
  }

  private fun MutableList<Int>.isZeroes(): Boolean {
    for (num in this) {
      if (num != 0) {
        return false
      }
    }
    return true
  }

  private fun MutableList<Int>.removeLast() {
    this.removeAt(this.size - 1)
  }

  private fun List<Int>.isGreaterThan(other: MutableList<Int>): Boolean {
    if (other.size < this.size) {
      return true
    }
    if (other.size > this.size) {
      return false
    }
    if (this == other) {
      return true
    }
    var numEquals = 0
    for (i in this.indices) {
      if (this[i] > other[i]) {
        return true
      }
      else if (other[i] > this[i]) {
        return false
      }
      else if (this[i] == other[i]) {
        numEquals++
      }
    }
    return numEquals == other.size
  }


  private fun getFlipped(num: Int): Int {
    return when(num) {
      0 -> 0
      1 -> 1
      8 -> 8
      6 -> 9
      9 -> 6
      else -> throw IllegalArgumentException()
    }
  }
}