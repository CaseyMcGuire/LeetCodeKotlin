package problems.problem1105

class Solution {
  fun minHeightShelves(books: Array<IntArray>, shelfWidth: Int): Int {
    val bookList = books.map { Book(it[0], it[1]) }
    val cache = mutableMapOf<CacheKey, Int>()
    fun findMinHeight(i: Int, spaceLeft: Int, currentRowHeight: Int): Int {
      if (i == books.size) {
        return currentRowHeight
      }
      val book = bookList[i]
      val cacheKey = CacheKey(i, spaceLeft)
      val cachedValue = cache[cacheKey]
      if (cachedValue != null) {
        return cachedValue
      }
      val placeBookOnNextShelfHeight = currentRowHeight + findMinHeight(i + 1, shelfWidth - book.thickness, book.height)

      if (book.thickness <= spaceLeft) {
        val newRowHeight = Math.max(currentRowHeight, book.height)
        val putBookOnCurrentShelfHeight = findMinHeight(i + 1, spaceLeft - book.thickness, newRowHeight)
        val smaller = Math.min(placeBookOnNextShelfHeight, putBookOnCurrentShelfHeight)
        cache[cacheKey] = smaller
        return smaller
      }
      else {
        cache[cacheKey] = placeBookOnNextShelfHeight
        return placeBookOnNextShelfHeight
      }
    }
    return findMinHeight(0, shelfWidth, 0)
  }
}

data class Book(val thickness: Int, val height: Int)
data class CacheKey(val index: Int, val spaceLeft: Int)