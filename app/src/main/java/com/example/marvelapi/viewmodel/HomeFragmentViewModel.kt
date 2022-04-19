package com.example.marvelapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapi.core.ApiResult
import com.example.marvelapi.model.CharacterModel
import com.example.marvelapi.viewmodel.repository.HomeFragmentRepository
import kotlinx.coroutines.launch

class HomeFragmentViewModel(private val homeFragmentRepository: HomeFragmentRepository):ViewModel() {

    var callCharactersResult = MutableLiveData<ApiResult<CharacterModel>>()

    fun callResult() {
        viewModelScope.launch {
            val result = homeFragmentRepository.getCharactersList()
            if (result is ApiResult.Success) {
                callCharactersResult.value = ApiResult.Success(result.value)
            }else if (result is ApiResult.Error) {
                callCharactersResult.value = ApiResult.Error(result.apiError)

            }
        }
    }
}
