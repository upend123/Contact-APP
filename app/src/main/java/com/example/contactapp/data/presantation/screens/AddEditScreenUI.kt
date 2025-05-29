package com.example.contactapp.data.presantation.screens

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.contactapp.data.database.ContactViewModel
import com.example.contactapp.data.utils.ImageCompress
import java.io.InputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreenUI(
    viewModel: ContactViewModel,
    onClick: () -> Unit,
    onBackIconClick: () -> Boolean
){
var state = viewModel.state.collectAsState()
    val context = LocalContext.current
    val pickMedia = rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) {
        uri ->
        if(uri != null){
            val inputStream : InputStream? = uri.let {
                context.contentResolver.openInputStream(it)
            }
            val byte = inputStream?.readBytes()
            if(byte != null){
                val compressedImage = ImageCompress(imageData = byte)
                if(compressedImage.size > 1024 * 1024){
                    Toast.makeText(context,"Image size is too large,Please choose a smaller image",
                        Toast.LENGTH_SHORT).show()
                }else
                {
                    state.value.image.value = compressedImage
                }
            }
        }

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Add & Edit Contact")
                },
                navigationIcon = {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "BackIcon",
                        modifier = Modifier.clickable{
                            onBackIconClick()
                        }.padding(12.dp)
                    )
                }

            )
        }
    ){
        innerPadding ->

        Column(modifier = Modifier.padding(innerPadding).fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            val image = state.value.image.value?.let {
                BitmapFactory.decodeByteArray(it,0,it.size)
            }?.asImageBitmap()
            Spacer(modifier = Modifier.height(8.dp))

            Box(modifier = Modifier.size(140.dp),
                contentAlignment = Alignment.BottomCenter){
                if(image != null){
                    Image(bitmap = image,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = "Contact Image",
                        modifier = Modifier.size(140.dp)
                            .clip(CircleShape)
                            .background(Color.Gray))

                }
                else{
                    Icon(imageVector = Icons.Default.Person
                    , contentDescription = "Contact Image",
                        modifier = Modifier.size(140.dp).
                        clip(CircleShape).background(Color.LightGray)
                            .padding(24.dp),
                        tint = MaterialTheme.colorScheme.onSurface


                    )
                }
                IconButton(onClick = {
                    pickMedia.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)

                    )
                }, modifier = Modifier.size(36.dp).clip(CircleShape).background(MaterialTheme.colorScheme.primary).align(Alignment.BottomEnd).padding(end = 5.dp),
                    ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "add"
                    , tint = Color.White)

                }
            }


            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = state.value.name.value,
                onValueChange = {
                    state.value.name.value = it
                }
                , modifier = Modifier.fillMaxWidth().padding(10.dp),
                label = {
                    Text("Name")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text
                ),
                leadingIcon = {
                    Image( Icons.Default.Person, contentDescription = null)
                }
            )
            OutlinedTextField(
                value = state.value.phoneNumber.value,
                onValueChange = {
                    state.value.phoneNumber.value = it
                }
                , modifier = Modifier.fillMaxWidth().padding(10.dp),
                label = {
                    Text("PhoneNumber")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),

                leadingIcon = {
                    Image( Icons.Default.Phone, contentDescription = null)
                }
            )
            OutlinedTextField(
                value = state.value.email.value,
                onValueChange = {
                    state.value.email.value = it
                }
                , modifier = Modifier.fillMaxWidth().padding(10.dp),
                label = {
                    Text("Email")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                leadingIcon = {
                    Image( Icons.Default.Email, contentDescription = null)
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
                  onClick()
              }, modifier = Modifier.fillMaxWidth().padding(10.dp),
              shape = RoundedCornerShape(15.dp),

                  ) {
                  Text("Save Contact")
              }
        }


    }


}

