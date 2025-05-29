package com.example.contactapp.data.presantation.screens

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.contactapp.MainActivity

import com.example.contactapp.data.database.AppState
import com.example.contactapp.data.database.ContactViewModel
import com.example.contactapp.data.enitity.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenUI(viewModel: ContactViewModel,
                 onClickNavigate: () -> Unit){
var state = viewModel.state.collectAsState().value
    var expanded by remember { mutableStateOf(false) }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Contact Keeper") }
         , actions = {

             IconButton(onClick = { expanded = true}, modifier = Modifier.padding(end = 10.dp)) {
                 Icon(imageVector = Icons.Default.MoreVert
                     , contentDescription = "Sort")
             }

                    DropdownMenu(expanded = expanded,
                        onDismissRequest = {
                            expanded = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Sort by Name (A-Z)")
                            },
                            onClick = {
                                viewModel.SortByName()
                                expanded = false

                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text("Sort by Date")
                            },
                            onClick = {
                                viewModel.SortByDate()
                                expanded = false

                            }
                        )

                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onClickNavigate()
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
        ){  innerPadding ->
            LazyColumn(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                items(state.dataOfContacts) {
                    contact ->
                    val bitmap = contact.image?.let {
                        BitmapFactory.decodeByteArray(it,0,it.size)
                    }?.asImageBitmap()
                    contactCart(
                       state = state,
//                        name = contact.name,
//                        email = contact.email,
//                        phone = contact.phoneNumber,
//                        imageByteArray = contact.image,
//                        id = contact.id,
//                        dateOfCreation = contact.dataOfCreation,
                       viewModel = viewModel,
                        contact = contact,
                        image = bitmap,
                        onClick = {
                            onClickNavigate()
                        }

                    )
                }

            }




    }


}
@Composable
fun contactCart(
//    name: String,
//    phone: String,
//    email : String,
//    dateOfCreation: Long,
//    id: Int,
//    imageByteArray: ByteArray?,
    contact: Contact,
    image: ImageBitmap?,
    viewModel: ContactViewModel,
   state : AppState,
    onClick : () -> Unit
){
    val context = LocalContext.current
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)
        .clip(RoundedCornerShape(12.dp))
        , onClick = {
        state.name.value = contact.name
        state.email.value = contact.email
        state.phoneNumber.value = contact.phoneNumber
        state.id.value = contact.id
        state.image.value = contact.image //imageByteArray
        state.dateOfCreation.value = contact.dateOfCreation
        onClick()
    }) {
        Row(modifier = Modifier.fillMaxWidth().padding(10.dp)
        , verticalAlignment = Alignment.CenterVertically,
            ) {

            if (image != null) {
                Image(
                    bitmap = image,
                    contentDescription = "Contact Image", contentScale = ContentScale.Crop,
                    modifier = Modifier.size(66.dp)
                        .clip(CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Contact Image",
                    modifier = Modifier.size(66.dp).clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onPrimaryContainer).padding(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.fillMaxWidth(0.8f)) {
                Text(
                    text = contact.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                            maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = contact.phoneNumber,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = contact.email,
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }


            Spacer(modifier = Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IconButton(onClick = {

                    viewModel.deleteContact(contact)
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete, contentDescription = null,
                        tint = MaterialTheme.colorScheme.error
                    )
                }

                    IconButton(onClick = {
                        if(ContextCompat.checkSelfPermission(context, android.Manifest.permission.CALL_PHONE)  != PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(context as Activity, arrayOf(android.Manifest.permission.CALL_PHONE),1)
                        }
                        val intent = Intent(Intent.ACTION_CALL)
                        intent.data = Uri.parse("tel:${contact.phoneNumber}")
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Call",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
