package problems.problem0937

class Solution {
  fun reorderLogFiles(logs: Array<String>): Array<String> {
    val letterLogs = mutableListOf<Log>()
    val digitLogs = mutableListOf<Log>()
    for (log in logs) {
      val components = log.split(" ")
      val logClass = Log(components[0], components.subList(1, components.size))
      if (logClass.isDigitLog()) {
        digitLogs.add(logClass)
      }
      else {
        letterLogs.add(logClass)
      }
    }

    val sortedLetterLogs = letterLogs.sortedWith(compareBy({ it.contents.joinToString(" ") }, { it.identifier }))
    return (sortedLetterLogs + digitLogs).map { it.toString() }.toTypedArray()
  }
}

data class Log(val identifier: String, val contents: List<String>) {
  fun isDigitLog(): Boolean {
    for (content in contents) {
      for (c in content) {
        if (!Character.isDigit(c)) {
          return false
        }
      }
    }
    return true
  }

  override fun toString(): String {
    val joinedContents = contents.joinToString(" ")
    return "$identifier $joinedContents"
  }
}