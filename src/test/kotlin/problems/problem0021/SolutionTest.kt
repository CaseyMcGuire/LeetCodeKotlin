package problems.problem0021

import datastructures.ListNode
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SolutionTest {

  private val solution = problems.problem0021.Solution()

  @Test
  fun `lists of same length`() {
    val first = ListNode.create(1,2,4)
    val second = ListNode.create(1,3,4)
    val expected = ListNode.create(1,1,2,3,4,4)
    Assertions.assertEquals(expected, solution.mergeTwoLists(first, second))
  }

  @Test
  fun `null lists` () {
    val first = ListNode.create(1,2,3)
    val second = null
    val expected = first
    Assertions.assertEquals(expected, solution.mergeTwoLists(first, second))
  }
}