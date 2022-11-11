package com.example.ageinmins

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.SimpleTimeZone

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener{ view ->
            clickDatePicker(view)
        }

    }

    fun clickDatePicker(view: View){


        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, selectedYear, selectedMonth, selectedDay ->

            val tvSelectedDate = findViewById<TextView>(R.id.tvSelectDate)
            val selectedDate = "$selectedYear/${selectedMonth+1}/$selectedDay"
            tvSelectedDate.setText(selectedDate)
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val theDate = sdf.parse(selectedDate)
            val selectedDateInMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinute = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinute - selectedDateInMinutes

            val tvSelectedDateInMinutes = findViewById<TextView>(R.id.tvSelectedDateInMinutes)

            tvSelectedDateInMinutes.setText(differenceInMinutes.toString())


        }
            ,year
            ,month
            ,day)

        dpd.datePicker.maxDate =Date().time - 86400000
        dpd.show()
    }
}