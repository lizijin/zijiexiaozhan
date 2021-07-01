package com.peter.viewgrouptutorial.coroutines

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository {
    var client: Webservice = RetrofitClient.webservice
    suspend fun getTodo(id: Int) = withContext(Dispatchers.IO) {
        client.getTodo(id)
    }
}