package com.example.tipcalc

import android.animation.ArgbEvaluator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "MainActivity"
private const val inf = 15

class MainActivity : AppCompatActivity() {
    private lateinit var john: TextView
    private lateinit var rob: TextView
    private lateinit var george: EditText
    private lateinit var jeff: TextView
    private lateinit var gina: TextView
    private lateinit var mary: SeekBar
    private lateinit var katy: TextView
    private lateinit var reggie: TextView
    private lateinit var jane: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        john = findViewById(R.id.john)
        rob = findViewById(R.id.rob)
        george = findViewById(R.id.george)
        jeff = findViewById(R.id.jeff)
        gina = findViewById(R.id.gina)
        mary = findViewById(R.id.mary)
        katy = findViewById(R.id.katy)
        reggie = findViewById(R.id.reggie)
        jane = findViewById(R.id.jane)
        gina.text = "$inf%"
        mary.progress = inf
        updatetipdesc(inf)
        computeTipAndTotal()
        mary.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                Log.i(TAG, "onProgressChanged $p1")
                gina.text = "$p1%"
                computeTipAndTotal()
                updatetipdesc(mary.progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })
        george.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged $p0")
                computeTipAndTotal()
            }

        })
        }

    private fun updatetipdesc(progress: Int) {
        jane.text = when (progress) {
            in 0..10 -> "bad"
            in 11..20 -> "ok"
            else -> "good"
        }

        val color = ArgbEvaluator().evaluate(progress.toFloat() / mary.max, resources.getColor(R.color.bad), resources.getColor(R.color.good))
        jane.setTextColor(color as Int)
    }

    private fun computeTipAndTotal() {
        if (george.text.isEmpty()) {
            katy.text = ""
            reggie.text = ""
            return
        }
        val base = george.text.toString().toDouble()
        val tip = mary.progress
        val tot = base * tip / 100
        katy.text = tip.toString()
        reggie.text = tot.toString()
    }
}
