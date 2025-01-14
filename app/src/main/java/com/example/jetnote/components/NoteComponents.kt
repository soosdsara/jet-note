package com.example.jetnote.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.elevatedButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.jetnote.data.converter.DateConverter
import com.example.jetnote.model.Note
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NoteInputTextField(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier,
        //TODO -> Add supporting text for title, if it is not letter or space
    )
}

@Composable
fun NoteButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    colors: ButtonColors = elevatedButtonColors()
) {
    ElevatedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = colors,
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 15.dp)
    ) {
        Text(text)
    }
}

@Composable
fun NoteWidget(
    modifier: Modifier = Modifier,
    note: Note,
    onClick: (Note) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClick(note) }),
        shape = RoundedCornerShape(topEnd = 30.dp, bottomStart = 30.dp),
        color = MaterialTheme.colorScheme.secondaryContainer,
        tonalElevation = 6.dp,
        shadowElevation = 3.dp
    ) {
        Column(Modifier.padding(horizontal = 14.dp, vertical = 6.dp)) {
            Text(note.title, fontWeight = FontWeight.Medium)
            Text(note.description)
            Text(
                SimpleDateFormat(
                    "yyyy-MM-dd EEE HH:mm:ss",
                    Locale.getDefault()
                ).format(DateConverter().timeStampFromDate(note.entryDate)),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
