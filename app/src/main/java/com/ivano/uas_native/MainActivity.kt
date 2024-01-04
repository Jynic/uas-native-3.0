package com.ivano.uas_native

import PrefFragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ivano.uas_native.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    val fragment: ArrayList<Fragment> = ArrayList()
    var iduser:String? = "1"
    var judulCreate = ""
    var descCreate = ""
    var imgCreate = ""
    var genreCreate = ""
    var accessCreate = ""
    var firstparaCreate = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iduser = intent.getStringExtra("id")
        fragment.add(HomeFragment())
        fragment.add(FollowingFragment())
        fragment.add(CreateFragment())
        fragment.add(UsersFragment())
        fragment.add(FragmentPrefs2())

        binding.viewPager.adapter = MyAdapter(this, fragment)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.bottomNav.selectedItemId = binding.bottomNav.menu.getItem(position).itemId
            }
        })
        
        binding.bottomNav.setOnItemSelectedListener {
            binding.viewPager.currentItem = when(it.itemId){
                R.id.itemHome -> 0
                R.id.itemFollowing -> 1
                R.id.itemCreate -> 2
                R.id.itemUsers -> 3
                R.id.itemPrefs -> 4
                else -> 0
            }
            true
        }
    }
}