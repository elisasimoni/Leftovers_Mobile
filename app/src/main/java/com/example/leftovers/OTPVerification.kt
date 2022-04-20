package com.example.leftovers

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

class OTPVerification : AppCompatActivity() {

    private var selectedETPosition = 0
    private val resendEnable = false
    private val resendTime = 60
    private var otpE1 = findViewById<EditText>(R.id.otpET)
    private var otpE2 = findViewById<EditText>(R.id.otpET2)
    private var otpE3 = findViewById<EditText>(R.id.otpET3)
    private var otpE4 = findViewById<EditText>(R.id.otpET4)
    private var resendButton: TextView = findViewById(R.id.resendOTP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)


        val verifyButton: TextView = findViewById(R.id.verifyBtn)
        val otpEmail = findViewById<TextView>(R.id.otpEMAIL)
        val otpMobile = findViewById<TextView>(R.id.mobile_number_text)
        val getEmail = intent.getStringExtra("EMAIL")
        val getMobile = intent.getStringExtra("MOBILE")

        otpEmail.text = getEmail
        otpMobile.text = getMobile

        otpE1.addTextChangedListener(textWatcher)
        otpE2.addTextChangedListener(textWatcher)
        otpE3.addTextChangedListener(textWatcher)
        otpE4.addTextChangedListener(textWatcher)


        showKeyboard(otpE1)
        startCountDown()
        resendButton.setOnClickListener {
            if (resendEnable) {
                startCountDown()
            }
        }
        verifyButton.setOnClickListener {
            val generateOTP: String =
                otpE1.text.toString() + otpE2.text.toString() + otpE3.text.toString() + otpE4.text.toString()
            if (generateOTP.length == 4) {
                Toast.makeText(this, "OTP Verified", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "OTP Incorrect", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun showKeyboard(otpET: EditText) {
        otpET.requestFocus()

        val inputMenager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMenager.showSoftInput(otpET, 0)
    }


    private fun startCountDown() {

        resendButton.setTextColor(Color.parseColor("#BDBDBD"))

        object : CountDownTimer((resendTime * 1000).toLong(), 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                resendButton.text = "Resend OTP in " + millisUntilFinished / 1000 + "s"
            }


            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                resendButton.text = "Resend OTP"
                resendButton.setTextColor(Color.parseColor("#FF0000"))
                resendButton.isEnabled = true
            }
        }.start()


    }


    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            if(s.isNotEmpty()){

                when (selectedETPosition) {
                    0 -> {
                        selectedETPosition = 1
                        showKeyboard(otpE2)
                    }
                    1 -> {
                        selectedETPosition = 2
                        showKeyboard(otpE3)
                    }
                    2 -> {
                        selectedETPosition = 3
                        showKeyboard(otpE4)
                    }
                }


                }
            }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    fun oneKeyUp(
        keyCode: Int,
        event: KeyEvent,
    ): Boolean {

        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (selectedETPosition == 3) {
                selectedETPosition = 2
                showKeyboard(otpE3)
            } else if (selectedETPosition == 2) {
                selectedETPosition = 1
                showKeyboard(otpE2)
            } else if (selectedETPosition == 1) {
                selectedETPosition = 0
                showKeyboard(otpE1)
            }
            return true
        }
        else{
                //
        }
        return super.onKeyUp(keyCode, event)
    }



}
