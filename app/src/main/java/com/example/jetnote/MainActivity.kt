package com.example.jetnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.jetnote.model.Note
import com.example.jetnote.screen.NoteScreen
import com.example.jetnote.ui.theme.JetNoteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            JetNoteTheme {
                val notes = remember { mutableStateListOf<Note>() }
                NoteScreen(notes,
                    onAddNote = { notes.add(it) },
                    onRemove = {notes.remove(it)})
            }
        }
    }
}



