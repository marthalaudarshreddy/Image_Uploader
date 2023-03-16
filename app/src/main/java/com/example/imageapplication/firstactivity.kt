package com.example.imageapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation.INFINITE
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class firstactivity : AppCompatActivity() {
    private lateinit var linioli:LinearLayout
    private lateinit var shi:ImageView
    private lateinit var lin:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firstactivity)
        linioli=findViewById(R.id.linoioi)
        shi=findViewById(R.id.shine)
        lin=findViewById(R.id.linoioi)
        supportActionBar?.hide()

        var ko= TranslateAnimation(0f, 300f,0f,0f)
        ko.duration=1000
        ko.startOffset=500
        ko.fillAfter=true
        ko.repeatCount=INFINITE
        shi.startAnimation(ko)
//        Toast.makeText(this, lin.width.toString(), Toast.LENGTH_SHORT).show()
        linioli.setOnClickListener{
            var intent=Intent(this,signinpage::class.java)
            startActivity(intent)
        }
    }
}