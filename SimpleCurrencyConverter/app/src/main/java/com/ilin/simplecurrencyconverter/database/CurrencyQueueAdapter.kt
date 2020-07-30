package com.ilin.simplecurrencyconverter.database

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class CurrencyQueueAdapter private constructor(val applicationContext: Context, val size: Int = 10) {

    var dao =
        Room.databaseBuilder(applicationContext, CurrencyDatabase::class.java, "currency-storage").build().currencyDao()

    fun insert(pair: CurrencyPair) {
        val currencies = dao.getAll()
        if (currencies.size >= size) {
            (size..currencies.size).forEach{dao.delete(dao.getFirst())}
        }
        dao.insert(pair)
    }

    suspend fun getAll(): List<CurrencyPair> {
        val deferred = GlobalScope.async { dao.getAll() }
        return deferred.await()
    }

    companion object {
        private lateinit var instance: CurrencyQueueAdapter
        fun getInstance(applicationContext: Context): CurrencyQueueAdapter {
            if (!::instance.isInitialized) instance = CurrencyQueueAdapter(applicationContext)
            return instance
        }
    }
}