package neon.day3

data class Coordinate(val x : Int, val y : Int) : ArrayList<Coordinate>() {
    fun getNeighbours() : List<Coordinate> {
        val myNeighbours = ArrayList<Coordinate>()
        for(i in this.x-1..this.x+1) {
            (this.y-1..this.y+1)
                    .filterNot { i == this.x && it == this.y }
                    .mapTo(myNeighbours) { Coordinate(i, it) }
        }
        return myNeighbours
    }
    fun up() = Coordinate(this.x, this.y+1)
    fun down() = Coordinate(this.x, this.y-1)
    fun left() = Coordinate(this.x-1, this.y)
    fun right() = Coordinate(this.x+1, this.y)
}


fun main(args: Array<String>) {
    val input = 312051
    partOne(input)
    partTwo(input)
}

private fun partTwo(input: Int) {
    val values = HashMap<Coordinate, Int>()
    val neighbours = HashMap<Coordinate, MutableList<Coordinate>>().withDefault { arrayListOf() }

    // Start with Coordinate 0,0

    var current = Coordinate(0, 0)
    values.put(current, 1);
    addMeAsNeighbour(current, neighbours)

    var n = 1 // circle

    while (values.withDefault { 0 }.getValue(current) < input) {
        current = current.right()
        values[current] = getVal(current, values)
        println(values[current])
        for (i in 1..2 * n - 1) {
            current = current.up()
            values[current] = getVal(current, values)
            println(values[current])
        }
        for (i in 1..2 * n) {
            current = current.left()
            values[current] = getVal(current, values)
            println(values[current])
        }
        for (i in 1..2 * n) {
            current = current.down()
            values[current] = getVal(current, values)
            println(values[current])
        }
        for (i in 1..2 * n) {
            current = current.right()
            values[current] = getVal(current, values)
            println(values[current])
        }
        n++
    }
}

fun getVal(current: Coordinate, values: MutableMap<Coordinate, Int>) : Int {
    return current.getNeighbours().map { values.withDefault { 0 }.getValue(it) }.fold(0) {x, y -> x + y}
}


fun addMeAsNeighbour(current : Coordinate, neighbours : MutableMap<Coordinate, MutableList<Coordinate>>) {
    for (c in current.getNeighbours()) {

        if (!neighbours.containsKey(c)) {
            neighbours.put(c, arrayListOf())
        }
        neighbours[c]?.add(current)
    }
}


private fun partOne(input: Int) {
    // Square n, ends with the number (2n+1)^2
    // Need to find first n for which (2n+1)^2 > input
    var n = 0
    while (Math.pow((2 * n + 1).toDouble(), 2.toDouble()) < input) {
        n++
    }

    // we know we must travel at least n steps since we need to get to the inner circle
    // but how many steps do we need to travel in the other direction?

    // Four of the numbers in any layer will have 0 distance to 0 in one direction
    val largestNbr = Math.pow((2 * n + 1).toDouble(), 2.toDouble()).toInt()
    val step = (2 * n + 1) / 2
    val minStepList = listOf(largestNbr - step, largestNbr - 3 * step, largestNbr - 5 * step, largestNbr - 7 * step)
    val dirX = minStepList.map { Math.abs(input - it) }.min()
    val result = n + dirX!!
    print(result)
}