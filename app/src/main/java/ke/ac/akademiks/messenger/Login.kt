package ke.ac.akademiks.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            val email = loginEmail.text.toString()
            val password = loginPass.text.toString()

            Log.i("LA","email is ${email}")
            Log.i("LA","password is ${password}")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.i("Login","you logged in as ${it.result?.user?.uid}")
                }
        }
        //back to register code function is finish()
    }
}
