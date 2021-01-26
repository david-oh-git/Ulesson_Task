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
package io.davidosemwota.core.network.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

/**
 * Response format for a [ResponseLesson] from the APi.
 *
 * @param id The lesson's id.
 * @param name The lesson's name.
 * @param icon The lesson's icon url.
 * @param mediaUrl The lesson's video url.
 * @param chapterId The associated chapter's id.
 */
@Keep
data class ResponseLesson(
    val id: Int,
    val name: String,
    val icon: String,
    @SerializedName("media_url")
    val mediaUrl: String,
    @SerializedName("subject_id")
    val subjectId: Int,
    @SerializedName("chapter_id")
    val chapterId: Int
)
