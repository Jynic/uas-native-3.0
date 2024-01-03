package com.ivano.uas_native

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ivano.uas_native.databinding.ActivityParagraphCardBinding

class paragraph_card : AppCompatActivity() {
    private lateinit var binding : ActivityParagraphCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paragraph_card)
    }
}