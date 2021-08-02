package problems.problem0017

class Solution {
  fun letterCombinations(digits: String): List<String> {
    if (digits.isBlank()) {
      return emptyList()
    }
    val combinations = mutableListOf<String>()
    fun recurse(index: Int, currentCombination: MutableList<Char>) {
      if (index == digits.length) {
        val comboString = currentCombination.joinToString("")
        combinations.add(comboString)
        return
      }
      getLettersForDigit(digits[index]).forEach {
        currentCombination.add(it)
        recurse(index + 1, currentCombination)
        currentCombination.removeAt(currentCombination.size - 1)
      }
    }
    recurse(0, mutableListOf())
    return combinations
  }

  private fun getLettersForDigit(digit: Char): List<Char> {
    return when (digit) {
      '2' -> listOf('a', 'b', 'c')
      '3' -> listOf('d','e','f')
      '4' -> listOf('g', 'h', 'i')
      '5' -> listOf('j', 'k', 'l')
      '6' -> listOf('m', 'n', 'o')
      '7' -> listOf('p', 'q' ,'r', 's')
      '8' -> listOf('t', 'u', 'v')
      '9' -> listOf('w', 'x', 'y', 'z')
      else -> emptyList()
    }
  }
}