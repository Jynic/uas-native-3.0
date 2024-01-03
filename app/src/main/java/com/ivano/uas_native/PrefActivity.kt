package com.ivano.uas_native

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ivano.uas_native.databinding.ActivityPrefBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class PrefActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrefBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrefBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve user data from SharedPreferences
        val userlogin = getSharedPreferences("loginaccount", Context.MODE_PRIVATE)
        val id = userlogin.getInt("id", 0)
        val name = userlogin.getString("name", "null")
        val imgUrl = userlogin.getString("img_url", "null")

        Picasso.get().load(imgUrl).into(binding.userImg)
        binding.nameTxt.setText(name.toString())
        // Set user image using imgUrl in ImageView (you may need a library like Picasso or Glide)
        // Example using Glide:
        // Glide.with(this).load(imgUrl).into(binding.userImg)

        binding.btnSignOut.setOnClickListener {
            // Clear SharedPreferences to sign out the user
            val editor = userlogin.edit()
            editor.clear()
            editor.apply()

            // Navigate back to the sign-in activity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to prevent going back to it from the sign-in activity
        }

        binding.changePassBtn.setOnClickListener {
            // Add logic to change the password
            val oldPassword = binding.oldPass.text.toString()
            val newPassword = binding.newPass.text.toString()
            val retypeNewPassword = binding.retypePass.text.toString()

            if (newPassword == retypeNewPassword) {
                // Call API to update the password and pass user ID, old password, and new password
                updatePassword(id, oldPassword, newPassword)
            } else {
                // Show a message that the new passwords do not match
                Toast.makeText(this, "New passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePassword(id: Int, oldPassword: String, newPassword: String) {
        // Implement the logic to update the password using an API call
        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421054/updatepass.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    // Password successfully updated
                    Toast.makeText(this, "Password updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle the case where the password update failed
                    Toast.makeText(this, "Failed to update password", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Handle errors or exceptions here
                Log.e("UpdatePasswordError", error.printStackTrace().toString())
                Toast.makeText(this, "Error updating password", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["id"] = id.toString()
                params["oldPassword"] = oldPassword
                params["newPassword"] = newPassword
                return params
            }
        }

        q.add(stringRequest)
    }
}
