package com.mobcoder.stickyrecyclerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobcoder.stickyrecyclerview.R
import com.mobcoder.stickyrecyclerview.data.Book
import com.mobcoder.stickyrecyclerview.databinding.ActivityMainBinding
import com.mobcoder.stickyrecyclerview.extensions.setDivider
import com.mobcoder.stickyrecyclerview.utils.JsonUtils
import com.mobcoder.stickyrecyclerview.utils.StickyHeaderDecoration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val fruitsAdapter = FruitsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val books: List<Book> = JsonUtils.getItems(this)
        val groupedBooks: Map<Char, List<Book>> =
            books.groupBy { book -> book.title.first().toUpperCase() }
        fruitsAdapter.bookData = groupedBooks.toSortedMap()

        binding.bookList.setDivider(R.drawable.list_divider)
        binding.bookList.addItemDecoration(
            StickyHeaderDecoration(fruitsAdapter, binding.root)
        )
        binding.bookList.layoutManager = LinearLayoutManager(this)
        binding.bookList.adapter = fruitsAdapter
    }

}