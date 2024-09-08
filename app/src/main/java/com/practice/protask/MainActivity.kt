package com.practice.protask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.NoteApplication
import com.practice.protask.ui.protask.MainScreen
import com.practice.protask.ui.theme.ProTaskTheme
import com.practice.view.NoteViewModel
import com.practice.view.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var  noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProTaskTheme {
                val viewModelFactory : NoteViewModelFactory = NoteViewModelFactory((application as NoteApplication).repository)

                noteViewModel = ViewModelProvider(this,viewModelFactory)[NoteViewModel::class]

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.bg_color)
                ) {

                    // Pass the ViewModel to MainScreen if needed
                    MainScreen(model = noteViewModel)
                }
            }
        }
    }
}

