package neon.day4

import java.io.File

fun main(args : Array<String>) {
    val f = File("inputfiles/day4.txt")
    val passphraseList = f.readLines().map { it.split("\\s".toRegex()) }
    val result = passphraseList.map { if (isOkPassphrase(it)) 1 else 0 }.sum()
    println(result)


}

fun isOkPassphrase(words : List<String>) : Boolean {
    // Part 1 - dont sort
    val sortedWords = words.map {it.toCharArray().sorted().toString()}
    return sortedWords.distinct().size == sortedWords.size
}


