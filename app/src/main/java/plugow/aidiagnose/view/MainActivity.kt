package plugow.aidiagnose.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.jetbrains.anko.startActivity
import plugow.aidiagnose.R
import plugow.aidiagnose.databinding.ActivityMainBinding
import plugow.aidiagnose.viewModel.MainViewModel
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel:MainViewModel
    lateinit var mFragmentManager: FragmentManager
    private var fragmentId: Int = 0
    private lateinit var disposable:Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        viewModel= ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
        binding.viewModel=viewModel
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
        val navHeader=nav_view.getHeaderView(0)
        val navHeaderTitle=navHeader.findViewById<TextView>(R.id.navHeaderText)
        disposable = viewModel.getUser().subscribeBy(
                onSuccess = {
                    navHeaderTitle.text=getString(R.string.user_name, it.firstName, it.lastName)
                }
        )


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


    private fun navigateFun(id: Int) {
        when (id) {
            1-> startActivity<VisitActivity>()
            2-> startActivity<VisitActivity>()
        }
        fragmentId=0
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
