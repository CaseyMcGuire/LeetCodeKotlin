package problems.problem1061

class Solution {
  fun smallestEquivalentString(s1: String, s2: String, baseStr: String): String {
    val map = mutableMapOf<Char, MutableSet<Char>>()
    for (i in 0 until s1.length) {
      val first = s1[i]
      val second = s2[i]
      val set1 = map.getOrDefault(first, mutableSetOf())
      val set2 = map.getOrDefault(second, mutableSetOf())
      val set = (set1 union set2).toMutableSet()
      set.add(first)
      set.add(second)
      for (c in set) {
        map[c] = set
      }
    }

    val orderMap = mutableMapOf<Char, Char>()
    for (entry in map.entries) {
      val equals = entry.value.toList().sorted().first()!!
      orderMap[entry.key] = equals
    }

    val builder = StringBuilder()
    for (i in 0 until baseStr.length) {
      val c = orderMap[baseStr[i]] ?: baseStr[i]
      builder.append(c)
    }
    return builder.toString()
  }
}