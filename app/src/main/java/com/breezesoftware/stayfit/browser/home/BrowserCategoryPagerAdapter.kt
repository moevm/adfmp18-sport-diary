package com.breezesoftware.stayfit.browser.home

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.breezesoftware.stayfit.R
import com.breezesoftware.stayfit.browser.BrowserItem
import com.breezesoftware.stayfit.core.StayFitApp
import javax.inject.Inject

/**
 * This file is part of Test Kotlin Application
 *
 * You can do everything with the code and files
 *
 * Created by popof on 28.03.2018.
 */

class BrowserCategoryPagerAdapter(private var items: List<BrowserItem>)
    : PagerAdapter() {

    @Inject
    lateinit var context : Context

    init {
        StayFitApp.component.inject(this)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.browser_category_item, container, false)

        setCategoryItem(view, position)

        container?.addView(view)

        return view
    }

    private fun setCategoryItem(browserItemView : View, position : Int) {
        //val imageView = browserItemView?.findViewById<ImageView>(R.id.itemImage)
        val nameTV = browserItemView.findViewById<TextView>(R.id.itemName)
        val descriptionTV = browserItemView.findViewById<TextView>(R.id.itemDescription)
        val priceTV = browserItemView.findViewById<TextView>(R.id.itemPrice)
        val ratingTV = browserItemView.findViewById<TextView>(R.id.itemRating)

        val item = items[position]

        nameTV?.text = item.name
        descriptionTV?.text = item.description

        if (item.price == 0) {
            priceTV?.text = item.price.toString()
        } else {
            priceTV?.text = context.getString(R.string.free)
        }

        ratingTV?.text = String.format("%s %d/5", context.getString(R.string.rating), item.rating)
    }

    override fun destroyItem(container: ViewGroup?, position: Int, view: Any?) {
        container?.removeView(view as View)
    }

    override fun isViewFromObject(view: View?, objectView: Any?): Boolean {
        return view == objectView as View
    }

    override fun getCount() = items.size


}