package org.example

import org.kohsuke.args4j.Argument
import org.kohsuke.args4j.CmdLineParser
import org.kohsuke.args4j.Option
import java.io.File



fun main(args: Array<String>) {
    CiphxorLauncher().launch(args)
}

class CiphxorLauncher {
    @Option(name = "-c", metaVar = "Encrypt", usage = "encrypt key", forbids = ["-d"])
    private var encryptKey: String = ""

    @Option(name = "-d", metaVar = "Decrypt", usage = "decrypt key", forbids = ["-c"])
    private var decryptKey: String = ""

    @Option(name = "-o", metaVar = "OutputName", usage = "output file name")
    private var outputFile: File? = null

    @Argument(metaVar = "InputName", usage = "input file name", required = true)
    private var inputFile: File? = null


    fun launch(args: Array<String>) {
        val parser = CmdLineParser(this)
        val key: String
        val expansion: String
        val a: Collection<String>
        a = args.toList()
        try {
            parser.parseArgument(a)
            if (encryptKey == "" && decryptKey == "") throw IllegalStateException("Не указан колюч кодировки")
            if (encryptKey != "") {
                key = encryptKey
                expansion = "Encrypted"
            } else {
                key = decryptKey
                expansion = "Decrypted"
            }
            if (key.length % 2 == 1 || !key.matches(Regex("[0-9a-f]+")))
                throw IllegalStateException("Неверный формат ключа")
            if (outputFile == null) {
                outputFile = if ('.' in inputFile!!.name) {
                    val ending = inputFile!!.name.substringAfterLast('.')
                    File(inputFile!!.name.removeSuffix(".$ending") + expansion + ".$ending")
                } else File(inputFile!!.name + expansion)
            }
            crypt(inputFile!!, key, outputFile!!)
        } catch (e: Exception) {
            println(e.message)
            throw Exception()
        }
    }
}

