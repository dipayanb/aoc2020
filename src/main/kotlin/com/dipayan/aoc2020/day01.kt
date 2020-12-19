package com.dipayan.aoc2020.day01

import com.dipayan.aoc2020.utils.readFromFile
import java.io.File
import java.lang.RuntimeException
import java.util.*

fun main() {
    println("The solution to day one part one is: ${solve()}")
    println("The solution to day one part two is: ${solveSecondPart()}")
}

private fun solve(): Long {
    val valueText = readFromFile(File("./inputs/day01.txt"))
    val values = valueText.split("\n").map { Integer.parseInt(it.trim()) }.toTypedArray()

    Arrays.sort(values)

    val (startIndex, endIndex) = solveGivenRanges(values, 0, values.size - 1, 2020L)!!
    return values[startIndex].toLong() * values[endIndex].toLong()
}

private fun solveGivenRanges(values: Array<Int>, start: Int, end: Int, limit: Long): Pair<Int, Int>? {
    var start1 = start
    var end1 = end
    while (start1 < end1) {
        val startValue = values[start1].toLong()
        val endValue = values[end1].toLong()
        // Exact case
        if (startValue + endValue == limit) {
            return Pair(start1, end1)
        }

        if (startValue + endValue > limit) {
            end1--
        } else start1++
    }
    return null
}

fun solveSecondPart(): Long {
    val valueText = readFromFile(File("./inputs/day01.txt"))
    val values = valueText.split("\n").map { Integer.parseInt(it.trim()) }.toTypedArray()

    Arrays.sort(values)
    values.indices.forEach { index ->
        val fix = values[index].toLong()
        val start = index + 1
        val end = values.size - 1;

        val pair = solveGivenRanges(values, start, end, 2020 - fix)

        if (pair != null) {
            return fix * values[pair.first] * values[pair.second]
        }
    }
    throw RuntimeException("Could not find a suitable coin")
}