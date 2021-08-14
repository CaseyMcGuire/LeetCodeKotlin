package problems.problem0489

class Solution {
  fun cleanRoom(robot: Robot) {
    val visited = mutableSetOf<Coordinate>()
    var currentDirection = Direction.UP

    fun turnToDirection(desiredDirection: Direction) {
      robot.turnRight()
      currentDirection = when(currentDirection) {
        Direction.UP -> Direction.RIGHT
        Direction.RIGHT -> Direction.DOWN
        Direction.DOWN -> Direction.LEFT
        Direction.LEFT -> Direction.UP
      }
      if (currentDirection != desiredDirection) {
        turnToDirection(desiredDirection)
      }
    }

    fun dfs(currentCoordinate: Coordinate, startingDirection: Direction) {
      if (!visited.add(currentCoordinate)) {
        turnToDirection(getOppositeDirection(startingDirection))
        robot.move()
        return
      }
      robot.clean()
      val backDirection = getOppositeDirection(startingDirection)
      val neighboringMovements = getNeighboringMovements(currentCoordinate)
      for (movement in neighboringMovements) {
        turnToDirection(movement.direction)
        // hit a wall
        if (!robot.move()) {
          visited.add(movement.coordinate)
        }
        else {
          dfs(movement.coordinate, movement.direction)
        }
      }
      turnToDirection(backDirection)
      robot.move()
    }

    dfs(Coordinate(0, 0), currentDirection)
  }

  private fun getOppositeDirection(direction: Direction): Direction {
    return when (direction) {
      Direction.UP -> Direction.DOWN
      Direction.LEFT -> Direction.RIGHT
      Direction.RIGHT -> Direction.LEFT
      Direction.DOWN -> Direction.UP
    }
  }
}

private fun getNeighboringMovements(coordinate: Coordinate): List<Movement> {
  val i = coordinate.i
  val j = coordinate.j
  return listOf(
    Movement(Coordinate(i + 1, j), Direction.DOWN),
    Movement(Coordinate(i - 1, j), Direction.UP),
    Movement(Coordinate(i, j + 1), Direction.RIGHT),
    Movement(Coordinate(i, j - 1), Direction.LEFT)
  )
}



data class Movement(val coordinate: Coordinate, val direction: Direction)

data class Coordinate(val i: Int, val j: Int)

enum class Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT
}

interface Robot {
  fun move(): Boolean
  fun turnLeft()
  fun turnRight()
  fun clean()
}