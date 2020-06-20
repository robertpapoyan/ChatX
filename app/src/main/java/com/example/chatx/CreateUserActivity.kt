package com.example.chatx

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlin.random.Random

class CreateUserActivity : AppCompatActivity() {

    var userAvatar = "profileDefault"
    var avatarColour = "[0.5, 0.5, 0.5, 1]"
    lateinit var mAuth: FirebaseAuth
    lateinit var referenceUsers: DatabaseReference
    var firebasUserID: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        mAuth = FirebaseAuth.getInstance()
    }

    fun generateUserAvatarImgClicked(view: View) {

        var random = Random
        var colour = random.nextInt(2)
        var avatar = random.nextInt(28)

        if (colour == 0) {

            userAvatar = "light$avatar"
        } else {

            userAvatar = "dark$avatar"
        }

        val resourceId = resources.getIdentifier(userAvatar, "drawable", packageName)
        generateUserAvatarImg.setImageResource(resourceId)
    }

    fun generateBackgroundColourBtnClicked(view: View) {

        val random = Random
        val r = random.nextInt(255)
        val g = random.nextInt(255)
        val b = random.nextInt(255)

        generateUserAvatarImg.setBackgroundColor(Color.rgb(r, g, b))

        val savedR = r.toDouble() / 255
        val savedG = g.toDouble() / 255
        val savedB = b.toDouble() / 255

        avatarColour = "[$savedR, $savedG, $savedB, 1]"
    }

    fun createUserBtnClicked(view: View) {

        val createUserName = createUserNameTxt.text.toString()
        val createUserEmail = createUserEmailTxt.text.toString()
        val createUserPassword = createUserPasswordTxt.text.toString()

        if (createUserName == "") {

            Toast.makeText(this, "Fill username please", Toast.LENGTH_LONG).show()

        } else if (createUserEmail == "") {

            Toast.makeText(this, "Fill email please", Toast.LENGTH_LONG).show()

        } else if (createUserPassword == "") {

            Toast.makeText(this, "Fill password please", Toast.LENGTH_LONG).show()

        } else {

            mAuth.createUserWithEmailAndPassword(createUserEmail, createUserPassword)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {

                        firebasUserID = mAuth.currentUser!!.uid
                        referenceUsers = FirebaseDatabase.getInstance()
                            .reference.child("Users").child(firebasUserID)

                        val userHashMap = HashMap<String, Any>()
                        userHashMap["uid"] = firebasUserID
                        userHashMap["userName"] = createUserName
                        userHashMap["userEmail"] = createUserEmail
                        userHashMap["userPassword"] = createUserPassword
                        userHashMap["userAvatar"] = userAvatar
                        userHashMap["avatarBackgroudColour"] = avatarColour

                        referenceUsers.updateChildren(userHashMap)
                            .addOnCompleteListener { task ->

                                if (task.isSuccessful) {

                                    val mainIntent = Intent(this, MainActivity::class.java)
                                    startActivity(mainIntent)
                                }
                            }
                    } else {

                        Toast.makeText(this, "something went wrong you prick", Toast.LENGTH_LONG)
                            .show()
                    }
                }
        }
    }
}
