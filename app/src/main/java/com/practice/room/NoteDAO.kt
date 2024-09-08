package com.practice.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.practice.model.Note


@Dao
interface NoteDAO {


    @Insert
    suspend fun insert(note : Note)
    @Query("UPDATE NoteTable SET title = :upTitle, descrition = :uDesc WHERE title = :title")
    suspend fun update(title: String, upTitle : String, uDesc : String)
    @Query("DELETE FROM NoteTable WHERE title = :title")
    suspend fun delete(title: String)
    @Query ("Select * from NoteTable order by id asc")
     fun getAllNotes(): LiveData<List<Note>>


}