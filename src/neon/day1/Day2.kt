package neon.day1

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val f = File("inputfiles/day2.txt")
    val inputString = f.readLines();
    // Create int matrix
    val intMatrix = inputString.map { it.split("\\s".toRegex()) }.map { it.map { it.toInt() } }

    val result1 = partOne(intMatrix)
    val result2 = partTwo(intMatrix)

    println(result1)
    println(result2)
}

fun partTwo(intMatrix: List<List<Int>>): Int {
    return intMatrix.map {
        calcDivisionValue(it)
    }.sum()
}

fun partOne(intMatrix: List<List<Int>>): Int {
    val result = intMatrix.
            map {
                it.fold(Int.MIN_VALUE) { x, y -> max(x, y) } -
                        it.fold(Int.MAX_VALUE) { x, y -> min(x, y) }
            }.
            sum()
    return result
}


fun calcDivisionValue(row: List<Int>): Int {
    println("Row")
    // Could be done with lazy evaluation to make it better
    return row.map { value ->
        row.map { if (it != value && it % value == 0) {
            println("$it divides $value"); it.div(value)} else 0 }.sum()
    }.sum()
}



