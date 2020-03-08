@file:Suppress("UNUSED_PARAMETER")

package lesson11.task1

import lesson1.task1.sqr
import java.lang.IllegalArgumentException

/**
 * Класс "комплексое число".
 *
 * Общая сложность задания -- лёгкая.
 * Объект класса -- комплексное число вида x+yi.
 * Про принципы работы с комплексными числами см. статью Википедии "Комплексное число".
 *
 * Аргументы конструктора -- вещественная и мнимая часть числа.
 */
class Complex(val re: Double, val im: Double) {

    /**
     * Конструктор из вещественного числа
     */
    constructor(x: Double) : this(x, 0.0)

    /**
     * Конструктор из строки вида x+yi
     */
    constructor(s: String) : this(
        s.split("-", "+")[0].toDouble(),
        if (s.split("-").size == 1) (s.split("+")[1].split("i")[0].toDouble())
        else (-s.split("-")[1].split("i")[0].toDouble())
    )

    /**
     * Сложение.
     */
    operator fun plus(other: Complex) = Complex(re + other.re, im + other.im)

    /**
     * Смена знака (у обеих частей числа)
     */
    operator fun unaryMinus(): Complex = Complex(re * -1, im * -1)

    /**
     * Вычитание
     */
    operator fun minus(other: Complex): Complex = Complex(re - other.re, im - other.im)

    /**
     * Умножение
     */
    operator fun times(other: Complex): Complex = Complex(re * other.re - im * other.im, im * other.re + re * other.im)

    /**
     * Деление
     */
    operator fun div(other: Complex): Complex {
        val mul = sqr(other.re) + sqr(other.im)
        return Complex(((re * other.re) + (im * other.im)) / mul, ((im * other.re) - (re * other.im)) / mul)
    }

    /**
     * Сравнение на равенство
     */
    override fun equals(other: Any?): Boolean {
        if (other is Complex && (re == other.re) && (im == other.im)) return true
        return false
    }

    /**
     * Преобразование в строку
     */
    override fun toString(): String {
        val str = StringBuilder()
        if (im > 0) "+$im"
        if (im < 0) "$im"
        else ""
        str.append("re" + im + "i")
        return str.toString()
    }
}