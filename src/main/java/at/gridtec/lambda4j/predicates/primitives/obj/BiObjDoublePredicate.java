/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.gridtec.lambda4j.predicates.primitives.obj;

import at.gridtec.lambda4j.predicates.TriPredicate;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.DoublePredicate;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of two object-valued and a {@code double}-valued argument. This is
 * the {@code (reference, reference, double)}-consuming specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(Object, Object, double)};
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjDoublePredicate<T, U> {

    /**
     * Calls the given {@link BiObjDoublePredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code BiObjDoublePredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static <T, U> boolean call(@Nonnull final BiObjDoublePredicate<? super T, ? super U> predicate, final T t,
            final U u, double value) {
        Objects.requireNonNull(predicate);
        return predicate.test(t, u, value);
    }

    /**
     * Creates a {@link BiObjDoublePredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjDoublePredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@code Predicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> onlyFirst(@Nonnull final Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.test(t);
    }

    /**
     * Creates a {@link BiObjDoublePredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@link Predicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjDoublePredicate} which uses the {@code second} parameter of this one as argument
     * for the given {@code Predicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> onlySecond(@Nonnull final Predicate<? super U> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.test(u);
    }

    /**
     * Creates a {@link BiObjDoublePredicate} which uses the {@code third} parameter of this one as argument for the
     * given {@link DoublePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjDoublePredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@code DoublePredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> onlyThird(@Nonnull final DoublePredicate predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.test(value);
    }

    /**
     * Creates a {@link BiObjDoublePredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param ret The return value for the constant
     * @return A {@code BiObjDoublePredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second target reference with which to compare for equality, which may be {@code null}
     * @param targetValue The target value with which to compare for equality
     * @return A {@code BiObjDoublePredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(Object, Object, double)
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> isEqual(@Nullable Object targetRef1, @Nullable Object targetRef2,
            double targetValue) {
        return (t, u, value) -> (t == null ? targetRef1 == null : t.equals(targetRef1)) && (t == null ? targetRef2
                == null : t.equals(targetRef2)) && (value == targetValue);
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate according to {@code value != target} method.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param targetRef1 The first target reference with which to compare for equality, which may be {@code null}
     * @param targetRef2 The second target reference with which to compare for equality, which may be {@code null}
     * @param targetValue The target value with which to compare for equality
     * @return A {@code BiObjDoublePredicate} that tests if the given arguments are not equal to the ones of this
     * predicate.
     * @see #isEqual(Object, Object, double)
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> isNotEqual(@Nullable Object targetRef1, @Nullable Object targetRef2,
            double targetValue) {
        return (t, u, value) -> !(t == null ? targetRef1 == null : t.equals(targetRef1)) || !(t == null ? targetRef2
                == null : t.equals(targetRef2)) || (value != targetValue);
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjDoublePredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link BiObjDoublePredicate} the always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @return A {@link BiObjDoublePredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U> BiObjDoublePredicate<T, U> alwaysFalse() {
        return (t, u, value) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(T t, U u, double value);

    /**
     * Evaluates this predicate on the given tuple.
     *
     * @param tuple The tuple to be applied to the predicate
     * @param value The primitive value to be applied to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default boolean test(@Nonnull Pair<T, U> tuple, double value) {
        Objects.requireNonNull(tuple);
        return test(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a {@link BiObjDoublePredicate} that represents the logical negation of this one.
     *
     * @return A {@code BiObjDoublePredicate} that represents the logical negation of this one.
     * @see TriPredicate#negate()
     */
    @Nonnull
    default BiObjDoublePredicate<T, U> negate() {
        return (t, u, value) -> !test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that represents a short-circuiting logical AND of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiObjDoublePredicate} that will be logically-ANDed with this one
     * @return A composed {@code BiObjDoublePredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BiObjDoublePredicate)
     * @see #xor(BiObjDoublePredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    @Nonnull
    default BiObjDoublePredicate<T, U> and(@Nonnull final BiObjDoublePredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code BiObjDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiObjDoublePredicate} that will be logically-ORed with this one
     * @return A composed {@code BiObjDoublePredicate} that represents the short-circuiting logical OR of this predicate
     * and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjDoublePredicate)
     * @see #xor(BiObjDoublePredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    @Nonnull
    default BiObjDoublePredicate<T, U> or(@Nonnull final BiObjDoublePredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) && other.test(t, u, value);
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that represents a short-circuiting logical XOR of this predicate
     * and another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation
     * of this {@code BiObjDoublePredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code BiObjDoublePredicate} that will be logically-XORed with this one
     * @return A composed {@code BiObjDoublePredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BiObjDoublePredicate)
     * @see #or(BiObjDoublePredicate)
     */
    @Nonnull
    default BiObjDoublePredicate<T, U> xor(@Nonnull final BiObjDoublePredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> test(t, u, value) ^ other.test(t, u, value);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 2};
     *
     * @param t The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default ObjDoublePredicate<U> partial(T t) {
        return (u, value) -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 2};
     *
     * @param value The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BiPredicate<T, U> partial(double value) {
        return (t, u) -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to two arguments. The result is a predicate of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the predicate
     * @param u The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default DoublePredicate partial(T t, U u) {
        return value -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to two arguments. The result is a predicate of arity {@code 1}.
     *
     * @param t The first argument to partially apply to the predicate
     * @param value The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default Predicate<U> partial(T t, double value) {
        return u -> test(t, u, value);
    }

    /**
     * Applies this predicate partially to three arguments. The result is an operation of arity {@code 0}.
     *
     * @param t The first argument to partially apply to the predicate
     * @param u The second argument to partially apply to the predicate
     * @param value The third argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BooleanSupplier partial(T t, U u, double value) {
        return () -> test(t, u, value);
    }

    /**
     * Returns a tupled version of this predicate.
     *
     * @return A tupled version of this predicate.
     */
    @Nonnull
    default ObjDoublePredicate<Pair<T, U>> tupled() {
        return this::test;
    }

    /**
     * Returns a composed {@link TriPredicate} which represents this {@link BiObjDoublePredicate}. Thereby the primitive
     * input argument for this predicate is autoboxed.
     *
     * @return A composed {@code TriPredicate} which represents this {@code BiObjDoublePredicate}.
     */
    @Nonnull
    default TriPredicate<T, U, Double> boxed() {
        return this::test;
    }
}
