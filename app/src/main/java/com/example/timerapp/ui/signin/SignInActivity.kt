package com.example.timerapp.ui.signin

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.timerapp.R
import com.example.timerapp.helpers.SessionManager
import com.example.timerapp.ui.main.MainActivity
import com.example.timerapp.widget.TextInput
import com.google.android.material.checkbox.MaterialCheckBox
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class SignInActivity : Activity()  {

    private var pred_username = ""
    private var pred_password = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        if(SessionManager.rememberSignedIn){
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //predefined username and password
        pred_username = "admin"
        pred_password = "admin123"

        val etusername: TextInput = findViewById(R.id.etUserName)
        val etpwd: TextInput = findViewById(R.id.etPassword)
        etusername.editText.setText(pred_username)
        etpwd.editText.setText(pred_password)
        val btnSignIn : Button = findViewById(R.id.btnSignIn)
        btnSignIn.setOnClickListener {
            try{
            val chkbx : MaterialCheckBox = findViewById(R.id.rememberme_checkBox)
            if(chkbx.isChecked){
                SessionManager.rememberSignedIn = true
            }
            val username = etusername.editText.text.toString()
            val pwd = etpwd.editText.text.toString()
            if(!username.isNullOrEmpty() && username.equals(pred_username) &&
                !pwd.isNullOrEmpty() && pwd.equals(pred_password)){

                SessionManager.userTime = 0L
                SessionManager.userLoginDate = ""
                val current = LocalDateTime.now()

                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                val formatted = current.format(formatter)
                SessionManager.userLoginDate = formatted
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
                else{
                AlertDialog.Builder(this)
                    .setTitle("Warning!")
                    .setMessage("Please login with the predefined username and password.")
                    .setCancelable(true)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
            }catch (ex: Exception){
                Log.e("SignInFragment ", " Error", ex)
            }
        }
    }
}