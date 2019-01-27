package com.example.h260210.kotmovies_prac_1.rest

import com.example.h260210.kotmovies_prac_1.common.APIConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator {

    companion object {
        private fun getInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(APIConstants.MOVIES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getInstance().create(serviceClass)
        }
    }

}
