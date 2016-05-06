package ru.spb.epam.common.second;
/**
 * Интерфейс для юнит-тестирования задания №13.
 *
 * Реализовать класс Graph, представляющий собой неориентированный граф.
 * В конструкторе класса передается количество вершин в графе.
 * Методы должны поддерживать быстрое добавление и удаление ребер.
 */
public interface ITestableTask13 extends ITaskStorage.ITestableTask {

    /**
     * @param numberNodes Количество вершин в графе.
     * @return Граф указанной конфигурации.
     */
    AbstractGraph createGraph(int numberNodes);

    /**
     * Абстрактный граф.
     * При создании задается количество вершин.
     * Нумерация вершин начинается с 0.
     * Допустимы операции добавления, удаления и проверки существования ребер.
     */
    abstract class AbstractGraph {

        /** Количество вершин */
        protected final int NUMBER_NODES;

        public AbstractGraph(int numberNodes) {
            this.NUMBER_NODES = numberNodes;
        }

        /**
         * Добавление ребра в граф.
         * @param first Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */
        public abstract void addEdge(int first, int second);

        /**
         * Удаление ребра из графа.
         * @param first Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */
        public abstract void removeEdge(int first, int second);

        /**
         * Проверка наличия ребра.
         * @param first Первая вершина.
         * @param second Вторая вершина.
         */
        public abstract boolean isExistEdge(int first, int second);
    }
}