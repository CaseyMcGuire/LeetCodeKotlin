package problems.problem1094

import java.util.PriorityQueue

class Solution {
  fun carPooling(tripArr: Array<IntArray>, capacity: Int): Boolean {
    val trips = tripArr.map { Trip(it[0], it[1], it[2]) }.sortedWith(compareBy({ it.from }, { it.to }))

    val activeTrips = PriorityQueue<Trip>(compareBy({ it.to }))
    var curPassengers = 0
    for (trip in trips) {
      while (activeTrips.isNotEmpty() && activeTrips.peek().to <= trip.from) {
        val endedTrip = activeTrips.poll()
        curPassengers -= endedTrip.numPassengers
      }
      curPassengers += trip.numPassengers
      if (curPassengers > capacity) {
        return false
      }
      activeTrips.add(trip)
    }
    return true
  }
}

data class Trip(val numPassengers: Int, val from: Int, val to: Int)