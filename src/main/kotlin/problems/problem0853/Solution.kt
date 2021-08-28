package problems.problem0853

import java.util.*

class Solution {
  fun carFleet(target: Int, position: IntArray, speed: IntArray): Int {
    val sortedFleets =
      position.indices
        .map { CarFleet(position[it], speed[it], (target - position[it]).toDouble() / speed[it].toDouble()) }
        .sortedBy {it.position}

    val fleetStack = ArrayDeque<CarFleet>()
    sortedFleets.forEach { fleetStack.push(it) }

    var numFleetsHitTarget = 0
    while (fleetStack.isNotEmpty()) {
      var currentFleet = fleetStack.pop()
      while (fleetStack.isNotEmpty()) {
        var prevFleet = fleetStack.peek()
        if (prevFleet.timeAtTarget <= currentFleet.timeAtTarget) {
          // merge fleets
          fleetStack.pop()
        }
        else {
          break
        }
      }
      numFleetsHitTarget++
    }
    return numFleetsHitTarget
  }
}

data class CarFleet(val position: Int, val speed: Int, val timeAtTarget: Double)

fun main(args: Array<String>) {
  println(Solution().carFleet(12, intArrayOf(10,8,0,5,3), intArrayOf(2,4,1,1,3)))
  println(Solution().carFleet(10, intArrayOf(6,8), intArrayOf(3,2)))

}