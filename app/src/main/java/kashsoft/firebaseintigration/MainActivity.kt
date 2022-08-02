package kashsoft.firebaseintigration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var editTextName: EditText
    lateinit var buttonSend: Button
    lateinit var textViewName : TextView

    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    val reference : DatabaseReference = database.reference.child("Users")
    val reference2 : DatabaseReference = database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName= findViewById(R.id.editTextName)
        buttonSend = findViewById(R.id.buttonsend)
        textViewName = findViewById(R.id.textViewName)
        reference2.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val realName: String = snapshot.child("Users").child("name").value as String
                textViewName.text =realName
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        buttonSend.setOnClickListener {
            val userName : String = editTextName.text.toString()
            reference.child("userName").setValue(userName)
        }

    }
}