package problems.problem0284

class PeekingIterator(val iterator:Iterator<Int>) : Iterator<Int> {

  var peekedElement: Int? = null

  fun peek(): Int {
    if (peekedElement != null) {
      return peekedElement!!
    }
    peekedElement = iterator.next()
    return peekedElement!!
  }

  override fun next(): Int {
    if (peekedElement != null) {
      val temp = peekedElement
      peekedElement = null
      return temp!!
    }
    return iterator.next()
  }

  override fun hasNext(): Boolean {
    return peekedElement != null || iterator.hasNext()
  }
}