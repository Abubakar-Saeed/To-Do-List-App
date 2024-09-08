package com

import android.app.Application
import com.practice.repository.NoteRepository
import com.practice.room.NoteDatabase

class NoteApplication : Application () {


    private val database by lazy { NoteDatabase.getDatabase(this) }
    val repository by lazy { NoteRepository(database.getNoteDao()) }

}

