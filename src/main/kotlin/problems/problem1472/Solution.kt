package problems.problem1472

class BrowserHistory(private val homepage: String) {

  private val backwardHistory = mutableListOf<String>()
  private val forwardHistory = mutableListOf<String>()

  fun visit(url: String) {
    forwardHistory.clear()
    backwardHistory.add(url)
  }

  fun back(steps: Int): String {
    for (i in 0 until steps) {
      if (backwardHistory.isEmpty()) {
        break
      }
      forwardHistory.add(backwardHistory.removeLast())
    }

    return getCurrentPage()
  }

  fun forward(steps: Int): String {
    for (i in 0 until steps) {
      if (forwardHistory.isEmpty()) {
        break
      }
      backwardHistory.add(forwardHistory.removeLast())
    }

    return getCurrentPage()
  }

  private fun getCurrentPage(): String {
    if (backwardHistory.isEmpty()) {
      return homepage
    }
    return backwardHistory.last()
  }

}