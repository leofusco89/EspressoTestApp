package com.example.mvpexample

import com.example.mvpexample.util.EdadValidator
import org.junit.Assert
import org.junit.Test

class EdadValidatorTest {

    private val edadValidator = EdadValidator()

    @Test
    fun debe_devolver_falso_cuando_edad_menora_cero() {
        val edad = -1

        val edadValida = edadValidator.edadEsValida(edad)

        Assert.assertFalse(edadValida)
    }

    @Test
    fun debe_devolver_falso_cuando_edad_mayora_120() {
        val edad = 200

        val edadValida = edadValidator.edadEsValida(edad)

        Assert.assertFalse(edadValida)
    }

    @Test
    fun debe_devolver_true_cuando_edad_mayora_cero_y_menora_120() {
        val edad = 30

        val edadValida = edadValidator.edadEsValida(edad)

        Assert.assertTrue(edadValida)
    }

}