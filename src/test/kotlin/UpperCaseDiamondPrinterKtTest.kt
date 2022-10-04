import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

const val A = 'A'
const val UNDERSCORE = '_'
const val EMPTY = ""

internal class UpperCaseDiamondPrinterKtTest {
    private var upperCaseDiamondPrinter: DiamondPrinter = UpperCaseDiamondPrinter()
    private var upperCaseCharRegex = Regex("[A-Z]")

    @TestFactory
    fun `given an invalid input char an exception expected`(): List<DynamicTest> {
        val dynamicTests = mutableListOf<DynamicTest>()
        ('a'..'z').forEach { inputChar ->
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, an exception is expected") {
                assertThrows(java.lang.IllegalArgumentException::class.java) {
                    upperCaseDiamondPrinter.printDiamond(inputChar)
                }
            })
        }
        return dynamicTests
    }

    @Test
    fun `given A as input param returns just A`() {
        val diamondA = upperCaseDiamondPrinter.printDiamond(A)
        assertEquals("A", diamondA)
    }

    @TestFactory
    fun `given range B to Z has to return fine diamond`(): List<DynamicTest> {
        val dynamicTests = mutableListOf<DynamicTest>()
        ('B'..'Z').forEach { inputChar ->
            val diamond = upperCaseDiamondPrinter.printDiamond(inputChar)
            val resultArray = diamond.split("\n")
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, row has to be with equal length") {
                resultArray.forEach { assertTrue(resultArray.first().length == it.length) }
            })
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, diamond has to be square") {
                val rowLength = resultArray.first().length
                resultArray.forEach { assertTrue(rowLength == it.length) }
            })
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, is vertically symmetrical") {
                assertEquals(resultArray, resultArray.reversed())
            })
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, is horizontally symmetrical") {
                resultArray.forEach {
                    assertEquals(it, it.reversed())
                }
            })
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, contains expected line numbers") {
                val expectedLineNumber = (inputChar - A) * 2 + 1
                assertEquals(resultArray.size, expectedLineNumber)
            })
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, first row must contains only single char A") {
                assertTrue(resultArray.first().count { it == A } == 1)
            })
            dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, last row must contains only single char A") {
                assertTrue(resultArray.last().count { it == A } == 1)
            })

            (A..inputChar).forEach { rowChar ->
                val row = rowChar - A
                val column = inputChar - rowChar
                dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char, has a $rowChar at row #$row and column #$column") {
                    assertEquals(rowChar, resultArray[row][column])
                })
                dynamicTests.add(DynamicTest.dynamicTest("given $inputChar input char verify that has nothing but $UNDERSCORE in row") {
                    resultArray.forEach {
                        assertTrue(upperCaseCharRegex.replace(it, EMPTY).all { it == UNDERSCORE })
                    }
                })
            }


        }
        return dynamicTests
    }
}