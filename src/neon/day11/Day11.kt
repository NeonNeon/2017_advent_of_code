package neon.day11

import java.io.File

fun main(args: Array<String>) {
    /*
    ne,ne,ne is 3 steps away.
    ne,ne,sw,sw is 0 steps away (back where you started).
    ne,ne,s,s is 2 steps away (se,se).
    se,sw,se,sw,sw is 3 steps away (s,s,sw).
     */
    val inputString = File("inputfiles/day11.txt").readText()
    val input = inputString.split(",").asSequence()

    val (lastNode, maxDistance) = input.fold(Pair(Node(0, 0), 0)) {
        (node, maxDist), dir ->
            val newNode = moveInDirection(dir, node)
            val distance = newNode.distanceToZero()
            Pair(newNode, if (distance>maxDist) distance else maxDist)
    }

    println(lastNode)
    println(lastNode.distanceToZero())
    println("Max distance from 0,0 was $maxDistance")

}

private fun moveInDirection(dir: String, node: Node): Node {
    return when (dir) {
        "n" -> node.n()
        "ne" -> node.ne()
        "nw" -> node.nw()
        "s" -> node.s()
        "se" -> node.se()
        "sw" -> node.sw()
        else -> {
            throw RuntimeException("Invalid input string")
        }
    }
}

data class Node(val x: Int, val y: Int) {
    fun ne() = Node(this.x + 1, this.y + 1)

    fun se() = Node(this.x + 1, this.y - 1)

    fun n() = Node(this.x, this.y + 2)

    fun s() = Node(this.x, this.y - 2)

    fun nw() = Node(this.x - 1, this.y + 1)

    fun sw() = Node(this.x - 1, this.y - 1)

    fun distanceToZero() : Int {
        val xDist = Math.abs(x)
        val yDist = Math.abs(y)
        return if (yDist/2 > xDist) {
            xDist + (yDist-xDist)/2
        } else {
            yDist/2 + (xDist-yDist/2)
        }

    }
}