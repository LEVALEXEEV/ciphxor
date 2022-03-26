package org.example

import org.junit.Test

class HelloTest {
    private fun assertEquals(expected: String, actual: String): Boolean {
        return expected == actual
    }

    @Test
    fun keyBuilder(){
        assertEquals(keyBuilder("123abc"), "" + 18.toChar() + 58.toChar() + 188.toChar())
        assertEquals(keyBuilder("11"), "" + 17.toChar())
    }


}
