package com.practice.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "NoteTable")
 class Note (val title : String, val descrition : String , val isComplete : Boolean) {

     @PrimaryKey(autoGenerate = true)
     var id = 0
}