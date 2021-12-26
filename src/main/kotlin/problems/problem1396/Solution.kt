package problems.problem1396

class UndergroundSystem() {

  private val idToStation = mutableMapOf<Int, StationCheck>()
  private val connectionToTravelTime = mutableMapOf<Connection, TravelTime>()

  fun checkIn(id: Int, stationName: String, t: Int) {
    val stationCheck = StationCheck(stationName, t)
    idToStation[id] = stationCheck
  }

  fun checkOut(id: Int, stationName: String, t: Int) {
    val start = idToStation[id]!!
    val connection = Connection(start.stationName, stationName)
    val existingTravelTime = connectionToTravelTime[connection] ?: TravelTime(0, emptyList())
    val nextTravelTime = existingTravelTime.getNext(t - start.time)
    connectionToTravelTime[connection] = nextTravelTime
    idToStation.remove(id)
  }

  fun getAverageTime(startStation: String, endStation: String): Double {
    val connection = Connection(startStation, endStation)
    return connectionToTravelTime[connection]!!.getAverage()
  }

}

data class StationCheck(val stationName: String, val time: Int)
data class Connection(val firstStation: String, val secondStation: String)
data class TravelTime(val numTrips: Int, val times: List<Int>) {
  fun getNext(time: Int): TravelTime {
    return TravelTime(numTrips + 1, times + listOf(time))
  }

  fun getAverage(): Double {
    return times.sum().toDouble() / numTrips.toDouble()
  }
}