package com.example.imageapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class signuppage : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    private lateinit var emai: TextInputEditText

    private lateinit var passowrd: TextInputEditText
    private lateinit var naem: TextInputEditText
    private lateinit var signup: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signuppage)
        emai=findViewById(R.id.emailso)
        passowrd=findViewById(R.id.passwordso)

        auth= FirebaseAuth.getInstance()
        var progressDialog: ProgressDialog = ProgressDialog(this)

        supportActionBar?.hide()
        signup=findViewById(R.id.signupso)
        naem=findViewById(R.id.nameso)
        signup.setOnClickListener{
            progressDialog.setTitle("Moving to main page !!")
            progressDialog.show()
            var ema=emai.text.toString()

            var passo=passowrd.text.toString()

            auth.createUserWithEmailAndPassword(ema, passo)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        var intent= Intent(this,MainActivity::class.java)
                        intent.putExtra("emails",emai.text.toString())
                        emai.setText("")
                        passowrd.setText("")
                        naem.setText("")
                        startActivity(intent)
                        progressDialog.dismiss()
                        finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        progressDialog.dismiss()
                        Toast.makeText(this@signuppage, "account is not", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}