package problems.problem2622

import java.util.TreeSet

/**
 * Write a class that allows getting and setting key-value pairs, however a time until expiration is associated with
 * each key.
 *
 * The class has three public methods:
 *
 * set(key, value, duration): accepts an integer key, an integer value, and a duration in milliseconds. Once the
 * duration has elapsed, the key should be inaccessible. The method should return true if the same un-expired key
 * already exists and false otherwise. Both the value and duration should be overwritten if the key already exists.
 *
 * get(key): if an un-expired key exists, it should return the associated value. Otherwise it should return -1.
 *
 * count(): returns the count of un-expired keys at the given timestamp
 */
// NOTE: I used Gemini to convert this to TypeScript
class TimeLimitedCache {

  private val keyToCacheItem = mutableMapOf<Int, CacheItem>()
  private val orderedCacheItems = TreeSet<CacheItem>(compareBy({ it.expirationTime }, { it.key }))

  fun set(key: Int, value: Int, duration: Long): Boolean {
    val existingCacheItem = keyToCacheItem[key]
    if (existingCacheItem != null) {
      orderedCacheItems.remove(existingCacheItem)
    }
    val cacheItem = CacheItem(key, value, getCurrentTime() + duration)
    keyToCacheItem[cacheItem.key] = cacheItem
    orderedCacheItems.add(cacheItem)
    if (existingCacheItem == null) {
      return false
    }
    else {
      return existingCacheItem.expirationTime > getCurrentTime()
    }
  }

  fun get(key: Int): Int {
    val cacheItem = keyToCacheItem[key]
    if (cacheItem == null) {
      return -1
    }
    val curTime = System.currentTimeMillis()
    if (cacheItem.expirationTime <= curTime) {
      keyToCacheItem.remove(key)
      orderedCacheItems.remove(cacheItem)
      return -1
    }
    return cacheItem.value
  }

  fun count(): Int {
    while (orderedCacheItems.isNotEmpty() && orderedCacheItems.first().expirationTime <= getCurrentTime()) {
      val first = orderedCacheItems.pollFirst()
      keyToCacheItem.remove(first.key)
    }
    return orderedCacheItems.size
  }

  fun getCurrentTime(): Long {
    return System.currentTimeMillis()
  }
}

data class CacheItem(val key: Int, val value: Int, val expirationTime: Long)