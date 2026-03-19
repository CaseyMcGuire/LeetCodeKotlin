package problems.problem0901

class StockSpanner() {

  private val monotonicStack = mutableListOf<Element>()
  private var curIndex = 0

  fun next(price: Int): Int {
    while (monotonicStack.isNotEmpty() && monotonicStack.last().price <= price) {
      monotonicStack.removeLast()
    }

    val largerIndex = monotonicStack.lastOrNull()?.index
      ?: -1
    val lengthOfSpan = curIndex - largerIndex
    monotonicStack.add(Element(curIndex, price))
    curIndex++
    return lengthOfSpan
  }

  data class Element(val index: Int, val price: Int)
}