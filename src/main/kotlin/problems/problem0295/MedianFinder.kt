package problems.problem0295

import java.util.*

class MedianFinder() {

  private val lessThan = FrequencyMap()
  private val greaterThan = FrequencyMap()

  fun addNum(num: Int) {
    if (this.size() == 0) {
      lessThan.add(num)
      return
    }
    val medianLeft = lessThan.greatest()
    if (this.size() == 1) {
      lessThan.add(num)
      val greatest = lessThan.greatest()!!
      lessThan.remove(greatest)
      greaterThan.add(greatest)
      return
    }

    if (num <= medianLeft!!) {
      lessThan.add(num)
    }
    else {
      greaterThan.add(num)
    }

    if (lessThan.size == greaterThan.size) {
      return
    }
    else if (greaterThan.size > lessThan.size) {
      val least = greaterThan.least()
      greaterThan.remove(least!!)
      lessThan.add(least)
    }
    else if (lessThan.size - greaterThan.size >= 2) {
      val greatest = lessThan.greatest()
      lessThan.remove(greatest!!)
      greaterThan.add(greatest)
    }

  }

  fun findMedian(): Double {
    if (this.size() % 2 == 0) {
      return (lessThan.greatest()!!.toDouble() + greaterThan.least()!!.toDouble()) / 2.toDouble()
    }
    else {
      return lessThan.greatest()!!.toDouble()
    }
  }

  private fun size(): Int {
    return lessThan.size() + greaterThan.size()
  }

}

class FrequencyMap {
  val map: TreeMap<Int, Int> = TreeMap()
  var size = 0

  fun add(num: Int) {
    map.merge(num, 1) { cur, acc -> cur + acc }
    size++
  }

  fun remove(num: Int) {
    val frequency = map[num]
    if (frequency == 1) {
      map.remove(num)
    }
    else {
      map[num] = frequency!! - 1
    }
    size--
  }

  fun size(): Int {
    return this.size
  }

  fun greatest(): Int? {
    return map.lastKey()
  }

  fun least(): Int? {
    return map.firstKey()
  }
}

fun main(args: Array<String>) {
  val foo = MedianFinder()
  foo.addNum(-1)
  println(foo.findMedian())
  foo.addNum(-2)
  println(foo.findMedian())
  foo.addNum(-3)
  println(foo.findMedian())
  foo.addNum(-4)
  println(foo.findMedian())
  foo.addNum(-5)
  println(foo.findMedian())
}