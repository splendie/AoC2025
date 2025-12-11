import java.io.File
import java.math.BigInteger

fun main() {
    //val input = File("input.txt").readLines()
    //var total: BigInteger = BigInteger.ZERO
    //for (line in input) {
        val s = "818181911112111"//"7225271234724212144362411223531221412264267255242242226526263674477422436246415625223811384523523527"
        val maxJoltage = getTwelveDigitMaxNumber(s)
        println(maxJoltage)
        //total += maxJoltage
    //}

    //println("Total joltage: $total")
}

/*

78711
3

need = n - stack.size
sisa = s.length - i

i = 0
num = 7
deque = []
action: push 7
sisa (5) >= need (3)


i = 1
num = 8
deque = [7]
action: pop 7, push 8
sisa (4) >= need (2)

i = 2
num = 7
deque = [8]
action: nothing
sisa (3) >= need (2)

i = 3
num = 1
deque = [8]
action: push 1
sisa (2) == need (2) 

i = 4
num = 1
deque = [8,7]
action: push 1
sisa (1) == need (1)

*/

private fun getTwelveDigitMaxNumber(s: String): BigInteger {
    println("s: $s. s.length: ${s.length}")
    val stack = ArrayDeque<Char>()
  
    s.forEachIndexed {i, char ->
        val num = char.toString().toInt()
        	println("---\ni: $i")
            println("num: $num")
            
        println("stack: [${stack.joinToString(separator = ",")}]")
        if (stack.isEmpty()) {
            println("add stack")
            stack.addLast(char)
        } else {       
            val top = stack.lastOrNull().toString().toInt()
            println("top: $top")
            val need = 12 - stack.size
            println("need: $need")
            // 888 911 112 111
        
            val sisa = s.length - i
            println("sisa: $sisa")
            if (num > top && need < sisa && need != 0) {
            
                    println("switch $top with $char")
                	stack.removeLast()
                	stack.addLast(char)
             } else {
                    println("add $char")
                	stack.addLast(char)
              
            } 
        }
        
    }

    val s = stack.joinToString(separator = "")
	println("s: $s")
    
    return BigInteger.ZERO //BigInteger(s)
}
