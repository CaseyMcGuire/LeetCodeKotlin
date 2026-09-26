package problems.problem0631

class Excel(height: Int, width: Char) {

  private val sheet = mutableMapOf<Coordinate, CellValue>()

  fun set(row: Int, column: Char, value: Int) {
    val coordinate = Coordinate(row, column.toAlphabetIndex())
    sheet[coordinate] = CellValue.Scalar(value)
  }

  fun get(row: Int, column: Char): Int {
    val startingCoordinate = Coordinate(row, column.toAlphabetIndex())
    if (!sheet.containsKey(startingCoordinate)) {
      return 0
    }
    val cache = mutableMapOf<Coordinate, Int>()

    fun recurse(coordinate: Coordinate): Int {
      val value = cache[coordinate]
      if (value != null) {
        return value
      }

      val cellValue = sheet[coordinate]
        ?: return 0
      val total = when (cellValue) {
        is CellValue.Scalar -> cellValue.value
        is CellValue.Formula ->
          cellValue.coordinateToFrequency.map { it.value * recurse(it.key) }.sum()
      }
      cache[coordinate] = total
      return total
    }

    return recurse(startingCoordinate)
  }

  fun sum(row: Int, column: Char, numbers: Array<String>): Int {
    val coordinateToFrequency = mutableMapOf<Coordinate, Int>()
    for (number in numbers) {
      val (start, end) = getStartAndEndCoordinate(number)

      for (i in start.i..end.i) {
        for (j in start.j..end.j) {
          coordinateToFrequency.merge(Coordinate(i, j), 1) { cur, acc -> cur + acc }
        }
      }
    }

    val coordinate = Coordinate(row, column.toAlphabetIndex())
    sheet[coordinate] = CellValue.Formula(coordinateToFrequency)
    return get(row, column)
  }

  private fun getStartAndEndCoordinate(number: String): Pair<Coordinate, Coordinate> {
    if (!number.contains(":")) {
      val coordinate = cellToCoordinate(number)
      return Pair(coordinate, coordinate)
    }
    else {
      val (topLeft, bottomRight) = number.split(":")
      return Pair(
        cellToCoordinate(topLeft),
        cellToCoordinate(bottomRight)
      )
    }
  }

  private fun cellToCoordinate(cell: String): Coordinate {
    if (cell.length == 2) {
      return Coordinate(cell[1].digitToInt(), cell[0].toAlphabetIndex())
    }
    else {
      return Coordinate(cell.substring(1, cell.length).toInt(), cell[0].toAlphabetIndex())
    }
  }

  private fun Char.toAlphabetIndex(): Int {
    return this - 'A'
  }

  sealed interface CellValue {
    data class Scalar(val value: Int): CellValue
    data class Formula(val coordinateToFrequency: Map<Coordinate, Int>): CellValue
  }

  data class Coordinate(val i: Int, val j: Int)
}