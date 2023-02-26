package com.example.loginapp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var userName: String? = null
    var password: String? = null
    var isChecked: Boolean? = null
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            if (edtUserName.text.toString().equals("VKU") && edtPassword.text.toString().equals("123")) {
                Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login failed!!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (chkRememberMe.isChecked)
            saveData()
    }

    override fun onResume() {
        super.onResume()
        getSavedData()
    }

    private fun getSavedData() {
        sharedPreferences = this.getSharedPreferences("rememberMe", Context.MODE_PRIVATE)
        userName = sharedPreferences.getString("key_userName", null)
        password = sharedPreferences.getString("key_password", null)
        isChecked = sharedPreferences.getBoolean("key_isChecked", false)

        edtUserName.setText(userName)
        edtPassword.setText(password)
        chkRememberMe.isChecked = isChecked!!
    }

    private fun saveData() {
        sharedPreferences = this.getSharedPreferences("rememberMe", Context.MODE_PRIVATE)
        userName = edtUserName.text.toString()
        password = edtPassword.text.toString()
        isChecked = chkRememberMe.isChecked

        val editor = sharedPreferences.edit()
        editor.putString("key_userName", userName)
        editor.putString("key_password", password)
        editor.putBoolean("key_isChecked", isChecked!!)
        editor.apply()
        Toast.makeText(applicationContext, "Information saved", Toast.LENGTH_SHORT).show()
    }
}