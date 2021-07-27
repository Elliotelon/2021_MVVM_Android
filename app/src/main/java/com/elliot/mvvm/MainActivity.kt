package com.elliot.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.main_recycleview)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set contactItemClick & contactItemLongClick lambda
        val adapter = ContactAdapter({ contact ->
            // put extras of contact info & start AddActivity
        }, { contact ->
            deleteDialog(contact)
        })

        val lm = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = lm
        recyclerView.setHasFixedSize(true)

        contactViewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("NO") { _, _ -> }
            .setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder.show()
    }
}