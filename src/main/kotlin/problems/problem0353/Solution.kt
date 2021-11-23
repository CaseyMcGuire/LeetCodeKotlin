package problems.problem0353

import java.util.*

class SnakeGame(val width: Int, val height: Int, food: Array<IntArray>) {

  val snake = LinkedList<Coordinate>()
  val snakeCoordinates = mutableSetOf<Coordinate>()
  val foodStack = ArrayDeque<Coordinate>()
  var isGameOver = false

  init {
    val initialCoordinate = Coordinate(0, 0)
    snake.addFirst(initialCoordinate)
    snakeCoordinates.add(initialCoordinate)
    food.map { Coordinate(it[0], it[1]) }.reversed().forEach { foodStack.push(it) }
  }

  fun move(direction: String): Int {
    if (isGameOver) {
      return -1
    }
    val next = getNextCoordinate(direction)
    // if we hit egdge of board, game is over
    if (next.i !in 0 until height || next.j !in 0 until width) {
      isGameOver = true
      return -1
    }
    val tail = snake.peekLast()
    // if we hit our body, game is over
    if (next != tail && snakeCoordinates.contains(next)) {
      isGameOver = true
      return -1
    }

    if (foodStack.isNotEmpty() && foodStack.peek() == next) {
      foodStack.pop()
      snake.addFirst(next)
      snakeCoordinates.add(next)
    }
    else {
      snake.addFirst(next)
      val last = snake.removeLast()
      snakeCoordinates.remove(last)
      snakeCoordinates.add(next)
    }
    return snake.size - 1
  }

  private fun getNextCoordinate(direction: String): Coordinate {
    val head = snake.peekFirst()
    val i = head.i
    val j = head.j

    return when (direction) {
      "U" -> Coordinate(i - 1, j)
      "D" -> Coordinate(i + 1, j)
      "L" -> Coordinate(i, j - 1)
      "R" -> Coordinate(i, j + 1)
      else -> throw RuntimeException("Invalid direction: $direction")
    }
  }

}

data class Coordinate(val i: Int, val j: Int)