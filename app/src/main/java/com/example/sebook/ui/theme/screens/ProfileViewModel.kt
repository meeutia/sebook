package com.example.sebook.ui.theme.screens

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sebook.data.Profile
import com.example.sebook.data.ProfileRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    data class UiState(
        val fullName: String = "",
        val username: String = "",
        val email: String = "",
        val imageUri: String? = null,
        val nameInvalid: Boolean = false,
        val emailInvalid: Boolean = false,
        val usernameInvalid: Boolean = false,
        val canSave: Boolean = false
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.profileFlow.collect { profile ->
                _uiState.value = _uiState.value.copy(
                    fullName = profile.fullName,
                    username = profile.username,
                    email = profile.email,
                    imageUri = profile.imageUri
                ).validated()
            }
        }
    }

    fun updateFullName(value: String) {
        _uiState.value = _uiState.value.copy(fullName = value).validated()
    }

    fun updateUsername(value: String) {
        _uiState.value = _uiState.value.copy(username = value).validated()
    }

    fun updateEmail(value: String) {
        _uiState.value = _uiState.value.copy(email = value).validated()
    }

    fun updateImage(uriString: String?) {
        _uiState.value = _uiState.value.copy(imageUri = uriString).validated()
    }

    fun saveProfile() {
        val s = _uiState.value
        if (!s.canSave) return
        viewModelScope.launch {
            repository.saveProfile(
                Profile(
                    fullName = s.fullName.trim(),
                    username = s.username.trim(),
                    email = s.email.trim(),
                    imageUri = s.imageUri
                )
            )
        }
    }

    private fun UiState.validated(): UiState {
        val nameInvalid = fullName.isNotBlank() && (
            fullName.trim().split(Regex("\\s+")).size < 2 || fullName.any { it.isDigit() }
        )
        val emailInvalid = email.isNotBlank() && !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val usernameInvalid = username.isBlank()
        val canSave = fullName.isNotBlank() && username.isNotBlank() && email.isNotBlank() && !nameInvalid && !emailInvalid
        return copy(nameInvalid = nameInvalid, emailInvalid = emailInvalid, usernameInvalid = usernameInvalid, canSave = canSave)
    }
}

class ProfileViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(ProfileRepository(context.applicationContext)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

