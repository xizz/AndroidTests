package com.xi_zz.saveinstancestate

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.input_text

// https://inthecheesefactory.com/blog/fragment-state-saving-best-practices/en

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private var storedNumber = 0

    companion object {
        private const val TAG = "MainActivity"
        private const val EXTRA_NUM = "EXTRA_NUM"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = input_text
    }

    // Called after onPause() and before onStop()
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")

        outState.putInt(EXTRA_NUM, storedNumber)
    }

    // Called after onStart() and before onPostCreate() and before onResume()
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.d(TAG, "onRestoreInstanceState")

        storedNumber = savedInstanceState.getInt(EXTRA_NUM)
    }


    fun saveNumber(v: View) {
        storedNumber = editText.text.toString().toInt()
    }

    fun showNumber(v: View) {
        Toast.makeText(this, storedNumber.toString(), Toast.LENGTH_SHORT).show()
    }

    /* Just for logs */

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d(TAG, "onPostCreate")
    }
}
