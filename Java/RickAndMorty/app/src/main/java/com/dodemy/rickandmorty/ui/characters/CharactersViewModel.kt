package com.dodemy.rickandmorty.ui.characters

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.dodemy.rickandmorty.data.repository.CharacterRepository


class CharactersViewModel @ViewModelInject constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters()
}
