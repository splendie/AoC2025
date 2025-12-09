import java.io.File

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

private fun getTwoDigitMaxNumber(s: String): Int {
    var firstMax = 0
    var idxOfFirstDigit = 0

    s.forEachIndexed {i, char ->
        val c = char.toString().toInt()
        if (i < s.length - 1) {
            if (c > firstMax) { // can't go to the end
                firstMax = c
                idxOfFirstDigit = i
            }

        }
    }

    var secondMax = 0

    for (i in idxOfFirstDigit+1 until s.length) {
        val c = s[i].toString().toInt()
        if (c > secondMax) secondMax = c
    }
    return firstMax*10 + secondMax
}