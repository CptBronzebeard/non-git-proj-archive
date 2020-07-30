package com.ilin.simplecurrencyconverter.util

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class HttpRequestBuilder {
    companion object {
        fun getRequest(url: String): String {
            var content = ""
            val u1 = URL(url)
            val uc1 = u1.openConnection() as HttpURLConnection
            if (uc1.responseCode == HttpURLConnection.HTTP_OK) {
                val is1 = uc1.inputStream
                val br = BufferedReader(InputStreamReader(is1, Charset.forName("UTF-8")))
                br.forEachLine {
                    content += it + "\n"
                }
            }
            return content
        }
    }
}