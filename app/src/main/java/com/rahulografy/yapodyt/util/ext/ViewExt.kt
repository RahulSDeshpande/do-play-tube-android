package com.rahulografy.yapodyt.util.ext

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.ui.component.view.DividerItemDecoration2
import jp.wasabeef.recyclerview.animators.FadeInLeftAnimator
import jp.wasabeef.recyclerview.animators.FadeInUpAnimator

/**
 * Show [Toast] message
 */
fun Context.toast(text: String?, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

/**
 * Show [Toast] message
 */
fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    context?.toast(text = text, duration = duration)
}

fun View.show(show: Boolean) {
    visibility =
        if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
}

/**
 * Set up RecyclerView LIST with default configurations like:
 *
 * * VERTICAL ->
 *   - itemAnimator -> FadeInUpAnimator
 *   - layoutManager -> LinearLayoutManager.VERTICAL
 *
 * * HORIZONTAL ->
 *   - itemAnimator -> FadeInRightAnimator
 *   - layoutManager -> LinearLayoutManager.HORIZONTAL
 */
fun RecyclerView.list(isVertical: Boolean = true) {
    layoutManager =
        if (isVertical) {
            itemAnimator = FadeInUpAnimator()
            if (itemDecorationCount == 0) {
                addItemDecoration(
                    DividerItemDecoration2(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.divider
                        )!!
                    )
                )
            }
            LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
        } else {
            itemAnimator = FadeInLeftAnimator()
            if (itemDecorationCount == 0) {
                addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.HORIZONTAL
                    )
                )
            }
            LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
}

/**
 * Notify any registered observers that the item at <code>position</code> has changed.
 * Equivalent to calling [notifyItemChanged(position, null);].
 *
 * This is an item change event, not a structural change event. It indicates that any
 * reflection of the data at [position] is out of date and should be updated.
 * The item at [position] retains the same identity.
 *
 * @param position Position of the item that has changed
 */
fun RecyclerView?.notifyChange(position: Int) {
    if (position >= 0) {
        this?.adapter?.notifyItemChanged(position)
    }
}
