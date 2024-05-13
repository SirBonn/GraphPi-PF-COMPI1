package srbn.graphpi

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import srbn.graphpi.BackEnd.DomainObjs.Errors.ErrorP
import srbn.graphpi.BackEnd.DomainObjs.Graphs.Graph
import srbn.graphpi.BackEnd.DomainObjs.Header
import srbn.graphpi.BackEnd.GManagement.GenerateChart


class ShowGraphsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_show_graphs)
        val chartGenerator = intent.getSerializableExtra("chartGenerator") as GenerateChart
        val errors = intent.getSerializableExtra("Errors") as ArrayList<ErrorP>



        chartGenerator.setContext(this)

        chartGenerator.setLayout(findViewById(R.id.graphs_activity_layout))

        chartGenerator.createCharts()

        //generate textView


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    fun setHeader(){
        val header = intent.getSerializableExtra("header") as Header
        findViewById<TextView>(R.id.titleTextView).setText(header.title)
        

    }

}