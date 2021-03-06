/*
 * MIT License
 *
 * Copyright (c) 2021   David Osemwota.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.davidosemwota.home.main.adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.davidosemwota.core.data.RecentLesson
import io.davidosemwota.home.main.adaptor.holders.RecentLessonViewHolder
import io.davidosemwota.ui.base.BaseListAdaptor

class RecentLessonAdaptor(
    private val onItemClickAction: (String, Int) -> Unit
) : BaseListAdaptor<RecentLesson>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    private val itemBackgroundColors = listOf(
        "#EA7052",
        "#506AAC",
        "#FCA964",
        "#68BC98",
        "#7B7FDA"
    )

    var colapse = false
        private set
    private val COLLAPSE_COUNT = 2

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ) = RecentLessonViewHolder(inflater)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is RecentLessonViewHolder -> holder.bind(
                itemBackgroundColors[position % itemBackgroundColors.size],
                getItem(position),
                onItemClickAction
            )
        }
    }

    override fun getItemCount(): Int {
        if (colapse && super.getItemCount() > 2)
            return COLLAPSE_COUNT

        return super.getItemCount()
    }

    fun collapseList() {
        colapse = !colapse
        notifyDataSetChanged()
    }
}
