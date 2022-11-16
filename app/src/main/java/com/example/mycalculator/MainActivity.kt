package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var tvinput : TextView ?= null
    var lastNumaric : Boolean = false // declaring variable to ger track of that last inputted value is numaric or not
    var lastDot : Boolean = false // declaring variable for assuring that "." is used only a time in TextView.
    var equalPressed : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvinput = findViewById(R.id.mainText)
        }

    fun onClr(view : View){ // when clr is pressed this code executed.
        tvinput?.text = null
    }

    fun onEqual(view : View){
        if(lastNumaric){ // if the last entered value is a numaric value then
            var tvValue = tvinput?.text.toString() // convert tvInput to string
            var prefix = "" // declaring prefix to get track of operators.


            try{
                if(tvValue.startsWith("-")){ // if the textView's value start with "-" then we got crash, in order to prevent this crash we use this substring method to remove "-" from the textView. if we won't do this our app will crash in this situation.
                    prefix = "-"
                    // before this substring method -> -99
                    tvValue = tvValue.substring(1)
                    // after this substring method -> 99
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    // .splits split the value of string into the array we created named splitValue
                    // it splits the string with the delimiters or arguments we passed above in .split(arguments)
                    // 99 - 1 -> [99, 1]
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    // showing results after substracting both spilleted values.

                    tvinput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString()) // using removeZeroAfterDor function to get rid of ".0" in result.
                    equalPressed = true
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    // .splits split the value of string into the array we created named splitValue
                    // it splits the string with the delimiters or arguments we passed above in .split(arguments)
                    // 99 + 1 -> [99, 1]
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    // showing results after adding both spilleted values.
                    tvinput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                    equalPressed = true
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    // .splits split the value of string into the array we created named splitValue
                    // it splits the string with the delimiters or arguments we passed above in .split(arguments)
                    // 99 * 1 -> [99, 1]
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    // showing results after multiplying both spilleted values.
                    tvinput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                    equalPressed = true
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    // .splits split the value of string into the array we created named splitValue
                    // it splits the string with the delimiters or arguments we passed above in .split(arguments)
                    // 99 / 1 -> [99, 1]
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    // showing results after dividing both spilleted values.
                    tvinput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                    equalPressed = true
                }


            }catch (e: java.lang.ArithmeticException){
                e.printStackTrace()
            }

        }

    }

    fun onDecimal(view : View){ // asssuring the "." exixts only once in TextView
        if (lastNumaric && !lastDot){
            tvinput?.append(".")
            lastNumaric = false
            lastDot = true
        }
    }

    fun onDigit(view: View){ // on clicking any digit the text which that digit contains appends in TextView and displays on screen.
       if(equalPressed){
           tvinput?.text = ""
           equalPressed = false
       }
        tvinput?.append((view as Button).text)
        lastNumaric = true
        lastDot = false
    }

    fun onOperator(view : View){ // when click on any operator this code executes.
        tvinput?.text?.let{
            if(lastNumaric && !isOperatorAdded(it.toString())){
                tvinput?.append((view as Button).text)
                lastNumaric = false
                lastDot = false
            }
        }
    }


    private fun removeZeroAfterDot(result : String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length-2)
        }
        return value
    }

    fun isOperatorAdded(value : String) : Boolean{ // chcking wheather any operator is added or not.
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}