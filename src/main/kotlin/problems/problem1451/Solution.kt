package problems.problem1451

class Solution {
  fun arrangeWords(text: String): String {
    return text.split(" ").withIndex().sortedWith(
      compareBy(
        { it.value.length },
        { it.index }
      )
    )
      .mapIndexed { i, element ->
        if (i == 0) element.value.capitalize()
        else element.value.lowercase()
      }
      .joinToString(" ")
  }
}