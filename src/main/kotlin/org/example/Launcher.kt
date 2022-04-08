package org.example

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import org.kohsuke.args4j.CmdLineException


fun main(args: Array<String>) {
    CiphxorLauncher().launch(args)
}

class CiphxorLauncher {
    @Option(name = "-c", metaVar = "Encrypt", usage = "encrypt key", forbids = ["-d"])
    var encryptKey: String = ""

    @Option(name = "-d", metaVar = "Decrypt", usage = "decrypt key", forbids = ["-c"])
    var decryptKey: String = ""

    @Option(name = "-o", metaVar = "OutputName", usage = "output file name")
    var outputFileName: String = "."

    @Argument(metaVar = "InputName", usage = "input file name")
    var inputFileName: String = "0"



    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        val key: String
        val a: Collection<String>
        a = args.toList()
        try {
            parser.parseArgument(a)
            if (encryptKey == "" && decryptKey == "") throw CmdLineException("Не указан колюч кодировки")
            if (encryptKey != "") {
                if (encryptKey.length % 2 == 1 || !encryptKey.matches(Regex("[0-9]+|[a-f]+")))
                    throw CmdLineException("Неверный формат ключа")
                if (outputFileName == ".") outputFileName =
                    inputFileName.removeSuffix('.' + inputFileName.substringAfterLast('.')) +
                    "Encrypted." + inputFileName.substringAfterLast('.')
                key = keyBuilder(encryptKey)
            } else {
                if (decryptKey.length % 2 == 1 || Regex("[0-9]|[a-f]").find(decryptKey) == null)
                    throw CmdLineException("Неверный формат ключа")
                if (outputFileName == ".") outputFileName =
                    inputFileName.removeSuffix('.' + inputFileName.substringAfterLast('.')) +
                    "Decrypted." + inputFileName.substringAfterLast('.')
                key = keyBuilder(decryptKey)
            }
            crypt(inputFileName, key, outputFileName)
        } catch (E: CmdLineException) {
            println(E.message)
        }
    }
}

fun keyBuilder(key: String): String {
    var k = ""
    var result = ""
    for (i in key.indices){
        k += key[i]
        if (i % 2 == 1){
            val r = k.toInt(16)
            result += r.toChar()
            k = ""
        }
    }
    return result
}