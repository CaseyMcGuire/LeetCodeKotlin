package problems.problem1825

import java.util.*

class MKAverage(private val m: Int, private val k: Int) {

  private val numToIndex = mutableMapOf<Int, Int>()
  private val lower = TreeSum()
  private val middle = TreeSum()
  private val higher = TreeSum()
  private val elementList = LinkedList<Entry>()

  fun addElement(num: Int) {
    val curIndex = numToIndex[num] ?: 0
    val nextIndex = curIndex + 1
    numToIndex[num] = nextIndex
    val nextEntry = Entry(num, nextIndex)
    elementList.addLast(nextEntry)
    if (getSize() == m) {
      val toRemove = elementList.removeFirst()
      lower.remove(toRemove)
      middle.remove(toRemove)
      higher.remove(toRemove)
    }

    middle.add(nextEntry)
    if (getSize() < m) {
      return
    }


    while (lower.size() < k) {
      lower.add(middle.removeLowest())
    }

    while (higher.size() < k) {
      higher.add(middle.removeHighest())
    }

    while (lower.highest().num > middle.lowest().num) {
      val top = lower.removeHighest()
      val bottom = middle.removeLowest()
      lower.add(bottom)
      middle.add(top)
    }

    while (middle.highest().num > higher.lowest().num) {
      val top = middle.removeHighest()
      val bottom = higher.removeLowest()
      middle.add(bottom)
      higher.add(top)
    }
  }

  fun calculateMKAverage(): Int {
    if (getSize() < m) {
      return -1
    }

    return middle.getTotal() / middle.size()
  }

  private fun getSize(): Int {
    return lower.size() + middle.size() + higher.size()
  }

}

data class Entry(val num: Int, val id: Int)

class TreeSum() {
  private var total = 0
  private val elements = sortedSetOf<Entry>(compareBy({ it.num }, { it.id }))

  fun getTotal(): Int {
    return total
  }

  fun size(): Int {
    return elements.size
  }

  fun add(entry: Entry) {
    if (elements.add(entry)) {
      total += entry.num
    }
  }

  fun remove(entry: Entry) {
    if (!elements.contains(entry)) {
      return
    }
    elements.remove(entry)
    total -= entry.num
  }

  fun removeHighest(): Entry {
    val highest = elements.last()
    remove(highest)
    return highest
  }

  fun removeLowest(): Entry {
    val lowest = elements.first()
    remove(lowest)
    return lowest
  }

  fun highest(): Entry {
    return elements.last()
  }

  fun lowest(): Entry {
    return elements.first()
  }

  override fun toString(): String {
    return "[elments: $elements total: $total]"
  }
}