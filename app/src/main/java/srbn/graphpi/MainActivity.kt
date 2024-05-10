package srbn.graphpi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import srbn.graphpi.BackEnd.Lexer.*;
import srbn.graphpi.BackEnd.Parser.*;
import java.io.StringReader


class MainActivity : AppCompatActivity() {

    private lateinit var lexer: Lexer
    private lateinit var parser: Parser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.compileButton).setOnClickListener {
            compileInputCode()

        }

    }

    private fun compileInputCode() {
        val inputCode = StringReader(findViewById<EditText>(R.id.inputCode).text.toString())
        try {
            lexer = Lexer(inputCode)
            parser = Parser(lexer)
            parser.parse()
            println("Compilation finished")
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}