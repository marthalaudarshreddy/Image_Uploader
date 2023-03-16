package com.example.imageapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.TranslateAnimation
import android.widget.Button
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class forgot : AppCompatActivity() {
    private lateinit var curroi:TextInputEditText
    private lateinit var newpoi:TextInputEditText
    private lateinit var confioi:TextInputEditText
    private lateinit var changepoi:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
        curroi=findViewById(R.id.currentpassword)
        newpoi=findViewById(R.id.newpassword)
        confioi=findViewById(R.id.confirmpassword)
supportActionBar?.hide()
        changepoi=findViewById(R.id.chngepoi)

//        bottom.setOnNavigationItemReselectedListener {
//            when(it.itemId){
//                R.id.home->startActivity(Intent(this,MainActivity::class.java))
//                R.id.change->startActivity(Intent(this,forgot::class.java))
//            }
//        }
//        bottom.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.homeoi->startActivity(Intent(this,MainActivity::class.java))
//                R.id.changeoi->startActivity(Intent(this,forgot::class.java))
//                else -> {}
//            }
//            true
//        }

        var ial=intent.getStringExtra("email2")

        changepoi.setOnClickListener{
            if(curroi.text!!.isNotEmpty() && newpoi.text!!.isNotEmpty() && confioi.text!!.isNotEmpty())
            {
                if(newpoi!!.text.toString()!!.equals(confioi.text.toString()))
                {
                    var user = Firebase.auth.currentUser!!

// Get auth credentials from the user for re-authentication. The example below shows
// email and password credentials but there are multiple possible providers,
// such as GoogleAuthProvider or FacebookAuthProvider.
                    var credential = EmailAuthProvider
                        .getCredential(ial.toString(), curroi.text.toString())

// Prompt the user to re-provide their sign-in credentials
                    user.reauthenticate(credential)
                        .addOnCompleteListener {
                            if(it.isSuccessful)
                            {
                                user!!.updatePassword(newpoi.text.toString())
                                    .addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            var intoioioi=Intent(this,signinpage::class.java)
                                            Toast.makeText(this@forgot, "changed succesfully", Toast.LENGTH_SHORT)
                                                .show()
                                            startActivity(intoioioi)
                                        }
                                        else
                                        {
                                            Toast.makeText(this@forgot, "updation failed", Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                    }
                            }
                            else
                            {
                                Toast.makeText(this, "incorrect password", Toast.LENGTH_SHORT).show()
                            }

                        }
                }
                else
                {
                    Toast.makeText(this@forgot, "incorrect confirm password", Toast.LENGTH_SHORT).show()
                }
            }
            else
            {
                Toast.makeText(this@forgot, "fill all the info", Toast.LENGTH_SHORT).show()
            }
           
        }
    }
}