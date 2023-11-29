package com.example.contactappfragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar

val items = arrayListOf<ItemModel>(
    ItemModel(1, "Đặng Văn A", "0928234876", "a@gmail.com"),
    ItemModel(2, "Nguyễn Thị B", "0123456789", "b@gmail.com"),
    ItemModel(3, "Trần Văn C", "0231458876", "c@gmail.com"),
    ItemModel(4, "Lê Thị D", "0987864128", "d@gmail.com"),
    ItemModel(5, "Phạm Văn E", "0369852147", "e@gmail.com"),
    ItemModel(6, "Hoàng Thị F", "0857496321", "f@gmail.com"),
    ItemModel(7, "Vũ Văn G", "0789632145", "g@gmail.com"),
    ItemModel(8, "Trịnh Thị H", "0258964713", "h@gmail.com"),
    ItemModel(9, "Đinh Văn I", "0856932147", "i@gmail.com"),
    ItemModel(10, "Mai Thị K", "0896547123", "k@gmail.com")
)
val listFragment = ListFragment.newInstance(items)

class MainActivity : AppCompatActivity(), AddContactFragment.OnContactAddedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.title = "Contact App"
        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, listFragment)
            .commit()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.item_menu, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.new_contact -> {
                val newContactFragment = AddContactFragment.newInstance(items.size)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, newContactFragment)
                    .commit()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onContactAdded(item: ItemModel) {
        items.add(item)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, listFragment)
            .commit()
    }
}