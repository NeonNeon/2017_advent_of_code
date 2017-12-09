package neon.day9

import java.io.File
import java.util.*
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
    val stream = File("inputfiles/day9.txt").readLines()[0].asSequence()
    val scores: MutableList<Int> = ArrayList()
    var insideGarbage = false
    var ignoreNextChar = false
    var nbrGarbageChars = 0
    var depth = 0
    stream.forEach {
        if (ignoreNextChar) {
            ignoreNextChar = false
        } else if (it == '!') {
            ignoreNextChar = true
        } else if (insideGarbage) {
            if (it == '>') {
                insideGarbage = false
            } else {
                nbrGarbageChars++
            }
        } else { // not in garbage
            when (it) {
                '{' -> {
                    scores.add(++depth)
                }
                '}' -> {
                    depth--
                }
                '<' -> {
                    insideGarbage = true
                }
                else -> {

                }
            }
        }
    }
    println(scores.sum())
    println("nbr garbage chars: $nbrGarbageChars")
}