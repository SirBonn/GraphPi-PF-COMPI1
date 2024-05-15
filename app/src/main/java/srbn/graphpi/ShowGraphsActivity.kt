package srbn.graphpi

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import srbn.graphpi.BackEnd.DomainObjs.Errors.ErrorP
import srbn.graphpi.BackEnd.DomainObjs.Header
import srbn.graphpi.BackEnd.DomainObjs.Sentences.Sentence
import srbn.graphpi.BackEnd.DomainObjs.Sentences.SentencesManager
import srbn.graphpi.BackEnd.DomainObjs.SymTable
import srbn.graphpi.BackEnd.GManagement.GenerateChart


class ShowGraphsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_show_graphs)

        val linearLayout = findViewById<LinearLayout>(R.id.graphs_activity_layout)
        val chartGenerator = intent.getSerializableExtra("chartGenerator") as GenerateChart
        val errors = intent.getSerializableExtra("Errors") as ArrayList<ErrorP>
        val sentences = intent.getSerializableExtra("sentences") as ArrayList<Sentence>
        val symbolTable = intent.getSerializableExtra("symbolTable") as SymTable
        val header = intent.getSerializableExtra("header") as Header

        setHeader(header)
        chartGenerator.setupGenerator(this, linearLayout, errors)
        linearLayout.addView(TextView(this))
        chartGenerator.createCharts()
        val sentencesManager = SentencesManager(sentences, symbolTable, chartGenerator)
        sentencesManager.executeSentences()

        val footherTextView = TextView(this)
        footherTextView.text = header.footer
        footherTextView.gravity = 1
        linearLayout.addView(footherTextView)
        setErrorsPane(errors)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun setHeader(header: Header){
        findViewById<TextView>(R.id.titleTextView).setText(header.title)
        findViewById<TextView>(R.id.descTextView).setText(header.desc)
        findViewById<TextView>(R.id.keysTextView).setText(header.keysString)
    }

    private fun setErrorsPane(errors: ArrayList<ErrorP>){
//        val errorPane = findViewById<LinearLayout>(R.id.errorPane)
//        for (error in errors){
//            val errorTextView = TextView(this)
//            errorTextView.text = error.toString()
//            errorPane.addView(errorTextView)
//        }
    }

}