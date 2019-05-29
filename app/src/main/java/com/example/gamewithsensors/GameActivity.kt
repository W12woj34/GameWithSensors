package com.example.gamewithsensors

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*


class GameActivity : AppCompatActivity() {
    private val zubr = Bizon(70, 70, 70, 70, 70, 70)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        bizonGameImage.setImageResource(R.drawable.ordinary_zuber)

        inicializeGame()
        sleepGameButton.setOnClickListener {
            sleepGameButtonOnClickListener()
        }

        foodGameButton.setOnClickListener {
            foodGameButtonOnClickListener()
        }

        drinkGameButton.setOnClickListener {
            drinkGameButtonOnClickListener()
        }

        toiletGameButton.setOnClickListener {
            toiletGameButtonOnClickListener()
        }

        funGameButton.setOnClickListener {
            funGameButtonOnClickListener()
        }

        hygieneGameButton.setOnClickListener {
            hygieneGameButtonOnClickListener()
        }

        retryGameButton.setOnClickListener {
            retryGameButtonOnClickListener()
        }
    }

    private fun sleepGameButtonOnClickListener() {
        disableButtons()
        infoGameLabel.text = "Cover light sensor!"
        zubr.sleep(true)
        bizonGameImage.setImageResource(R.drawable.zubr_sleep)
        inicializeGame()
    }

    private fun foodGameButtonOnClickListener() {
        disableButtons()
        zubr.eat()
        bizonGameImage.setImageResource(R.drawable.zubr_food)
        inicializeGame()
    }

    private fun drinkGameButtonOnClickListener() {
        disableButtons()
        zubr.drink()
        bizonGameImage.setImageResource(R.drawable.zubr_drink)
        inicializeGame()
    }

    private fun toiletGameButtonOnClickListener() {
        disableButtons()
        zubr.toilet()
        bizonGameImage.setImageResource(R.drawable.zubr_toilet)
        inicializeGame()
    }

    private fun funGameButtonOnClickListener() {
        disableButtons()
        zubr.play()
        bizonGameImage.setImageResource(R.drawable.zubr_play)
        inicializeGame()
    }

    private fun hygieneGameButtonOnClickListener() {
        disableButtons()
        infoGameLabel.text = "Shake!"
        zubr.wash(true)
        bizonGameImage.setImageResource(R.drawable.zubr_wash)
        inicializeGame()
    }


    private fun retryGameButtonOnClickListener() {
        resetBison()
        inicializeGame()
    }

    private fun resetBison() {
        zubr.reset()
        bizonGameImage.setImageResource(R.drawable.ordinary_zuber)
        infoGameLabel.text = "Just ordinary zuber"
        retryGameButton.visibility = View.GONE
        inicializeGame()
    }


    private fun inicializeGame() {
        sleepLevel.text = zubr.getSleep().toString()
        sleepLevel.setTextColor(Color.parseColor(chooseColor(zubr.getSleep())))
        foodLevel.text = zubr.getFood().toString()
        foodLevel.setTextColor(Color.parseColor(chooseColor(zubr.getFood())))
        drinkLevel.text = zubr.getDrink().toString()
        drinkLevel.setTextColor(Color.parseColor(chooseColor(zubr.getDrink())))
        toiletLevel.text = zubr.getToilet().toString()
        toiletLevel.setTextColor(Color.parseColor(chooseColor(zubr.getToilet())))
        funLevel.text = zubr.getPlay().toString()
        funLevel.setTextColor(Color.parseColor(chooseColor(zubr.getPlay())))
        hygieneLevel.text = zubr.getHygiene().toString()
        hygieneLevel.setTextColor(Color.parseColor(chooseColor(zubr.getHygiene())))

        if (zubr.idDead()) {
            infoGameLabel.text = "ZUBER DEAD!"
            retryGameButton.visibility = View.VISIBLE
            bizonGameImage.setImageResource(R.drawable.zubr_dead)
        } else {
            infoGameLabel.text = "Just ordinary zuber"
            enableButtons()

        }
    }

    private fun disableButtons() {
        sleepGameButton.isClickable = false
        foodGameButton.isClickable = false
        drinkGameButton.isClickable = false
        toiletGameButton.isClickable = false
        funGameButton.isClickable = false
        hygieneGameButton.isClickable = false
    }

    private fun enableButtons() {
        sleepGameButton.isClickable = true
        foodGameButton.isClickable = true
        drinkGameButton.isClickable = true
        toiletGameButton.isClickable = true
        funGameButton.isClickable = true
        hygieneGameButton.isClickable = true
    }

    private fun chooseColor(value: Int): String {
        return when {
            value > 90 -> "#39ff14"
            value > 70 -> "#0B6623"
            value > 40 -> "#FFD300"
            value > 15 -> "#DE1738"
            else -> "#070C0F"
        }

    }
}




