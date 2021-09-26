package problems.problem0380

import java.util.*

class RandomizedSet() {

  /** Initialize your data structure here. */
  val set = mutableSetOf<Int>()
  val list = mutableListOf<Int>()


  /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
  fun insert(`val`: Int): Boolean {
    val added = set.add(`val`)
    if (added) {
      list.add(`val`)
    }
    return added
  }

  /** Removes a value from the set. Returns true if the set contained the specified element. */
  fun remove(`val`: Int): Boolean {
    val removed = set.remove(`val`)
    if (removed) {
      list.remove(`val`)
    }
    return removed
  }

  /** Get a random element from the set. */
  fun getRandom(): Int {
    val random = Random()
    val randomIndex = random.nextInt(list.size)
    return list[randomIndex]
  }

}

fun main(args: Array<String>) {
  val set = RandomizedSet()
  set.insert(1)
  set.remove(2)
  set.insert(2)
  set.getRandom()
  set.insert(1)
  set.remove(2)
  set.getRandom()
}