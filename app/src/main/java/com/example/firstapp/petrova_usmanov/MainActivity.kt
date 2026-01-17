package com.example.firstapp.petrova_usmanov

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var diceValueTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var rollButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Включаем edge-to-edge (полноэкранный режим)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Настройка отступов под системные панели
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация элементов интерфейса
        initViews()

        // Настройка кнопки
        setupButton()
    }

    private fun initViews() {
        diceValueTextView = findViewById(R.id.diceValueTextView)
        resultTextView = findViewById(R.id.resultTextView)
        rollButton = findViewById(R.id.rollButton)
    }

    private fun setupButton() {
        rollButton.setOnClickListener {
            rollDice()
        }
    }

    private fun rollDice() {
        // Генерируем случайное число от 1 до 6
        val diceValue = Random.nextInt(1, 7)

        // Обновляем значение кубика
        diceValueTextView.text = diceValue.toString()

        // Обновляем текст кнопки (если хотим)
        rollButton.text = "БРОСИТЬ СНОВА"

        // Показываем результат с эмодзи
        val resultMessage = getResultMessage(diceValue)
        resultTextView.text = resultMessage

        // Небольшая анимация (опционально)
        animateDice()
    }

    private fun getResultMessage(value: Int): String {
        return when (value) {
            1 -> "🎲 Единица! Маловато будет..."
            2 -> "🎲🎲 Двойка! Уже лучше!"
            3 -> "🎲🎲🎲 Тройка! Неплохо!"
            4 -> "🎲🎲🎲🎲 Четверка! Хороший бросок!"
            5 -> "🎲🎲🎲🎲🎲 Пятерка! Отлично!"
            6 -> "🎲🎲🎲🎲🎲🎲 Шестерка! Максимум!"
            else -> "Что-то пошло не так..."
        }
    }

    private fun animateDice() {
        // Простая анимация масштабирования
        diceValueTextView.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(150)
            .withEndAction {
                diceValueTextView.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(150)
                    .start()
            }
            .start()
    }
}