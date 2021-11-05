package problems.problem0281

class ZigzagIterator {

  private val elements: MutableList<Iter>
  private var curIndex = 0

  constructor(v1: IntArray, v2: IntArray) {
    elements = mutableListOf(Iter(v1, 0), Iter(v2, 0)).filter { it.array.isNotEmpty() }.toMutableList()
  }

  fun next(): Int {
    val curIter = elements[curIndex]
    val item = curIter.array[curIter.index]
    curIter.index++
    if (curIter.index == curIter.array.size) {
      elements.removeAt(curIndex)
    }

    else {
      curIndex++

    }
    if (curIndex == elements.size) {
      curIndex = 0
    }
    return item
  }

  fun hasNext(): Boolean {
    return elements.isNotEmpty()
  }
}

data class Iter(val array: IntArray, var index: Int)