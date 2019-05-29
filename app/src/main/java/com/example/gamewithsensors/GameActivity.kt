package com.example.gamewithsensors

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_game.*
import java.io.IOException
import com.squareup.seismic.ShakeDetector




class GameActivity : AppCompatActivity(), SensorEventListener, ShakeDetector.Listener {

    private val zubr = Bizon(70, 70, 70, 70, 70, 70)
    private lateinit var sensorManager: SensorManager
    private var light: Sensor? = null
    private var shake: ShakeDetector? = null
    private var isDark = false
    private var isShake = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        bizonGameImage.setImageResource(R.drawable.ordinary_zuber)

        this.sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        shake = ShakeDetector(this)
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME)
        shake!!.start(sensorManager)


        retryGameButton.visibility = View.GONE
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

    override fun hearShake() {
        isShake = true
    }

    override fun onSensorChanged(event: SensorEvent?) = try {
        isDark = event!!.values[0] < 60 && !isDark
    } catch (e: IOException) {
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_GAME)
        shake!!.start(sensorManager)

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        shake!!.stop()
    }


    private fun sleepGameButtonOnClickListener() {
        disableButtons()
        zubr.sleep(isDark)
        if( isDark){
            infoGameLabel.text = "Zuber slept well that night"
        }else{
            infoGameLabel.text = "It wasn't a night to remember"
        }
        bizonGameImage.setImageResource(R.drawable.zubr_sleep)
        inicializeGame()
    }

    private fun foodGameButtonOnClickListener() {
        disableButtons()
        zubr.eat()
        bizonGameImage.setImageResource(R.drawable.zubr_food)
        infoGameLabel.text = "What can Zuber eat? Maybe carbonara?"
        inicializeGame()
    }

    private fun drinkGameButtonOnClickListener() {
        disableButtons()
        zubr.drink()
        bizonGameImage.setImageResource(R.drawable.zubr_drink)
        infoGameLabel.text = "Not so thirsty zuber, at least for now"
        inicializeGame()
    }

    private fun toiletGameButtonOnClickListener() {
        disableButtons()
        zubr.toilet()
        bizonGameImage.setImageResource(R.drawable.zubr_toilet)
        infoGameLabel.text = "Zuber feels 2kg lighter"
        inicializeGame()
    }

    private fun funGameButtonOnClickListener() {
        disableButtons()
        zubr.play()
        bizonGameImage.setImageResource(R.drawable.zubr_play)
        infoGameLabel.text = "Zuber is happy, just like after databases"
        inicializeGame()
    }

    private fun hygieneGameButtonOnClickListener() {
        disableButtons()
        zubr.wash(isShake)
        if(isShake){
            infoGameLabel.text = "Zuber is clean and perfumed"
        }else{
            infoGameLabel.text = "Zuber is a bit smelly"
        }
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
        infoGameLabel.text = "Just ordinary Zuber"
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
        isShake = false

        if (zubr.idDead()) {
            infoGameLabel.text = "ZUBER DEAD!"
            retryGameButton.visibility = View.VISIBLE
            bizonGameImage.setImageResource(R.drawable.zubr_dead)
        } else {
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




