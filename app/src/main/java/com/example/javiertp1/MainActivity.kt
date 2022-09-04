package com.example.javiertp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val PASSWORD_REGEX = """\A(?=.*[A-Z])(?=.*\d)[a-zA-Z0-9]{7,}\z""".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clearAllErrorMessages()
        setListeners()

    }

    private fun setListeners() {
        val btnSubmit: Button = findViewById(R.id.button)

        btnSubmit.setOnClickListener {
            if(validMailInput()) {
                if(validPassword()) {
                    showSuccess(getText(R.string.success_message))
                }
            }
        }

        val emailAddress: EditText = findViewById(R.id.editTextTextEmailAddress)
        addTextClearListener(emailAddress)
        val passwordText: EditText = findViewById(R.id.editTextTextPassword)
        addTextClearListener(passwordText)
    }

    private fun clearAllErrorMessages() {
        val mailErrorText: TextView = findViewById(R.id.message)
        mailErrorText.visibility = View.INVISIBLE
    }

    private fun addTextClearListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                clearAllErrorMessages()
            }
        })
    }

    private fun validMailInput(): Boolean {
        val emailAddress: EditText = findViewById(R.id.editTextTextEmailAddress)
        val mail = emailAddress.text

        if(mail.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            showError(getString(R.string.mail_error_message))
            return false
        }
        return true
    }

    private fun validPassword(): Boolean {
        val passwordText: EditText = findViewById(R.id.editTextTextPassword)
        val password = passwordText.text

        if(password.isBlank() || !password.matches(PASSWORD_REGEX)) {
            showError(getString(R.string.password_error_message))
            return false
        }
        return true
    }

    private fun showError(message: CharSequence) {
        val errorText: TextView = findViewById(R.id.message)
        errorText.text = message
        errorText.setTextColor(getColor(R.color.error))
        errorText.visibility = View.VISIBLE
    }

    private fun showSuccess(message: CharSequence) {
        val errorText: TextView = findViewById(R.id.message)
        errorText.text = message
        errorText.setTextColor(getColor(R.color.success))
        errorText.visibility = View.VISIBLE
    }


}