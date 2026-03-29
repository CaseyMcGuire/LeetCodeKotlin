package problems.problem0423

class Solution {
  fun originalDigits(s: String): String {
    val charToFrequency = mutableMapOf<Char, Int>()
    for (c in s) {
      charToFrequency.increment(c)
    }

    val wordToNum = mutableMapOf(
      "zero" to 0,
      "one" to 1,
      "two" to 2,
      "three" to 3,
      "four" to 4,
      "five" to 5,
      "six" to 6,
      "seven" to 7,
      "eight" to 8,
      "nine" to 9
    )

    // this bit was non-obvious to me
    val lettersToWords = mutableListOf(
      'z' to listOf("zero"),
      'w' to listOf("two"),
      'u' to listOf("four"),
      'x' to listOf("six"),
      'g' to listOf("eight"),
      'h' to listOf("three", "eight"),
      'f' to listOf("five", "four"),
      's' to listOf("seven", "six"),
      'i' to listOf("nine", "five", "six", "eight"),
      'n' to listOf("one", "seven", "nine")
    )
    val nums = mutableListOf<Int>()
    fun recurse(): Boolean {
      for ((letter, words) in lettersToWords) {
        if (!charToFrequency.containsKey(letter)) {
          continue
        }

        for (word in words) {
          val containsAll = word.all { charToFrequency.containsKey(it) }
          if (!containsAll) {
            continue
          }
          nums.add(wordToNum[word]!!)
          word.forEach { charToFrequency.decrement(it) }
          if (charToFrequency.isEmpty() || recurse()) {
            return true
          }
          nums.removeLast()
          word.forEach { charToFrequency.increment(it) }
        }

      }
      return false
    }
    recurse()
    return nums.sorted().joinToString("")
  }

  private fun MutableMap<Char, Int>.increment(c: Char) {
    this.merge(c, 1) { cur, acc -> cur + acc }
  }

  private fun MutableMap<Char, Int>.decrement(c: Char) {
    val frequency = this[c] ?: error("No character: $c")
    if (frequency == 1) {
      this.remove(c)
    }
    else {
      this[c] = frequency - 1
    }
  }
}