package com.practice.protask.ui.protask

import android.annotation.SuppressLint
import androidx.compose.runtime.livedata.observeAsState

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.model.Note
import com.practice.protask.R
import com.practice.view.NoteViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun MainScreen(model : NoteViewModel){

    Scaffold(

        topBar = {Header(model = model)},
        content = { it -> Body(it,model = model)},
        bottomBar = { BottomHeader(model = model)}

    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(model : NoteViewModel) {

    val allNotes by model.allNotes.observeAsState(emptyList())

    val size = allNotes.size
    TopAppBar(

        modifier = Modifier
            .wrapContentSize()
            .height(80.dp)
            .fillMaxWidth(),

        title = {

            Row (horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {

                Column(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {

                    Text(

                        text = "You have got $size tasks",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White

                    )
                    Text(
                        text = "today to complete 👋",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White
                    )
                }
                Row(modifier = Modifier.padding(15.dp)) {


                    Image(

                        painter = painterResource(id = R.drawable.pf),
                        contentDescription = " Profile pic" ,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(100)),
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center)

                }
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.bg_color),
            titleContentColor = Color.White
        )

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body(pValue: PaddingValues, model : NoteViewModel){

    Box(modifier = Modifier

        .padding(pValue)
        .fillMaxWidth()
        .fillMaxHeight()
        .background(
            colorResource(
                id = R.color.bg_color
            )
        )){

       Column {
           TaskList(model = model)

       }


    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskList(model : NoteViewModel){

    val searchText = remember {
        mutableStateOf("")
    }

    TextField(value = searchText.value ,label = { Text("Search Tasks", color = Color.White, fontSize = 12.sp)} ,
        leadingIcon ={

            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.White
            )
        },

        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .background(
                colorResource(id = R.color.second_color),
                shape = RoundedCornerShape(25.dp)
            )

            .clip(RoundedCornerShape(25.dp)),
        colors = TextFieldDefaults.textFieldColors(

            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,

            ),textStyle = TextStyle(color = Color.White),

        onValueChange = {

            searchText.value = it

        })


    val allNotes by model.allNotes.observeAsState(emptyList())

    var check = remember {
        mutableStateOf(false)
    }

    var selectedNote by remember { mutableStateOf<Note?>(null) }

    var delete = remember {
        mutableStateOf(false)
    }
    val filteredNotes = if (searchText.value.isEmpty()) {
        allNotes
    } else {
        allNotes.filter { it.title.contains(searchText.value, ignoreCase = true) || it.descrition.contains(searchText.value, ignoreCase = true) }
    }
    LazyColumn(){

        items(

            count = filteredNotes.size,
            itemContent = { index ->

               

                val task = filteredNotes[index]

                Card(
                    modifier = Modifier
                        .height(120.dp)
                        .fillMaxWidth()
                        .padding(7.dp),

                    elevation = CardDefaults.cardElevation(8.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(

                        containerColor = colorResource(id = R.color.card_color)
                    )
                ) {


                    Row(modifier = Modifier.fillMaxWidth()){


                        Column(
                            modifier = Modifier.padding( 20.dp), horizontalAlignment = Alignment.Start
                        ) {



                                Text(
                                    text = task.title,
                                    style = MaterialTheme.typography.titleLarge,
                                    modifier = Modifier.padding(bottom = 4.dp),
                                    color = Color.Black
                                )

                                Text(
                                    text = task.descrition,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.DarkGray
                                )



                        }
                        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxSize()) {


                            IconButton(onClick = {

                                delete.value = true
                                selectedNote = filteredNotes[index]

                            }
                            ) {

                                Icon(

                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null
                                )
                            }

                            IconButton(onClick = {

                                check.value = true
                                selectedNote = allNotes[index]


                            }) {


                                Icon(

                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null

                                )
                            }

                        }


                    }

                }



            }
        )


    }


    if (delete.value){


        AlertDialog(
            onDismissRequest = { delete.value = false },
            confirmButton = {
                Button(onClick = {
                    delete.value = false

                    selectedNote?.let { model.delete(it.title) }
                }) {
                    Text(text = "Delete")



                }
            },
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Delete Task", style = MaterialTheme.typography.titleLarge)
                }
            },
            text = {
                Column {
                    
                    Text(text = "Are you sure you want to delete this task?")
                }
            },
            dismissButton = {
                Button(onClick = { delete.value = false }) {
                    Text(text = "Cancel")
                }
            },

            )
    }

    selectedNote?.let { updateTask(note = it, dialogStatus = check,model) }



}
@Composable
fun BottomHeader(model : NoteViewModel){

    val dialogStatus = remember { mutableStateOf(false) }

    val context = LocalContext.current
    BottomAppBar(
        containerColor = colorResource(id = R.color.bg_color),
        contentColor = Color.White

    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {


            IconButton(
                onClick = {

                          dialogStatus.value = true

                },

                modifier = Modifier.padding(16.dp), colors = IconButtonDefaults.iconButtonColors(

                    containerColor = Color.White
                )
            ) {


                    Icon(

                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = colorResource(id = R.color.bg_color),

                        )

            }
        }
    }


    DialogBox(dialogStatus = dialogStatus, model = model)

}

@Composable
fun DialogBox(dialogStatus: MutableState<Boolean>,model : NoteViewModel){
    val taskTitle = remember {
        mutableStateOf("")
    }
  val taskDes = remember {
        mutableStateOf("")
    }


    if (dialogStatus.value){


        AlertDialog(
            onDismissRequest = { dialogStatus.value = false },
            confirmButton = {
                Button(onClick = {
                    dialogStatus.value = false
                    var  note : Note = Note(title = taskTitle.value, descrition = taskDes.value, isComplete = false )
                    model.insert(note)}) {
                    Text(text = "Add")
                    taskTitle.value = ""
                    taskDes.value = ""



                }
            },
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Add Task", style = MaterialTheme.typography.titleLarge)
                }
            },
            text = {
                Column {
                    TextField(
                        value = taskTitle.value,
                        onValueChange = {
                            taskTitle.value = it
                        },
                        label = { Text("Enter Title") } ,modifier = Modifier.clip(RoundedCornerShape(25.dp))
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    TextField(
                        value = taskDes.value,
                        onValueChange = {
                            taskDes.value = it
                        },
                        label = { Text("Enter Detail") },modifier = Modifier.clip(RoundedCornerShape(25.dp))
                    )
                }
            },
            dismissButton = {
                Button(onClick = { dialogStatus.value = false }) {
                    Text(text = "Cancel")
                }
            },

        )
    }




}
@Composable
fun updateTask(note : Note,dialogStatus : MutableState<Boolean>,model : NoteViewModel){

    val title = remember {
        mutableStateOf(note.title)
    }
    val task = remember {
        mutableStateOf(note.descrition)
    }


    if (dialogStatus.value){


        AlertDialog(
            onDismissRequest = { dialogStatus.value = false },
            confirmButton = {
                Button(onClick = {

                    dialogStatus.value = false
                    model.update(note.title,title.value,task.value)


                }) {
                    Text(text = "Update")



                }
            },
            title = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Update Task", style = MaterialTheme.typography.titleLarge)
                }
            },
            text = {
                Column {
                    TextField(
                        value = title.value,
                        onValueChange = {
                            title.value = it
                        },
                        label = { Text("Enter Title") } ,modifier = Modifier.clip(RoundedCornerShape(25.dp))
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    TextField(
                        value = task.value,
                        onValueChange = {
                            task.value = it
                        },
                        label = { Text("Enter Detail") },modifier = Modifier.clip(RoundedCornerShape(25.dp))
                    )
                }
            },
            dismissButton = {
                Button(onClick = { dialogStatus.value = false }) {
                    Text(text = "Cancel")
                }
            },

            )
    }


}
