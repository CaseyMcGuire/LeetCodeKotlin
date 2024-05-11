package problems.problem0419

class Solution {
  fun countBattleships(board: Array<CharArray>): Int {
    if (board.isEmpty() || board[0].isEmpty()) {
      return 0
    }
    val visited = mutableSetOf<Coordinate>()
    fun traverse(c: Coordinate, d: Direction) {
      if (c.i !in 0 until board.size ||
        c.j !in 0 until board[c.i].size ||
        board[c.i][c.j] != 'X') {
        return
      }

      visited.add(c)
      when (d) {
        Direction.UP -> traverse(Coordinate(c.i - 1, c.j), d)
        Direction.DOWN -> traverse(Coordinate(c.i + 1, c.j), d)
        Direction.RIGHT -> traverse(Coordinate(c.i, c.j + 1), d)
        Direction.LEFT -> traverse(Coordinate(c.i, c.j - 1), d)
      }
    }

    var totalBattleships = 0
    for (i in board.indices) {
      for (j in board[i].indices) {
        val c = Coordinate(i, j)
        if (visited.contains(c)) {
          continue
        }
        if (board[i][j] == 'X') {

          totalBattleships++
          Direction.values().forEach { traverse(c, it) }
        }
      }
    }
    return totalBattleships
  }
}

data class Coordinate(val i: Int, val j: Int)
enum class Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT
}