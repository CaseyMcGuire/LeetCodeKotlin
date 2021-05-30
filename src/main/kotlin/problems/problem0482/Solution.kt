package problems.problem0482

class Solution {
  fun licenseKeyFormatting(s: String, k: Int): String {
    val stringWithoutDashes = s.filter { it != '-'}
      .map { it.toUpperCase() }
      .joinToString("")
    val sizeOfFirstGroup = stringWithoutDashes.length % k
    val formattedString = StringBuilder()
    val firstGroup = stringWithoutDashes.take(sizeOfFirstGroup)
    val remainingGroups = stringWithoutDashes.drop(sizeOfFirstGroup).chunked(k)
    formattedString.append(firstGroup)
    remainingGroups.forEach {
      formattedString.append("-").append(it)
    }
    return if (formattedString.startsWith("-")) {
     formattedString.drop(1).toString()
    }
    else {
     formattedString.toString()
    }
  }
}