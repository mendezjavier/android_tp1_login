package com.example.javiertp1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    companion object {
        private val PASSWORD_REGEX = """(?=.*[A-Z])(?=.*[a-z])(?=.*\d)[a-zA-Z0-9]{7,}\z""".toRegex()
    }

    private lateinit var btnSubmit: Button
    private lateinit var emailAddress: EditText
    private lateinit var passwordText: EditText
    private lateinit var errorText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit = findViewById(R.id.button)
        emailAddress = findViewById(R.id.editTextTextEmailAddress)
        passwordText = findViewById(R.id.editTextTextPassword)
        errorText = findViewById(R.id.message)

        clearAllErrorMessages()
        setListeners()

    }

    private fun setListeners() {
        btnSubmit.setOnClickListener {
            if (validMailInput()) {
                if (validPassword()) {
                    showSuccess(getText(R.string.success_message))
                }
            }
        }
        addTextClearListener(emailAddress)
        addTextClearListener(passwordText)
    }

    private fun clearAllErrorMessages() {
        errorText.visibility = View.INVISIBLE
    }

    private fun addTextClearListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                clearAllErrorMessages()
            }
        })
    }

    private fun validMailInput(): Boolean {
        val mail = emailAddress.text

        if (mail.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            showError(getString(R.string.mail_error_message))
            return false
        }
        return true
    }

    private fun validPassword(): Boolean {
        val password = passwordText.text

        if (password.isBlank() || !password.matches(PASSWORD_REGEX)) {
            showError(getString(R.string.password_error_message))
            return false
        }
        return true
    }

    private fun showError(message: CharSequence) {
        errorText.text = message
        errorText.setTextColor(getColor(R.color.error))
        errorText.visibility = View.VISIBLE
    }

    private fun showSuccess(message: CharSequence) {
        errorText.text = message
        errorText.setTextColor(getColor(R.color.success))
        errorText.visibility = View.VISIBLE
    }


}