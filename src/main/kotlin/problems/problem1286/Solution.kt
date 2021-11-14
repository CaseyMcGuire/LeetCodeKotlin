package problems.problem1286

class CombinationIterator(val characters: String, combinationLength: Int) {

  private val pointers = IntArray(combinationLength)
  private var hasNext: Boolean

  init {
    for (i in 0 until combinationLength) {
      pointers[i] = i
    }

    hasNext = combinationLength != 0 && combinationLength < characters.length
  }

  fun next(): String {
    val builder = StringBuilder()
    for (pointer in pointers) {
      builder.append(characters[pointer])
    }

    // adjust our pointers
    val nextPointerIndex = findPointerIndexNotInLastPlace()
    if (nextPointerIndex == null) {
      hasNext = false
    }
    else {
      val value = pointers[nextPointerIndex] + 1
      pointers[nextPointerIndex] = value
      for (i in 1 until pointers.size - nextPointerIndex) {
        pointers[i + nextPointerIndex] = value + i
      }
    }
    return builder.toString()
  }

  private fun findPointerIndexNotInLastPlace(): Int? {
    for (i in pointers.indices.reversed()) {
      val charIndex = pointers[i]
      if (i == pointers.size - 1 && pointers[i] != characters.length - 1) {
        return i
      }
      else if (i != pointers.size - 1 && pointers[i] + 1 != pointers[i + 1]) {
        return i
      }
    }
    return null
  }

  fun hasNext(): Boolean {
    return hasNext
  }

}