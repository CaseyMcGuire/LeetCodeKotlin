package problems.problem0359

class Logger() {

  private val messageToTimestamp = mutableMapOf<String, Int>()

  fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
    val lastValidTimestamp = messageToTimestamp[message]
    if (lastValidTimestamp == null) {
      messageToTimestamp[message] = timestamp
      return true
    }
    val timeElapsed = timestamp - lastValidTimestamp
    if (timeElapsed < 10) {
      return false
    }
    messageToTimestamp[message] = timestamp
    return true
  }

}