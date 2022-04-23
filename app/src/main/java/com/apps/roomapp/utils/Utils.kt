@file:Suppress("NAME_SHADOWING")

package com.apps.scanbarcode.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
//import com.bumptech.glide.request.RequestOptions

import java.io.IOException
import java.io.InputStream
import java.util.*

/**
 * Created by Panacea-Soft on 5/8/18.
 * Contact Email : teamps.is.cool@gmail.com
 */


object Utils {

    val isRTL: Boolean
        get() = isRTL(Locale.getDefault())
    private fun isRTL(locale: Locale): Boolean {
        val directionality = Character.getDirectionality(locale.displayName[0]).toInt()
        return directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT.toInt() || directionality == Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC.toInt()
    }
    fun desc():String{
        return "waktu_s"
    }

    var Asc = "jarak_m"
    var Desc = "jarak_m"

//    fun setImageToImageView(context: Context, imageView: ImageView, drawable: Int) {
//        val requestOptions = RequestOptions()
//            .diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
//            .skipMemoryCache(true)
//
//        Glide.with(context)
//            .load(drawable)
//            .apply(requestOptions)
//            .transition(DrawableTransitionOptions.withCrossFade())
//            .into(imageView)
//    }

    fun inputStreamToString(inputStream: InputStream): String? {
        try {
            val bytes = ByteArray(inputStream.available())

            inputStream.read(bytes, 0, bytes.size)

            return String(bytes)
        } catch (e: IOException) {
            return null
        }
    }

    fun loading(view: ProgressBar,visible:Boolean){
        if (visible){
            view.visibility = View.VISIBLE
        }else{
            view.visibility = View.INVISIBLE
        }
    }

    fun peringatan(context: Context, text:String){
        val toast = Toast.makeText(context,text, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }

    fun toggleUpDownWithAnimation(view: View): Boolean {
        if (view.rotation == 0f) {
            view.animate().setDuration(150).rotation(180f)
            return true
        } else {
            view.animate().setDuration(150).rotation(0f)
            return false
        }
    }

    fun toggleUpDownWithAnimation(view: View, duration: Int, degree: Int) {

        view.animate().setDuration(duration.toLong()).rotation(degree.toFloat())

    }

    /**
     * Function to convert milliseconds time to
     * Timer Format
     * Hours:Minutes:Seconds
     */
    fun milliSecondsToTimer(milliseconds: Long): String {
        var finalTimerString = ""
        val secondsString: String

        // Convert total duration into time
        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
        // Add hours if there
        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0$seconds"
        } else {
            secondsString = "" + seconds
        }

        finalTimerString = "$finalTimerString$minutes:$secondsString"

        // return timer string
        return finalTimerString
    }

    /**
     * Function to get Progress percentage
     */
    fun getProgressPercentage(currentDuration: Long, totalDuration: Long): Int {
        val percentage: Double?

        val currentSeconds = (currentDuration / 1000).toInt().toLong()
        val totalSeconds = (totalDuration / 1000).toInt().toLong()

        // calculating percentage
        percentage = currentSeconds.toDouble() / totalSeconds * 100

        // return percentage
        return percentage.toInt()
    }

    /**
     * Function to change progress to timer
     *
     * @param progress      -
     * @param totalDuration returns current duration in milliseconds
     */
    fun progressToTimer(progress: Int, totalDuration: Int): Int {
        var totalDuration = totalDuration
        val currentDuration: Int
        totalDuration = totalDuration / 1000
        currentDuration = (progress.toDouble() / 100 * totalDuration).toInt()

        // return current duration in milliseconds
        return currentDuration * 1000
    }

    fun getDrawableInt(context: Context?, name: String?): Int {
        return context?.resources!!.getIdentifier(name, "drawable", context.packageName)
    }

    fun getResourceInt(context: Context?, defType: String, name: String): Int {
        return context?.resources!!.getIdentifier(name, defType, context.packageName)
    }


    fun hideFirstFab(v: View) {
        v.visibility = View.GONE
        v.translationY = v.height.toFloat()
        v.alpha = 0f
    }

    fun twistFab(v: View, rotate: Boolean): Boolean {
        v.animate().setDuration(300)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                    }
                })
                .rotation(if (rotate) 165f else 0f)
        return rotate
    }

    fun showFab(v: View) {
        v.visibility = View.VISIBLE
        v.alpha = 0f
        v.translationY = v.height.toFloat()
        v.animate()
                .setDuration(300)
                .translationY(0f)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        super.onAnimationEnd(animation)
                    }
                })
                .alpha(1f)
                .start()
    }

    fun hideFab(v: View) {
        v.visibility = View.VISIBLE
        v.alpha = 1f
        v.translationY = 0f
        v.animate()
                .setDuration(300)
                .translationY(v.height.toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        v.visibility = View.GONE
                        super.onAnimationEnd(animation)
                    }
                }).alpha(0f)
                .start()
    }

}
