package problems.problem0288

class ValidWordAbbr(dictionary: Array<String>) {

  private val abbreviationsToWords = mutableMapOf<String, MutableSet<String>>()

  init {
    for (word in dictionary) {
      val a = getAbbreviation(word)
      val set = abbreviationsToWords[a] ?: mutableSetOf()
      set.add(word)
      abbreviationsToWords[a] = set
    }
  }

  fun isUnique(word: String): Boolean {
    val abbr = getAbbreviation(word)
    val abbreviations = abbreviationsToWords[abbr]
    if (abbreviations == null) {
      return true
    }
    return abbreviations.size == 1 && abbreviations.contains(word)
  }

  private fun getAbbreviation(s: String): String {
    if (s.length <= 2) {
      return s
    }
    val middleSize = s.length - 2
    val first = s[0]
    val last = s[s.length - 1]
    return "$first$middleSize$last"
  }

}