package algorithms

import leetcode.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class LeetcodeKtTest {

    companion object {
        val bracesPatterns = hashMapOf(
            "(test)" to true,
            ")string(" to false,
            "(())" to true,
            "()()" to true,
            "(({}[()]))" to true,
            "[]{}" to true,
            "(()" to false,
            "(]" to false
        )
    }

    @TestFactory
    fun revBracesTest() = bracesPatterns.map { (key, value) ->
        DynamicTest.dynamicTest("Testing for pattern $key") {
            assertEquals(revBraces(key), value)
        }
    }

    @TestFactory
    fun isPalindromeTest() = hashMapOf(
        121 to true, 250052 to true, 122 to false, 1 to true, 96 to false
    ).map { (digit, result) ->
        DynamicTest.dynamicTest("Test for $digit") {
            assertEquals(isPalindrome(digit), result)
        }
    }


    @Test
    fun reverseArrayTest() {
        val arr = arrayOf(30, 50, 90, 150, 250, 320)
        assertTrue(
            reverseArray(arr).contentEquals(arrayOf(320, 250, 150, 90, 50, 30))
        )
    }

    @Test
    fun fibonacciTest() {
        assertAll(
            { assertTrue(fibonacci(1).last() == 0) },
            { assertTrue(fibonacci(2).last() == 1) },
            { assertTrue(fibonacci(9).last() == 34) },
            { assertTrue(fibonacci(13).last() == 233) }
        )
    }

    @Test
    fun factorialTest() {
        assertAll(
            { assertTrue(factorial(1) == 1) },
            { assertTrue(factorial(2) == 2) },
            { assertTrue(factorial(5) == 120) },
            { assertTrue(factorial(10) == 3628800) }
        )
    }
}