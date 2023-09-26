package com.example.myplayer

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.view.get

class Mainue : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainue)

        val btn=findViewById<LinearLayout>(R.id.btn_video)
        val btn1=findViewById<LinearLayout>(R.id.btn_video1)
        val btn2=findViewById<LinearLayout>(R.id.btn_video2)
        val btn3=findViewById<LinearLayout>(R.id.btn_video3)
        val btn4=findViewById<LinearLayout>(R.id.btn_video4)
        val btn5=findViewById<LinearLayout>(R.id.btn_video5)

        btn.setOnClickListener {
            var intent= Intent(this,WatchActivity::class.java)
           val videopath="android.resource://"+packageName+"/"+R.raw.my
            intent.putExtra("videopath",videopath)
            startActivity(intent)
        }
        btn1.setOnClickListener {
            var intent= Intent(this,WatchActivity::class.java)
            val videopath="https://www.rmp-streaming.com/media/big-buck-bunny-360p.mp4"
            intent.putExtra("videopath",videopath)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            var intent= Intent(this,WatchActivity::class.java)
            val videopath="android.resource://"+packageName+"/"+R.raw.video4
            intent.putExtra("videopath",videopath)
            startActivity(intent)
        }

        btn3.setOnClickListener {
            var intent= Intent(this,WatchActivity::class.java)
            val videopath="android.resource://"+packageName+"/"+R.raw.video1
            intent.putExtra("videopath",videopath)
            startActivity(intent)
        }

        btn4.setOnClickListener {
            var intent= Intent(this,WatchActivity::class.java)
            val videopath="android.resource://"+packageName+"/"+R.raw.video2
            intent.putExtra("videopath",videopath)
            startActivity(intent)
        }
        btn5.setOnClickListener {
            var intent= Intent(this,WatchActivity::class.java)
            val videopath="android.resource://"+packageName+"/"+R.raw.video3
            intent.putExtra("videopath",videopath)
            startActivity(intent)
        }



    }
}