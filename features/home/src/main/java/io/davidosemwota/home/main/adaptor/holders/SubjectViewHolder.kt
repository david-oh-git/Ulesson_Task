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
package io.davidosemwota.home.main.adaptor.holders

import android.graphics.Color
import android.view.LayoutInflater
import io.davidosemwota.core.data.Subject
import io.davidosemwota.home.databinding.ListSubjectItemBinding
import io.davidosemwota.ui.base.BaseViewHolder
import io.davidosemwota.ui.extentions.setImage

class SubjectViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListSubjectItemBinding>(
    binding = ListSubjectItemBinding.inflate(inflater)
) {

    fun bind(color: String, subject: Subject, onItemClickAction: (String, Int) -> Unit) {
        binding.apply {
            cardContainer.setCardBackgroundColor(
                Color.parseColor(color)
            )
            cardContainer.setOnClickListener {
                onItemClickAction.invoke(subject.name, subject.subjectId)
            }
            subjectName.text = subject.name
            subjectIcon.setImage(subject.icon, null)
        }
    }
}
