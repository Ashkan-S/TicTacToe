package com.navarayan.tictactoe

import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.navarayan.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var playerTurn = "X"

    private var pointX = 0
    private var pointO = 0

    private var soundPool: SoundPool? = null
    private val soundId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        soundPool = SoundPool.Builder().setMaxStreams(6).build()
        soundPool!!.load(baseContext, R.raw.win, 1)

        binding.btn1.setOnClickListener { btnClicked(binding.btn1) }
        binding.btn2.setOnClickListener { btnClicked(binding.btn2) }
        binding.btn3.setOnClickListener { btnClicked(binding.btn3) }
        binding.btn4.setOnClickListener { btnClicked(binding.btn4) }
        binding.btn5.setOnClickListener { btnClicked(binding.btn5) }
        binding.btn6.setOnClickListener { btnClicked(binding.btn6) }
        binding.btn7.setOnClickListener { btnClicked(binding.btn7) }
        binding.btn8.setOnClickListener { btnClicked(binding.btn8) }
        binding.btn9.setOnClickListener { btnClicked(binding.btn9) }

        binding.btnStartOver.setOnClickListener { startOver() }
        binding.btnReset.setOnClickListener { resetAll() }
    }

    private fun resetAll() {
        binding.btn1.isEnabled = true
        binding.btn2.isEnabled = true
        binding.btn3.isEnabled = true
        binding.btn4.isEnabled = true
        binding.btn5.isEnabled = true
        binding.btn6.isEnabled = true
        binding.btn7.isEnabled = true
        binding.btn8.isEnabled = true
        binding.btn9.isEnabled = true

        binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""
        binding.btn9.text = ""

        playerTurn = "X"

        pointX = 0
        pointO = 0

        binding.txtResultX.text = pointX.toString()
        binding.txtResultO.text = pointO.toString()

        binding.btnStartOver.isVisible = false

    }

    private fun startOver() {
        binding.btn1.isEnabled = true
        binding.btn2.isEnabled = true
        binding.btn3.isEnabled = true
        binding.btn4.isEnabled = true
        binding.btn5.isEnabled = true
        binding.btn6.isEnabled = true
        binding.btn7.isEnabled = true
        binding.btn8.isEnabled = true
        binding.btn9.isEnabled = true

        binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""
        binding.btn9.text = ""

        playerTurn = "X"

        binding.btnStartOver.isVisible = false
    }

    private fun btnClicked(btn: Button) {
        if (btn.text == "")
            when (playerTurn) {
                "X" -> {
                    btn.text = "X"
                    playerTurn = "O"
                }
                "O" -> {
                    btn.text = "O"
                    playerTurn = "X"
                }
            }
        checkResult()
    }

    private fun checkResult() {
        if (binding.btn1.text == "X" && binding.btn2.text == "X" && binding.btn3.text == "X"
            || binding.btn4.text == "X" && binding.btn5.text == "X" && binding.btn6.text == "X"
            || binding.btn7.text == "X" && binding.btn8.text == "X" && binding.btn9.text == "X"
            || binding.btn1.text == "X" && binding.btn4.text == "X" && binding.btn7.text == "X"
            || binding.btn2.text == "X" && binding.btn5.text == "X" && binding.btn8.text == "X"
            || binding.btn3.text == "X" && binding.btn6.text == "X" && binding.btn9.text == "X"
            || binding.btn1.text == "X" && binding.btn5.text == "X" && binding.btn9.text == "X"
            || binding.btn3.text == "X" && binding.btn5.text == "X" && binding.btn7.text == "X"
        ) {
            Toast.makeText(this, "X is winner", Toast.LENGTH_LONG).show()
            stopGame("X")
        } else if (binding.btn1.text == "O" && binding.btn2.text == "O" && binding.btn3.text == "O"
            || binding.btn4.text == "O" && binding.btn5.text == "O" && binding.btn6.text == "O"
            || binding.btn7.text == "O" && binding.btn8.text == "O" && binding.btn9.text == "O"
            || binding.btn1.text == "O" && binding.btn4.text == "O" && binding.btn7.text == "O"
            || binding.btn2.text == "O" && binding.btn5.text == "O" && binding.btn8.text == "O"
            || binding.btn3.text == "O" && binding.btn6.text == "O" && binding.btn9.text == "O"
            || binding.btn1.text == "O" && binding.btn5.text == "O" && binding.btn9.text == "O"
            || binding.btn3.text == "O" && binding.btn5.text == "O" && binding.btn7.text == "O"
        ) {
            Toast.makeText(this, "O is winner", Toast.LENGTH_LONG).show()
            stopGame("O")
        } else if (binding.btn1.text != "" && binding.btn2.text != "" && binding.btn3.text != ""
            && binding.btn4.text != "" && binding.btn5.text != "" && binding.btn6.text != ""
            && binding.btn7.text != "" && binding.btn8.text != "" && binding.btn9.text != ""
        ) {
            Toast.makeText(this, "It's Draw", Toast.LENGTH_LONG).show()
            stopGame("")
        }
    }

    private fun stopGame(winner: String) {
        when (winner) {
            "X" -> {
                pointX++
                binding.txtResultX.text = pointX.toString()
                soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)
            }
            "O" -> {
                pointO++
                binding.txtResultO.text = pointO.toString()
                soundPool?.play(soundId, 1F, 1F, 0, 0, 1F)
            }
        }

        binding.btnStartOver.isVisible = true
        binding.btn1.isEnabled = false
        binding.btn2.isEnabled = false
        binding.btn3.isEnabled = false
        binding.btn4.isEnabled = false
        binding.btn5.isEnabled = false
        binding.btn6.isEnabled = false
        binding.btn7.isEnabled = false
        binding.btn8.isEnabled = false
        binding.btn9.isEnabled = false
    }
}