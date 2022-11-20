package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*
import java.security.Key


const val key1 = "player1"
const val key2 = "player2"

const val key11 = "player1"
const val key22 = "player2"

class MainActivity2 : AppCompatActivity() {


    lateinit var _playerName1 : String
    lateinit var _playerName2 : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        playgame.setOnClickListener{
            _playerName1 = player1.text.toString()
            _playerName2 = player2.text.toString()

            val intent = Intent(this,MainActivity::class.java)
            if(_playerName1.isNotBlank() && _playerName2.isNotEmpty()) {
                intent.putExtra(key1, _playerName1)
                intent.putExtra(key2, _playerName2)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Enter the name..!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}