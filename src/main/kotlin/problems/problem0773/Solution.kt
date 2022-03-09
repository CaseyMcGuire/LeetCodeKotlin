package problems.problem0773

import java.util.*

class Solution {
  fun slidingPuzzle(board: Array<IntArray>): Int {
    val queue = LinkedList<BoardState>()
    val visited = mutableSetOf<String>()
    val solvedState = "123450"

    queue.addLast(BoardState(board, 0))

    while (queue.isNotEmpty()) {
      val cur = queue.removeFirst()
      val boardStr = cur.state.asString()
      if (boardStr == solvedState) {
        return cur.numMoves
      }

      if (!visited.add(boardStr)) {
        continue
      }

      val nextStates = getNextConfigurations(cur)
      for (state in nextStates) {
        queue.addLast(state)
      }
    }
    return -1
  }

  private fun getNextConfigurations(boardState: BoardState): List<BoardState> {
    val board = boardState.state
    var zeroCoordinate = Coordinate(0, 0)
    for (i in board.indices) {
      for (j in board[i].indices) {
        val curNum = boardState.state[i][j]
        if (curNum == 0) {
          zeroCoordinate = Coordinate(i, j)
          break
        }
      }
    }

    val neighbors = zeroCoordinate.getNeighbors()
    val boardStates = mutableListOf<BoardState>()
    for (neighbor in neighbors) {
      if (neighbor.i !in board.indices || neighbor.j !in board[neighbor.i].indices) {
        continue
      }
      val nextBoard = copyAndSwap(boardState.state, neighbor, zeroCoordinate)
      boardStates.add(BoardState(nextBoard, boardState.numMoves + 1))
    }
    return boardStates
  }

  private fun copyAndSwap(arr: Array<IntArray>, first: Coordinate, second: Coordinate): Array<IntArray> {
    val copy = Array<IntArray>(2) { IntArray(3) }
    for (i in arr.indices) {
      for (j in arr[i].indices) {
        copy[i][j] = arr[i][j]
      }
    }

    val temp = copy[first.i][first.j]
    copy[first.i][first.j] = copy[second.i][second.j]
    copy[second.i][second.j] = temp
    return copy
  }

  private fun Array<IntArray>.asString(): String {
    val builder = StringBuilder()
    for (arr in this) {
      for (num in arr) {
        builder.append(num)
      }
    }
    return builder.toString()
  }
}

data class BoardState(val state: Array<IntArray>, val numMoves: Int)
data class Coordinate(val i: Int, val j: Int) {
  fun getNeighbors(): List<Coordinate> {
    return listOf(
      Coordinate(i + 1, j),
      Coordinate(i - 1, j),
      Coordinate(i, j + 1),
      Coordinate(i, j - 1)
    )
  }
}