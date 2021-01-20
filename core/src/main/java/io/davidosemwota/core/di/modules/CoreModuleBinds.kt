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
package io.davidosemwota.core.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.davidosemwota.core.data.UlessonLocalSource
import io.davidosemwota.core.data.UlessonRemoteSource
import io.davidosemwota.core.data.UlessonRepository
import io.davidosemwota.core.data.UlessonRepositoryImpl
import io.davidosemwota.core.data.source.local.ChapterDao
import io.davidosemwota.core.data.source.local.LessonDao
import io.davidosemwota.core.data.source.local.SubjectDao
import io.davidosemwota.core.data.source.local.UlessonDatabase
import io.davidosemwota.core.data.source.local.UlessonLocalSourceImpl
import io.davidosemwota.core.data.source.remote.UlessonRemoteSourceImpl
import io.davidosemwota.core.mappers.LessonListMapper
import io.davidosemwota.core.mappers.LessonMapper
import io.davidosemwota.core.mappers.SubjectListMapper
import io.davidosemwota.core.mappers.SubjectMapper
import io.davidosemwota.core.network.UlessonApiFactory
import io.davidosemwota.core.network.UlessonService
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ApplicationComponent::class)
interface CoreModuleBinds {

    @get:Binds
    val UlessonRepositoryImpl.repository: UlessonRepository

    @get:Binds
    val UlessonLocalSourceImpl.localSource: UlessonLocalSource

    @get:Binds
    val UlessonRemoteSourceImpl.remoteSource: UlessonRemoteSource

    companion object {

        @Provides
        @Singleton
        fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

        @Provides
        @Singleton
        fun provideSubjectDao(@ApplicationContext context: Context): SubjectDao =
            provideRoomDatabase(context).subjectDao()

        @Provides
        @Singleton
        fun provideChapterDao(@ApplicationContext context: Context): ChapterDao =
            provideRoomDatabase(context).chapterDao()

        @Provides
        @Singleton
        fun provideLessonDao(@ApplicationContext context: Context): LessonDao =
            provideRoomDatabase(context).lessonDao()

        @Provides
        @Singleton
        fun provideUlessonRetrofitService(): UlessonService =
            UlessonApiFactory.provideUlessonApiService()

        @Provides
        @Singleton
        fun provideSubjectMapper(): SubjectMapper = SubjectMapper()

        @Provides
        @Singleton
        fun provideLessonMapper(): LessonMapper = LessonMapper()

        @Provides
        @Singleton
        fun provideSubjectListMapper(subjectMapper: SubjectMapper): SubjectListMapper =
            SubjectListMapper(subjectMapper)

        @Provides
        @Singleton
        fun provideLessonListMapper(lessonMapper: LessonMapper): LessonListMapper =
            LessonListMapper(lessonMapper)

        private fun provideRoomDatabase(context: Context): UlessonDatabase =
            Room.databaseBuilder(
                context.applicationContext,
                UlessonDatabase::class.java,
                "ulesson.db"
            ).build()
    }
}
