package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

var tvInput: TextView? = null

var lastNumeric: Boolean = false
var lastDot: Boolean = false


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     tvInput = findViewById(R.id.tvInput)


    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
        lastNumeric = true
    }

    fun onClr(view: View){
        tvInput?.setText("")
        lastNumeric = false
        lastDot = false
    }

    fun onDot(view: View){
        if(lastNumeric && !lastDot){
            tvInput?.append(".")
            lastDot = true
        }
    }

    fun onOperation(view: View){
        if(lastNumeric && !isOpratorAdded(tvInput?.text.toString())){
            tvInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }

    private fun removeZeroAfterDot(result:String): String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length - 2)
        }
        return value
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text= removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text= removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text= removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(!prefix.isEmpty()){
                        one = prefix + one
                    }
                    if(two.toDouble() != 0.0 ){
                        tvInput?.text= removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    }

                }





            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun isOpratorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/") || value.contains("-") ||
                    value.contains("*") || value.contains("+")
        }
    }

}