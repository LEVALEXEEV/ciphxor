package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException

class Test {

    private fun assertFileContent(file: File, expected: String) {
        val content = file.readLines().joinToString("\n")
        assertEquals(expected, content)
    }

    @Test
    fun keyMaking() {
        assertEquals(makeKey("123abc"), listOf(18, 58, 188))
        assertEquals(makeKey("11"), listOf(17))
    }

    @Test
    fun crypt() {
        fun test(input: File, key: String, output: File, res: String) {
            crypt(input, key, output)
            assertFileContent(output, res)
            output.delete()
        }
        test(
            File("test.txt"),
            "11",
            File("testEncrypted.txt"),
            """ɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐ
ɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐ
ɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓ"""
        )
        test(
            File("test2.txt"),
            "11",
            File("testEncrypted.txt"),
            """aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"""
        )
        test(
            File("test3.txt"),
            "11",
            File("test3Encrypted.txt"),
            ""
        )
        assertThrows(FileNotFoundException::class.java) { crypt(File(""), "", File(""))}
    }

    @Test
    fun ciphxor() {
        fun test(args: Array<String>, res: String) {
            main(args)
            assertFileContent(File("testEncrypted.txt"), res)
            File("testEncrypted.txt").delete()
        }
        test(arrayOf("-c", "11", "test.txt"),
            """ɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐ
ɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐ
ɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓ""")
       test(arrayOf("-d", "11", "test2.txt", "-o", "testEncrypted.txt"),
            """aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb""")
        test(arrayOf("-c", "11", "test4", "-o", "testEncrypted.txt"),
            """ɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐ
ɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐɐ
ɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓɓ""")
        assertThrows(Exception::class.java) { main(arrayOf("", "", ""))}
    }
}
