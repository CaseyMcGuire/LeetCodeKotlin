package problems.problem0320

class Solution {
  fun generateAbbreviations(word: String): List<String> {
    val abbreviations = mutableListOf<String>()
    val curWord = mutableListOf<String>()
    fun recurse(index: Int, count: Int) {
      if (index == word.length) {
        val countStr = if (count > 0) count.toString() else ""
        abbreviations.add(curWord.joinToString("") + countStr)

        return
      }

      if (count > 0) {
        curWord.add(count.toString())
        curWord.add(word[index].toString())
        recurse(index + 1, 0)
        curWord.removeLast()
        curWord.removeLast()
      }
      else {
        curWord.add(word[index].toString())
        recurse(index + 1, 0)
        curWord.removeLast()
      }

      recurse(index + 1, count + 1)
    }
    recurse(0, 0)
    return abbreviations
  }

  private fun MutableList<String>.removeLast() {
    this.removeAt(this.size - 1)
  }
}