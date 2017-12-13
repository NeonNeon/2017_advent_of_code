package neon.day13

import java.io.File
import kotlin.coroutines.experimental.buildSequence
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val time =measureTimeMillis { partTwo() }
    println("Execution time: $time ms")
}

private fun partTwo() {
    val input = File("inputfiles/day13.txt").readLines().map {
        val arr = it.split(": ")
        Pair<Int, Int>(arr[0].trim().toInt(), arr[1].trim().toInt())
    }
    var result = -1
    var waitingTime = -1
    var caught = true
    while (caught) {
        waitingTime++
        caught = input.asSequence().any { (layer, depth) ->
            (layer + waitingTime) % (2 * depth - 2) == 0
        }
        /*result = input.fold(0) { cost, (layer, depth) ->
            val caughtCost = if ((layer + waitingTime) % (2 * depth - 2) == 0) {
                Math.max(layer * depth, 1)
            } else 0
            caughtCost + cost
        }
        */
    }
    println("The minimal waiting time is $waitingTime")
}