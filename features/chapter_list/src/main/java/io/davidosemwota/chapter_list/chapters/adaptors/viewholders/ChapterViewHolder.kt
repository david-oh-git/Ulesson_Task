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
package io.davidosemwota.chapter_list.chapters.adaptors.viewholders

import android.view.LayoutInflater
import io.davidosemwota.chapter_list.R
import io.davidosemwota.chapter_list.chapters.adaptors.LessonAdaptor
import io.davidosemwota.chapter_list.databinding.ListChapterItemBinding
import io.davidosemwota.core.data.ChapterWithLessons
import io.davidosemwota.ui.base.BaseViewHolder
import io.davidosemwota.ui.extentions.setItemDecorationSpacing

class ChapterViewHolder(
    layoutInflater: LayoutInflater
) : BaseViewHolder<ListChapterItemBinding>(
    binding = ListChapterItemBinding.inflate(layoutInflater)
) {

    fun bind(lessonItemClickAction: (String, Int) -> Unit, chapterWithLessons: ChapterWithLessons) {
        binding.chapterName.text = chapterWithLessons.chapter.name
        val lessonAdaptor = LessonAdaptor(
            chapterWithLessons.chapter.name,
            lessonItemClickAction
        )
        lessonAdaptor.submitList(chapterWithLessons.lessons)
        binding.lessonsList.apply {
            adapter = lessonAdaptor
            setItemDecorationSpacing(
                resources.getDimension(R.dimen.view_chapter_list_item_padding)
            )
        }
    }
}
