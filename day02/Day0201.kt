import java.math.BigInteger

fun main() {
    val input = "82853534-82916516,2551046-2603239,805115-902166,3643-7668,4444323719-4444553231,704059-804093,32055-104187,7767164-7799624,25-61,636-1297,419403897-419438690,66-143,152-241,965984-1044801,1-19,376884-573880,9440956-9477161,607805-671086,255-572,3526071225-3526194326,39361322-39455443,63281363-63350881,187662-239652,240754-342269,9371-26138,1720-2729,922545-957329,3477773-3688087,104549-119841"
    val ranges = input.split(',')
    var sum: BigInteger = BigInteger.ZERO

    ranges.forEach { range ->
        val startAndEnd = range.split('-').map { BigInteger(it) }
        println(startAndEnd)

        sum += getSumOfTwinNumbersInARange(startAndEnd[0], startAndEnd[1])
        println("sum: $sum")
    }
}

private fun getSumOfTwinNumbersInARange(start: BigInteger, end: BigInteger): BigInteger {
    var sum: BigInteger = BigInteger.ZERO
    var i = start // Use 'var' so we can reassign 'i' in each iteration
    while (i.compareTo(end) <= 0) { // Keep looping as long as i <= end
        if (isTwinNumber(i)) {
            // println(i)
            sum += i
        }

        // Increment i by 1 for the next iteration
        i = i.add(BigInteger.ONE)
    }
    return sum
}

private fun isTwinNumber(num: BigInteger): Boolean {
    val listOfDigits = extractDigitsBigInt(num)
    val size = listOfDigits.size
    if (size % 2 == 1) return false

    for (i in 0 until size/2){
        if (listOfDigits[i] != listOfDigits[i+size/2]) return false
    }
    return true
}

private fun extractDigitsBigInt(n: BigInteger): List<Int> {
    // 12345 becomes listOf(1,2,3,4,5)
    val list = mutableListOf<Int>()
    val zero = BigInteger.ZERO
    val ten = BigInteger.TEN
    var num = n

    while (num > zero) {
        val remainder = num.remainder(ten)
        list.add(remainder.toInt()) // Safe cast as remainder will always be 0-9
        num = num.divide(ten)
    }

    return list.reversed()
}