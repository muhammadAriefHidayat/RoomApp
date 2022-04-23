package com.apps.roomapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.apps.roomapp.R
import com.apps.scanbarcode.utils.Utils
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Timer().schedule(timerTask {
            Utils.loading(pb_splash, true)
            val intents = Intent(this@SplashActivity, MainActivity::class.java)
            intents.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intents)
            finish()
        }, 3500)
    }
}