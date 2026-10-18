package problems.problem1109

import java.util.PriorityQueue

class Solution {
  fun corpFlightBookings(bookings: Array<IntArray>, n: Int): IntArray {
    val seats = IntArray(n) { 0 }
    if (bookings.isEmpty()) {
      return seats
    }
    val runningBookings = PriorityQueue<IntArray>(compareBy { it[1] })
    val pendingBookings = PriorityQueue<IntArray>(compareBy { it[0] })
    bookings.forEach { pendingBookings.add(it) }
    var i = 0
    var numSeats = 0
    while (runningBookings.isNotEmpty() || pendingBookings.isNotEmpty()) {
      if (runningBookings.isEmpty()) {
        i = pendingBookings.peek()[0]
      }

      while (pendingBookings.isNotEmpty() && pendingBookings.peek()[0] == i) {
        val next = pendingBookings.poll()
        runningBookings.add(next)
        numSeats += next[2]
      }

      seats[i - 1] = numSeats
      i++

      while (runningBookings.isNotEmpty() && runningBookings.peek()[1] < i) {
        numSeats -= runningBookings.poll()[2]
      }
    }
    return seats
  }
}