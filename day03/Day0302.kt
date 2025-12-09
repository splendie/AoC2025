import java.io.File
import java.math.BigInteger

fun main() {
    val input = File("input.txt").readLines()
    var total = 0
    for (line in input) {
        val maxJoltage = getTwoDigitMaxNumber(line)
        println(maxJoltage)
        total += maxJoltage
    }

    println("Total joltage: $total")
}

private fun getTwelveDigitMaxNumber(s: String): BigInteger {
    var firstMax = 0
    var idxOfFirstDigit = 0

    val sb = StringBuilder()

    s.forEachIndexed {i, char ->
        val c = char.toString().toInt()
        if (i < s.length - 1) {
            if (c > firstMax) { // can't go to the end
                firstMax = c
                idxOfFirstDigit = i
                sb.append(char)
            }
        }
    }

    var curMax = 0
    var curIdx = idxOfFirstDigit + 1

    for (i in 1..11) {
        for (idx in curIdx until s.length) {
            val c = s[i].toString().toInt()
            if (i < 11 && idx < s.length - 1) {
                if (c > curMax) {
                    curMax = c
                    sb.append(s[idx])
                }
            } else if (i == 11) {
                if (c > curMax) {
                    curMax = c
                    sb.append(s[idx])
                }
            }
        }
    }

    return BigInteger(sb.toString())
}