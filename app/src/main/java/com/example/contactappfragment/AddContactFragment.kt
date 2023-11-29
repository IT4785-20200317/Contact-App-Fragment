package com.example.contactappfragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class AddContactFragment : Fragment() {
    private var onContactAddedListener: OnContactAddedListener? = null

    interface OnContactAddedListener {
        fun onContactAdded(item: ItemModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact, container, false)

        val name = view.findViewById<EditText>(R.id.contact_name)
        val phoneNumber = view.findViewById<EditText>(R.id.contact_phone_number)
        val email = view.findViewById<EditText>(R.id.contact_email)
        val id = requireArguments().getInt("id")

        view.findViewById<Button>(R.id.save_contact).setOnClickListener {
            val contact = ItemModel(id, name.text.toString(), phoneNumber.text.toString(), email.text.toString())
            onContactAddedListener?.onContactAdded(contact)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(currentId: Int) =
            AddContactFragment().apply {
                arguments = Bundle().apply {
                    putInt("id", currentId + 1)
                }
            }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnContactAddedListener) {
            onContactAddedListener = context
        } else {
            throw IllegalArgumentException("Activity must implement OnContactAddedListener")
        }
    }
}