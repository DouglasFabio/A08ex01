package com.example.a08ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val service = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(UserService::class.java)

        val btnPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val edtUsuario = findViewById<EditText>(R.id.editTextUsuario)

        btnPesquisar.setOnClickListener {
            val usuario = edtUsuario.text.toString()
            service.getUser(usuario).enqueue(object : Callback<UserResponse> {
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>){
                    val dadosRecebidos = response.body()
                    val usuario = dadosRecebidos?.login
                    val nome = dadosRecebidos?.name
                    findViewById<TextView>(R.id.txtNome).text = usuario + "\n" + nome
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable){
                    TODO("Not yet implemented")
                }
            })
        }
    }
}