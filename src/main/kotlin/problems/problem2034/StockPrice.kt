package problems.problem2034

import java.util.TreeMap

class StockPrice() {

  private val priceToFrequency = TreeMap<Int, Int>()
  private val timestampToPrice = TreeMap<Int, Int>()

  fun update(timestamp: Int, price: Int) {
    val existingPrice = timestampToPrice[timestamp]
    if (existingPrice == price) {
      return
    }
    else if (existingPrice != null) {
      priceToFrequency.decrement(existingPrice)
    }
    timestampToPrice[timestamp] = price
    priceToFrequency.increment(price)
  }

  fun current(): Int {
    return timestampToPrice.lastEntry().value
  }

  fun maximum(): Int {
    return priceToFrequency.lastKey()
  }

  fun minimum(): Int {
    return priceToFrequency.firstKey()
  }

  private fun TreeMap<Int, Int>.increment(price: Int) {
    this.merge(price, 1) { cur, acc -> cur + acc }
  }

  private fun TreeMap<Int, Int>.decrement(price: Int) {
    val frequency = this[price]
      ?: return
    if (frequency == 1) {
      this.remove(price)
    }
    else {
      this[price] = frequency - 1
    }
  }

}