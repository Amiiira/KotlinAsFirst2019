@file:Suppress("UNUSED_PARAMETER")

package lesson12.task1



/**
 * Класс "хеш-таблица с открытой адресацией"
 *
 * Общая сложность задания -- сложная.
 * Объект класса хранит данные типа T в виде хеш-таблицы.
 * Хеш-таблица не может содержать равные по equals элементы.
 * Подробности по организации см. статью википедии "Хеш-таблица", раздел "Открытая адресация".
 * Методы: добавление элемента, проверка вхождения элемента, сравнение двух таблиц на равенство.
 * В этом задании не разрешается использовать библиотечные классы HashSet, HashMap и им подобные,
 * а также любые функции, создающие множества (mutableSetOf и пр.).
 *
 * В конструктор хеш-таблицы передаётся её вместимость (максимальное количество элементов)
 */
class OpenHashSet<T>(val capacity: Int) {
    private var start: Node? = null

    public class Node(
        val value: String,
        var next: Node? // next == null
    )

    /**
     * Массив для хранения элементов хеш-таблицы
     */
    internal val elements = Array<Any?>(capacity) { null }

    /**
     * Число элементов в хеш-таблице
     */
    val size: Int get() = TODO()

    /**
     * Признак пустоты
     */
    fun isEmpty(): Boolean {
        if (elements.isEmpty()) return true
        return false
    }

    /**
     * Добавление элемента.
     * Вернуть true, если элемент был успешно добавлен,
     * или false, если такой элемент уже был в таблице, или превышена вместимость таблицы.
     */
    fun add(element: T): Boolean {
        if (element in elements || elements.size == capacity) return false
        else start = Node(element.toString(), start)
        return true
    }

    /**
     * Проверка, входит ли заданный элемент в хеш-таблицу
     */
    operator fun contains(element: T): Boolean {
        if (elements.contains(element)) return true
        return false
    }

    /**
     * Таблицы равны, если в них одинаковое количество элементов,
     * и любой элемент из второй таблицы входит также и в первую
     */
    override fun equals(other: Any?): Boolean {
        if (other !is OpenHashSet<*>) return false
        var elem = start
        var otherElem = other.start
        while (elem != null) {
            if (otherElem == null || elem.value != otherElem.value) return false
            elem = elem.next
            otherElem = otherElem.next
        }
        return true
    }
}