package com.example.imageapplication

import android.Manifest;
import android.annotation.SuppressLint
import android.app.appsearch.SetSchemaRequest.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import java.io.File
import java.lang.reflect.Field
import kotlin.contracts.contract

class MainActivity : AppCompatActivity() {
    private lateinit var image:ImageView
    private lateinit var butto:Button
    private lateinit var lo:Button
    private lateinit var buoi:Button
private lateinit var imageuri:Uri
    private lateinit var auth:FirebaseAuth
    private lateinit var changepasooi:Button
    private lateinit var permissonoi:ActivityResultLauncher<Array<String>>

    private var contract=registerForActivityResult(ActivityResultContracts.TakePicture())
    {image.setImageURI(null)
        image.setImageURI(imageuri)
    }
    private var isreadper=false
    private var isloca=false
    private var isrecordd=false
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
supportActionBar?.hide()
        image=findViewById(R.id.imageView3)
        butto=findViewById(R.id.button)
        lo=findViewById(R.id.logutsoi)
        buoi=findViewById(R.id.button2)
permissonoi=registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){permissions->
    isreadper=permissions[Manifest.permission.READ_EXTERNAL_STORAGE]?: isreadper
    isloca=permissions[Manifest.permission.ACCESS_FINE_LOCATION]?: isloca
    isrecordd=permissions[Manifest.permission.RECORD_AUDIO]?: isrecordd

}



imageuri= createuri()!!


buoi.setOnClickListener {
    contract.launch(imageuri)
}


        isrecordd=ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) ==PackageManager.PERMISSION_GRANTED
        isloca=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) ==PackageManager.PERMISSION_GRANTED
        isreadper=ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED

var premisionrequest:MutableList<String> =ArrayList()
        if(!isreadper)
        {
            premisionrequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if(!isloca)
        {
            premisionrequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if(!isrecordd)
        {
            premisionrequest.add(Manifest.permission.RECORD_AUDIO)
        }
        if(premisionrequest.isNotEmpty())
        {
            permissonoi.launch((premisionrequest.toTypedArray()))
        }
        var ial2 = intent.getStringExtra("email")
        var ail2 = intent.getStringExtra("emails")
        auth=FirebaseAuth.getInstance()
        lo.setOnClickListener {

            auth.signOut()
            Toast.makeText(this, "successfully logout", Toast.LENGTH_SHORT).show()
            var into = Intent(this, signinpage::class.java)
            startActivity(into)

        }
        changepasooi=findViewById(R.id.changepassoi)

        changepasooi.setOnClickListener{
            Toast.makeText(this, "redirected to change password", Toast.LENGTH_SHORT).show()
auth=FirebaseAuth.getInstance()
            var intoij=Intent(this,forgot::class.java)
            if(ial2==null)
            {
                intoij.putExtra("email2",ail2)

            }
            else
            {
                intoij.putExtra("email2",ial2)

            }
            startActivity(intoij)

        }
//        bottom=findViewById(R.id.bottomnavoi)
//        bottom.setOnNavigationItemReselectedListener {
//            when(it.itemId){
//                R.id.homeoi->startActivity(Intent(this,MainActivity::class.java))
//                R.id.changeoi->startActivity(Intent(this,forgot::class.java))
//            }
//        }
//        isreadper=ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
        var ko=registerForActivityResult(ActivityResultContracts.GetContent())
        {
            image.setImageURI(it)
        }
        butto.setOnClickListener{
            ko.launch("image/*")
        }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.navmenu,menu)
//        return super.onCreateOptionsMenu(menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//       if(item.itemId==R.id.logout)
//       {
//           auth.signOut()
//           var inte=Intent(this,signinpage::class.java)
//           startActivity(inte)
//           finish()
//       }
//        return super.onOptionsItemSelected(item)

    }
    private fun createuri():Uri?
    {
        val image=File(applicationContext.filesDir,"camera_photo.png")
        return FileProvider.getUriForFile(applicationContext,"com.example.imageapplication.contentprovider",image)
    }
}