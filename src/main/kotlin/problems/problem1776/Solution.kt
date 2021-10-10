package problems.problem1776

import java.util.*

class Solution {
  fun getCollisionTimes(cars: Array<IntArray>): DoubleArray {
    if (cars.isEmpty()) {
      return DoubleArray(0)
    }
    val carList = cars.mapIndexed { index, elem -> Car(elem[0], elem[1], index) }
    val collisionTime = DoubleArray(cars.size).map { -1.0 }.toDoubleArray()
    val stack = ArrayDeque<Car>()
    for (i in carList.indices.reversed()) {
      val currentCar = carList[i]

      while (stack.isNotEmpty()) {
        val frontCar = stack.pop()
        // find all cars ahead of this one that are slower
        if (frontCar.speed >= currentCar.speed) {
          continue
        }
        // if we find a slower car but it has already hit a car before this one hits it, we have to keep going to the next car
        val currentCarCollisionTime = currentCar.getCollision(frontCar)
        val frontCarCollisionTime = collisionTime[frontCar.index]
        if (currentCarCollisionTime <= frontCarCollisionTime || frontCarCollisionTime == -1.0) {
          collisionTime[currentCar.index] = currentCarCollisionTime
          stack.push(frontCar)
          break
        }
      }
      stack.push(currentCar)
    }
    return collisionTime
  }
}

data class Car(val position: Int, val speed: Int, val index: Int) {
  fun getCollision(other: Car): Double {
    val (leftCar, rightCar) = if (other.position < this.position) Pair(other, this) else Pair(this, other)
    val relativeSpeed = leftCar.speed - rightCar.speed
    val distance = rightCar.position - leftCar.position
    val collisionTime = distance.toDouble() / relativeSpeed.toDouble()
    return collisionTime
  }
}