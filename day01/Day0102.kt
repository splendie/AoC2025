import java.io.File

fun testSuspiciousCases() {
    val tests = listOf(
        Triple(0,  "R100", "Test 1"),
        Triple(0,  "R200", "Test 2"),
        Triple(0,  "R250", "Test 3"),
        Triple(99, "R201", "Test 4"),
        Triple(99, "R101", "Test 5"),
        Triple(1,  "R199", "Test 6"),
        Triple(1,  "R100", "Test 7"),
        Triple(50, "R250", "Test 8")
    )

    var savedVerbose = true // enable your prints

    println("==== ZERO CROSSING TESTS ====")
    for ((start, turn, label) in tests) {
        var currentPos = start
        val (newPos, during) = computeTurn(currentPos, turn)

        println("$label: start=$start turn=$turn")
        println("  during zero hits: $during")

        val final = if (newPos == 0) 1 else 0
        println("  final zero hit:   $final")
        println("  total:            ${during + final}")
        println()
    }
}

fun main() {
    testSuspiciousCases()
//    val input = File("fakeinput.txt").readLines()
//
//    var currentPos = 0  // Start in the middle of the dial (0–99)
//    println("currentPos: $currentPos")
//    var countZero = 0
//
//    var numLines = 0
//    for (line in input) {
//        numLines++
//
//        val result = computeTurn(currentPos, line)
//        currentPos = result.first
//        println("After turn $line, currentPos: $currentPos, wraps: ${result.second}")
//        countZero += result.second
//        if (currentPos == 0) {
//            println("Hit zero")
//            countZero++
//        }
//    }
//
//    println("Answer: $countZero")
}

//     99 0
// 	75		25
//		50
// turn example: L68, R30 (direction + length)
// value can be > 99
// L will shift downward. R upward
// returns Pair(newPosition, numberOfWraps)
private fun computeTurn(startingPos: Int, turnStr: String): Pair<Int, Int> {
    val turn = turnStr.convertToTurn()
   // println(turn)

    val finalLength: Int = turn.length % 100
    var numWraps: Int = turn.length / 100
    val newPosition: Int

   // println("finalLength: $finalLength")

    when (turn.direction) {
        'R'-> {
            newPosition = (startingPos + finalLength) % 100
            if (newPosition == 0 && startingPos == 0) {
                numWraps -= 1
            }
            return Pair(newPosition, numWraps + if ((startingPos + finalLength) >= 100 && startingPos != 0 && newPosition != 0) 1 else 0)
        }
        'L'-> {
            newPosition = (startingPos - finalLength + 100) % 100
            if (newPosition == 0 && startingPos == 0) {
                numWraps -= 1
            }
            return Pair(newPosition, numWraps + if ((startingPos - finalLength) < 0 && startingPos != 0 && newPosition != 0) 1 else 0)
        }
        else -> return Pair(0, 0)
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