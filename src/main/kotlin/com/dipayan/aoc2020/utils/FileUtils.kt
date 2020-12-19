package com.dipayan.aoc2020.utils

import java.io.File

fun readFromFile(file: File): String{
    return file.readText(Charsets.UTF_8)
}