package problems.problem1672

class Solution {
  fun maximumWealth(accounts: Array<IntArray>): Int {
    return accounts.map { it.sum() }.max()!!
  }
}