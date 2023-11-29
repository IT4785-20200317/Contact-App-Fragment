package com.example.contactappfragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class DetailFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val item = requireArguments().getParcelable<ItemModel>("item")

        val detailId = view.findViewById<TextView>(R.id.detailId)
        val detailName = view.findViewById<TextView>(R.id.detailName)
        val detailPhoneNumber = view.findViewById<TextView>(R.id.detailPhoneNumber)
        val detailEmail = view.findViewById<TextView>(R.id.detailEmail)

        if (item != null) {
            detailId.text = "Id: ${item.id}"
            detailName.text = "Name: ${item.name}"
            detailPhoneNumber.text = "Phone Number: ${item.phoneNumber}"
            detailEmail.text = "Email: ${item.email}"
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(item: ItemModel) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("item", item)
                }
            }
    }
}