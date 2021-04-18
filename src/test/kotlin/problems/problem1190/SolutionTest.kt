package problems.problem1190

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SolutionTest {

  private val solution = problems.problem1190.Solution()

  @Test
  fun `reverse parens`() {
    Assertions.assertEquals("apbq", solution.reverseParentheses("a(bp)q"))

  }
}