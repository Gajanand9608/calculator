package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var lastNumeric : Boolean =false
    var lastDot : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSeven.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnEight.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnNine.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnFour.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnFive.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnSix.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnThree.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnTwo.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnOne.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnZero.setOnClickListener { view ->
            onDigit(view)
        }
        binding.btnDivide.setOnClickListener { view ->
            onOperator(view)
        }
        binding.btnMulti.setOnClickListener { view ->
            onOperator(view)
        }
        binding.btnMinus.setOnClickListener { view ->
            onOperator(view)
        }
        binding.btnPlus.setOnClickListener { view ->
            onOperator(view)
        }
        binding.btnDecimal.setOnClickListener { view ->
            onDecimal(view)
        }
        binding.btnEqual.setOnClickListener { view ->
            onEqual(view)
        }
        binding.btnClear.setOnClickListener { view ->
            onClear(view)
        }


    }
    fun onDigit(view:View){
        binding.tvInput.append((view as Button).text)
        lastNumeric=true
    }

    fun onClear(view:View){
        binding.tvInput.text=""
        lastDot=false
        lastNumeric=false
    }

    fun onDecimal(view:View){
        if(lastNumeric && !lastDot){
            binding.tvInput.append(".")
            lastDot=true
            lastNumeric=false
        }
    }

    fun onEqual(view:View){
        if(lastNumeric){
            var tvValue = binding.tvInput.text.toString()
            var prefix =""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue =tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                        var value1 = splitValue[0]
                        if(!prefix.isEmpty()){
                            value1 = prefix + value1
                        }
                        var value2 = splitValue[1]
                        binding.tvInput.text = removeZeroAfterDot ((value1.toDouble()-value2.toDouble()).toString())
                }else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    var value1 = splitValue[0]
                    if(!prefix.isEmpty()){
                        value1 = prefix + value1
                    }
                    var value2 = splitValue[1]
                    binding.tvInput.text = removeZeroAfterDot ((value1.toDouble()+value2.toDouble()).toString())
                }else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    var value1 = splitValue[0]
                    if(!prefix.isEmpty()){
                        value1 = prefix + value1
                    }
                    var value2 = splitValue[1]
                    binding.tvInput.text = removeZeroAfterDot ((value1.toDouble()*value2.toDouble()).toString())
                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    var value1 = splitValue[0]
                    if(!prefix.isEmpty()){
                        value1 = prefix + value1
                    }
                    var value2 = splitValue[1]
                    binding.tvInput.text = removeZeroAfterDot ((value1.toDouble()/value2.toDouble()).toString())
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        if(lastNumeric && !isOperatorAdded(binding.tvInput.text.toString())){
            binding.tvInput.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }
    private fun isOperatorAdded(value: String) : Boolean {
        return if(value.startsWith("-")){
            return false
        }else{
            value.contains("/") || value.contains("*") || value.contains("-") || value.contains("+")
        }
    }

    private fun removeZeroAfterDot(result:String):String {
        var value = result
//        var countZero=0
//        for (i in value.length - 1 downTo 0) {
//            if(value[i]=='0'){
//                countZero++;
//            }else{
//                break
//            }
//        }
//        value = result.substring(0,result.length-countZero)
        if(value.contains(".0")){
            value = result.substring(0,result.length-2)
        }


        return value
    }

}

