package io.github.braayy.common.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.FileOpen
import androidx.compose.material.icons.filled.Paid
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIconDefaults
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import io.github.braayy.common.modalBackground
import io.github.braayy.common.model.Database
import io.github.braayy.common.util.FileChooser
import io.github.braayy.common.util.FileChooserOperation
import io.github.braayy.common.util.FileFilter
import io.github.braayy.common.util.conditional

typealias OnClickDatabase = (Database) -> Unit
typealias OnCreateDatabase = (String, String) -> Unit
typealias OnDeleteDatabase = (Database) -> Unit

@Composable
fun HomeScreen(
    databases: List<Database>,
    onClickDatabase: OnClickDatabase,
    onCreateDatabase: OnCreateDatabase,
    onDeleteDatabase: OnDeleteDatabase,
) {
    var showCreateAccountGroupModal by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .conditional(showCreateAccountGroupModal) {
                    blur(10.dp)
                }
                .fillMaxSize(),
        ) {
            Text(
                text = "Dengi",
                style = MaterialTheme.typography.h1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
            )

            DatabaseList(
                databases = databases,
                onClickDatabase = onClickDatabase,
                onDeleteDatabase = onDeleteDatabase,
                ignoreInteractions = showCreateAccountGroupModal,
            )

            Button(
                onClick = { showCreateAccountGroupModal = true },
                enabled = !showCreateAccountGroupModal,
            ) {
                Text("Criar Grupo")
            }
        }

        if (showCreateAccountGroupModal) {
            CreateDatabaseModal(
                onCreateDatabase = onCreateDatabase,
                onCancel = { showCreateAccountGroupModal = false }
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CreateDatabaseModal(
    onCreateDatabase: OnCreateDatabase,
    onCancel: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var showFileChooser by remember { mutableStateOf(false) }

    FileChooser(
        show = showFileChooser,
        title = "Salvar Banco de Dados do Dengi",
        operation = FileChooserOperation.Save,
        fileFilter = FileFilter("Banco de Dados do Dengi", "dengi"),
        onFileSelected = { filePath -> location = filePath }
    )

    Surface(
        color = MaterialTheme.colors.modalBackground,
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
            .size(500.dp, 200.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Nome") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )

                TextField(
                    value = location,
                    onValueChange = { location = it },
                    placeholder = { Text("Caminho do Arquivo") },
                    readOnly = true,
                    singleLine = true,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.FileOpen,
                            contentDescription = "Open file picker",
                            tint = Color.White,
                            modifier = Modifier
                                .clickable { showFileChooser = true }
                                .pointerHoverIcon(PointerIconDefaults.Default),
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                Button(
                    onClick = { onCreateDatabase(name, location) },
                ) {
                    Text("Criar")
                }

                Button(
                    onClick = onCancel,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                    ),
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}

@Composable
fun DatabaseList(
    databases: List<Database>,
    onClickDatabase: OnClickDatabase,
    onDeleteDatabase: OnDeleteDatabase,
    ignoreInteractions: Boolean,
) {
    Column (
        modifier = Modifier
            .sizeIn(
                maxWidth = 700.dp,
                maxHeight = 700.dp,
            )
            .clip(RoundedCornerShape(4.dp))
            .fillMaxSize(0.8f)
            .background(MaterialTheme.colors.surface)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        for (database in databases) {
            Database(
                database = database,
                onClick = { onClickDatabase(database) },
                onClickDelete = { onDeleteDatabase(database) },
                ignoreInteractions = ignoreInteractions,
            )
        }
    }
}

@Composable
fun Database(
    database: Database,
    onClick: () -> Unit,
    onClickDelete: () -> Unit,
    ignoreInteractions: Boolean,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondaryVariant)
            .clickable(
                enabled = !ignoreInteractions,
                onClick = onClick,
            )
            .padding(10.dp),
    ) {
        Icon(
            imageVector = Icons.Default.Paid,
            contentDescription = "Account Group Image",
            modifier = Modifier
                .size(40.dp)
        )

        Text(
            text = database.name,
            fontSize = 1.2.em,
        )

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete Account Group",
            modifier = Modifier
                .clickable(onClick = onClickDelete)
                .padding(5.dp),
        )

    }
}