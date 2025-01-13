package com.example.jetnote.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetnote.components.NoteButton
import com.example.jetnote.components.NoteInputTextField
import com.example.jetnote.components.NoteWidget
import com.example.jetnote.data.NotesDataSource
import com.example.jetnote.model.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemove: (Note) -> Unit
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(
                            "My Notes",
                            modifier = Modifier.padding(start = 10.dp),
                            fontWeight = FontWeight.Medium
                        )
                    }
                },
                modifier = Modifier.padding(3.dp),
                colors = topAppBarColors(MaterialTheme.colorScheme.primaryContainer),
                actions = {
                    SmallFloatingActionButton(
                        modifier = Modifier.padding(end = 18.dp),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.secondary,
                        onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Note Icon",
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputTextField(
                modifier = Modifier.padding(top = 20.dp),
                text = title,
                label = "Title",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() }) title = it
                },
            )
            NoteInputTextField(
                modifier = Modifier.padding(top = 10.dp),
                text = description,
                label = "Description",
                onTextChange = {
                    description = it
                },
                onImeAction = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                    }
                }
            )
            NoteButton(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .width(100.dp),
                text = "Save",
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title, description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                })
            HorizontalDivider(Modifier.padding(horizontal = 6.dp, vertical = 20.dp))
            LazyColumn {
                items(notes) { note ->
                    NoteWidget(note = note, onClick = {
                        onRemove(note)
                    })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreen(NotesDataSource().loadNotes(), {}, {})
}