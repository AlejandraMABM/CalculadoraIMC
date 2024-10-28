package com.example.calculadoraimc

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightEditText: EditText
    lateinit var weightEditText: EditText
    lateinit var calculateButton: Button
    lateinit var resultTextView: TextView
    lateinit var resultImageView: ImageView
    lateinit var descriptionTextView: TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightEditText = findViewById(R.id.heightEditText)
        weightEditText = findViewById(R.id.weightEditText)
        calculateButton = findViewById(R.id.calculateButton)
        resultTextView = findViewById(R.id.resultTextView)
        resultImageView = findViewById(R.id.resultImageView)
        descriptionTextView = findViewById(R.id.descriptionTextView)



        calculateButton.setOnClickListener {

            Log.i("MainActivity", "He pulsado el boton calcular")
            var height = heightEditText.text.toString().toFloat()
            var weight = weightEditText.text.toString().toFloat()
            val result = weight / (height / 100).pow(2)


            resultTextView.text = String.format(Locale.getDefault(), "%.2f", result)

            // Log.i("MainActivity", "La Altura es $height y el peso es $weight")


            calculateState(result)

        }
    }

    fun calculateState(result: Float) {


        Log.i("IMC", "valor de result $result")
        when (result) {
            in 0.00..<18.50 -> {
                setColorText(R.color.underWeight, R.string.underWeight, R.drawable.imc_22)

            }

            in 18.50..<25.00 -> {
                setColorText(R.color.normalWeight, R.string.normalWeight, R.drawable.imc_23)
            }

            in 25.00..<30.00 -> {
                setColorText(R.color.overWeight, R.string.overWeight, R.drawable.imc_24)

            }

            in 30.00..<35.00 -> {
                setColorText(R.color.obesity, R.string.obesity, R.drawable.imc_25)

            }

            else -> {
                setColorText(R.color.extremeObesity, R.string.extremeObesity, R.drawable.imc_26)
            }

        }
    }

    fun setColorText(colorRes: Int, textRes: Int, imageRes: Int) {
        val color = getColor(colorRes)
        val text = getString(textRes)
        val drawable = getDrawable(imageRes)
        descriptionTextView.setTextColor(color)
        resultTextView.setTextColor(color)
        descriptionTextView.text = text
        resultImageView.setImageDrawable(drawable)
        resultImageView.setColorFilter(color)

        scaleImage()

    }

    fun scaleImage()
    {
        resultImageView.scaleX = 0.0f
        resultImageView.scaleY = 0.0f
        val scaleUpX = ObjectAnimator.ofFloat(resultImageView,scaleX,1f)
        val scaleUpY = ObjectAnimator.ofFloat(resultImageView,scaleY,1f)

        scaleUpX.setDuration(500)
        scaleUpY.setDuration(500)

        val scaleUp = AnimatorSet()
        scaleUp.play(scaleUpX).with(scaleUpY)

        scaleUp.start()


    }

}
