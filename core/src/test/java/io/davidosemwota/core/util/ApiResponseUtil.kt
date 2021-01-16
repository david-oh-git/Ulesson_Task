/*
 * MIT License
 *
 * Copyright (c) $today.day/$today.month/2021 $today.hour24:$today.minute   David Osemwota.
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
package io.davidosemwota.core.util

import io.davidosemwota.core.network.responses.Chapter
import io.davidosemwota.core.network.responses.Lesson
import io.davidosemwota.core.network.responses.Subject

val id
    get() = listOf(22, 63, 456, 11, 71, 28, 32, 56).random()

val subjectId
    get() = listOf(22, 63, 456, 11, 71, 28, 32, 56).random()

val chapterId
    get() = listOf(22, 63, 456, 11, 71, 28, 32, 56).random()

val lessonName
    get() = listOf(
        "How to defeat your first arrancar Part I",
        "Kido for dummies",
        "Gotei 13 Combat Fundamentals",
        "Katana for beginners"
    ).random()

val icon
    get() = listOf(
        "https://hellomedia/images/dog.png",
        "https://hellomedia/images/cat.png",
        "https://hellomedia/images/rat.png",
        "https://hellomedia/images/lion.png",
        "https://hellomedia/images/tiger.png"
    ).random()

val mediaUrl
    get() = listOf(
        "https://hellomedia/images/dog_dancing.mp4",
        "https://hellomedia/images/goat_dancing.mp4",
        "https://hellomedia/images/kido101.mp4",
        "https://hellomedia/images/combat_statistics.mp4",
        "https://hellomedia/images/kido.mp4",
        "https://hellomedia/images/kido_pt2.mp4"
    ).random()

fun getLesson(): Lesson = Lesson(
    id = id,
    name = lessonName,
    icon = icon,
    mediaUrl = mediaUrl,
    chapterId = chapterId,
    subjectId = subjectId
)

fun getLessons(numberOfLessons: Int = 2): List<Lesson> {
    return mutableListOf(numberOfLessons).map { getLesson() }
}

fun getChapter(numberOfLessons: Int = 2): Chapter = Chapter(
    id = id,
    name = lessonName,
    getLessons(numberOfLessons)
)

fun getChapters(numberOfChapters: Int = 2): List<Chapter> {
    return mutableListOf(numberOfChapters).map { getChapter() }
}

fun getSubject(numberOfChapters: Int = 2): Subject = Subject(
    id = id,
    name = lessonName,
    chapters = getChapters(numberOfChapters),
    icon = icon
)

fun getSubjects(numberOfSubjects: Int = 2): List<Subject> {
    return mutableListOf(numberOfSubjects).map { getSubject() }
}
