package com.chanho.basic.model

data class MovieResModel(

    /**
     * 검색결과를 생성한 시간
     */
    var lastBuildDate:String?,
    var items:ArrayList<Movie>
)