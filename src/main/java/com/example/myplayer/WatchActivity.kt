package com.example.myplayer

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import java.util.Scanner

@Suppress("DEPRECATION")
class WatchActivity : AppCompatActivity() {
   companion object{
       var isFullScreen=false
       var isLock=false

   }

    lateinit var simpleExoPlayer: SimpleExoPlayer
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watch)
        val playerView=findViewById<PlayerView>( R.id.player)
        val progressbar=findViewById<ProgressBar>(R.id.progres_bar)
        val bt_fullscreen=findViewById<ImageView>(R.id.btn_fullscreen)
        val bt_lockscreen=findViewById<ImageView>(R.id.exo_lock)
        val btn_play=findViewById<ImageView>(R.id.exo_play)
        val btn_ffd=findViewById<ImageView>(R.id.exo_ffd)

        bt_fullscreen.setOnClickListener {
            if(!isFullScreen)
            {
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.baseline_fullscreen_exit_24))
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE)
            }
            else{
                bt_fullscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.baseline_fullscreen_24))
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            }

            isFullScreen=!isFullScreen

        }

        bt_lockscreen.setOnClickListener {
            if (!isLock){
                bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.baseline_lock_24))
            }
            else{
                bt_lockscreen.setImageDrawable(ContextCompat.getDrawable(applicationContext,R.drawable.baseline_lock_open_24))
            }
            isLock =! isLock
            lockScreen(isLock)
        }
        val simpleExoPlayer= SimpleExoPlayer.Builder(this)
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()
        playerView.player=simpleExoPlayer
        playerView.keepScreenOn=true
        simpleExoPlayer.addListener(object: Player.Listener{
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                // super.onPlayerStateChanged(playWhenReady, playbackState)
                if (playbackState== Player.STATE_BUFFERING)
                {
                    progressbar.visibility=View.VISIBLE
                }
                else if(playbackState== Player.STATE_READY)
                {
                    progressbar.visibility=View.GONE
                }
            }
        })

        val videopath=intent.getStringExtra("videopath")
        val videoSource= Uri.parse(videopath)
        val mediaItem= MediaItem.fromUri(videoSource)
        simpleExoPlayer.setMediaItem(mediaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.play()



        btn_ffd.setOnClickListener{
            simpleExoPlayer.seekForward()


        }


    }

    private fun lockScreen(lock: Boolean) {
        val sec_mid=findViewById<LinearLayout>(R.id.sec_conrolvid1)
        val sec_bottom=findViewById<LinearLayout>(R.id.sec_conrolvid2)
        if (lock){
            sec_mid.visibility= View.INVISIBLE
            sec_bottom.visibility= View.INVISIBLE
        }
        else{
            sec_mid.visibility= View.VISIBLE
            sec_bottom.visibility= View.VISIBLE
        }
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.pause()
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }


}