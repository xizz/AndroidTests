package com.xi_zz.saveinstancestate


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_main.view.input_text
import kotlinx.android.synthetic.main.fragment_main.view.save_button
import kotlinx.android.synthetic.main.fragment_main.view.show_button


class MainFragment : Fragment() {

    private lateinit var editText: EditText
    private lateinit var saveButton: Button
    private lateinit var showButton: Button

    private var storedNumber = 10

    companion object {
        private const val TAG = "MainFragment"
        private const val EXTRA_NUM = "EXTRA_NUM"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView")
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        editText = view.input_text
        saveButton = view.save_button
        showButton = view.show_button

        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Unlike text, click events are not restored automatically.
        saveButton.setOnClickListener { storedNumber = editText.text.toString().toInt() }
        showButton.setOnClickListener { Toast.makeText(context, storedNumber.toString(), Toast.LENGTH_SHORT).show() }

        // Setup display
        if (savedInstanceState == null) {
            editText.setText(storedNumber.toString())
        }
    }

    // Always called before onDestroy() because it might be killed by Android, except back press.
    // Not called when just view is being destroy, FragmentManager.replace() for example.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(EXTRA_NUM, storedNumber)
    }

    // Called if the fragment is actually killed and restored
    // Called if the fragment's view is created for the first time and savedInstanceState will be null
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG, "onViewStateRestored " + savedInstanceState)

        storedNumber = savedInstanceState?.getInt(EXTRA_NUM) ?: storedNumber
    }

    /* Just for logs */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    // When DKA is on, fragments are destroyed as well as activities.
    // When fragments are being replaced by manager, it's not destroyed.
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    // When fragments are being replaced by manager, the view is destroyed, but not the fragment.
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

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
}
