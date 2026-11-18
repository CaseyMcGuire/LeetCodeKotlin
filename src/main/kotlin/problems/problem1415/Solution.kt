package problems.problem1415

class Solution {
  fun getHappyString(n: Int, k: Int): String {
    return getHappyStrings(n).getOrNull(k - 1) ?: ""
  }

  private fun getHappyStrings(n: Int): List<String> {
    if (n == 1) {
      return listOf("a", "b", "c")
    }

    val builder = mutableListOf<Char>()
    val strings = mutableListOf<String>()

    fun recurse() {
      println(builder)
      if (builder.size == n) {
        strings.add(builder.joinToString(""))
        return
      }

      for (c in listOf('a', 'b', 'c')) {
        if (builder.lastOrNull() != c) {
          builder.add(c)
          recurse()
          builder.removeLast()
        }
      }
    }
    recurse()
    return strings
  }
}