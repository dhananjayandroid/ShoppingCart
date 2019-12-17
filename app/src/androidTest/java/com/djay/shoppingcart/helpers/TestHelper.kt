package com.djay.shoppingcart.helpers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.internal.util.Checks
import com.djay.shoppingcart.R
import com.djay.shoppingcart.productlist.ProductListAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher

object TestHelper {

    fun hasItem(matcher: Matcher<View?>): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item: ")
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val adapter = view.adapter
                for (position in 0 until adapter!!.itemCount) {
                    val type = adapter.getItemViewType(position)
                    val holder = adapter.createViewHolder(view, type)
                    adapter.onBindViewHolder(holder, position)
                    if (matcher.matches(holder.itemView.findViewById(R.id.tvTitle))) {
                        return true
                    }
                }
                return false
            }
        }
    }

    fun withProductTitle(taskTitle: String): Matcher<RecyclerView.ViewHolder?>? {
        Checks.checkNotNull(taskTitle)
        return object : BoundedMatcher<RecyclerView.ViewHolder?, ProductListAdapter.MViewHolder>(
            ProductListAdapter.MViewHolder::class.java
        ) {
            override fun matchesSafely(holder: ProductListAdapter.MViewHolder): Boolean {
                val holderTaskTitle: String = holder.tvTitle.text.toString()
                return taskTitle == holderTaskTitle
            }

            override fun describeTo(description: Description) {
                description.appendText("with task title: $taskTitle")
            }
        }
    }

    /**
     * function to click a specific view of RecyclerView item
     */
    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) =
            ViewActions.click().perform(uiController, view.findViewById<View>(viewId))
    }
}