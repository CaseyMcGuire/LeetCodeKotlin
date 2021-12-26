package problems.problem0811

class Solution {
  fun subdomainVisits(cpdomains: Array<String>): List<String> {
    val domains = mutableListOf<Domain>()
    for (domain in cpdomains) {
      val split = domain.split(" ")
      val count = split[0].toInt()
      val subdomains = split[1].split(".")
      domains.add(Domain(count, subdomains))
    }

    val subdomainFrequency = mutableMapOf<String, Int>()
    for (domain in domains) {
      val subdomains = domain.subdomains
      for (i in subdomains.indices) {
        val curSubdomain = subdomains.subList(i, subdomains.size).joinToString(".")
        subdomainFrequency.merge(curSubdomain, domain.count) { cur, acc -> cur + acc }
      }
    }

    val visits = mutableListOf<String>()
    for (entry in subdomainFrequency.entries) {
      val subdomain = entry.key
      val count = entry.value
      visits.add("$count $subdomain")
    }

    return visits
  }
}

data class Domain(val count: Int, val subdomains: List<String>)