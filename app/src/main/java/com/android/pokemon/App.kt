package com.android.pokemon

import android.app.Application
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.cache.normalized.CacheKey
import com.apollographql.apollo.cache.normalized.CacheKeyResolver
import com.apollographql.apollo.cache.normalized.sql.ApolloSqlHelper
import com.apollographql.apollo.cache.normalized.sql.SqlNormalizedCacheFactory
import okhttp3.OkHttpClient

class App : Application() {

    companion object {
        private const val BASE_URL = "https://graphql-pokemon.now.sh/"
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder().build()
                chain.proceed(request)
            }
            .build()
    }

    private val apolloSqlHelper = ApolloSqlHelper.create(this, "github_cache")
    private val sqlNormalizedCacheFactory = SqlNormalizedCacheFactory(apolloSqlHelper)
    private val cacheKeyResolver = object : CacheKeyResolver() {
        override fun fromFieldRecordSet(field: ResponseField, recordSet: MutableMap<String, Any>): CacheKey {
            return if (recordSet["__typename"] == "Repository") {
                CacheKey.from(recordSet["id"] as String)
            } else {
                CacheKey.NO_KEY
            }
        }

        override fun fromFieldArguments(field: ResponseField, variables: Operation.Variables): CacheKey {
            return CacheKey.NO_KEY
        }
    }

    val apolloClient: ApolloClient by lazy {
        ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .normalizedCache(sqlNormalizedCacheFactory, cacheKeyResolver)
            .build()
    }

}