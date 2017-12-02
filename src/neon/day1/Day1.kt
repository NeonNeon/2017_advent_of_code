package neon.day1

import java.io.File

fun main(args : Array<String>) {
    val f = File("inputfiles/input_day1.txt")
    val inputString = f.readLines();
    val intList = inputString[0].map { it.toInt() - '0'.toInt() }
    val result = intList.
            mapIndexed{index, it -> adder(intList, index, it)}.
            fold(0) {x, y -> x + y}
    println(result)
}

fun adder(numbers : List<Int>, index : Int, value : Int) : Int {
    val step = numbers.size/2
    val other = numbers.get((index+step) % numbers.size)
    if (value != other) {
        return 0
    }
    return value
}
