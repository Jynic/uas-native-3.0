package com.ivano.uas_native

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ivano.uas_native.databinding.ActivityRegisterBinding
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLog.setOnClickListener {
            val intent = Intent(it.context, LoginActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnReg.setOnClickListener {
            // Call the method to perform registration
            registerUser()
        }
    }

    private fun registerUser() {
        val name = binding.txtName.text.toString()
        val password = binding.txtPass.text.toString()
        val urlProfile = binding.txtUrl.text.toString()

        val q = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421054/register.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                val obj = JSONObject(response)
                if (obj.getString("Result") == "Success") {
                    // Registration successful, navigate to the login page or perform any other action
                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    // Registration failed, display an error message
                    Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Handle errors or exceptions here
                Log.e("RegisterError", error.printStackTrace().toString())
                Toast.makeText(this, "Error registering user", Toast.LENGTH_SHORT).show()
            }
        ) {
            override fun getParams(): MutableMap<String, String>? {
                val params = HashMap<String, String>()
                params["name"] = name
                params["password"] = password
                params["urlProfile"] = urlProfile
                return params
            }
        }

        q.add(stringRequest)
    }
}
