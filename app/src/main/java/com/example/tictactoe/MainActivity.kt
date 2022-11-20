package com.example.tictactoe


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity : AppCompatActivity(),View.OnClickListener {



    var PLAYER = true
    var TURN_COUNT = 0
    var boardstatus  = Array(3) { IntArray (3)}
    var _namePlayer1:String = "x"
    var _namePlayer2:String = "y"
    lateinit var board:Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _namePlayer1 = intent.getStringExtra(key1).toString()
        _namePlayer2 = intent.getStringExtra(key2).toString()


        backbtn.setOnClickListener{
            val i = Intent(this,MainActivity2::class.java)
            startActivity(i)
        }



        board = arrayOf(
            arrayOf(button,button1,button2),
            arrayOf(button3,button4,button5),
            arrayOf(button6,button7,button8)
        )

        for (i in board){
            for (button in i){
                button.setOnClickListener(this)
            }
        }

        initializeBoardStatus()

        resetBtn.setOnClickListener{
            TURN_COUNT = 0
            PLAYER = true
            initializeBoardStatus()
        }
    }

    private fun initializeBoardStatus() {
        TURN_COUNT = 0
        PLAYER = true
        updateDisplay("$_namePlayer1 turns")
        for (i in 0..2) {
            for (j in 0..2) {
                boardstatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View ) {
        when (view.id){
            R.id.button->{
                updateValue(row =0,col = 0,player = PLAYER)
            }
            R.id.button1->{
                updateValue(row =0,col = 1,player = PLAYER)
            }
            R.id.button2->{
                updateValue(row =0,col = 2,player = PLAYER)
            }
            R.id.button3->{
                updateValue(row =1,col = 0,player = PLAYER)
            }
            R.id.button4->{
                updateValue(row =1,col = 1,player = PLAYER)
            }
            R.id.button5->{
                updateValue(row =1,col = 2,player = PLAYER)
            }
            R.id.button6->{
                updateValue(row =2,col = 0,player = PLAYER)
            }
            R.id.button7->{
                updateValue(row =2,col = 1,player = PLAYER)
            }
            R.id.button8->{
                updateValue(row =2,col = 2,player = PLAYER)
            }
        }
        PLAYER = !PLAYER
        TURN_COUNT++

        if (PLAYER){
            updateDisplay("$_namePlayer1 Turns ")
        }else{
            updateDisplay("$_namePlayer2 Turns ")
        }

        if (TURN_COUNT == 9){
            updateDisplay("Oops!! Game Draw")

        }

        checkWinner()
    }

    private fun checkWinner() {
        // HoriZontal Rows
        for (i in 0..2)
        {
            if (boardstatus[i][0] == boardstatus[i][1] && boardstatus[i][0] == boardstatus[i][2] ){
                if (boardstatus[i][0] == 1)
                {
                    updateDisplay("Hurray!! $_namePlayer1 Wins the Match")
                    break
                }else if (boardstatus[i][0] == 0){
                    updateDisplay("Hurray!! $_namePlayer2 Wins the Match")
                    break
                }
            }
        }

        // Vertical Columns
        for (i in 0..2)
        {
            if (boardstatus[0][i] == boardstatus[1][i] && boardstatus[0][i] == boardstatus[2][i] ){
                if (boardstatus[0][i] == 1)
                {
                    updateDisplay("Hurray!! $_namePlayer1 Wins the Match")
                    break
                }else if (boardstatus[0][i] == 0){
                    updateDisplay("Hurray!! $_namePlayer2 Wins the Match")
                    break
                }
            }
        }

    // First Diagonal
        if (boardstatus[0][2] == boardstatus[1][1] && boardstatus[0][2] == boardstatus[2][0]){
            if (boardstatus[0][2] == 1)
            {
                updateDisplay("Hurray!! $_namePlayer1 Wins the Match")

            }else if (boardstatus[0][2] == 0){
                updateDisplay("Hurray!! $_namePlayer2 Wins the Match")

            }
        }

     // Second Diagonal
        if (boardstatus[0][0] == boardstatus[1][1] && boardstatus[0][0] == boardstatus[2][2]){
            if (boardstatus[0][0] == 1)
            {
                updateDisplay("Hurray!! $_namePlayer1 Wins the Match")

            }else if (boardstatus[0][0] == 0){
                updateDisplay("Hurray!! $_namePlayer2 Wins the Match")

            }
        }

    }

    private fun disableButton() {
        for (i in board){
            for (button in i){
                button.isEnabled =false
            }
        }
    }

    private fun updateDisplay(text: String) {
         displayTv.text = text
        if (text.contains("Winner")){
            disableButton()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun updateValue(row: Int, col: Int, player: Boolean) {
        val  text = if (player) "X" else "O"
        val value  = if (player) 1 else 0
          board[row][col].apply {
              isEnabled = false
              setText(text)
          }

        boardstatus[row][col] = value
    }
}