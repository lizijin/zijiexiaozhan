package com.peter.viewgrouptutorial.coroutines

class TodoRepository {
    var client: Webservice = RetrofitClient.webservice
    suspend fun getTodo(id: Int) =
        client.getTodo(id)


}