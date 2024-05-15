package srbn.graphpi.FrontEnd

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class ExportCharts(private val activity: AppCompatActivity, private val charts: ArrayList<Chart<*>>){

    fun exportChartsAsImages() {
        for ((index, chart) in charts.withIndex()) {
            val bitmap = Bitmap.createBitmap(chart.width, chart.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            chart.draw(canvas)

            val fileName = "chart_$index.png"
            saveBitmapToFile(bitmap, fileName)
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap, fileName: String) {
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun exportChartsAsPdf() {
        val document = PdfDocument()
        for ((index, chart) in charts.withIndex()) {
            val pageInfo = PdfDocument.PageInfo.Builder(chart.width, chart.height, index + 1).create()
            val page = document.startPage(pageInfo)
            val canvas = page.canvas
            val bitmap = Bitmap.createBitmap(chart.width, chart.height, Bitmap.Config.ARGB_8888)
            val chartCanvas = Canvas(bitmap)
            chart.draw(chartCanvas)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            document.finishPage(page)
        }
        val fileName = "charts.pdf"
        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName)
        try {
            document.writeTo(FileOutputStream(file))
            Toast.makeText(activity, "PDF creado en Descargas/$fileName", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        document.close()
    }

    fun exportChartsAsHtml() {
        exportChartsAsImages()
        var htmlContent = """
            <html>
            <body>
                <h1>Graficos</h1>
        """.trimIndent()

        for ((index, _) in charts.withIndex()) {
            val imageUrl = "raw:/storage/emulated/0/Download/chart_$index.png"
            htmlContent += "<img src=\"$imageUrl\" alt=\"Chart $index\"><br><br>"
        }

        htmlContent += "</body></html>"

        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "charts.html")
        try {
            file.writeText(htmlContent)
            Toast.makeText(activity, "HTML creado en Descargas/charts.html", Toast.LENGTH_SHORT).show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}