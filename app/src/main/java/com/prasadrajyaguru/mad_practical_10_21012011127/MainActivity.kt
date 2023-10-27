package com.prasadrajyaguru.mad_practical_10_21012011127

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fav1: FloatingActionButton = findViewById(R.id.float1)

        fav1.setOnClickListener {
            SendDataToListview()
        }

    }

    private fun getPersonDetailsFromJson(sJson: String?) {
        val personList = ArrayList<Contact>()
        try {
            val jsonArray = JSONArray(sJson)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray[i] as JSONObject
                val person = Contact(jsonObject)
                personList.add(person)
            }

            val personlistview = findViewById<ListView>(R.id.list1)
            personlistview.adapter = ContactAdapter(this, personList)
        } catch (ee: JSONException) {
            ee.printStackTrace()
        }
    }

    fun SendDataToListview() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val data = httprequest().makeServiceCall(
                    "https://api.json-generator.com/templates/qjeKFdjkXCdK/data",
                    "rbn0rerl1k0d3mcwgw7dva2xuwk780z1hxvyvrb1")
                withContext(Dispatchers.Main) {
                    try {
                        if (data != null)
                            runOnUiThread { getPersonDetailsFromJson(data) }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}