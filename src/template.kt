import java.io.File

fun main() {
    val inputFile = File("src/InputData/inputXX.txt")
    val inputString = inputFile.readText()

    val partAResult = solvePartA(inputString)
    val partBResult = solvePartB(inputString)

    println("Part A: $partAResult")
    println("Part B: $partBResult")
}

fun solvePartA(input: String): Int {
    return 0
}

fun solvePartB(input: String): Int {
    return 0
}

