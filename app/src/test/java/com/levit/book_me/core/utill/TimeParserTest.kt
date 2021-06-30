package com.levit.book_me.core.utill

import com.levit.book_me.core.extensions.*
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class TimeParserTest {

    private val dateFormat = TimeParser.baseDateFormat

    @Test
    fun getDateInCurrentUTC_firstTestCase_returnValidDate() {
        val stringDate = "11.02.2002 16:31:21"

        val expectedDay = 11
        val expectedMonth = 2
        val expectedYear = 2002
        val expectedHours = 16
        val expectedMinutes = 31
        val expectedSeconds = 21

        val date = dateFormat.parse(stringDate)!!

        val actualDay = date.getDayOfMonth()
        val actualMonth = date.getMonthOfYear()
        val actualYear = date.getCurrentYear()
        val actualHours = date.getCurrentHours()
        val actualMinutes = date.getCurrentMinutes()
        val actualSeconds = date.getCurrentSeconds()

        assertEquals(expectedDay, actualDay)
        assertEquals(expectedMonth, actualMonth)
        assertEquals(expectedYear, actualYear)
        assertEquals(expectedHours, actualHours)
        assertEquals(expectedMinutes, actualMinutes)
        assertEquals(expectedSeconds, actualSeconds)
    }

    @Test
    fun getDateInCurrentUTC_secondTestCase_returnValidDate() {
        val stringDate = "17.11.2015 06:00:00"

        val expectedDay = 17
        val expectedMonth = 11
        val expectedYear = 2015
        val expectedHours = 6
        val expectedMinutes = 0
        val expectedSeconds = 0

        val date = dateFormat.parse(stringDate)!!

        val actualDay = date.getDayOfMonth()
        val actualMonth = date.getMonthOfYear()
        val actualYear = date.getCurrentYear()
        val actualHours = date.getCurrentHours()
        val actualMinutes = date.getCurrentMinutes()
        val actualSeconds = date.getCurrentSeconds()

        assertEquals(expectedDay, actualDay)
        assertEquals(expectedMonth, actualMonth)
        assertEquals(expectedYear, actualYear)
        assertEquals(expectedHours, actualHours)
        assertEquals(expectedMinutes, actualMinutes)
        assertEquals(expectedSeconds, actualSeconds)
    }

    @Test
    fun getDateInCurrentUTC_thirdTestCase_returnValidDate() {
        val stringDate = "01.01.2000 00:00:00"

        val expectedDay = 1
        val expectedMonth = 1
        val expectedYear = 2000
        val expectedHours = 0
        val expectedMinutes = 0
        val expectedSeconds = 0

        val date = dateFormat.parse(stringDate)!!

        val actualDay = date.getDayOfMonth()
        val actualMonth = date.getMonthOfYear()
        val actualYear = date.getCurrentYear()
        val actualHours = date.getCurrentHours()
        val actualMinutes = date.getCurrentMinutes()
        val actualSeconds = date.getCurrentSeconds()

        assertEquals(expectedDay, actualDay)
        assertEquals(expectedMonth, actualMonth)
        assertEquals(expectedYear, actualYear)
        assertEquals(expectedHours, actualHours)
        assertEquals(expectedMinutes, actualMinutes)
        assertEquals(expectedSeconds, actualSeconds)
    }

    @Test
    fun getTimeFromBasePattern_firstTestCase_returnValidDate() {
        val dateMillis = 1013418802000L

        val expectedDay = 11
        val expectedMonth = 2
        val expectedYear = 2002
        val expectedHours = 12
        val expectedMinutes = 13
        val expectedSeconds = 22

        val date = getDateFrom(dateMillis)

        val actualDay = date.getDayOfMonth()
        val actualMonth = date.getMonthOfYear()
        val actualYear = date.getCurrentYear()
        val actualHours = date.getCurrentHours()
        val actualMinutes = date.getCurrentMinutes()
        val actualSeconds = date.getCurrentSeconds()

        assertEquals(expectedDay, actualDay)
        assertEquals(expectedMonth, actualMonth)
        assertEquals(expectedYear, actualYear)
        assertEquals(expectedHours, actualHours)
        assertEquals(expectedMinutes, actualMinutes)
        assertEquals(expectedSeconds, actualSeconds)
    }

    @Test
    fun getTimeFromBasePattern_secondTestCase_returnValidDate() {
        val dateMillis = 564673993000L

        val expectedDay = 23
        val expectedMonth = 11
        val expectedYear = 1987
        val expectedHours = 16
        val expectedMinutes = 53
        val expectedSeconds = 13

        val date = getDateFrom(dateMillis)

        val actualDay = date.getDayOfMonth()
        val actualMonth = date.getMonthOfYear()
        val actualYear = date.getCurrentYear()
        val actualHours = date.getCurrentHours()
        val actualMinutes = date.getCurrentMinutes()
        val actualSeconds = date.getCurrentSeconds()

        assertEquals(expectedDay, actualDay)
        assertEquals(expectedMonth, actualMonth)
        assertEquals(expectedYear, actualYear)
        assertEquals(expectedHours, actualHours)
        assertEquals(expectedMinutes, actualMinutes)
        assertEquals(expectedSeconds, actualSeconds)
    }

    @Test
    fun getTimeFromBasePattern_thirdTestCase_returnValidDate() {
        val dateMillis = 1578430799000

        val expectedDay = 7
        val expectedMonth = 1
        val expectedYear = 2020
        val expectedHours = 23
        val expectedMinutes = 59
        val expectedSeconds = 59

        val date = getDateFrom(dateMillis)

        val actualDay = date.getDayOfMonth()
        val actualMonth = date.getMonthOfYear()
        val actualYear = date.getCurrentYear()
        val actualHours = date.getCurrentHours()
        val actualMinutes = date.getCurrentMinutes()
        val actualSeconds = date.getCurrentSeconds()

        assertEquals(expectedDay, actualDay)
        assertEquals(expectedMonth, actualMonth)
        assertEquals(expectedYear, actualYear)
        assertEquals(expectedHours, actualHours)
        assertEquals(expectedMinutes, actualMinutes)
        assertEquals(expectedSeconds, actualSeconds)
    }

    private fun getDateFrom(time: Long): Date {
        val calendar = Calendar.getInstance(LocaleHelper.DEFAULT_LOCALE)
        calendar.timeInMillis = time
        return calendar.time
    }
}