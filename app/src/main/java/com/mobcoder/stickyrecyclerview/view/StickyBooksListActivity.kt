package com.mobcoder.stickyrecyclerview.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobcoder.stickyrecyclerview.R
import com.mobcoder.stickyrecyclerview.data.Book
import com.mobcoder.stickyrecyclerview.databinding.ActivityMainBinding
import com.mobcoder.stickyrecyclerview.extensions.setDivider
import com.mobcoder.stickyrecyclerview.utils.JsonUtils
import com.mobcoder.stickyrecyclerview.utils.StickyHeaderDecoration
import java.util.*

/**
 *
 */
class MainActivity : AppCompatActivity() {

    //
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStickyAdapter()
    }

    private fun setStickyAdapter(){
        var stickyData = getStickyData()
        val headersAdapter = HeadersAdapter()

        binding.rvStickyHeader.setDivider(R.drawable.list_divider)
        binding.rvStickyHeader.addItemDecoration(
            StickyHeaderDecoration(headersAdapter, binding.root)
        )
        binding.rvStickyHeader.adapter = headersAdapter
        headersAdapter.bookData = stickyData
    }


    private fun getStickyData(): SortedMap<Char, MutableList<Book>>? {
        val books: MutableList<Book>? = JsonUtils.getItems(this)
        val bookGroupBy = books?.groupBy {
            it.title.first().uppercaseChar()
        }
       return  bookGroupBy?.toSortedMap() as SortedMap<Char, MutableList<Book>>
    }
}