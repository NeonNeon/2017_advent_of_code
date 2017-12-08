package neon.day8

import java.io.File


fun main(args: Array<String>) {
    val lineGenerator = File("inputfiles/day8.txt").readLines()
    val cpuState = CpuMemory(HashMap<String, Int>(), HashSet<Int>())
    lineGenerator.forEach {
        computeInstruction(it, cpuState)
    }
    for (key in cpuState.registerValues.keys) {
        println("Register $key has value ${cpuState.registerValues[key]}")
    }
    println(cpuState.registerValues.values.max())
    println(cpuState.valueMemory.max())
}

fun computeInstruction(instruction: String, cpu: CpuMemory) {
    // instruction: kw dec -876 if fwk == -7372
    val splitIf = instruction.split("if")
    val program = splitIf[0].trim().split("\\s".toRegex()).map { it.trim() }
    val ifClause = splitIf[1].trim().split("\\s".toRegex()).map { it.trim() }
    // ifClause: "fwk", "==", "-7372"
    if (isValid(ifClause, cpu.registerValues)) {
        runProgram(program, cpu)
    }

}

fun runProgram(program: List<String>, cpu: CpuMemory) {
    // kw dec -876
    val register = program[0]
    val command = program[1]
    val operand = program[2].toInt()
    when (command) {
        "dec" -> {
            val newVal = cpu.registerValues.getOrDefault(register, 0) - operand
            cpu.valueMemory.add(newVal)
            cpu.registerValues.put(register, newVal)
        }
        "inc" -> {
            val newVal = cpu.registerValues.getOrDefault(register, 0) + operand
            cpu.valueMemory.add(newVal)
            cpu.registerValues.put(register, newVal)
        }
    }

}

fun isValid(clause: List<String>, registerValues: Map<String, Int>): Boolean {
    // ifClause: "fwk", "==", "-7372"
    val register = clause[0]
    val command = clause[1]
    val value = clause[2].toInt()
    return when (command) {
        "==" -> registerValues[register] ?: 0 == value
        "!=" -> registerValues[register] ?: 0 != value
        ">" -> registerValues[register] ?: 0 > value
        "<" -> registerValues[register] ?: 0 < value
        ">=" -> registerValues[register] ?: 0 >= value
        "<=" -> registerValues[register] ?: 0 <= value
        else -> throw RuntimeException("wrong command parsed")
    }
}

data class CpuMemory(val registerValues: MutableMap<String, Int>, val valueMemory: MutableSet<Int>)
