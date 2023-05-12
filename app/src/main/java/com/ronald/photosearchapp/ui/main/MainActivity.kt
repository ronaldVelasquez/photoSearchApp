package com.ronald.photosearchapp.ui.main

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import com.ronald.photosearchapp.R
import com.ronald.photosearchapp.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), UpdateResults {
    private lateinit var binding: ActivityMainBinding
    private lateinit var seachInterface: SearchInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = MainFragment()
        seachInterface = fragment
        supportFragmentManager.beginTransaction().add(binding.mainFragment.id, fragment).commit()

        binding.sovSearch.setOnOrbClickedListener {
            binding.setSearch.visibility = View.VISIBLE
            binding.setSearch.requestFocus()
        }
        binding.setSearch.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                showKeyboard(v)
            }
        }


        binding.setSearch.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                v.clearFocus()
                v.visibility = View.GONE
                val text = binding.setSearch.text.toString()
                seachInterface.searchBy(text)
            }
            true;
        }
    }

    fun showKeyboard(view: View) {
        val inputManager:InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputManager != null) {
            inputManager.showSoftInput(view, 0)
        }
    }

    override fun isGetSomeResult(isResults: Boolean) {
        val textSearch = if (isResults) {
            "Search results for"
        } else {
            "No search results for"
        }
        binding.txtResults.text = "$textSearch \"${binding.setSearch.text.toString()}\""
    }
}