package com.dodemy.todolistwithroomtesting.list

import com.dodemy.todolistwithroomtesting.R
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ListUtilsKtTest(
        private val expected: Int,
        private val dueDate: Long?,
        private val done: Boolean,
        private val scenario: String
) {
    companion object {
        private val now = System.currentTimeMillis()
        private const val day = 1000 * 60 * 60 * 24

        @JvmStatic
        @Parameterized.Parameters(name = "determineCardColor: {3}")
        fun todos() = listOf(
                arrayOf(R.color.todoDone, null, true, "Done, no date"),
                arrayOf(R.color.todoNotDue, null, false, "Not done, no date"),
                arrayOf(R.color.todoOverDue, now - day, false, "Not done, due yesterday")
        )
    }

    @Test
    fun testDetermineCardColor() {
        //Act
        val actual = determineCardColor(dueDate, done)
        //Assert
        assertEquals(expected, actual)
    }
}