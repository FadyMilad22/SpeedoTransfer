import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.EditProfileRepo
import com.example.speedotransfer.model.UpdateCustomerRequest
import com.example.speedotransfer.model.UpdateCustomerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EditProfileScreenViewModel(private val editProfileRepo: EditProfileRepo) : ViewModel() {

    // StateFlow for handling the current state of the screen
    private val _editProfileState = MutableStateFlow<UpdateCustomerResponse?>(null)
    val editProfileState: StateFlow<UpdateCustomerResponse?> = _editProfileState
    // Loading states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // Error handling state
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage


    // MutableStateFlows for form fields
    var fullName = MutableStateFlow("")
    var email = MutableStateFlow("")
    var country = MutableStateFlow("")
    var dateOfBirth = MutableStateFlow("")

    // Method to update profile
    fun updateProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val request = UpdateCustomerRequest(
                    name = fullName.value,
                    email = email.value,
                    phoneNumber = ""
//                    country = country.value,
//                    dateOfBirth = dateOfBirth.value
                )
                val response = editProfileRepo.updateCustomerByEmail(email.value, request)
                _editProfileState.value = response
            } catch (e: Exception) {
                _errorMessage.value = "Error during editing profile: ${e.message}"}
                finally {
                    _isLoading.value = false
                }
            }
        }
    }


