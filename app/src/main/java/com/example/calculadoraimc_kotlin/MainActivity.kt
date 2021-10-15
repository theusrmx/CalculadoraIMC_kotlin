package com.example.calculadoraimc_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        buttonCalcular.setOnClickListener {
            if(edtAltura.text.toString() != "" || edtPeso.text.toString() != ""){
                calcularIMC(edtPeso.text.toString(), edtAltura.text.toString())
            }else{
                Toast.makeText(this, "Preencha todos os campos para continuar.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun calcularIMC(peso: String, altura: String){
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()


        if(peso != null && altura != null){
            val imc = peso / (altura * altura)

            val df = DecimalFormat("#.##")

            if (imc <= 18.5){
                txtResultado.text = "Seu IMC é ${df.format(imc)}. Você está abaixo do peso."
                imgResultado.setImageResource(R.drawable.imc_resultado_vermelho)
            }else if(imc > 18.5 && imc <= 24.9 ){
                txtResultado.text = "Seu IMC é ${df.format(imc)}. Você está com o seu peso adequado."
                imgResultado.setImageResource(R.drawable.imc_resultado_verde)
            }else if(imc >= 25 && imc < 29.9){
                txtResultado.text = "Seu IMC é ${df.format(imc)}. Você está no sobrepeso."
                imgResultado.setImageResource(R.drawable.imc_resultado_amarelo)
            }else if(imc >= 30 && imc < 34.9){
                txtResultado.text = "Seu IMC é ${df.format(imc)}. Este nível se encontra em Obesidade de grau I."
                imgResultado.setImageResource(R.drawable.imc_resultado_amarelo_v2)
            }else if(imc >= 35 && imc < 39.9){
                txtResultado.text = "Seu IMC é ${df.format(imc)}. Este nível se encontra em Obesidade de grau II."
                imgResultado.setImageResource(R.drawable.imc_resultado_amarelo_v2)
            }else if(imc >= 40){
                txtResultado.text = "Seu IMC é ${df.format(imc)}. Você está com Obesidade de grau III (Mórbida)."
                imgResultado.setImageResource(R.drawable.imc_resultado_vermelho)
            }

            cardViewResult.visibility = View.VISIBLE
        }
    }

}

