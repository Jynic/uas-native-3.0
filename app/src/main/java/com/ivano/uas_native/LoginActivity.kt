package com.ivano.uas_native

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ivano.uas_native.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    var get_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtReg.setOnClickListener{
            val intent= Intent(it.context, RegisterActivity::class.java)
            it.context.startActivity(intent)
        }

        binding.btnLog.setOnClickListener{
            Log.d("name", binding.txtName.text.toString())
            Log.d("pass", binding.txtPass.text.toString())

            val q = Volley.newRequestQueue(this)
            val url = "https://ubaya.me/native/160421054/login.php"
            val stringRequest = object: StringRequest(Request.Method.POST, url,
                {
                    val obj = JSONObject(it)
                    if(obj.getString("result") == "OK") {
                        get_id = obj.getInt("data")

                        // Store user data in SharedPreferences
                        val loginaccount = getSharedPreferences("loginaccount", Context.MODE_PRIVATE)
                        val editor = loginaccount.edit()

                        editor.putInt("id", get_id)
                        editor.putString("name", obj.getString("name"))
                        editor.putString("img_url", obj.getString("img_url"))
                        editor.apply()

                        Log.d("apiresult", get_id.toString())
                        updateList()
                    }
                },
                {
                    Log.e("apierror", it.printStackTrace().toString())
                }
            ){
                override fun getParams(): MutableMap<String, String>? {
                    val params = HashMap<String, String>()
                    params["name"] = binding.txtName.text.toString()
                    params["password"] = binding.txtPass.text.toString()
                    return params
                }
            }
            q.add(stringRequest)
        }
    }

    fun updateList() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val idUser = "ID"
    }
}
