package com.example.tester

import kotlin.random.Random

class Question {
    private val randomDigitList = List(2) { Random.nextInt(10, 90) }
    val digitOne = randomDigitList[0]
    val digitTwo = randomDigitList[1]

    private val operationList = listOf("+", "-", "*", "/")
    private val randomOperationNumber = Random.nextInt(0, 4)
    val operation = operationList[randomOperationNumber]

    val result =
        when (operation) {
            "+" -> digitOne + digitTwo
            "-" -> digitOne - digitTwo
            "*" -> digitOne * digitTwo
            "/" -> (digitOne / digitTwo)
            else -> 0
        }
}