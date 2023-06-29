package com.example.taskmanager.ui.auth.phone

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class PhoneNumberFormattingWatcher(private val editText: EditText, private val countryCode: String) : TextWatcher {

    private var isFormatting = false

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (isFormatting) {
            return
        }

        isFormatting = true

        val digitsOnly = s?.toString()?.replace("[^\\d]".toRegex(), "") ?: ""
        val formattedNumber = formatPhoneNumber(digitsOnly, countryCode)

        editText.setText(formattedNumber)
        editText.setSelection(formattedNumber.length)

        isFormatting = false
    }

    private fun formatPhoneNumber(digits: String, countryCode: String): String {
        val formattedNumber = StringBuilder()

        formattedNumber.append(countryCode)

        var index = 0
        while (index < digits.length) {
            if (index == 3 || index == 5) {
                formattedNumber.append("-")
            }

            formattedNumber.append(digits[index])
            index++
        }

        return formattedNumber.toString()
    }
}