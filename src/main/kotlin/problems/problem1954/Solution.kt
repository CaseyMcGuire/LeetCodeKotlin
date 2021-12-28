package problems.problem1954

import java.math.BigInteger
import java.util.*

class Solution {
  fun maxProductPath(grid: Array<IntArray>): Int {
    val productGrid = Array<Array<PathPair>>(grid.size) { Array<PathPair>(grid[0].size) { PathPair(BigInteger("0"), BigInteger("0")) } }
    val queue = LinkedList<Coordinate>()
    val visited = mutableSetOf<Coordinate>()
    productGrid[0][0] = PathPair(BigInteger(grid[0][0].toString()), BigInteger(grid[0][0].toString()))
    queue.addLast(Coordinate(1, 0))
    queue.addLast(Coordinate(0, 1))
    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      if (!isInBounds(cur, grid)) {
        continue
      }
      if (!visited.add(cur)) {
        continue
      }

      val currentElement = BigInteger(grid[cur.i][cur.j].toString())
      val left = cur.getLeft()
      val products = mutableListOf<BigInteger>()
      if (isInBounds(left, grid)) {
        val prev = productGrid[left.i][left.j]
        products.add(prev.first.multiply(currentElement))
        products.add(prev.second.multiply(currentElement))
      }

      val top = cur.getTop()
      if (isInBounds(top, grid)) {
        val prev = productGrid[top.i][top.j]
        products.add(prev.first.multiply(currentElement))
        products.add(prev.second.multiply(currentElement))
      }
      products.sort()
      productGrid[cur.i][cur.j] = PathPair(products[0], products[products.size - 1])
      queue.addLast(cur.getRight())
      queue.addLast(cur.getDown())
    }

    val modulo = BigInteger("10").pow(9).add(BigInteger("7"))
    val last = productGrid[productGrid.size - 1][productGrid[0].size - 1]
    val first = last.first
    val second = last.second
    if (first.isNegative() && second.isNegative()) {
      return -1
    }

    val firstMod = first.mod(modulo).toInt()
    val secondMod = second.mod(modulo).toInt()

    if (first.isNegative()) {
      return secondMod
    }
    else if (second.isNegative()) {
      return firstMod
    }
    else {
      return Math.max(firstMod, secondMod)
    }
  }

  private fun isInBounds(coordinate: Coordinate, grid: Array<IntArray>): Boolean {
    val rowRange = 0 until grid.size
    val columnRange = 0 until grid[0].size
    return coordinate.i in rowRange && coordinate.j in columnRange
  }

  private fun BigInteger.isNegative(): Boolean {
    return this.signum() == -1
  }
}

data class PathPair(val first: BigInteger, val second: BigInteger)
data class Coordinate(val i: Int, val j: Int) {
  fun getRight(): Coordinate = Coordinate(i, j + 1)
  fun getDown(): Coordinate = Coordinate(i + 1, j)
  fun getLeft(): Coordinate = Coordinate(i, j - 1)
  fun getTop(): Coordinate = Coordinate(i - 1, j)
}