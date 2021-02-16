package com.example.mvpexample.util

class EdadValidator {

    fun edadEsValida(edad: Int): Boolean {
        return edad in 0..120
    }

}