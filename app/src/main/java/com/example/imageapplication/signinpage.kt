package com.example.imageapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class signinpage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var emai: TextInputEditText
    private lateinit var lino: LinearLayout
    private lateinit var passowrd: TextInputEditText
    private lateinit var signin: Button
    private lateinit var signup: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signinpage)
        auth= FirebaseAuth.getInstance()
        emai=findViewById(R.id.email)
        passowrd=findViewById(R.id.password)
        signin=findViewById(R.id.login)
        signup=findViewById(R.id.signup)
        supportActionBar?.hide()
        lino=findViewById(R.id.lino)

        signup.setOnClickListener{
            var intentoi= Intent(this,signuppage::class.java)
            startActivity(intentoi)
        }

var pro:ProgressDialog=ProgressDialog(this)
        signin.setOnClickListener(
            {
                var em=emai.text.toString()
                var passo=passowrd.text.toString()
                pro.setTitle("Moving to main page !!")
                pro.show()
                auth.signInWithEmailAndPassword(em, passo)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            var intent= Intent(this,MainActivity::class.java)
                            val ial1=emai.text.toString()
                            emai.setText("")
                            passowrd.setText("")
                            intent.putExtra("email",ial1)
                            startActivity(intent)
                            pro.hide()
                            finish()

                        } else {
//
                            lino.visibility= View.VISIBLE
                            Toast.makeText(this@signinpage, "account not created", Toast.LENGTH_SHORT).show()
                            pro.hide()

                            // If sign in fails, display a message to the user.

                        }
                    }
            }
        )
    }
}