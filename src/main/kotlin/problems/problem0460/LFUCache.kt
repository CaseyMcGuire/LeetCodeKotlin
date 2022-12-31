package problems.problem0460

import java.util.*

class LFUCache(val capacity: Int) {

  // map key -> (value, hits, time, key)
  //
  private val map = mutableMapOf<Int, Item>()
  private val orderedItems = TreeSet<Item>(compareBy({it.hits}, {it.time}))
  private var time = 0

  fun get(key: Int): Int {

    val item = map[key] ?: return -1
    if(!orderedItems.remove(item)) {
      throw RuntimeException()
    }
    val newItem = Item(key, item.value, time, item.hits + 1)
    orderedItems.add(newItem)
    map[key] = newItem
    time++
    return newItem.value
  }

  fun put(key: Int, value: Int) {
    if (capacity == 0) {
      return
    }
    val item = map[key]
    if (map.size == capacity && item == null) {
      val lfu = orderedItems.pollFirst()
      if (map.remove(lfu.key) == null) {
        throw RuntimeException()
      }
    }
    else if (item != null) {
      orderedItems.remove(item)
    }
    val newItem = Item(key, value, time, (item?.hits ?: 0) + 1)
    orderedItems.add(newItem)
    map[key] = newItem

    time++
  }

}
data class Item(val key: Int, val value: Int, val time: Int, val hits: Int)

