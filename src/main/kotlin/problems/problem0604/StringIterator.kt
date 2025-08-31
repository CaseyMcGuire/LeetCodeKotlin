package problems.problem0604

class StringIterator(compressedString: String) {

  private val components = ArrayDeque<Component>()

  init {
    var i = 0
    while (i < compressedString.length) {
      var letter = compressedString[i]
      i++

      val numBuilder = StringBuilder()
      while (i < compressedString.length && compressedString[i].isDigit()) {
        numBuilder.append(compressedString[i])
        i++
      }
      components.add(Component(letter, numBuilder.toString().toInt()))
    }
  }

  fun next(): Char {
    if (components.isEmpty()) {
      return ' '
    }
    val first = components.first()
    first.frequency--
    if (first.frequency == 0) {
      components.removeFirst()
    }
    return first.letter
  }

  fun hasNext(): Boolean {
    return components.isNotEmpty()
  }

}

data class Component(val letter: Char, var frequency: Int)
