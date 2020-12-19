package com.dipayan.aoc2020.day02

import com.dipayan.aoc2020.utils.readFromFile
import java.io.File

fun main() {
    println("The solution to day two part one is: ${solve()}")
    println("The solution to day two part two is: ${solveSecond()}")

}

data class PasswordInput(val password: String, val validationChar: Char, val minTimes: Int, val maxTimes: Int) {
    fun isValid(): Boolean {
        val actualSize = password.toCharArray().filter { it == validationChar }.size
        return actualSize in minTimes..maxTimes
    }
    fun isValidByToboggan(): Boolean {
        val chars = password.toCharArray()
        val firstMatch = chars[minTimes - 1] == validationChar
        val secondMatch = chars[maxTimes - 1] == validationChar
        return  firstMatch xor secondMatch
    }
}


val policyRegex = "(\\d+)-(\\d+) ([a-z]+): (.*)".toRegex()

fun solve(): Int {
    val valueText = readFromFile(File("./inputs/day02.txt"))
    return valueText.split("\n").map { it.trim().toPasswordInput() }.filter { it.isValid() }.size
}

fun solveSecond(): Int {
    val valueText = readFromFile(File("./inputs/day02.txt"))
    return valueText.split("\n").map { it.trim().toPasswordInput() }.filter { it.isValidByToboggan() }.size
}

private fun String.toPasswordInput(): PasswordInput {
    val matchResult = policyRegex.matchEntire(this)!!

    val minTimes = matchResult.groupValues[1]
    val maxTimes = matchResult.groupValues[2]
    val validationChar = matchResult.groupValues[3]
    val password = matchResult.groupValues[4]

    return PasswordInput(password, validationChar[0], Integer.parseInt(minTimes), Integer.parseInt(maxTimes))
}


