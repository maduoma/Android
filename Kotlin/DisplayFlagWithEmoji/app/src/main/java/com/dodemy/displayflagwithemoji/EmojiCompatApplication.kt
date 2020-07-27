package com.dodemy.displayflagwithemoji

import android.app.Application
import android.util.Log
import androidx.core.provider.FontRequest
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig


class EmojiCompatApplication : Application() {
    companion object {
        private const val TAG = "EmojiCompatApplication"

        // Change to false when you want to use downloadable Emoji font
        private val USE_BUNDLED_EMOJI = true
    }

    override fun onCreate() {
        super.onCreate()

        val config: EmojiCompat.Config
        if (USE_BUNDLED_EMOJI) {
            config = BundledEmojiCompatConfig(applicationContext)
        } else {
            val fontRequest = FontRequest(
                "com.google.android.gms.fonts",
                "com.google.android.gms",
                "Noto Color Emoji Compat",
                R.array.com_google_android_gms_fonts_certs
            )
            config = FontRequestEmojiCompatConfig(applicationContext, fontRequest)
                .setReplaceAll(true)
                .registerInitCallback(object : EmojiCompat.InitCallback() {
                    override fun onInitialized() {
                        Log.i(TAG, "EmojiCompat initialized")
                    }

                    override fun onFailed(throwable: Throwable?) {
                        Log.e(TAG, "EmojiCompat initialization failed", throwable)
                    }
                })
        }
        EmojiCompat.init(config)
    }
}