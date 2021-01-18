package com.dodemy.mvvm_creaturemon.model

import com.dodemy.mvvm_creaturemon.model.room.CreatureAttributesConverter
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class CreatureAttributesConverterTest {

    private lateinit var attributesConverter: CreatureAttributesConverter

    @Before
    fun setup() {
        attributesConverter = CreatureAttributesConverter()
    }

    @Test
    fun testFromCreatureAttributes() {
        val attributes = CreatureAttributes(
            intelligence = 10,
            strength = 0,
            endurance = 7
        )
        val expectValue = "10,0,7"

        assertEquals(expectValue, attributesConverter.fromCreatureAttributes(attributes))
    }

    @Test
    fun testFromCreatureAttributesNull() {
        assertNull(attributesConverter.fromCreatureAttributes(null))
    }

    @Test
    fun testToCreatureAttributes() {
        val value = "3,7,10"
        val expectedAttributes = CreatureAttributes(
            intelligence = 3,
            strength = 7,
            endurance = 10
        )

        assertEquals(expectedAttributes, attributesConverter.toCreatureAttributes(value))
    }

    @Test
    fun testToCreatureAttributesNull() {
        assertNull(attributesConverter.toCreatureAttributes(null))
    }
}