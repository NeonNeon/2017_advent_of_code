package neon.day10

import java.lang.*;

fun main(args: Array<String>) {
    val input = "76,1,88,148,166,217,130,0,128,254,16,2,130,71,255,229"
    val lengths = input.toCharArray().map { it.toInt() }.toMutableList()
    lengths.addAll(listOf(17, 31, 73, 47, 23))

    lengths.forEach { print("$it,") }

    val sparseHash = IntArray(256,{ x->x})
    var currentIndex = 0
    var skip = 0
    for (i in 1 until 65) {
        lengths.forEach {
            reverseSubLust(sparseHash, currentIndex, it)
            currentIndex = (currentIndex + it + skip) % sparseHash.size
            skip += 1
        }
    }
    println()
    println("Sparsehash:")
    sparseHash.forEach { print("$it,") }
    println()
    val hexes = arrayListOf<String>()
    for (j in 1..16) {
        val hexInt = sparseHash.slice((j-1)*16 until j*16).fold(0)
        {
            x, y -> x xor y
        }
        val hexString = Integer.toHexString(hexInt)
        hexes.add(if(hexString.length == 1) "0" + hexString else hexString)
    }

    println()
    println(hexes.fold("", {acc, y -> acc + y}))
}

fun reverseSubLust(hash: IntArray, startIx: Int, length: Int) {
    var stopIx = (startIx + length - 1) % hash.size
    var c = 0
    var left = startIx

    while (c < length/2) {
        val tmp = hash[stopIx]
        hash[stopIx] = hash[left]
        hash[left] = tmp
        c++
        left = (left + 1) % hash.size
        stopIx =  if (stopIx - 1 < 0) hash.size - 1 else stopIx - 1
    }



}
