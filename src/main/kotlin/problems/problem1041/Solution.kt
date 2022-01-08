package problems.problem1041

class Solution {
  fun isRobotBounded(instructions: String): Boolean {
    var direction = Direction.UP
    var point = Point(0, 0)
    for (i in 0 until 4) {
      for (c in instructions) {
        if (c == 'G') {
          point = point.move(direction)
        }
        else {
          direction = direction.turn(c)
        }
      }
    }
    return point == Point(0, 0)
  }

}



data class Point(val x: Int, val y: Int) {
  fun move(direction: Direction): Point {
    return when (direction) {
      Direction.UP -> Point(x, y - 1)
      Direction.DOWN -> Point(x, y + 1)
      Direction.LEFT -> Point(x - 1, y)
      Direction.RIGHT -> Point(x + 1, y)
    }
  }
}
enum class Direction {
  UP {
    override fun turn(c: Char) = if (c == 'L') Direction.LEFT else Direction.RIGHT
  },
  DOWN {
    override fun turn(c: Char) = if (c == 'L') Direction.RIGHT else Direction.LEFT
  },
  LEFT {
    override fun turn(c: Char) = if (c == 'L') Direction.DOWN else Direction.UP
  },
  RIGHT {
    override fun turn(c: Char) = if (c == 'L') Direction.UP else Direction.DOWN
  };

  abstract fun turn(c: Char): Direction
}
