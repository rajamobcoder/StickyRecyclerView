package com.mobcoder.stickyrecyclerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.constraintlayout.widget.ConstraintLayout
import com.mobcoder.stickyrecyclerview.R
import com.mobcoder.stickyrecyclerview.data.Book
import com.mobcoder.stickyrecyclerview.databinding.ViewBookDetailsComponentBinding
import com.mobcoder.stickyrecyclerview.databinding.ViewBookDetailsListItemBinding
import com.mobcoder.stickyrecyclerview.extensions.requestLayoutForChangedDataset

class BookDetailsComponentView : ConstraintLayout {

    private lateinit var binding: ViewBookDetailsComponentBinding
    private lateinit var bookItemBinding: ViewBookDetailsListItemBinding
    private lateinit var adapter: BookAdapter

    var books: List<Book> = emptyList()
        set(value) {
            field = value
            onItemsUpdated()
        }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        binding = ViewBookDetailsComponentBinding.inflate(LayoutInflater.from(context), this, true)
        adapter = BookAdapter(context)
        binding.bookDetailsList.adapter = adapter
    }

    private fun onItemsUpdated() {
        adapter.notifyDataSetChanged()
        binding.bookDetailsList.requestLayoutForChangedDataset()
    }

    inner class BookAdapter(private val context: Context) : BaseAdapter() {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val book: Book = books[position]
            var view: View? = convertView

            if (view == null) {
                view = LayoutInflater.from(context)
                    .inflate(R.layout.view_book_details_list_item, parent, false)
                bookItemBinding = ViewBookDetailsListItemBinding.bind(view)
                view.tag = bookItemBinding
            } else {
                bookItemBinding = view.tag as ViewBookDetailsListItemBinding
            }

            bookItemBinding.apply {
                tvTitle.text = book.title
                tvAuthor.text = book.author
                tvCountry.text = book.country
                tvYear.text = book.year.toString()
            }

            return bookItemBinding.root
        }

        override fun getItem(position: Int): Any {
            return books[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getCount(): Int {
            return books.size
        }

        override fun isEnabled(position: Int): Boolean {
            return false
        }
    }
}