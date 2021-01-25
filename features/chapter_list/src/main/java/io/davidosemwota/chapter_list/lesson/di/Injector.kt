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
package io.davidosemwota.chapter_list.lesson.di

import dagger.hilt.android.EntryPointAccessors
import io.davidosemwota.chapter_list.lesson.LessonFragment
import io.davidosemwota.core.di.CoreComponent
import io.davidosemwota.ulessontask.di.AppComponent

internal fun inject(fragment: LessonFragment) =
    DaggerLessonComponent
        .factory()
        .create(
            appComponent(fragment),
            coreComponent(fragment)
        )
        .inject(fragment)

private fun coreComponent(fragment: LessonFragment): CoreComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        CoreComponent::class.java
    )

private fun appComponent(fragment: LessonFragment): AppComponent =
    EntryPointAccessors.fromApplication(
        fragment.requireContext().applicationContext,
        AppComponent::class.java
    )