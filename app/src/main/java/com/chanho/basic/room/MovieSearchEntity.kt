package com.chanho.basic.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieSearch")
data class MovieSearchEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int?,

    @ColumnInfo(name="search")
    var search:String
){
    constructor(search: String):this(null,search)
}
