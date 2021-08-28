package problems.problem0735

import java.util.*

class Solution {
  fun asteroidCollision(asteroids: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    for (asteroid in asteroids.reversed()) {
      val direction = if (asteroid < 0) Direction.LEFT else Direction.RIGHT
      if (direction == Direction.LEFT) {
        stack.push(asteroid)
      }
      else {
        if (stack.isEmpty()) {
          stack.push(asteroid)
          continue
        }
        var rightWasDestroyed = false
        while (stack.isNotEmpty()) {
          if (stack.peek() >= 0) {
            break
          }
          val leftwardAsteroid = stack.pop()
          val leftwardAsteroidSize = Math.abs(leftwardAsteroid)
          if (leftwardAsteroidSize == asteroid) {
            rightWasDestroyed = true
            break
          }
          else if (asteroid < leftwardAsteroidSize) {
            rightWasDestroyed = true
            stack.push(leftwardAsteroid)
            break
          }
          else {
            // rightward asteroid destroyed left
          }
        }
        if (!rightWasDestroyed) {
          stack.push(asteroid)
        }

      }
    }
    return stack.toIntArray()
  }
}

enum class Direction {
  LEFT,
  RIGHT
}