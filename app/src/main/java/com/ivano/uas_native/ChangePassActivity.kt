package com.ivano.uas_native

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ivano.uas_native.databinding.ActivityChangePassBinding
import org.json.JSONObject

class ChangePassActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePassBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePassBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)

        // Show back button on the toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        binding.btnChangePass.setOnClickListener {
            try {
                val oldPass = binding.txtOldPass.text.toString()
                val newPass = binding.txtNewPass.text.toString()
                val confPass = binding.txtrConfPass.text.toString()

                // Memeriksa apakah semua field teks diisi
                if (oldPass.isNotEmpty() && newPass.isNotEmpty() && confPass.isNotEmpty()) {

                    // Memeriksa apakah password baru dan konfirmasi password sama
                    if (newPass == confPass) {

                        // Memeriksa apakah password lama sesuai dengan yang diharapkan
                        // Anda dapat mengganti ini dengan mengambil password lama dari SharedPreferences
                        val sharedFile = "com.ivano.uas_native"
                        val shared: SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

                        // Panggil web service untuk mengubah password
                        val idUser = shared.getInt("ID", 0) // Mengambil ID pengguna dari SharedPreferences
                        Log.d("changePass", "ID Pengguna: $idUser")


                        changePassword(idUser, oldPass, newPass)

                    } else {
                        throw Exception("Password baru dan konfirmasi password tidak sesuai")
                    }
                } else {
                    throw Exception("Harap isi semua field teks")
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Terjadi kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Handle back button press
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    // Handle back button press
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun changePassword(idUser: Int, oldPassword: String, newPassword: String) {
        val url = "https://ubaya.me/native/160421054/change_pass.php"
        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    // Password berhasil diubah, Anda dapat menambahkan logika sesuai kebutuhan
                    Toast.makeText(this, "Password berhasil diubah", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, FragmentPrefs::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Gagal mengubah password
                    Toast.makeText(this, "Gagal mengubah password", Toast.LENGTH_SHORT).show()
                }
            },
            Response.ErrorListener { error ->
                // Kesalahan koneksi atau server
                Toast.makeText(this, "Terjadi kesalahan: ${error.message}", Toast.LENGTH_SHORT).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["idUsers"] = idUser.toString()
                params["old_password"] = oldPassword
                params["new_password"] = newPassword
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}
