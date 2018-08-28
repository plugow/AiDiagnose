package plugow.aidiagnose.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity
import plugow.aidiagnose.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var mFragmentManager: FragmentManager
    private var fragmentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        mFragmentManager = supportFragmentManager
        val fragment = MapsFragment()
        val fragmentTransaction = mFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.detail_fragment_container, fragment, "Tag_path")
                fragmentTransaction.addToBackStack("Tag_path")
        fragmentTransaction.commit()
        nav_view.setNavigationItemSelectedListener(this)

        val actionBarDrawerToggle = object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            override fun onDrawerClosed(drawerView: View) {
                navigateFun(fragmentId)
                super.onDrawerClosed(drawerView)
            }

        }
        drawer_layout!!.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navVisits -> {
                fragmentId = 1
            }
            R.id.navAbout -> {
                fragmentId =2
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun navigateFun(fragmentId: Int) {
        when (fragmentId) {
            1-> startActivity<VisitActivity>()
            2-> startActivity<VisitActivity>()
        }
    }
}
