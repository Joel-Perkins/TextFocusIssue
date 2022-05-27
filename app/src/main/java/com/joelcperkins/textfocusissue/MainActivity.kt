package com.joelcperkins.textfocusissue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import com.joelcperkins.textfocusissue.ui.theme.TextFocusIssueTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFocusIssueTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    RequestFocus("Android")
                }
            }
        }
    }
}

@Composable
fun ClearFocus(name: String) {
    var text: String by remember { mutableStateOf("") }
    val focusManager: FocusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") },
        modifier = Modifier.onFocusChanged {
            if (it.isFocused) {
                focusManager.clearFocus(true)
            }
        },
    )
}

@Composable
fun RequestFocus(name: String) {
    var text: String by remember { mutableStateOf("") }
    val textFocus = FocusRequester()

    Column() {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            modifier = Modifier.onFocusChanged {
                if (it.isFocused) {
                    textFocus.requestFocus()
                }
            },
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label2") },
            modifier = Modifier.focusRequester(textFocus)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TextFocusIssueTheme {
        ClearFocus("Android")
    }
}