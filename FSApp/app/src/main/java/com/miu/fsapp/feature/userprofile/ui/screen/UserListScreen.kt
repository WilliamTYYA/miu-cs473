package com.miu.fsapp.feature.userprofile.ui.screen

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.fsapp.core.network.ApiProvider
import com.miu.fsapp.feature.userprofile.data.remote.api.UserApiService
import com.miu.fsapp.feature.userprofile.data.repository.UserRepositoryImpl
import com.miu.fsapp.feature.userprofile.ui.state.CreateUserUiState
import com.miu.fsapp.feature.userprofile.ui.viewmodel.CreateUserViewModel
import com.miu.fsapp.feature.userprofile.ui.viewmodel.UserListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListScreen(modifier: Modifier = Modifier) {
    val userApiService: UserApiService = remember {
        ApiProvider.userApiService
    }
    val userListViewModel: UserListViewModel = viewModel {
        UserListViewModel(
            UserRepositoryImpl(userApiService)
        )
    }
    // Read uiState from viewmodel
    val userListUiState by userListViewModel.userListUiState.collectAsStateWithLifecycle()
    val openDialog = rememberSaveable { mutableStateOf(false) }

    val createUserViewModel: CreateUserViewModel = viewModel {
        CreateUserViewModel(
            UserRepositoryImpl(userApiService)
        )
    }
    val createUserUiState by createUserViewModel.createUserUiState.collectAsStateWithLifecycle()

    // it will be automatically triggered whenever there is some chnage in userResponseDto
    LaunchedEffect(createUserUiState.userResponseDto) {
        userListViewModel.getAllUsers()
        //empty the createUserUiState
        createUserViewModel.resetUserUiState()
    }

    val context = LocalContext.current
    LaunchedEffect(createUserUiState.error) {
        if (createUserUiState.error != null) {
            Toast.makeText(context, createUserUiState.error ?: "Registration failed", Toast.LENGTH_LONG).show()
        }
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                },
//                containerColor = Color.Red
            ) {
                // Include an icon to display
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
//                    tint = Color.Green
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(userListUiState.users) {
                Card(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = it.username)
                    Text(text = it.profile.firstName)
                    Text(text = it.profile.lastName)
                    Text(text = it.profile.bio)
                }

                HorizontalDivider()
                Spacer(modifier=Modifier.height(8.dp))
            }
        }
    }

    if (openDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality, simply use an empty
                // onDismissRequest.
                openDialog.value = false
            }
        ) {
            Surface(
                modifier = Modifier.wrapContentWidth().wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    // Provide 8 outlineTextFields to register a user
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.username,
                        onValueChange = {
                            createUserViewModel.updateUserName(it)
                        },
                        label = { Text("username") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.password,
                        onValueChange = {
                            createUserViewModel.updatePassword(it)
                        },
                        label = { Text("password") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.profileRequestDto.firstName,
                        onValueChange = {
                            createUserViewModel.updateFirstName(it)
                        },
                        label = { Text("First Name") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.profileRequestDto.lastName,
                        onValueChange = {
                            createUserViewModel.updateLastName(it)
                        },
                        label = { Text("Last Name") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.profileRequestDto.dateOfBirth,
                        onValueChange = {
                            createUserViewModel.updateDateOfBirth(it)
                        },
                        label = { Text("Date of Birth") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.profileRequestDto.email,
                        onValueChange = {
                            createUserViewModel.updateEmail(it)
                        },
                        label = { Text("Email") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.profileRequestDto.phoneNumber,
                        onValueChange = {
                            createUserViewModel.updatePhoneNumber(it)
                        },
                        label = { Text("Phone Number") },
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = createUserUiState.userRequestDto.profileRequestDto.bio,
                        onValueChange = {
                            createUserViewModel.updateBio(it)
                        },
                        label = { Text("Bio") },
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        TextButton(
                            onClick = {

                            },
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("Clear")
                        }
                        TextButton(
                            onClick = {
                                createUserViewModel.createUser()
                                openDialog.value = false
                                      },
                            modifier = Modifier.weight(1f),
                        ) {
                            Text("Register")
                        }
                    }

                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun UserListScreenPreview() {
    UserListScreen()
}