
package com.ivano.uas_native

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ivano.uas_native.databinding.ActivityLoginBinding
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var account: ArrayList<User> = ArrayList()

    val IDACCOUNT = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val t = Volley.newRequestQueue(this)
        val url = "https://ubaya.me/native/160421054/login.php"
        var stringRequest = StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                if (obj.getString("result") == "OK") {
                    val data = obj.getJSONArray("data")
//                    for (i in 0 until data.length()) {
//                        val plyObj = data.getJSONObject(i)
//                        val users = User(
//                            plyObj.getInt("id"),
//                            plyObj.getString("username"),
//                            plyObj.getString("password"),
//                            plyObj.getString("img_url")
//                        )
//                        account.add(users)
//                    }
                    val plyObj = data.getJSONObject(0)
                    val users = User(
                        plyObj.getInt("id"),
                        plyObj.getString("username"),
                        plyObj.getString("password"),
                        plyObj.getString("img_url")
                    )
                    account.add(users)
                }
                Log.d("cekisiarray", account.toString())
            },
            Response.ErrorListener {
                // Handle error here
                Log.e("apiresult", it.message.toString())
            }
        )
        t.add(stringRequest)

        binding.btnLog.setOnClickListener {
            var username = binding.txtLoginName.text.toString()
            var password = binding.txtLoginPass.text.toString()
            var status = false
            Log.d("btnclick", account.toString())
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "$username Data cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (account.isNotEmpty()) {
                    val user = account[0]
                    if (user.username == username && user.password == password) {
                        Toast.makeText(this, "$username Sign In Success", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra(IDACCOUNT, user.id)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Username or Password is Incorrect", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "No user data available", Toast.LENGTH_SHORT).show()
                }
            }
//            if(username.isEmpty() || password.isEmpty()){
//                Toast.makeText(this,"${username} Data cannot be empty", Toast.LENGTH_SHORT).show()
//
//            }else{
////                for(accounts in account){
////                    if(accounts.username == username && accounts.password == password){
////                        status = true
////                        Toast.makeText(this, "${username} Sign In Success", Toast.LENGTH_SHORT).show()
////                        val intent = Intent(this, MainActivity::class.java)
////                        var idAccount = accounts.id
////                        intent.putExtra(IDACCOUNT, idAccount)
//////                        Global.id_user = idAccount
////                        startActivity(intent)
////                        finish()
////                        break
////                    }
////                    else{
////                        status = false
////
////                    }
////                }
//                if(account[0].username == username && account[0].password == password){
//                    status = true
//                    Toast.makeText(this, "${username} Sign In Success", Toast.LENGTH_SHORT).show()
//                    val intent = Intent(this, MainActivity::class.java)
//                    var idAccount = account[0].id
//                    intent.putExtra(IDACCOUNT, idAccount)
////                        Global.id_user = idAccount
//                    startActivity(intent)
//                    finish()
//                }
//                else{
//                    status = false
//
//                }
//                if(!status){
//                    Toast.makeText(this,"Username or Password is Incorrect", Toast.LENGTH_SHORT).show()
//                }
//            }
        }
        binding.txtReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}