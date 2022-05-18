package org.example

import java.io.File

fun crypt(input: File, k: String, output: File) {
    val key = makeKey(k)
    var position = 0
    val writer = output.bufferedWriter()
    writer.use {
        for (line in input.readLines()) {
            var cryptLine = ""
            for (i in line) {
                var p1 = i.code / 32
                var p2 = i.code % 32
                p1 = p1 xor key[position]
                position++
                if (position == key.size) position = 0
                p2 = p2 xor key[position]
                position++
                if (position == key.size) position = 0
                cryptLine += (p1 * 32 + p2).toChar()
            }
            it.write(cryptLine)
            it.newLine()
        }
    }
}

fun makeKey(key: String): List<Int> {
    val bytes = mutableListOf<Int>()
    var k = ""
    for (i in key.indices) {
        k += key[i]
        if (i % 2 == 1) {
            bytes.add(k.toInt(16))
            k = ""
        }
    }
    return bytes
}