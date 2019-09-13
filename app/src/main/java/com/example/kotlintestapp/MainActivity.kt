package com.example.kotlintestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        one.setOnClickListener { append("1", true) }
        two.setOnClickListener { append("2", true) }
        three.setOnClickListener { append("3", true) }
        four.setOnClickListener { append("4", true) }
        five.setOnClickListener { append("5", true) }
        six.setOnClickListener { append("6", true) }
        seven.setOnClickListener { append("7", true) }
        eight.setOnClickListener { append("8", true) }
        nine.setOnClickListener { append("9", true) }
        zero.setOnClickListener { append("0", true) }
        dot.setOnClickListener { append(".", true) }

        //Operators
        plus.setOnClickListener { append("+", false) }
        minus.setOnClickListener { append("-", false) }
        mul.setOnClickListener { append("*", false) }
        divide.setOnClickListener { append("/", false) }
        open.setOnClickListener { append("(", false) }
        close.setOnClickListener { append(")", false) }

        clear.setOnClickListener {
            expression.text = ""
            tvResult.text = ""
        }

        back.setOnClickListener {
            val string = expression.text.toString()
            if(string.isNotEmpty()){
                expression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        equals.setOnClickListener {
            try {

                val expression = ExpressionBuilder(expression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            }catch (e:Exception){
                Log.d("Exception"," message : " + e.message )
            }
        }

    }

    fun append(string: String, canClear: Boolean) {

        if(tvResult.text.isNotEmpty()){
            expression.text = ""
        }

        if (canClear) {
            tvResult.text = ""
            expression.append(string)
        } else {
            expression.append(tvResult.text)
            expression.append(string)
            tvResult.text = ""
        }
    }
}
