package io.davidosemwota.core.network.responses

import com.google.gson.annotations.SerializedName

/**
 * Response format for a [Lesson] from the APi.
 *
 * @param id The lesson's id.
 * @param name The lesson's name.
 * @param icon The lesson's icon url.
 * @param mediaUrl The lesson's video url.
 * @param chapterId The associated chapter's id.
 */
data class Lesson(
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