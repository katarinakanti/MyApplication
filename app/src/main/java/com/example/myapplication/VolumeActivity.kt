package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.theme.MyApplicationTheme

class VolumeActivity : ComponentActivity(), View.OnClickListener {
    private lateinit var tvResult: TextView
    private lateinit var etWidth: EditText
    private lateinit var etLength: EditText
    private lateinit var etHeight: EditText
    private lateinit var btnCalculate: Button
    private lateinit var btnSurface: Button
    private lateinit var btnPerimeter: Button

    private val KEY_RESULT = "key_result"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                setContentView(R.layout.activity_volume)

                tvResult = findViewById<TextView>(R.id.tv_result)
                etWidth = findViewById<EditText>(R.id.et_width)
                etLength = findViewById<EditText>(R.id.et_length)
                etHeight = findViewById<EditText>(R.id.et_height)
                btnCalculate = findViewById<Button>(R.id.btn_calculate)
                btnSurface = findViewById<Button>(R.id.btn_surface)
                btnPerimeter = findViewById<Button>(R.id.btn_perimeter)

                btnCalculate.setOnClickListener(this)
                btnSurface.setOnClickListener(this)
                btnPerimeter.setOnClickListener(this)

                if(savedInstanceState != null) {
                    val result = savedInstanceState.getString(KEY_RESULT)
                    tvResult.text = result
                }
            }
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            // Ambil nilai yang diberikan pengguna pada seluruh EditText
            val inputLength: String = etLength.text.toString().trim();
            val inputHeight: String = etHeight.text.toString().trim();
            val inputWidth: String = etWidth.text.toString().trim();

            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                etLength.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                etHeight.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                etWidth.error = "Field ini tidak boleh kosong"
            }

            //perintah hanya akan dieksekusi jika btn_calculate ditekan
            if (view.id == R.id.btn_calculate) {

                //hitung volume balok
                val volume: Double = inputLength.toDouble() * inputHeight.toDouble() * inputWidth.toDouble();


                //tampilkan hasil perhitungan ke TextView -> tvResult
                tvResult.text = String.format("Volume: %s", volume.toString());
            }

            //perintah hanya akan dieksekusi jika btn_surface ditekan
            if (view.id == R.id.btn_surface) {

                //hitung volume balok
                val surface: Double = 2 *(inputLength.toDouble() * inputHeight.toDouble()) + 2 *(inputLength.toDouble() * inputWidth.toDouble()) + 2 *(inputWidth.toDouble() * inputHeight.toDouble());

                //tampilkan hasil perhitungan ke TextView -> tvResult
                tvResult.text = String.format("Luas Permukaan: %s", surface.toString());
            }

            //perintah hanya akan dieksekusi jika btn_perimeter ditekan
            if (view.id == R.id.btn_perimeter) {

                //hitung volume balok
                val perimeter: Double = 4 *(inputLength.toDouble()) + 4 *(inputWidth.toDouble()) + 4 *(inputHeight.toDouble());

                //tampilkan hasil perhitungan ke TextView -> tvResult
                tvResult.text = String.format("Keliling: %s", perimeter.toString());
            }
        }
    }

    override fun onSaveInstanceState (outState: Bundle) {
        super.onSaveInstanceState(outState)

        val calculateResult = tvResult.text.toString()
        outState.putString(KEY_RESULT, calculateResult)
    }
}