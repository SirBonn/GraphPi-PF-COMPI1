package srbn.graphpi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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

        indentCode()

    }

    private fun compileInputCode() {
        val inputCode = StringReader(findViewById<EditText>(R.id.inputCode).text.toString())
        try {
            lexer = Lexer(inputCode)
            parser = Parser(lexer)
            parser.parse()

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
}
