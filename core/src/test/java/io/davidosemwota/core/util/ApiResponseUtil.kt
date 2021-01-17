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

import io.davidosemwota.core.data.Chapter
import io.davidosemwota.core.data.Lesson
import io.davidosemwota.core.data.Subject
import io.davidosemwota.core.network.responses.ResponseChapter
import io.davidosemwota.core.network.responses.ResponseLesson
import io.davidosemwota.core.network.responses.ResponseSubject

val id
    get() = listOf(22, 63, 456, 11, 71, 28, 32, 56).random()

val subjectId
    get() = listOf(213, 678, 945, 235, 654, 567, 234, 134, 234, 256, 324, 390, 321).random()

val chapterId
    get() = listOf(456, 12, 345, 67, 23, 50, 431, 101, 79, 91, 70, 39, 42, 149).random()

val lessonName
    get() = listOf(
        "How to defeat your first arrancar Part I",
        "Kido for dummies",
        "Gotei 13 Combat Fundamentals",
        "Katana for beginners"
    ).random()

val subjectName
    get() = listOf(
        "Combat",
        "History",
        "Kido",
        "Peotry",
        "Music",
        "Science"
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

fun getResponseLesson(): ResponseLesson = ResponseLesson(
    id = id,
    name = lessonName,
    icon = icon,
    mediaUrl = mediaUrl,
    chapterId = chapterId,
    subjectId = subjectId
)

fun getResponseLessons(numberOfLessons: Int = 2): List<ResponseLesson> {
    return mutableListOf(numberOfLessons).map { getResponseLesson() }
}

fun getResponseChapter(numberOfLessons: Int = 2): ResponseChapter = ResponseChapter(
    id = id,
    name = lessonName,
    getResponseLessons(numberOfLessons)
)

fun getResponseChapters(numberOfChapters: Int = 2): List<ResponseChapter> {
    return mutableListOf(numberOfChapters).map { getResponseChapter() }
}

fun getResponseSubject(numberOfChapters: Int = 2): ResponseSubject = ResponseSubject(
    id = id,
    name = lessonName,
    chapters = getResponseChapters(numberOfChapters),
    icon = icon
)

fun getResponseSubjects(numberOfSubjects: Int = 2): List<ResponseSubject> {
    return mutableListOf(numberOfSubjects).map { getResponseSubject() }
}

fun getChapter(): Chapter = Chapter(
    id = id,
    chapterId = chapterId,
    subjectId = subjectId,
    subjectName = subjectName,
    name = lessonName
)

fun getChapters(numberOfChapters: Int = 2): List<Chapter> =
    mutableListOf(numberOfChapters).map { getChapter() }

fun getLesson(): Lesson = Lesson(
    id = id,
    name = lessonName,
    subjectId = subjectId,
    icon = icon,
    chapterId = chapterId,
    lessonId = id,
    mediaUrl = mediaUrl
)

fun getLessons(numberOfLessons: Int = 2): List<Lesson> =
    mutableListOf(numberOfLessons).map { getLesson() }

fun getSubject(): Subject = Subject(
    subjectId = subjectId,
    icon = icon,
    name = subjectName
)

fun getSubjects(numberOfSubjects: Int = 2): List<Subject> =
    mutableListOf(numberOfSubjects).map { getSubject() }
