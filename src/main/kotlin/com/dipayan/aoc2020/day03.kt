package com.dipayan.aoc2020.day03

import com.dipayan.aoc2020.utils.readFromFile
import java.io.File
import java.lang.RuntimeException

fun main() {
    println("The solution to day three part one is: ${solve()}")
    println("The solution to day three part two is: ${solveSecond()}")

}

sealed class GridContent {
    object OpenSpace : GridContent()
    object Tree : GridContent()
}

fun solve(): Int {
    val valueText = readFromFile(File("./inputs/day03.txt"))
    val grid = valueText.split("\n").map { it.trim().toGridContentRow() }
    return solveWithInputs(grid, 3, 1)
}

private fun solveWithInputs(grid: List<List<GridContent>>, slopeX: Int, slopeY: Int): Int {
    val gridRows = grid.size
    val gridCols = grid.first().size

    val gridArray = grid.flatten().toTypedArray()

    var currentRow = 0
    var currentCol = 0
    var treesEncountered = 0

    while (currentRow < gridRows - 1) {

        currentRow += slopeY
        currentCol = (currentCol + slopeX) % gridCols

        when (gridArray[(currentRow * gridCols) + currentCol]) {
            GridContent.Tree -> treesEncountered++
            else -> {
            }
        }


    }

    return treesEncountered;
}

private fun String.toGridContentRow(): List<GridContent> {
    return this.map { it ->
        when (it) {
            '.' -> GridContent.OpenSpace
            '#' -> GridContent.Tree
            else -> throw RuntimeException("Bad Input $it")
        }
    }
}


fun solveSecond(): Int {
    val valueText = readFromFile(File("./inputs/day03.txt"))
    val grid = valueText.split("\n").map { it.trim().toGridContentRow() }
    val slopes = listOf(Pair(1, 1), Pair(3, 1), Pair(5, 1), Pair(7, 1), Pair(1, 2))

    return slopes.map { solveWithInputs(grid, it.first, it.second) }.foldRight(1) { acc, entry -> acc * entry}
}

