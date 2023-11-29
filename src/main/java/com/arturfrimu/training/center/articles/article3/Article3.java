package com.arturfrimu.training.center.articles.article3;

public class Article3 {

    public static void main(String[] args) {
        BinaryOperator<Integer> add = (x, y) -> x + y;
        BinaryOperator<Integer> multiply = (x, y) -> x * y;

        BinaryOperator<Integer> composed = compose(add, multiply);

        System.out.println(composed.apply(2, 3));
    }


    @FunctionalInterface
    interface BinaryOperator<T> {
        T apply(T t1, T t2);
    }

    public static <T> BinaryOperator<T> compose(BinaryOperator<T> op1, BinaryOperator<T> op2) {
        return (x, y) -> {
            T apply1 = op2.apply(x, y);
            T apply2 = op1.apply(apply1, y);
            return apply2;
        };
    }
}
