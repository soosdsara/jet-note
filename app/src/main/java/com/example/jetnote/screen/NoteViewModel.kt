package com.example.jetnote.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note

class NoteViewModel : ViewModel() {
    val notes = mutableStateListOf<Note>()

    init {
        notes.addAll(NotesDataSource().loadNotes())
    }
    fun addNote(note: Note) {
        notes.add(note)
    }

    fun removeNote(note: Note) {
        notes.remove(note)
    }

    fun getAllNotes(): List<Note> {
        return notes
    }

}