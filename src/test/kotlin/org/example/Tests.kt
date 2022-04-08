package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import java.io.File


class Test {

    private fun assertFileContent(name: String, expected: String) {
        val file = File(name)
        val content = file.readLines().joinToString("\n")
        assertEquals(expected, content)
    }



    @Test
    fun keyBuilder() {
        assertEquals(keyBuilder("123abc"), "" + 18.toChar() + 58.toChar() + 188.toChar())
        assertEquals(keyBuilder("11"), "" + 17.toChar())
    }

    @Test
    fun crypt() {
        fun test(inputName: String, key: String, outputName: String, res: String) {
            crypt(inputName, key, outputName)
            assertFileContent(outputName, res)
            File(outputName).delete()
        }
        test(
            "test.txt",
            keyBuilder("11"),
            "testEncrypted.txt",
            "ppppppppppppppppppppppppppppppppppppppppppppppp\n" +
                "ppppppppppppppppppppppppppppppppppppppppppppppp\n" +
                "sssssssssssssssssssssssssssssssssssssssssssssss"
        )
    }

    @Test
    fun ciphxor() {
        fun test(args: Array<String>, res: String) {
            main(args)
            assertFileContent("testEncrypted.txt", res)
            File("testEncrypted.txt").delete()
        }
        test(arrayOf("-c","11", "test.txt"),
            "ppppppppppppppppppppppppppppppppppppppppppppppp\n" +
                "ppppppppppppppppppppppppppppppppppppppppppppppp\n" +
                "sssssssssssssssssssssssssssssssssssssssssssssss")
        test(arrayOf("-d","11", "test2.txt", "-o", "testEncrypted.txt"),
            "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa\n" +
                "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
    }
}
