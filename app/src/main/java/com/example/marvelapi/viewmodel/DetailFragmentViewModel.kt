package com.example.marvelapi.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapi.core.ApiResult
import com.example.marvelapi.model.ComicModel
import com.example.marvelapi.viewmodel.repository.DetailFragmentRepository
import kotlinx.coroutines.launch

class DetailFragmentViewModel(private val detailFragmentRepository: DetailFragmentRepository):ViewModel() {

    var callComicsLiveData=MutableLiveData<ApiResult<ComicModel>>()


    fun callComics(id:String,dateRange:String,orderBy:String,limit:String){
        viewModelScope.launch {
            val result = detailFragmentRepository.callComics(id,dateRange,orderBy,limit)
            if(result is ApiResult.Success){
                callComicsLiveData.value=ApiResult.Success(result.value)
            }else if(result is ApiResult.Error){
                callComicsLiveData.value=ApiResult.Error(result.apiError)
            }
        }
    }
}