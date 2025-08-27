package problems.problem0381

class RandomizedCollection() {

  private val valueToIndices = mutableMapOf<Int, MutableSet<Int>>()
  private val values = mutableListOf<Int>()

  fun insert(value: Int): Boolean {
    val indices = valueToIndices.getOrPut(value) { mutableSetOf() }
    values.add(value)
    indices.add(values.size - 1)
    return indices.size == 1
  }

  fun remove(value: Int): Boolean {
    val indices = valueToIndices[value]
      ?: return false
    val indexToRemove = indices.removeOneElement()

    if (indices.isEmpty()) {
      valueToIndices.remove(value)
    }

    if (indexToRemove == values.size - 1) {
      values.removeLast()
    }
    else {
      val indexToSwap = values.size - 1
      values.swap(indexToRemove, indexToSwap)
      values.removeLast()
      val newValue = values[indexToRemove]
      val indicesForSwapped = valueToIndices[newValue]!!
      indicesForSwapped.remove(indexToSwap)
      indicesForSwapped.add(indexToRemove)
    }

    return true
  }

  private fun MutableSet<Int>.removeOneElement(): Int {
    var element: Int? = this.first()
    this.remove(element)
    return element!!
  }

  fun getRandom(): Int {
    return values.random()
  }

  private fun MutableList<Int>.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
  }

}