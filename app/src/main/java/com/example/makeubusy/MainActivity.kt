package com.example.makeubusy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.makeubusy.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton
import retrofit2.HttpException
import java.io.IOException

const val TAG = "MAINACTIVITY"
class MainActivity : AppCompatActivity() {
    private lateinit var prefs : SharedPreferences
    private var count = 0;
    private lateinit var btn: Button
    private lateinit var btn2: Button
    private lateinit var pg: ProgressBar
    private lateinit var t1: TextView
    private lateinit var t2: MaterialButton
    private lateinit var t3: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prefs = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        btn = findViewById(R.id.button)
        btn2=  findViewById(R.id.button2)
        pg = findViewById(R.id.progressBar)
        t1 = findViewById(R.id.textView)
        t2 = findViewById(R.id.textView2)
        t3 = findViewById(R.id.textView3)
        count = prefs.getInt("count",0)
        t3.text = count.toString()
        apiCall()
        btn.setOnClickListener {
            apiCall()
        }
        btn2.setOnClickListener{
            count++
            t3.text = count.toString()
            editor.apply{
                putInt("count", count)
                apply() }
            apiCall()
        }
        t2.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(t2.text.toString()))
            startActivity(i)
        }
    }
    private fun apiCall(){
        lifecycleScope.launchWhenCreated {
            pg.isVisible = true
            val resp = try{
                RetrofitInstance.api.getActivities()
            }
            catch(e: IOException) {
                Log.e(TAG, "${e.stackTrace}")
                pg.isVisible = false
                return@launchWhenCreated
            } catch (e: HttpException) {
                Log.e(TAG, "HttpException, unexpected response")
                pg.isVisible = false
                return@launchWhenCreated
            }
            println(resp.body())
            if(resp.isSuccessful && resp.body()!=null){
                pg.isVisible=false
                val obj = resp.body()!!
                t1.text = obj.activity
                t2.text = obj.link
            }
            else if(resp.body()==null){
                Log.d(TAG, "resp is null")
                println(resp.body())
            }
        }
    }
}