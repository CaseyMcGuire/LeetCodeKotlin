package problems.problem0341

import java.util.*

class NestedIterator(private val nestedList: List<NestedInteger>) {

  private val list: LinkedList<Int> = LinkedList()

  init {
    flattenList()
  }

  private fun flattenList() {
    val queue = LinkedList(nestedList)
    while (queue.isNotEmpty()) {
      val first = queue.removeFirst()
      if (first.isInteger()) {
        list.add(first.getInteger()!!)
      }
      else {
        val nestedList = first.getList()!!
        if (nestedList.isNotEmpty()) {
          val first = nestedList[0]
          if (nestedList.size > 1) {
            val sublist = nestedList.subList(1, nestedList.size)
            for (i in sublist.indices.reversed()) {
              queue.addFirst(sublist[i])
            }
          }
          if (first.isInteger()) {
            list.add(first.getInteger()!!)
          }
          else {
            queue.addFirst(first)
          }
        }
      }
    }
  }

  fun next(): Int {
    return list.removeFirst()
  }

  fun hasNext(): Boolean {
    return list.isNotEmpty()
  }
}

class NestedInteger {

  constructor() {}

  constructor(value: Int) {

  }
  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  fun isInteger(): Boolean {
    return true
  }

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  fun getInteger(): Int? {
    return null
  }

  // Set this NestedInteger to hold a single integer.
  fun setInteger(value: Int): Unit {

  }

  // Set this NestedInteger to hold a nested list and adds a nested integer to it.
  fun add(ni: NestedInteger): Unit {

  }

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return null if this NestedInteger holds a single integer
  fun getList(): List<NestedInteger>? {
    return null
  }
}