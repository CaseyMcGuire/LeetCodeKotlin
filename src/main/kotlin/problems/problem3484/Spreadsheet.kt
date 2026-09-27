package problems.problem3484

class Spreadsheet(rows: Int) {

  private val cellToValue = mutableMapOf<String, Int>()

  fun setCell(cell: String, value: Int) {
    cellToValue[cell] = value
  }

  fun resetCell(cell: String) {
    cellToValue.remove(cell)
  }

  fun getValue(formula: String): Int {
    val (first, second) = formula.substring(1, formula.length).split("+")

    return first.asScalarOrCellValue() +
        second.asScalarOrCellValue()
  }

  private fun String.asScalarOrCellValue(): Int {
    return this.toIntOrNull()
      ?: cellToValue[this]
      ?: 0
  }

}
