package com.dodemy.mvvm_creaturemon.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dodemy.mvvm_creaturemon.model.Creature
import com.dodemy.mvvm_creaturemon.model.CreatureAttributes
import com.dodemy.mvvm_creaturemon.model.CreatureGenerator
import com.dodemy.mvvm_creaturemon.model.CreatureRepository
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CreatureViewModelTest {

    private lateinit var  creatureViewModel: CreatureViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockGenerator: CreatureGenerator

    @Mock
    lateinit var repository: CreatureRepository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        creatureViewModel = CreatureViewModel(mockGenerator, repository)
    }

    @Test
    fun testSetupCreature(){
        val attributes = CreatureAttributes(10, 3, 7)
        val stubCreature = Creature(attributes, 87, "Test Creature")
        `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

        creatureViewModel.intelligence = 10
        creatureViewModel.strength = 3
        creatureViewModel.endurance = 7

        creatureViewModel.updateCreature()

        assertEquals(stubCreature, creatureViewModel.creature)
    }

    @Test
    fun testCantSaveCreatureWithBlankName(){
        creatureViewModel.intelligence = 10
        creatureViewModel.strength = 3
        creatureViewModel.endurance = 7
        creatureViewModel.drawable = 1
        creatureViewModel.name.set("")

        val canSaveCreature = creatureViewModel.canSaveCreature()
        assertEquals(false, canSaveCreature)
    }

    @Test
    fun testCantSaveCreatureWithoutStrength(){
        creatureViewModel.intelligence = 10
        creatureViewModel.strength = 0
        creatureViewModel.endurance = 7
        creatureViewModel.drawable = 1
        creatureViewModel.name.set("Name of creature")

        val canSaveCreature = creatureViewModel.canSaveCreature()
        assertEquals(false, canSaveCreature)
    }
}