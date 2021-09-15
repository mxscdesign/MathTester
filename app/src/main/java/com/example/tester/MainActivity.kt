package com.example.tester

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var correctAnswers: Double = 0.0
    lateinit var taskOneQuestion: Question
    lateinit var taskTwoQuestion: Question
    lateinit var taskThreeQuestion: Question
    lateinit var taskFourQuestion: Question
    lateinit var taskFiveQuestion: Question

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taskOneQuestion = Question()
        tv_quest1.text =
            "${taskOneQuestion.digitOne} ${taskOneQuestion.operation} ${taskOneQuestion.digitTwo}"

        taskTwoQuestion = Question()
        tv_quest2.text =
            "${taskTwoQuestion.digitOne} ${taskTwoQuestion.operation} ${taskTwoQuestion.digitTwo}"

        taskThreeQuestion = Question()
        tv_quest3.text =
            "${taskThreeQuestion.digitOne} ${taskThreeQuestion.operation} ${taskThreeQuestion.digitTwo}"

        taskFourQuestion = Question()
        tv_quest4.text =
            "${taskFourQuestion.digitOne} ${taskFourQuestion.operation} ${taskFourQuestion.digitTwo}"

        taskFiveQuestion = Question()
        tv_quest5.text =
            "${taskFiveQuestion.digitOne} ${taskFiveQuestion.operation} ${taskFiveQuestion.digitTwo}"

        btn_help_quest1.setOnClickListener(this)
        btn_help_quest2.setOnClickListener(this)
        btn_help_quest3.setOnClickListener(this)
        btn_help_quest4.setOnClickListener(this)
        btn_help_quest5.setOnClickListener(this)

        btn_result.setOnClickListener {
            if (et_answer1.text.toString() == taskOneQuestion.result.toString())
                correctAnswers++
            if (et_answer2.text.toString() == taskTwoQuestion.result.toString())
                correctAnswers++
            if (et_answer3.text.toString() == taskThreeQuestion.result.toString())
                correctAnswers++
            if (et_answer4.text.toString() == taskFourQuestion.result.toString())
                correctAnswers++
            if (et_answer5.text.toString() == taskFiveQuestion.result.toString())
                correctAnswers++
            if (correctAnswers < 0)
                correctAnswers = 0.0

            tv_result.visibility = View.VISIBLE
            btn_reset.visibility = View.VISIBLE

            tv_result.text = "Вы набрали $correctAnswers баллов"

            btn_reset.setOnClickListener {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onClick(view: View?) {
        var helpDialogBuilder = AlertDialog.Builder(this)
        helpDialogBuilder.setTitle("Подсказка")
        helpDialogBuilder.setMessage("Вы уверены, что хотите воспользоваться подсказкой? Вы потеряете очки")
        helpDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.cancel()
        }
        helpDialogBuilder.setPositiveButton("Yes") { dialog, which ->

            var number = 0
            correctAnswers -= 0.5

            when (view?.id) {
                R.id.btn_help_quest1 -> number = taskOneQuestion.result % 10
                R.id.btn_help_quest2 -> number = taskTwoQuestion.result % 10
                R.id.btn_help_quest3 -> number = taskThreeQuestion.result % 10
                R.id.btn_help_quest4 -> number = taskFourQuestion.result % 10
                R.id.btn_help_quest5 -> number = taskFiveQuestion.result % 10
            }

            helpDialogBuilder = AlertDialog.Builder(this)
            helpDialogBuilder.setMessage("В ответе есть цифра ${kotlin.math.abs(number)}")
            helpDialogBuilder.setPositiveButton("OK") { dialog, which -> }
            helpDialogBuilder.show()
        }
        helpDialogBuilder.show()
    }
}
