package com.example.firstapp.petrova_usmanov

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstapp.petrova_usmanov.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupButtonClickListener()
    }

    private fun setupButtonClickListener() {
        binding.rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        val diceValue = Random.nextInt(1, 7)

        binding.rollButton.text = getString(R.string.roll_again_text)

        val resultMessage = getResultMessage(diceValue)
        binding.resultTextView.text = resultMessage

        animateDiceImage()
    }

    private fun getResultMessage(value: Int): String {
        return when (value) {
            1 -> getString(R.string.result_one)
            2 -> getString(R.string.result_two)
            3 -> getString(R.string.result_three)
            4 -> getString(R.string.result_four)
            5 -> getString(R.string.result_five)
            6 -> getString(R.string.result_six)
            else -> getString(R.string.result_error)
        }
    }

    private fun animateDiceImage() {
        binding.diceImageView.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(150)
            .withEndAction {
                binding.diceImageView.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .start()
            }
            .start()
    }
}