package com.example.se2einzelphase

import android.util.Log
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.Socket

internal class TCP : Runnable {
    private var outgoing: PrintWriter? = null
    private var incomming: BufferedReader? = null
    var response: String = ""
    var mnumber: String = ""
    override fun run() {
        val port: Int = 20080
        val hostname: String = "se2-submission.aau.at"
        try {

            //Create Socket
            val socket = Socket(hostname, port)

            //setup Writer and Reader
            outgoing =
                PrintWriter(BufferedWriter(OutputStreamWriter(socket.getOutputStream())), true)
            incomming = BufferedReader(InputStreamReader(socket.getInputStream()))

            //send mnumber to server and wait for response
            try {

                outgoing!!.println(mnumber)

                response = incomming!!.readLine()

            } catch (e: Exception) {
                Log.d("T", "Error while sending or receiving\n")
            } finally {
                //close reader writer and socket
                incomming!!.close()
                outgoing!!.close()
                socket.close()
            }
        } catch (e: Exception) {
            Log.d("T", "Error while connecting\n")
        }
    }
}