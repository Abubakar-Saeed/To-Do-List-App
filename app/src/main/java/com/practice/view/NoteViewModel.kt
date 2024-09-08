package com.practice.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practice.model.Note
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModelProvider

import com.practice.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val  repository : NoteRepository ) : ViewModel(){

    var allNotes : LiveData<List<Note>> = repository.allNotes


    fun insert(note : Note) {

        viewModelScope.launch (Dispatchers.IO ){
            repository.insert(note)

        }

    }
    fun update(title: String, upTitle : String, uDesc : String) {

        viewModelScope.launch (Dispatchers.IO ){
            repository.update(title,upTitle,uDesc)

        }

    }
    fun delete(note: String) {


        viewModelScope.launch (Dispatchers.IO ){
            repository.delete(note)

        }

    }

}
class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            return NoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}