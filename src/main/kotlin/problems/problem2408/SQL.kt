package problems.problem2408

class SQL(names: List<String>, columns: List<Int>) {

  private val nameToTable = mutableMapOf<String, Table>()

  init {
    for (i in names.indices) {
      nameToTable[names[i]] = Table(columns[i])
    }
  }

  fun ins(name: String, row: List<String>): Boolean {
    val table = nameToTable[name]
      ?: return false
    return table.insert(row)
  }

  fun rmv(name: String, rowId: Int) {
    val table = nameToTable[name]
      ?: return
    table.remove(rowId)
  }

  fun sel(name: String, rowId: Int, columnId: Int): String {
    return nameToTable[name]?.get(rowId)?.get(columnId - 1)
      ?: "<null>"
  }

  fun exp(name: String): List<String> {
    val table = nameToTable[name]
      ?: return emptyList()
    return table.getRowsAsString()
  }

  class Table(val numColumns: Int) {
    private val idToRow = mutableMapOf<Int, Row>()
    private var curId = 1

    fun insert(cols: List<String>): Boolean {
      if (cols.size != numColumns) {
        return false
      }
      idToRow[curId] = Row(cols)
      curId++
      return true
    }

    fun remove(rowId: Int) {
      idToRow.remove(rowId)
    }

    fun get(rowId: Int): Row? {
      return idToRow[rowId] ?: null
    }

    fun getRowsAsString(): List<String> {
      val rows = mutableListOf<String>()
      for (entry in idToRow) {
        rows.add("${entry.key},${entry.value.toString()}")
      }
      return rows
    }
  }

  class Row(val cols: List<String>) {
    fun get(colIndex: Int): String? {
      return cols.getOrNull(colIndex)
    }

    override fun toString(): String {
      return cols.joinToString(",")
    }
  }
}