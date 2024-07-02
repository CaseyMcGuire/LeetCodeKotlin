package problems.problem1381

class CustomStack(private val maxSize: Int) {

  private val list = mutableListOf<Entry>()

  fun push(x: Int) {
    if (list.size == maxSize) {
      return
    }
    list.add(Entry(x))
  }

  fun pop(): Int {
    if (list.isEmpty()) {
      return -1
    }
    return list.removeLast().num
  }

  fun increment(k: Int, `val`: Int) {
    for (i in 0 until Math.min(k, list.size)) {
      list[i].num += `val`
    }
  }

  private fun MutableList<Entry>.removeLast(): Entry {
    val element = this[this.size - 1]
    this.removeAt(this.size - 1)
    return element
  }

}

data class Entry(var num: Int)