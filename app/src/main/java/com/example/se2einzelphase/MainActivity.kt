package com.example.se2einzelphase

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var mButton: Button? = null
    private var buttonSecondEx: Button? = null
    private var inputField: EditText? = null
    private var serverResponse:TextView? = null
    private lateinit var myNet: Thread;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        //Ex 1
        inputField = findViewById<View>(R.id.editTextNumber) as EditText?
        serverResponse = findViewById<View>(R.id.textViewResponse) as TextView?
        mButton = findViewById<View>(R.id.button_send) as Button?
        mButton?.setOnClickListener(View.OnClickListener {

            Log.d("T", inputField?.text.toString())

            val stuff = TCP()
            stuff.mnumber = inputField?.text.toString()
            myNet = Thread(stuff)
            myNet.start()
            myNet.join()

            Log.d("T2", stuff.response)
            serverResponse?.text = stuff.response

        })
    }
}