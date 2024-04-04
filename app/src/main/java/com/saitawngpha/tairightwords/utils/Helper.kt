package com.saitawngpha.tairightwords.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.saitawngpha.tairightwords.R
import com.saitawngpha.tairightwords.model.TaiRWModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */
object Helper {

    private var jsonData: TaiRWModel? = null
    private var mediaPlayer: MediaPlayer? = null

    fun playAudio(audioUrl: String) {
        mediaPlayer = MediaPlayer()

        try {
            mediaPlayer?.setDataSource(audioUrl)
            mediaPlayer?.prepare()
            mediaPlayer?.start()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        mediaPlayer?.setOnCompletionListener {
            // Audio playback has completed
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    fun getJsonData(context: Context): TaiRWModel {
        return jsonData ?: runCatching {
            context.resources.openRawResource(R.raw.tai_rightwords).use { inputStream ->
                val jsonString = BufferedReader(InputStreamReader(inputStream)).readText()

                // Parse the JSON string using Gson
                val gson = Gson()
                jsonData = gson.fromJson(jsonString, TaiRWModel::class.java)
            }

            jsonData ?: throw JsonParseException("Failed to parse JSON")
        }.getOrElse {
            // Handle exception
            println("Error while parsing JSON: ${it.localizedMessage}")
            throw it
        }
    }

    fun extractNameFromResponseData(responseData: String): String? {
        val nameRegex = """name=([^,]+)""".toRegex()
        val matchResult = nameRegex.find(responseData)

        return matchResult?.groupValues?.get(1)
    }

    fun addVowelsToConsonants(str: String): String {
        val consonantsRegex = Regex("[ၵၶငၸသၺတထၼပၽမယရလဝႁဢ](?![ႃိီုူၢႆၢႆွႂေႄၵ်င်ၺ်တ်ၼ်ပ်မ်ျြ])")
        return str.replace(consonantsRegex) { matchResult ->

            StringBuilder().apply {
                matchResult.groupValues.forEach{ result ->
                    //println(result + "ႃႉ")
                    append(result + "ႃႉ")
                }
            }
        }
    }

    fun openPlayStoreApp(context: Context, pkgName:String?){
        if(!pkgName.isNullOrEmpty()) {
            try {
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$pkgName")))
            } catch (e: ActivityNotFoundException) {
                context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$pkgName")
                    )
                )
            }
        }
    }
}