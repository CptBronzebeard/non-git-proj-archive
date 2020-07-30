package com.example.myapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider

import com.ilin.simplecurrencyconverter.database.CurrencyDao
import com.ilin.simplecurrencyconverter.database.CurrencyDatabase
import com.ilin.simplecurrencyconverter.database.CurrencyPair
import com.ilin.simplecurrencyconverter.database.CurrencyQueueAdapter
import com.ilin.simplecurrencyconverter.model.Currency
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.util.*


@RunWith(
    RobolectricTestRunner::class)
class DbTest {
    private lateinit var currencyPairDao: CurrencyDao
    private lateinit var db: CurrencyDatabase
    private lateinit var queue: CurrencyQueueAdapter

    /*@Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, CurrencyDatabase::class.java).build()
        currencyPairDao = db.currencyDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }*/

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadToList() {
        val cur = CurrencyPair(
            Currency("FRM", "192.0".toBigDecimal(), 1, "FROM"),
            Currency("TO", "96.0".toBigDecimal(), 1, "To"),"1".toBigDecimal(), Calendar.getInstance())
        val curs = (0..14).map { CurrencyPair(
            Currency(it.toString(), "192.0".toBigDecimal(), 1, "FROM"),
            Currency("TO", "96.0".toBigDecimal(), 1, "To"),"1".toBigDecimal(), Calendar.getInstance()) }
        GlobalScope.launch {
            val context = ApplicationProvider.getApplicationContext<Context>()
            db = Room.inMemoryDatabaseBuilder(
                context, CurrencyDatabase::class.java).build()
            currencyPairDao = db.currencyDao()
            queue = CurrencyQueueAdapter.getInstance(context)
            queue.dao = currencyPairDao
            curs.forEach {
                queue.insert(it)
            }
            val byName = queue.getAll()
            db.close()
            assertEquals("5", byName[0].charCodeFrom)
        }

    }
}