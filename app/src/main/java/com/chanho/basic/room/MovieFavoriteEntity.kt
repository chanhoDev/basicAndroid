package com.chanho.basic.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "moviefavorite")
data class MovieFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "link")
    var link: String?,

    @ColumnInfo(name = "image")
    var image: String?,

    @ColumnInfo(name = "subtitle")
    var subtitle: String?,

    @ColumnInfo(name = "pubDate")
    var pubDate: String?,

    @ColumnInfo(name = "director")
    var director: String?,

    @ColumnInfo(name = "actor")
    var actor: String?,

    @ColumnInfo(name = "userRating")
    var userRating: String?

) {
    constructor(
        title: String,
        link: String,
        image: String,
        subtitle: String,
        pubDate: String,
        director: String,
        actor: String,
        userRating: String
    ) : this(null,title, link, image, subtitle, pubDate, director, actor, userRating)
}
