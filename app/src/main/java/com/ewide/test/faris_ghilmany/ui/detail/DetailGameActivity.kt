package com.ewide.test.faris_ghilmany.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ewide.test.faris_ghilmany.R
import com.ewide.test.faris_ghilmany.databinding.ActivityDetailGameBinding

class DetailGameActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDetailGameBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}