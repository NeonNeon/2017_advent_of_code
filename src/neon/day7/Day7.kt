package neon.day7

import java.io.File

fun main(args: Array<String>) {
    val weightMap = HashMap<String, Int>()
    val ontop = HashMap<String, MutableSet<String>>()
    val allPrograms = ArrayList<String>()

    File("inputfiles/day7.txt").readLines().forEach {
        val arr = it.split("->")
        val programAndWeight = arr[0].trim().split("\\s".toRegex())
        val programName = programAndWeight[0]
        val left = programAndWeight[1].indexOf('(')
        val right = programAndWeight[1].indexOf(')')
        val weight = programAndWeight[1].subSequence(left + 1, right).toString().toInt()
        weightMap[programName] = weight
        allPrograms.add(programName)
        if (arr.size > 1) {
            arr[1].split(",").map { it.trim() }.forEach {
                if (ontop.containsKey(programName)) {
                    ontop[programName]!!.add(it)
                } else {
                    ontop.put(programName, mutableSetOf(it))
                }
            }
        }


    }
    var root: String = ""
    for (program in allPrograms) {

        if (ontop.contains(program)) {
            if (ontop.values.all { !it.contains(program) }) {
                root = program
                println(program)
            }
        }
    }

    /*
    for (below in ontop.keys) {
        stackWeights[below] = calculateWeight(below, allPrograms, ontop, weightMap)
        println("The stack of $below \tweighs ${stackWeights[below]}")
    }
    */

    root = "exrud"
    println(stackWeights[root])
    ontop[root]!!.forEach { print("$it = ${calculateWeight(it, allPrograms, ontop, weightMap)} & weight = ${weightMap[it]}, ") }
}

val stackWeights = HashMap<String, Int>()

fun calculateWeight(below: String, allPrograms: List<String>, ontop: Map<String, MutableSet<String>>, weights: Map<String, Int>): Int {
    if (stackWeights.containsKey(below)) {
        return stackWeights[below]!!
    } else {
        val newVal =
                if (!ontop.containsKey(below)) {
                    weights[below]!!
                } else {
                    weights[below]!! + ontop[below]!!.map {
                        calculateWeight(it, allPrograms, ontop, weights)
                    }.sum()
                }
        stackWeights[below] = newVal
        return newVal
    }
}
