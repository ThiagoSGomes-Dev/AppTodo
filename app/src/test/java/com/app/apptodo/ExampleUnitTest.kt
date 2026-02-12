package com.app.apptodo

import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(JUnit4::class)
class CalculatorTest {

    @Before
    fun setUp() {
        print("Executa antes de cada teste")
    }

    @After
    fun tearDown() {
        print("Executa depois cada teste")
    }

    @Test
    fun `sum should return correct value`() {
        val numbers: Int = 2 + 7
        assertEquals(9, numbers)
    }

    @Test
    fun `multiply should return correct value`() {
        val multiply: Int = 2 * 7
        assertEquals(14, multiply)
    }

    @Test
    fun `sum should fail when value is wrong`() {
        val result: Int = 2 + 2
        assertNotEquals(-1, result)
    }

    @Test
    fun `return result not null`() {
        val result: String? = "Ok"
        assertNotNull("O Resultado não deveria ser nullo", result)
    }

    @Test
    fun `return result is null`() {
        val result: String? = null
        assertNull("O Resultado deve ser nullo", result)
    }
}