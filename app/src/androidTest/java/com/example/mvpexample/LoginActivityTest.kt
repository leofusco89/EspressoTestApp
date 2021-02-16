package com.example.mvpexample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.mvpexample.ui.HomeActivity
import com.example.mvpexample.ui.LoginActivity
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.not
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(LoginActivity::class.java) //Indicamos la activity a testear

    @Test
    fun btn_iniciar_sesion_must_be_visible() {
        onView(withId(R.id.btnLogin)) //Componente ViewMatcher de Espresso: Indicamos vista a aplicarle ViewActions y ViewAssertions
                .check(matches(isDisplayed())) //Componente ViewAssertions de Espresso: Indicamos test de validación de que esa vista debe estar visible
    }

    @Test
    fun toast_must_be_shown_when_email_and_password_empty() {
        //Vamos a dejar vacíos los textos de usuario y contraseña, y clickear Iniciar sesión, para
        //verificar si se ve el Toast indicando el error "Información inválida":

        //Dejar vacíos los textos de usuario y contraseña
        onView(withId(R.id.etEmail)) //Componente ViewMatcher de Espresso: Indicamos vista a aplicarle ViewActions y ViewAssertions
                .perform(typeText("")) //Componente ViewActions de Espresso: Indicamos la acción de completar con el texto indicado
        onView(withId(R.id.etPassword)) //Componente ViewMatcher de Espresso: Indicamos vista para realizarle cambios
                .perform(typeText("")) //Componente ViewActions de Espresso: Indicamos la acción de completar con el texto indicado

        //Clickear Iniciar sesión
        onView(withId(R.id.btnLogin)) //Componente ViewMatcher de Espresso: Indicamos vista a aplicarle ViewActions y ViewAssertions
                .perform(click()) //Componente ViewActions de Espresso: Indicamos que se realice acción click sobre la vista

        //Verificar si se ve el Toast indicando el error "Información inválida"
        onView(withText("Información inválida")) //Componente ViewMatcher de Espresso: Indicamos vista a aplicarle ViewActions y ViewAssertions
                .inRoot(withDecorView(not(`is`(activityRule.activity.window.decorView)))) //Indicamos que el texto anterior esté asignado a este contexto, lo cual indicaría que es un Toast
                .check(matches(isDisplayed())) //Componente ViewAssertions de Espresso: Indicamos test de validación de que este texto  debe estar visible
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setup() {
            Intents.init()
        }
    }

    @Test
    fun must_launch_home_activity_when_login_with_email_password_valid() {
        //Vamos a completar usuario y contraseña válido, y clickear Iniciar sesión, esperar delay
        //programado, y vverificar si se ve el Toast indicando el error "Información inválida":

        //Completar usuario y contraseña válido
        onView(withId(R.id.etEmail)) //Componente ViewMatcher de Espresso: Indicamos vista a aplicarle ViewActions y ViewAssertions
                .perform(typeText("leofusco89@gmail.com")) //Componente ViewActions de Espresso: Indicamos la acción de completar con el texto indicado
        onView(withId(R.id.etPassword)) //Componente ViewMatcher de Espresso: Indicamos vista para realizarle cambios
                .perform(typeText("123456")) //Componente ViewActions de Espresso: Indicamos la acción de completar con el texto indicado

        //Clickear Iniciar sesión
        onView(withId(R.id.btnLogin)) //Componente ViewMatcher de Espresso: Indicamos vista a aplicarle ViewActions y ViewAssertions
                .perform(click()) //Componente ViewActions de Espresso: Indicamos que se realice acción click sobre la vista

        //Delay de 3 segundos debido a delay existente al apretar Iniciar sesión
        Thread.sleep(3100)

        //Verificar si inició la activity Home
        intended(
                hasComponent(HomeActivity::class.java.name)
        )
    }
}