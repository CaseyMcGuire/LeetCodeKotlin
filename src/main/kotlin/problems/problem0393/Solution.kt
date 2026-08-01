package problems.problem0393

class Solution {
  fun validUtf8(data: IntArray): Boolean {
    var bytesRemaining = 0
    for (byte in data) {
      if (bytesRemaining == 0) {
        val numBytes = getNumBytes(byte)
          ?: return false
        bytesRemaining = numBytes - 1
      }
      else {
        if (getNumLeadingOnes(byte) != 1) {
          return false
        }
        bytesRemaining--
      }
    }
    return bytesRemaining == 0
  }

  private fun getNumLeadingOnes(byte: Int): Int {
    var curByte = byte
    var numOnes = 0
    while (true) {

      val isLeadingOne = (curByte and 128) == 128
      if (!isLeadingOne) {
        break
      }
      numOnes++
      curByte = curByte shl 1
    }
    return numOnes
  }

  private fun getNumBytes(byte: Int): Int? {
    val numLeadingOnes = getNumLeadingOnes(byte)
    if (numLeadingOnes == 0) {
      return 1
    }
    else if (numLeadingOnes in (2..4)) {
      return numLeadingOnes
    }
    else {
      return null
    }
  }

}