package alphaTab

import alphaTab.core.ecmaScript.Uint8Array
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.nio.file.Paths
import kotlin.contracts.ExperimentalContracts

@ExperimentalUnsignedTypes
@ExperimentalContracts
public class TestPlatformPartials {
    companion object {
        public val projectRoot: String = findProjectRoot()

        private fun findProjectRoot():String {
            var path = Paths.get("").toAbsolutePath()
            while(!Paths.get(path.toString(), "package.json").toFile().exists()) {
                path = path.parent
                    ?: throw AlphaTabError(AlphaTabErrorType.General, "Could not find project root")
            }
            println(path.toString())
            return path.toString()
        }

        public fun loadFile(path: String): Uint8Array {
            val filePath = Paths.get(projectRoot, path)
            val fs = FileInputStream(filePath.toString())
			val ms = ByteArrayOutputStream()
			fs.use {
                fs.copyTo(ms)
            }
			return Uint8Array(ms.toByteArray().asUByteArray())
        }

        public fun saveFile(name:String, data:Uint8Array) {
            val path = Paths.get(projectRoot, "test-results", name)
            path.parent.toFile().mkdirs()
            val fs = path.toFile().outputStream()
            fs.use {
                fs.write(data.buffer.raw.asByteArray())
            }
        }

        public fun listDirectory(path:String): MutableList<String> {
            val dirPath = Paths.get(projectRoot, path)
            return dirPath.toFile().listFiles()?.map { it.name }?.toMutableList() ?: mutableListOf()
        }
    }
}
