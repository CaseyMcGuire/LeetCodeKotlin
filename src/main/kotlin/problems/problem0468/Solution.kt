package problems.problem0468

import java.lang.NumberFormatException

class Solution {
  fun validIPAddress(IP: String): String {
    val ipv4 = IP.split(".")
    if (isValidIpv4(ipv4)) {
      return "IPv4"
    }
    val ipv6 = IP.split(":")
    if (isValidIpv6(ipv6)) {
      return "IPv6"
    }
    return "Neither"
  }

  private fun isValidIpv4(sections: List<String>): Boolean {
    if (sections.size != 4) {
      return false
    }
    for (section in sections) {
      val containsExtraneousLeadingZero = section.length > 1 && section[0] == '0'
      if (containsExtraneousLeadingZero) {
        return false
      }
      val number = try { section.toInt() } catch (_: NumberFormatException) { return false }
      if (number > 255 || number < 0) {
        return false
      }
    }
    return true
  }

  private fun isValidIpv6(sections: List<String>): Boolean {
    if (sections.size != 8) {
      return false
    }
    for (section in sections) {
      if (section.length > 4 || section.isEmpty()) {
        return false
      }
      for (c in section.toCharArray()) {
        if (c in 'a'..'f') {
          continue
        }
        if (c in 'A'..'F') {
          continue
        }
        if (!c.isDigit()) {
          return false
        }
      }
    }
    return true
  }
}