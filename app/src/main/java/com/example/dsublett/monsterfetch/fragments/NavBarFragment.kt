package com.example.dsublett.monsterfetch.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dsublett.monsterfetch.R
import kotlinx.android.synthetic.main.fragment_nav_bar.view.*

class NavBarFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nav_bar, container, false).apply {
            this.monstersButton.setOnClickListener{
                replaceRvFragment(MonstersFragment())
            }
            this.classesButton.setOnClickListener{
                replaceRvFragment(ClassesFragment())
            }
            this.spellsButton.setOnClickListener{
                replaceRvFragment(SpellsFragment())
            }
        }
    }
    private fun replaceRvFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.listContainer, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }
}
