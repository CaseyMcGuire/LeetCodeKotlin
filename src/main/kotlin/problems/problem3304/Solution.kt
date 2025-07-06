package problems.problem3304

class Solution {
  fun kthCharacter(k: Int): Char {
    if (k == 1) {
      return 'a'
    }
    val builder = StringBuilder("a")
    while (true) {
      val curSize = builder.length
      for (i in 0 until curSize) {
        val nextChar = builder[i].nextChar()
        builder.append(nextChar)
        if (builder.length == k) {
          return nextChar
        }
      }
    }
  }

  fun Char.nextChar(): Char {
    if (this == 'z') {
      return 'a'
    }
    else {
      return this.inc()
    }
  }
}