package com.example.contactappfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView

class ListFragment : Fragment() {
    private var adapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val data = requireArguments().getParcelableArrayList<ItemModel>("data")

        adapter = data?.let { ItemAdapter(it) }
        val listView = view.findViewById<ListView>(R.id.listView)
        listView.adapter = adapter

        listView.onItemClickListener  = AdapterView.OnItemClickListener { parent, view, position, id ->
            val detailFragment = data?.let { DetailFragment.newInstance(it[position]) }
            val fragmentManager = requireActivity().supportFragmentManager
            if (detailFragment != null) {
                fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

        registerForContextMenu(listView)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(data: ArrayList<ItemModel>) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList("data", data)
                }
            }
    }
}