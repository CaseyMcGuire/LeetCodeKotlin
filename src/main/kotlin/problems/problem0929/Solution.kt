package problems.problem0929

class Solution {
  fun numUniqueEmails(emails: Array<String>): Int {
    val uniqueEmails = HashSet<String>()
    for (email in emails) {
      val splitEmail = email.split("@")
      val localName = splitEmail[0]
      val domainName = splitEmail[1]
      val localNameFiltered = localName.split("+")[0].filter { it != '.' }
      uniqueEmails.add("$localNameFiltered@$domainName")
    }
    return uniqueEmails.size
  }
}