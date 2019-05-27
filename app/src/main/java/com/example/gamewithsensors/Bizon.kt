package com.example.gamewithsensors

class Bizon(var sleep: Int, var food: Int, var drink: Int, var toilet: Int, var play: Int, val hygiene: Int) {

    fun idDead(): Boolean {
        return sleep <= 0 || food <= 0 || drink <= 0 || toilet <= 0 || play <= 0 || hygiene <= 0

    }

    fun eat() {

    }

    fun drink() {

    }

    fun sleep(correct: Boolean) {

    }

    fun wash(correct: Boolean) {

    }

    fun play() {

    }

    fun toilet() {

    }
}