import java.io.File

fun main() {
    val inputFile = File("src/InputData/input03.txt")
    val myString = inputFile.readText()

    // Part A: No regex, just mul(...)
    val sum1 = partA(myString)

    // Part B: With regex, mul(...), do(), don't()
    val sum2 = partB(myString)

    println("Part A: $sum1")
    println("Part B: $sum2")
}

fun partA(myString: String): Int {
    var sum1 = 0
    var currentIndex = 0

    while (true) {
        val mulIndex = myString.indexOf("mul(", currentIndex)
        if (mulIndex == -1) break

        val start = mulIndex + "mul(".length
        val end = myString.indexOf(')', start)
        if (end == -1) {
            currentIndex = mulIndex + 4
            continue
        }

        val inside = myString.substring(start, end)
        val parts = inside.split(",")
        if (parts.size == 2 && parts.all { isNumber(it) }) {

            sum1 += parts.map{it.toInt()}.reduce{a, b -> a * b}
        }

        currentIndex = end + 1
    }

    return sum1
}

fun partB(myString: String): Int {
    var sum2 = 0
    var isEnabled = true

    // Regex matches: mul(x,y) with up to 3-digit numbers, do(), don't()
    val regex = Regex("(mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\))")

    regex.findAll(myString).forEach { match ->
        val instruction = match.value
        when {
            instruction == "do()" -> {
                isEnabled = true
            }
            instruction == "don't()" -> {
                isEnabled = false
            }
            instruction.startsWith("mul(") && isEnabled -> {

                // #ILoveFunctionalProgramming UwU
                sum2 += instruction.substringAfter('(').substringBefore(')').split(",").map { it.toInt() }.reduce { a, b -> a * b }
            }
        }
    }

    return sum2
}

fun isNumber(str: String) = str.all { it.isDigit() }
