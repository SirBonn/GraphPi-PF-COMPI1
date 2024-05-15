package srbn.graphpi

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import srbn.graphpi.BackEnd.GManagement.GenerateChart
import srbn.graphpi.BackEnd.Lexer.*;
import srbn.graphpi.BackEnd.Parser.*;
import srbn.graphpi.FrontEnd.FileManagement
import java.io.StringReader


class MainActivity : AppCompatActivity() {

    private lateinit var lexer: Lexer
    private lateinit var parser: Parser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val fileManager = FileManagement(this)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.compileButton).setOnClickListener {
            compileInputCode()

        }

        findViewById<Button>(R.id.openFileButton).setOnClickListener {
            fileManager.selectFile()
        }

        findViewById<Button>(R.id.saveFileButton).setOnClickListener {
            fileManager.saveFile()
        }

        findViewById<Button>(R.id.new_file_button).setOnClickListener {
            makeNewFile(fileManager)
        }

        indentCode()

    }

    private fun compileInputCode() {
        val inputCode = StringReader(findViewById<EditText>(R.id.inputCode).text.toString())
        try {
            lexer = Lexer(inputCode)
            parser = Parser(lexer)
            parser.parse()

            if(parser.errors.isNotEmpty()){
                Toast.makeText(this, "EXISTEN ERRORES EN EL CODIGO", Toast.LENGTH_LONG).show()
            }

            val generateChart = GenerateChart(parser.graphs)
            val intent = Intent(this, ShowGraphsActivity::class.java)
            intent.putExtra("chartGenerator", generateChart)
            intent.putExtra("Errors", parser.errors)
            intent.putExtra("header", parser.header)
            intent.putExtra("sentences", parser.sentences)
            intent.putExtra("symbolTable", parser.symTable)
            startActivity(intent)

        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun indentCode() {
        val input = findViewById<EditText>(R.id.inputCode)
        val lineCounter = findViewById<TextView>(R.id.lineCounter)
        input.addTextChangedListener(object : TextWatcher {

            private var previousText = ""
            private var isAutoIndenting = false
            val column = findViewById<TextView>(R.id.column_number)
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (!isAutoIndenting) {
                    previousText = s.toString()
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val lines = input.lineCount
                var linesCount = "";
                for (i in 1..lines) {
                    linesCount += "$i\n"
                }
                lineCounter.text = linesCount
            }

            override fun afterTextChanged(s: Editable?) {

                s?.let {
                    val text = it.toString()
                    val wordsToColor =
                        listOf("if", "else", "do", "while", "for") // Palabras que quieres colorear
                    val logialOperators =
                        listOf("==", "!=", "<", ">", "<=", ">=") // Operadores lógicos
                    val agrupationOperators =
                        listOf("(", ")", "{", "}", "[", "]") // Operadores de agrupación
                    val allWords =
                        text.split(" ","\n", "\t", ";", ",", ":") // Dividimos el texto en palabras

                    for (word in allWords) {
                        if (word.matches(Regex("[0-9]+"))) {
                            colorText(
                                input,
                                word,
                                Color.BLACK,
                                Color.parseColor("#FFF8D9")
                            ) // Coloreamos la palabra
                        } else if (!word.matches(Regex("\"([^\"]*)\"")) ) {
                            colorText(
                                input,
                                word,
                                Color.BLACK,
                                Color.parseColor("#FF9494")
                            ) // Coloreamos la palabra
                        } else {
                            colorText(
                                input,
                                word,
                                Color.BLACK,
                                Color.WHITE
                            ) // Coloreamos la palabra
                        }
                    }
                    for (word in wordsToColor) {
                        colorText(input, word, Color.YELLOW, Color.WHITE) // Coloreamos la palabra
                    }
                    for (word in logialOperators) {
                        colorText(input, word, Color.BLUE, Color.WHITE) // Coloreamos la palabra
                    }
                    for (word in agrupationOperators) {
                        colorText(input, word, Color.MAGENTA, Color.WHITE) // Coloreamos la palabra
                    }

                }

                var currentColumn = 0;
                s?.let {
                    val text = it.toString()
                    val cursorPosition = input.selectionStart
                    currentColumn = calculateColumn(text, cursorPosition)
                    column.text = "Columna: $currentColumn"
                }

                if (s == null || isAutoIndenting) return

                val newText = s.toString()
                val cursorPosition = input.selectionStart

                val lastChar =
                    if (newText.isNotEmpty() && cursorPosition > 0) newText[cursorPosition - 1] else null

                if (lastChar == '{' || lastChar == '}' || lastChar == '[' || lastChar == ']') {
                    isAutoIndenting = true
                    val indentedText = applyIndentation(newText)

                    input.setText(indentedText)
                    input.setSelection(cursorPosition + (indentedText.length - newText.length))

                    isAutoIndenting = false
                }
            }
        })
    }

    private fun applyIndentation(text: String): String {
        val lines = text.split("\n")
        val indentedLines = mutableListOf<String>()
        var indentLevel = 0

        for (line in lines) {
            val trimmedLine = line.trim()

            if (trimmedLine.endsWith("}") || trimmedLine.endsWith("]")) {
                indentLevel = maxOf(0, indentLevel - 1)
            }

            val indentedLine = " ".repeat(indentLevel * 4) + trimmedLine
            indentedLines.add(indentedLine)

            if (trimmedLine.endsWith("{") || trimmedLine.endsWith("[") || trimmedLine.endsWith("\n")) {
                indentLevel++
            }
        }

        return indentedLines.joinToString("\n")
    }

    private fun colorText(editText: EditText, word: String, color: Int, backC: Int) {
        val text = editText.text.toString()
        val start = text.indexOf(word)
        if (start != -1) {
            val end = start + word.length
            editText.text.setSpan(
                android.text.style.ForegroundColorSpan(color),
                start,
                end,
                android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            editText.text.setSpan(
                android.text.style.BackgroundColorSpan(backC),
                start,
                end,
                android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }

    private fun calculateColumn(text: String, cursorPosition: Int): Int {
        var column = 0
        for (i in 0 until cursorPosition) {
            if (text[i] == '\n') {
                column = 0 // Reiniciar el número de columna al encontrar un salto de línea
            } else {
                column++
            }
        }
        return column
    }


    private fun makeNewFile(fileManager: FileManagement) {
        if (findViewById<EditText>(R.id.inputCode).text.isNotEmpty()) {
            fileManager.saveFile()
        }
        findViewById<EditText>(R.id.inputCode).setText("")
    }
}
