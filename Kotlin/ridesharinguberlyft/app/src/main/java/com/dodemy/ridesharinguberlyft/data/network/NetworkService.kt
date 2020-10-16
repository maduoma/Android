package com.dodemy.ridesharinguberlyft.data.network

import com.dodemy.ridesharinguberlyft.simulator.WebSocket
import com.dodemy.ridesharinguberlyft.simulator.WebSocketListener

class NetworkService {

    fun createWebSocket(webSocketListener: WebSocketListener): WebSocket {
        return WebSocket(webSocketListener)
    }

}