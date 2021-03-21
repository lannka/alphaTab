package alphaTab

import alphaTab.core.ecmaScript.Uint8Array
import android.R.attr
import java.io.ByteArrayOutputStream
import java.nio.file.Paths
import kotlin.contracts.ExperimentalContracts
import androidx.test.platform.app.InstrumentationRegistry
import android.util.Log

import java.io.IOException

import android.R.attr.data
import android.content.Context
import android.provider.MediaStore

import java.io.OutputStreamWriter
import android.net.Uri

import android.R.attr.data
import android.content.ContentValues
import android.os.Environment

import android.R.attr.data





@ExperimentalUnsignedTypes
@ExperimentalContracts
public class TestPlatformPartials {
    companion object {
        public fun loadFile(path: String): Uint8Array {
            var subpath = Paths.get(path)
            subpath = subpath.subpath(1, subpath.nameCount)

            val testContext = InstrumentationRegistry.getInstrumentation().context
            val assets = testContext.assets

            val fs = assets.open(subpath.toString())
            val ms = ByteArrayOutputStream()
            fs.use {
                fs.copyTo(ms)
            }
            return Uint8Array(ms.toByteArray().asUByteArray())
        }

        public fun saveFile(name: String, data: Uint8Array) {
            val testContext = InstrumentationRegistry.getInstrumentation().context

            val values = ContentValues()

            var path = Paths.get(name)

            values.put(
                MediaStore.MediaColumns.DISPLAY_NAME,
                path.fileName.toString()
            )

            values.put(
                MediaStore.MediaColumns.MIME_TYPE,
                "image/png"
            )

            values.put(
                MediaStore.MediaColumns.RELATIVE_PATH,
                "${Environment.DIRECTORY_DOCUMENTS}/test-results/${path.parent}"
            )

            val uri = testContext.contentResolver.insert(
                MediaStore.Files.getContentUri("external"), values)

            Log.i("AlphaTabTest", "Saving file '$name' to '$uri'")
            val fs = testContext.contentResolver.openOutputStream(uri!!)
            fs?.use {
                fs.write(data.buffer.raw.asByteArray())
            }
        }

        public fun listDirectory(path: String): MutableList<String> {
            val testContext = InstrumentationRegistry.getInstrumentation().context
            val assets = testContext.assets
            return assets.list(path)!!.toMutableList()
        }
    }
}
