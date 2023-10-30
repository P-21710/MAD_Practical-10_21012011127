package com.prasadrajyaguru.mad_practical_10_21012011127

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class PersonAdapter (context: Context, val PersonArray:ArrayList<Person>):ArrayAdapter<Person>(context,0,PersonArray) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        view.findViewById<TextView>(R.id.name).text = PersonArray[position].name
        view.findViewById<TextView>(R.id.mobile).text = PersonArray[position].phoneNo
        view.findViewById<TextView>(R.id.emailid).text = PersonArray[position].emailId
        view.findViewById<TextView>(R.id.address).text = PersonArray[position].address
        view.findViewById<MaterialButton>(R.id.btnLocation).setOnClickListener {
            Intent(context, MapsActivity::class.java).putExtra("Object", PersonArray[position])
                .apply { context.startActivity(this) }}
        return view

    }
}