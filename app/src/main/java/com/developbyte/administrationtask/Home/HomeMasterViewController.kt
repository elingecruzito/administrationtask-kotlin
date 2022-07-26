package com.developbyte.administrationtask.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.developbyte.administrationtask.Abstract.AbstractViewController
import com.developbyte.administrationtask.App.InjectionManager
import com.developbyte.administrationtask.Interface.IMasterViewController
import com.developbyte.administrationtask.R
import java.util.*


class HomeMasterViewController : AppCompatActivity(), IMasterViewController {

    private val injectionManager = InjectionManager()
    private var fragments: HashMap<Int, AbstractViewController>? = null
    private var tags: ArrayList<Int>? = null

    val HOME_CONTROLLER = 0
    val INFOPROJECT_CONTROLLER = 1
    val NEWPROJECT_CONTROLLER = 2
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragments = HashMap()
        tags = ArrayList()

        val fr: FragmentManager = supportFragmentManager
        if (supportFragmentManager.fragments != null) {
            for (fragment in supportFragmentManager.fragments) {
                if (fragment != null) {
                    fr.beginTransaction().remove(fragment).commit()
                    supportFragmentManager.executePendingTransactions()
                }
            }
        }

        injectionManager.startHome(this)

    }

    override fun addFragment(fr: AbstractViewController?) {
        if (fr != null) {
            fragments?.put(fr.tag, fr)
        };
    }

    override fun presetFragment(tag: Int) {
        tags!!.add(0, tag)
        val fragment: Fragment? = fragments!![tag]
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.homeMasterView, fragment)
                .addToBackStack(null)
                .commit()
        }
        supportFragmentManager.executePendingTransactions()
    }

    override fun presetFragment2(tag: Int): Boolean {
        tags!!.add(0, tag)
        val fragment: Fragment? = fragments!![tag]
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.homeMasterView, fragment!!)
            .addToBackStack(null)
            .commit()

        return supportFragmentManager.executePendingTransactions()
    }

    override fun presentMenu(tag: Int) {
    }

    override fun onBackPressed() {
        val index = tags!!.removeAt(0)
        fragments!![index]!!.onBackPressed()
    }

    override fun finishThis() {
        finish()
    }
}