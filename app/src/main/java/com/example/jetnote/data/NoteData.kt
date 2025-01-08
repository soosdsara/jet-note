package com.example.jetnote.data

import com.example.jetnote.model.Note

class NotesDataSource{
    fun loadNotes(): List<Note>{
        return listOf(
            Note(title = "Dinner", description = "Pizza"),
            Note(title = "A good day", description = "Yesterdays was a good day, because we went to the beach"),
            Note(title = "Homework", description = "I have to finnish my NoteApp"),
            Note(title = "SLQ", description = "Practice SQL on CodeAcademy"),
        )
    }
}