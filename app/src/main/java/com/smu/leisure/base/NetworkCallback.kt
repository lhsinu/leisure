package com.smu.leisure.base

interface NetworkCallback {
    fun onMessageReceived(message: String)
    fun onConnectionEstablished()
    fun onConnectionClosed()
}