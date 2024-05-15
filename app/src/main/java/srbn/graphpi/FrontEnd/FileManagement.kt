package srbn.graphpi.FrontEnd


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import srbn.graphpi.MainActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import srbn.graphpi.R
import java.io.BufferedWriter
import java.io.OutputStreamWriter


class FileManagement(private val activity: MainActivity) {


    private val chooserFile =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    readFile(uri)
                }
            }
        }

    private val storageFile =
        activity.registerForActivityResult(ActivityResultContracts.CreateDocument()) { uri ->
            uri?.let {
                val input = activity.findViewById<EditText>(R.id.inputCode)
                writteFile(input.text.toString(), activity, uri)
            }
        }

    fun selectFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "text/plain"
        chooserFile.launch(intent)
    }

    fun saveFile() {
        storageFile.launch("archivo_guardado.txt")
    }

    private fun readFile(uri: Uri) {
        try {
            val inputStream = activity.contentResolver.openInputStream(uri)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
                stringBuilder.append("\n")
            }
            inputStream?.close()
            val editText = activity.findViewById<EditText>(R.id.inputCode)
            editText.setText(stringBuilder.toString())
        } catch (e: Exception) {
            Toast.makeText(activity, "Error al leer el archivo", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

    private fun writteFile(textToSave: String, context: Context, uri: Uri) {
        try {
            activity.contentResolver.openOutputStream(uri)?.use { outputStream ->
                BufferedWriter(OutputStreamWriter(outputStream)).use { writer ->
                    writer.write(textToSave)
                }
            }
            Toast.makeText(context, "Archivo guardado correctamente", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Error al guardar el archivo", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }

}