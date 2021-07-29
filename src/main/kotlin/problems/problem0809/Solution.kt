package problems.problem0809

class Solution {
  fun expressiveWords(s: String, words: Array<String>): Int {
    val sGroups = getGroupedCharacters(s)
    val wordGroups = words.map { getGroupedCharacters(it) }
    return wordGroups.filter { isExpressive(sGroups, it) }.count()
  }

  private fun isExpressive(stretchedGroups: List<GroupedCharacters>, groups: List<GroupedCharacters>): Boolean {
    if (stretchedGroups.size != groups.size) {
      return false
    }
    val grouped = stretchedGroups.zip(groups)
    for ((stretchedGroup, group) in grouped) {
      if (stretchedGroup.character != group.character ||
        group.frequency > stretchedGroup.frequency ||
        stretchedGroup.frequency <= 2 && stretchedGroup.frequency != group.frequency
      ) {
        return false
      }
    }
    return true
  }

  private fun getGroupedCharacters(s: String): List<GroupedCharacters> {
    val groups = mutableListOf<GroupedCharacters>()
    var currentGroup: GroupedCharacters? = null
    for (c in s.toCharArray()) {
      when {
        currentGroup == null -> {
          currentGroup = GroupedCharacters(c, 1)
        }
        currentGroup.character != c -> {
          groups.add(currentGroup)
          currentGroup = GroupedCharacters(c, 1)
        }
        else -> {
          currentGroup.frequency++
        }
      }
    }
    if (currentGroup != null) {
      groups.add(currentGroup)
    }
    return groups
  }

  data class GroupedCharacters(val character: Char, var frequency: Int)
}