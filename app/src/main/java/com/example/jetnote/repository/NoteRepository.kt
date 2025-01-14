package com.example.jetnote.repository

import com.example.jetnote.data.NoteDatabaseDao
import com.example.jetnote.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {
    suspend fun addNote(note: Note) = noteDatabaseDao.insertNote(note)
    suspend fun deleteNote(note: Note) = noteDatabaseDao.deleteNote(note)
    suspend fun deleteAllNotes() = noteDatabaseDao.deleteAll()
    suspend fun updateNote(note: Note) = noteDatabaseDao.updateNote(note)
    suspend fun getNoteById(id: String) = noteDatabaseDao.getNoteById(id)
    fun getNotes() = noteDatabaseDao.getNotes().flowOn(Dispatchers.IO).conflate()
}