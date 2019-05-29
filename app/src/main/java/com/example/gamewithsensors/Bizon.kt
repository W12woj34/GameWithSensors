package com.example.gamewithsensors

class Bizon(
    private var sleep: Int,
    private var food: Int,
    private var drink: Int,
    private var toilet: Int,
    private var play: Int,
    private var hygiene: Int
) {

    fun getSleep(): Int {
        return sleep
    }

    fun getFood(): Int {
        return food
    }

    fun getDrink(): Int {
        return drink
    }

    fun getToilet(): Int {
        return toilet
    }

    fun getPlay(): Int {
        return play
    }

    fun getHygiene(): Int {
        return hygiene
    }

    fun idDead(): Boolean {
        return sleep <= 0 || food <= 0 || drink <= 0 || toilet <= 0 || play <= 0 || hygiene <= 0
    }

    fun eat() {
        food += 40
        drink -= 10
        toilet -= 5
        sleep -= 5
        play -= 10
        hygiene -= 15

        checkLimit()
    }

    fun drink() {
        food -= 3
        drink += 45
        toilet -= 10
        sleep -= 5
        play -= 5
        hygiene -= 3

        checkLimit()
    }

    fun sleep(correct: Boolean) {

        if (correct) {
            food -= 15
            drink -= 20
            toilet -= 10
            sleep += 80
            play -= 15
            hygiene -= 15

        } else {
            food -= 15
            drink -= 20
            toilet -= 10
            sleep -= 5
            play -= 15
            hygiene -= 15
        }
        checkLimit()
    }

    fun wash(correct: Boolean) {
        if (correct) {
            food -= 6
            drink -= 6
            toilet -= 6
            sleep -= 6
            play -= 5
            hygiene += 75
        } else {
            food -= 6
            drink -= 6
            toilet -= 6
            sleep -= 6
            play -= 5
            hygiene -= 5
        }
        checkLimit()
    }

    fun play() {
        food -= 3
        drink -= 5
        toilet -= 6
        sleep -= 15
        play += 60
        hygiene -= 7

        checkLimit()
    }

    fun toilet() {
        food -= 2
        drink -= 3
        toilet += 90
        sleep -= 5
        play -= 5
        hygiene -= 20

        checkLimit()
    }

    private fun checkLimit() {

        if (food < 0) {
            food = 0
        }
        if (drink < 0) {
            drink = 0
        }
        if (toilet < 0) {
            toilet = 0
        }
        if (sleep < 0) {
            sleep = 0
        }
        if (play < 0) {
            play = 0
        }
        if (hygiene < 0) {
            hygiene = 0
        }
        if (food > 100) {
            food = 100
        }
        if (drink > 100) {
            drink = 100
        }
        if (toilet > 100) {
            toilet = 100
        }
        if (sleep > 100) {
            sleep = 100
        }
        if (play > 100) {
            play = 100
        }
        if (hygiene > 100) {
            hygiene = 100
        }
    }

    fun reset(){
        food = 70
        drink = 70
        sleep = 70
        toilet = 70
        play = 70
        hygiene = 70
    }
}