package com.techflix.starwars.utils

import java.net.InetAddress


class NetworkUtils {

    companion object {
        fun isInternetAvailable(): Boolean {
            return try {
                val ipAddr = InetAddress.getByName("google.com").toString()
                //You can replace it with your name
                ipAddr.equals("").not()
            } catch (e: Exception) {
                false
            }
        }
    }

}