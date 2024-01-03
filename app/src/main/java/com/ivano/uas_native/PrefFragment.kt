import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ivano.uas_native.LoginActivity
import com.ivano.uas_native.databinding.FragmentPrefBinding
import com.squareup.picasso.Picasso
import org.json.JSONObject

class PrefFragment : Fragment() {
    private lateinit var binding: FragmentPrefBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrefBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve user data from SharedPreferences
        val userlogin = requireActivity().getSharedPreferences("loginaccount", Context.MODE_PRIVATE)
        val id = userlogin.getInt("id", 0)
        val name = userlogin.getString("name", "null")
        val imgUrl = userlogin.getString("img_url", "null")

        // Load image using Picasso or Glide
        Picasso.get().load(imgUrl).into(binding.userImg)
        binding.nameTxt.setText(name.toString())

        binding.btnSignOut.setOnClickListener {
            // Clear SharedPreferences to sign out the user
            val editor = userlogin.edit()
            editor.clear()
            editor.apply()

            // Navigate back to the sign-in activity
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish() // Finish the current activity to prevent going back to it from the sign-in activity
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
                Toast.makeText(requireContext(), "New passwords do not match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePassword(id: Int, oldPassword: String, newPassword: String) {
        val q = Volley.newRequestQueue(requireContext())
        val url = "https://ubaya.me/native/160421054/updatepass.php"

        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val obj = JSONObject(response)
                if (obj.getString("result") == "OK") {
                    // Password successfully updated
                    Toast.makeText(requireContext(), "Password updated successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Handle the case where the password update failed
                    Toast.makeText(requireContext(), "Failed to update password", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                // Handle errors or exceptions here
                Log.e("UpdatePasswordError", error.printStackTrace().toString())
                Toast.makeText(requireContext(), "Error updating password", Toast.LENGTH_SHORT).show()
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
