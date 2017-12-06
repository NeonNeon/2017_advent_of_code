package neon.day6

import java.io.File
import kotlin.math.max

fun main(args: Array<String>) {
    val intArray = File("inputfiles/day6.txt").readLines()[0].split("\\s".toRegex()).map { it.toInt() }.toMutableList()
    val sequencesSeen = HashMap<String,Int>()
    println(intArray.toString())
    var counter = 0
    do {
        println(intArray.toString())
        sequencesSeen.put(intArray.toString(),counter)
        reallocateBlocks(intArray)
        counter++
    } while (!sequencesSeen.contains(intArray.toString()))
    val sizeOfLoop = counter - sequencesSeen.get(intArray.toString())!!
    println(sizeOfLoop)
}

fun reallocateBlocks(intArray: MutableList<Int>) {
    val maxIdx = intArray.indices.maxBy { intArray[it] } ?: -1
    val maxValue = intArray[maxIdx]
    intArray[maxIdx] = 0
    println("Index $maxIdx has the largest value of $maxValue")
    intArray.mapIndexed {
        index, _ ->
        intArray[index] += maxValue/intArray.size
        val offset = maxValue%intArray.size
        if ((index > maxIdx && index <= maxIdx + offset)
                || (maxIdx + offset >= intArray.size && index <= (maxIdx + offset) % intArray.size)) {
            intArray[index] += 1
        }
    }
}
