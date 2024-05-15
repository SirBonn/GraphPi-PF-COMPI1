package srbn.graphpi

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import srbn.graphpi.BackEnd.DomainObjs.Errors.ErrorP

class ErrorReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_error_report)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val errors = intent.getSerializableExtra("Errors") as ArrayList<ErrorP>
        val linearLayout = findViewById<LinearLayout>(R.id.error_report_layout)

        for (error in errors) {
            val errorTextView = TextView(this)
            errorTextView.text = error.toString()
            linearLayout.addView(errorTextView)
            val separator = TextView(this)
            linearLayout.addView(separator)
            linearLayout.addView(separator)
        }
    }
}