import java.io.File
import kotlin.math.abs

fun main() {
    val inputFile = File("src/InputData/input01.txt")
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    inputFile.readLines().forEach {
        val parts = it.split("   ")
        list1.add(parts[0].toInt())
        list2.add(parts[1].toInt())
    }

    // Sort both lists
    list1.sort()
    list2.sort()

    var diff = 0
    for (i in list1.indices) {
        diff += abs(list1[i] - list2[i])
    }
    println(diff)

    val frequencyMap = list2.groupingBy { it }.eachCount()
    var sim = 0

    for (myNumber in list1) {
        sim += myNumber * frequencyMap.getOrDefault(myNumber, 0)
    }

    println(sim)

}
