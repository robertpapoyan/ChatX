package com.example.chatx

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun loginLoginBtnClicked(view: View) {

        val loginUserEmail = loginEmailTxt.text.toString()
        val loginUserPassword = loginPasswordTxt.text.toString()

        if (loginUserEmail == "") {

            Toast.makeText(this, "Fill email please", Toast.LENGTH_LONG).show()

        } else if (loginUserPassword == "") {

            Toast.makeText(this, "Fill password please", Toast.LENGTH_LONG).show()

        } else {

            mAuth.signInWithEmailAndPassword(loginUserEmail,loginUserPassword)
                .addOnCompleteListener { task ->

                    if(task.isSuccessful){

//                        val mainIntent = Intent(this, MainActivity::class.java)
//                        startActivity(mainIntent)
                        Toast.makeText(this,"Great! Now you are in", Toast.LENGTH_LONG).show()

                    } else{

                        Toast.makeText(this,"Something went wrong you prick", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    fun loginCreateUserBtnClicked(view: View){

        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity(createUserIntent)
    }
}
