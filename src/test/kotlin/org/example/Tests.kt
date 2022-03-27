package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import java.io.File


class HelloTest {

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
}
