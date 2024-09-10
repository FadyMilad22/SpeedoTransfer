import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.speedotransfer.data.repository.favourite.FavRepo
import com.example.speedotransfer.model.DeleteFavouriteResponse
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.model.FavouriteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavViewModel(private val favRepo: FavRepo) : ViewModel() {

    // StateFlow for holding the list of favourites
    private val _favourites = MutableStateFlow<List<FavouriteResponse>>(emptyList())
    val favourites: StateFlow<List<FavouriteResponse>> = _favourites

    // StateFlow for delete response
    private val _deleteResponse = MutableStateFlow<DeleteFavouriteResponse?>(null)
    val deleteResponse: StateFlow<DeleteFavouriteResponse?> = _deleteResponse


    // StateFlow for add favourite response
    private val _addFavResponse = MutableStateFlow<FavouriteResponse?>(null)
    val addFavResponse: StateFlow<FavouriteResponse?> = _addFavResponse


    // Loading and error states
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    // Fetch all favourites
    fun getAllFav(authToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val favouritesResponse = favRepo.getAllFavourites(authToken)
                withContext(Dispatchers.Main) {
                    _favourites.value = favouritesResponse
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    // Delete a favourite
    fun deleteFavourite(authToken: String, favouriteId: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val deleteResponse = favRepo.deleteFavourite(authToken, favouriteId)
                withContext(Dispatchers.Main) {
                    _deleteResponse.value = deleteResponse
                    // Optionally refresh the list after deletion
                    getAllFav(authToken)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

    // Add a customer to favourites
    fun addCustomerToFavourite(authToken: String, favouriteRequest: FavouriteRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isLoading.value = true
                val addResponse = favRepo.addCustomerToFavourite(authToken, favouriteRequest)
//                withContext(Dispatchers.Main) {
                    _addFavResponse.value = addResponse
                    Log.d("Favourite", "Favourite added successfully: ${addResponse.addedAt}")
//                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _errorMessage.value = e.message
                    Log.e("Favourite Error", "Error adding favourite: ${e.localizedMessage}")
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }

}
