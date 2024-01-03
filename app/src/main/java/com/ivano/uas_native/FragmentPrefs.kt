package com.ivano.uas_native

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.ivano.uas_native.databinding.FragmentPrefsBinding
import org.json.JSONObject


@SuppressLint("StaticFieldLeak")
private lateinit var binding: FragmentPrefsBinding

class FragmentPrefs : Fragment() {

    private lateinit var binding: FragmentPrefsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrefsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val q = Volley.newRequestQueue(activity)
        val url = "https://ubaya.me/native/160421054/get_user.php"

        // Mengambil ID pengguna dari SharedPreferences
        //Ambil userID yang login
        var sharedFile = "com.ivano.uas_native"
        var shared: SharedPreferences = requireContext().getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
        val idUser = shared.getInt("ID",0)

        Log.d("FragmentPrefs", "ID Pengguna: $idUser")

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            {
                Log.d("apiresult", it)
                val obj = JSONObject(it)
                val arrayData = obj.getJSONArray("data")
                if (arrayData.length() > 0) {
                    val userData = arrayData.getJSONObject(0)
                    val url_foto = userData.getString("url_foto")

                    Picasso.get().load(url_foto).into(binding.profileImageView)

                    binding.txtUsername.text=userData.getString("username")
                }
            },
            {
                Log.e("apiresult", it.message.toString())
            }
        ) {
            // Override metode getParams() untuk menambahkan parameter ID pengguna
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["idUsers"] = idUser.toString()
                return params
            }
        }
        q.add(stringRequest)

        binding.signOutButton.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            // ini untuk tutup aktivitas di main
            activity?.finish()
        }

        binding.changePasswordButton.setOnClickListener {
            val intent = Intent(activity, ChangePassActivity::class.java)
            startActivity(intent)
        }

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            Log.d("FragmentPrefs", "Before Dark Mode Change - isChecked: $isChecked")

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.darkModeSwitch.isChecked = isChecked
            } else {
                binding.darkModeSwitch.isChecked = false
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            Log.d("FragmentPrefs", "After Dark Mode Change - isChecked: $isChecked")
        }

        binding.notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // In-app notifications are enabled
            } else {
                // In-app notifications are disabled
            }
        }
    }
}