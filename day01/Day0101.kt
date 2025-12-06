import java.io.File

fun main() {
    val input = File("input.txt").readLines()

    var currentPos = 50  // Start in the middle of the dial (0–99)
    var countZero = 0

    var numLines = 0
    for (line in input) {
        numLines++
        println("currentPos: $currentPos")
        currentPos = computeTurn(currentPos, line)
        println("After turn $line, currentPos: $currentPos")
        if (currentPos == 0) {
            // println("Hit zero on turn: $line")
            countZero++
        }
    }

    println("Answer: $countZero")
    // println("numLines: $numLines")
}

//     99 0
// 	75		25
//		50
// turn example: L68, R30 (direction + length)
// value can be > 99
// L will shift downward. R upward
private fun computeTurn(startingPos: Int, turnStr: String): Int {
    val turn = turnStr.convertToTurn()
   // println(turn)

    val finalLength: Int = turn.length % 100
   // println("finalLength: $finalLength")

    when (turn.direction) {
        'R'-> return (startingPos + finalLength) % 100
        'L'-> return (startingPos + 100 - finalLength) % 100
        else -> return 0
    }

}

data class Turn(var direction: Char, var length: Int = 0)

fun String.convertToTurn(): Turn {
    val direction: Char
    val length: Int

    try {
        direction = this[0]
        length = this.substring(1).toInt()
    } catch (e: Exception) {
        return Turn(direction = 'L', length = 0)
    }
    return Turn(direction, length)
}