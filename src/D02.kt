import java.io.File
import kotlin.math.abs

fun isSafe(myList: List<Int>): Boolean = isIncOrDec(myList) && isGapOK(myList)

fun isIncOrDec(myList: List<Int>): Boolean =
    myList == myList.sorted() || myList == myList.sorted().reversed()

fun isGapOK(myList: List<Int>): Boolean {
    for (i in 0..myList.lastIndex - 1) {
        val gapDiff = abs(myList[i + 1] - myList[i])
        if (gapDiff > 3 || gapDiff < 1) {
            return false
        }
    }
    return true
}

fun isSafe2(myList: List<Int>): Boolean {
    if (isSafe(myList)) return true

    // Try removing each element and check if that makes the list safe
    for (i in myList.indices) {
        val newList = myList.toMutableList().apply { removeAt(i) }
        if (isSafe(newList)) {
            return true
        }
    }

    return false
}


fun main() {
    val inputFile = File("src/InputData/input02.txt")

    var safeCount = 0
    var safeCount2 = 0

    inputFile.forEachLine { line ->
        val numbers = line.split(" ").map { it.toInt() }
        if (isSafe(numbers)) {
            safeCount++
        }
        if (isSafe2(numbers)) {
            safeCount2++
        }
    }

    println(safeCount)
    println(safeCount2)
}
