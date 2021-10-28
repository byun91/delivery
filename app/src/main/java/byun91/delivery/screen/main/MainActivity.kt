package byun91.delivery.screen.main

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import byun91.delivery.R
import byun91.delivery.databinding.ActivityMainBinding
import byun91.delivery.screen.main.home.HomeFragment
import byun91.delivery.screen.main.my.MyFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() = with(binding) {
        bottomNav.setOnItemSelectedListener(this@MainActivity)
        showFragment(HomeFragment.newInstance(), HomeFragment.TAG )
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean
    = when(item.itemId){
        R.id.menu_home ->{
            showFragment(HomeFragment.newInstance(), HomeFragment.TAG )
            true
        }
        R.id.menu_my ->{
            showFragment(MyFragment.newInstance(), MyFragment.TAG)
            true
        }
        else -> false
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        supportFragmentManager.fragments.forEach{ fm ->
            supportFragmentManager.beginTransaction().hide(fm).commit()
        }
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commit()
        } ?: kotlin.run {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer,fragment,tag)
                .commitAllowingStateLoss()
                // activity state 저장하지 못한 상태에서 commit 시 에러발생 방지
        }


    }
}