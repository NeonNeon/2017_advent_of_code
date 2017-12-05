package neon.day5

import java.io.File

fun main(args: Array<String>) {
    val f = File("inputfiles/day5.txt")
    var instructions = f.readLines().map { it.toInt() }.toMutableList()
    var current = 0
    var stepsTaken = 0
    while (current >= 0 && current < instructions.size) {
        val stepLength = instructions[current]
        // part 1
        // val diff = 1
        val change = if(stepLength >= 3) -1 else 1
        instructions[current] += change
        current += stepLength
        stepsTaken += 1
    }
    println(stepsTaken)
}
// 372071 fel