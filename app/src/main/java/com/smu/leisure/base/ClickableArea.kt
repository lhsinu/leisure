package com.smu.leisure.base

class ClickableArea(
    private val left: Float,
    private val top: Float,
    private val right: Float,
    private val bottom: Float,
    private val action: () -> Unit
) {
    fun contains(x : Float, y : Float) : Boolean {
        return x >= left && x <= right && y >= top && y <= bottom
    }

    fun performAction() {
        action.invoke()
    }
}