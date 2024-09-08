package com.practice.repository

import androidx.lifecycle.LiveData
import com.practice.model.Note
import com.practice.room.NoteDAO

class NoteRepository (private  var noteDAO : NoteDAO){


    val allNotes : LiveData<List<Note>> = noteDAO.getAllNotes()

    suspend fun insert(note: Note){

        noteDAO.insert(note)

    }
    suspend fun update(title: String, upTitle : String, uDesc : String){

        noteDAO.update(title,upTitle,uDesc)

    }
    suspend fun delete(title: String){

        noteDAO.delete(title)

    }

}