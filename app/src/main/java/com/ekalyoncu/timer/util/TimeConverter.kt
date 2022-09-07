package com.ekalyoncu.timer.util

class TimeConverter {

    fun getLeftMinutes(millis: Long): Int{
        return (millis / 60000).toInt()
    }

    fun getLeftSeconds(millis: Long): Int {
        return ((millis % 60000) / 1000).toInt()
    }

}