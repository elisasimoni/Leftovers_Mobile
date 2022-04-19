package activities

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
import com.example.leftovers.R

class OTPVerification : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)
        val otpE1 = findViewById<EditText>(R.id.otpET)
        val otpE2 = findViewById<EditText>(R.id.otpET2)
        val otpE3 = findViewById<EditText>(R.id.otpET3)
        val otpE4 = findViewById<EditText>(R.id.otpET4)
        val resendButton: TextView = findViewById(R.id.resendOTP)
        val verifyButton: TextView = findViewById(R.id.verifyBtn)
        val otpEmail = findViewById<TextView>(R.id.otpEMAIL)
        val otpMobile = findViewById<TextView>(R.id.mobile_number_text)
        val getEmail = intent.getStringExtra("EMAIL")
        val getMobile = intent.getStringExtra("MOBILE")
        val resendEnable = false
        val resendTime = 60
        val selectedETPosition = false

        otpEmail.text = getEmail
        otpMobile.text = getMobile
/*
        otpE1.addTextChangedListener(textWatcher)
        otpE2.addTextChangedListener(textWatcher)
        otpE3.addTextChangedListener(textWatcher)
        otpE4.addTextChangedListener(textWatcher)*/
        //BY DEFAULT OPEN KEYBOARD AT OPE1

        showKeyboard(otpE1)
        startCountDown(resendEnable, resendButton, resendTime)
        resendButton.setOnClickListener {
            if (resendEnable) {
                startCountDown(resendEnable, resendButton, resendTime)
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


    private fun startCountDown(resendEnable: Boolean, resendButton: TextView, resendTime: Int) {

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

}
/*
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
            if(s.length > 0){
                if(selectedETPosition == 0){
                    selectedETPosition = 1
                    showKeyboard(otpET2)
                }
                else if(selectedETPosition == 1){
                    selectedETPosition = 2
                    showKeyboard(otpET3)
                }
                else if(selectedETPosition == 2){
                    selectedETPosition = 3
                    showKeyboard(otpET4)
                }


                }
            }

        }

        fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }*/
/*
    fun oneKeyUp(
        E1: EditText,
        E2: EditText,
        E3: EditText,
        E4: EditText,
        keyCode: Int,
        event: KeyEvent,
        setpo: Int
    ): Boolean {
        var setp = setpo
        if (keyCode == KeyEvent.KEYCODE_DEL) {
            if (setp == 3) {
                setp = 2
                showKeyboard(E3)
            } else if (setp == 2) {
                setp = 1
                showKeyboard(E2)
            } else if (setp == 1) {
                setp = 0
                showKeyboard(E1)
            }
            return true
        }
        else{

        }
        return super.onKeyUp(keyCode, event)
    }
}*/



