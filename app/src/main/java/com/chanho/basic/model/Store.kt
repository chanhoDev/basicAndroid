package com.chanho.basic.model

data class Store(
    var addr: String?,
    val code: String?,
    val created_at: String?,
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    val name: String?,
    val remain_stat: String?,
    val stock_at: String?,
    val type: String?,
    val distance: Double = 0.0
):Comparable<Store>{
    override fun compareTo(other: Store): Int {
        return compareValues(distance,other.distance)
    }
}