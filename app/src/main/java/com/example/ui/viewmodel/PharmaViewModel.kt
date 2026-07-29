package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.db.AppDatabase
import com.example.data.model.FavoriteEntity
import com.example.data.model.PharmaProduct
import com.example.data.repository.PharmaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PharmaViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PharmaRepository
    val favorites: StateFlow<List<FavoriteEntity>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = PharmaRepository(database.favoriteDao())
        favorites = repository.allFavorites.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedGalenicFilter = MutableStateFlow("Tous")
    val selectedGalenicFilter: StateFlow<String> = _selectedGalenicFilter.asStateFlow()

    private val _selectedCategoryFilter = MutableStateFlow("Toutes")
    val selectedCategoryFilter: StateFlow<String> = _selectedCategoryFilter.asStateFlow()

    private val _searchResults = MutableStateFlow<List<PharmaProduct>>(emptyList())
    val searchResults: StateFlow<List<PharmaProduct>> = _searchResults.asStateFlow()

    private val _selectedProductForDetail = MutableStateFlow<PharmaProduct?>(null)
    val selectedProductForDetail: StateFlow<PharmaProduct?> = _selectedProductForDetail.asStateFlow()

    // Simulator State
    private val _simulatorCifInput = MutableStateFlow("100000")
    val simulatorCifInput: StateFlow<String> = _simulatorCifInput.asStateFlow()

    private val _simulatorCurrency = MutableStateFlow("MAD")
    val simulatorCurrency: StateFlow<String> = _simulatorCurrency.asStateFlow()

    private val _simulatorDutyRate = MutableStateFlow(2.5)
    val simulatorDutyRate: StateFlow<Double> = _simulatorDutyRate.asStateFlow()

    init {
        performSearch()
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        performSearch()
    }

    fun onGalenicFilterSelect(filter: String) {
        _selectedGalenicFilter.value = filter
        performSearch()
    }

    fun onCategoryFilterSelect(category: String) {
        _selectedCategoryFilter.value = category
        performSearch()
    }

    private fun performSearch() {
        val query = _searchQuery.value
        val galenic = _selectedGalenicFilter.value
        val category = _selectedCategoryFilter.value
        _searchResults.value = repository.searchProducts(query, galenic, category)
    }

    fun selectProductForDetail(product: PharmaProduct?) {
        _selectedProductForDetail.value = product
        if (product != null) {
            _simulatorDutyRate.value = product.dutyRate
        }
    }

    fun toggleFavorite(product: PharmaProduct) {
        viewModelScope.launch {
            val isFav = repository.isFavorite(product.id)
            if (isFav) {
                repository.removeFavorite(product.id)
            } else {
                repository.addFavorite(product)
            }
        }
    }

    fun isProductFavorite(productId: String, favList: List<FavoriteEntity>): Boolean {
        return favList.any { it.id == productId }
    }

    fun getCategories(): List<String> = repository.getAllCategories()
    fun getGalenicForms(): List<String> = repository.getAllGalenicForms()

    // Simulator updates
    fun updateSimulatorCif(input: String) {
        _simulatorCifInput.value = input
    }

    fun updateSimulatorCurrency(currency: String) {
        _simulatorCurrency.value = currency
    }

    fun updateSimulatorDutyRate(rate: Double) {
        _simulatorDutyRate.value = rate
    }
}
