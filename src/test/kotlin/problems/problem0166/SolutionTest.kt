package problems.problem0166

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionTest {

  val solution = Solution()

  @Test
  fun `27 div 9`() {
    assertEquals("1.(421052631578947368)", solution.fractionToDecimal(27, 19))
  }

  @Test
  fun `1 div 6`() {
    assertEquals("0.1(6)", solution.fractionToDecimal(1, 6))
  }

  @Test
  fun `-1 div -2147483648`() {
    assertEquals("0.0000000004656612873077392578125", solution.fractionToDecimal(-1,-2147483648))
  }

  @Test
  fun `asf`() {
    assertEquals("-2147483648", solution.fractionToDecimal(-2147483648,1))
  }
}