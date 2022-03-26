package org.example

import java.io.File
import java.io.FileNotFoundException

fun crypt(inputName: String, key: String, outputName: String) {
    var position = 0
    val writer = File(outputName).bufferedWriter()
    try {
        for (line in File(inputName).readLines()) {
            var cryptLine = ""
            for (i in line.indices) {
                if (position == key.length) position = 0
                cryptLine += (line[i].code xor key[position].code).toChar()
                position++
            }
            writer.write(cryptLine)
            writer.newLine()
        }
    } catch (E: FileNotFoundException) {
        println("File is not found")
    }
    writer.close()
}
