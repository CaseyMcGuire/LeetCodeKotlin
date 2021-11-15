package problems.problem0157

class Solution : Reader4() {

  /**
   * @param buf Destination buffer
   * @param n Number of characters to read
   * @return The number of actual characters read
   */
  override fun read(buf:CharArray, n:Int): Int {
    var i = 0
    var buffer = CharArray(4)
    while (i < n) {
      val length = read4(buffer)
      if (length == 0) {
        break
      }

      for (j in 0 until length) {
        if (i == n) {
          break
        }
        buf[i] = buffer[j]
        i++
      }
    }
    return i
  }
}

open class Reader4 {
  fun read4(buf: CharArray): Int {return 1}
  open fun read(buf: CharArray, n: Int): Int { return 1}
}