package ke.ac.akademiks.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signUpBtn.setOnClickListener {
            register()
            saveUserToFirebaseDatabase()

            acc_present.setOnClickListener {
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                Log.i("MA", "account present")
            }
        }
    }

    private fun register(){
        val name = signUpName.text.toString()
        val email = signUpEmail.text.toString()
        val password = signUpPassword.text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty())
            Toast.makeText(this,"Please fill the blank spaces",Toast.LENGTH_LONG).show()
        return


        Log.i("MA","name is ${name}")
        Log.i("MA","email is ${email}")
        Log.i("MA","password is ${password}")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                //successful user creation else case
                Log.i("Main","successfully created user with uid: ${it.result?.user?.uid}")
            }
            .addOnFailureListener {
                Log.i("Main","failed to create user ${it.message}")
            }
    }

    private fun saveUserToFirebaseDatabase(profileImageURL: String){
        val uid = FirebaseAuth.getInstance().uid ?:""
        //val reference = FirebaseDatabase.getInstance().getReference("/users/$uid")
    }
}

