package neon.day12

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val lines = File("inputfiles/day12.txt").readLines()
    val edges: MutableMap<Int, MutableSet<Int>> = HashMap<Int, MutableSet<Int>>()
    lines.forEach {
        updateEdges(edges, it)
    }

    val superSet = HashSet<MutableSet<Int>>()
    val zeroGroup = HashSet<Int>()
    expandGroup(zeroGroup, 0, edges)
    superSet.add(zeroGroup)
    println("The size of the zero group is ${zeroGroup.size}")

    for (node in edges.keys) {
        if (superSet.all { !it.contains(node) }) {
            val newGroup = HashSet<Int>()
            expandGroup(newGroup, node, edges)
            superSet.add(newGroup)
        }
    }

    println("There are ${superSet.size} number of groups")
}

fun expandGroup(zeroGroup: MutableSet<Int>, node: Int, edges: MutableMap<Int, MutableSet<Int>>) {
    val examinedNodes = HashSet<Int>()
    val queue = ArrayDeque<Int>()
    queue.add(node)
    while(queue.isNotEmpty()) {
        val head = queue.pop()
        zeroGroup.add(head)
        if (!examinedNodes.contains(head)) {
            queue.addAll(edges.get(head)!!)
            examinedNodes.add(head)
        }
    }


}

fun updateEdges(edges: MutableMap<Int, MutableSet<Int>>, line: String) {
    val split = line.split("<->")
    val node = split[0].trim().toInt()
    val newChildren = split[1].split(",").map { it.trim().toInt() }
    val children =  edges.getOrPut(node) {
        HashSet()
    }
    children.addAll(newChildren)
}
