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
package at.gridtec.lambda4j.predicates.primitives;

import at.gridtec.lambda4j.predicates.ThrowablePredicate;
import at.gridtec.lambda4j.supplier.ThrowableBooleanSupplier;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Represents a predicate (boolean-valued function) of one {@code char}-valued argument which is able to throw any
 * {@link Throwable}. This is the {@code char}-consuming primitive type specialization of {@link ThrowablePredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(char)}.
 *
 * @see Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableCharPredicate {

    /**
     * Calls the given {@link ThrowableCharPredicate} with the given argument and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value The argument to the predicate
     * @return The result from the given {@code ThrowableCharPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     * @throws Throwable Any throwable from the given predicates action
     */
    static boolean call(@Nonnull final ThrowableCharPredicate predicate, char value) throws Throwable {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(value);
    }

    /**
     * Creates a {@link ThrowableCharPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableCharPredicate} which always returns a given value.
     */
    @Nonnull
    static ThrowableCharPredicate constant(boolean ret) {
        return value -> ret;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} that tests if the given argument is equal to the one of this predicate
     * according to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableCharPredicate} that tests if the given argument is equal to the one of this predicate.
     * @see #isNotEqual(char)
     */
    @Nonnull
    static ThrowableCharPredicate isEqual(char target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} that tests if the given argument is not equal to the one of this
     * predicate according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code ThrowableCharPredicate} that tests if the given argument is not equal to the one of this
     * predicate.
     * @see #isEqual(char)
     */
    @Nonnull
    static ThrowableCharPredicate isNotEqual(char target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} that always returns {@code true}.
     *
     * @return A {@link ThrowableCharPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static ThrowableCharPredicate alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} the always returns {@code false}.
     *
     * @return A {@link ThrowableCharPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static ThrowableCharPredicate alwaysFalse() {
        return value -> false;
    }

    /**
     * Evaluates this predicate on the given argument.
     *
     * @param value The argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean testThrows(char value) throws Throwable;

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a {@link ThrowableCharPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableCharPredicate} that represents the logical negation of this one.
     * @see ThrowablePredicate#negate()
     */
    @Nonnull
    default ThrowableCharPredicate negate() {
        return value -> !testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableCharPredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * If evaluation of this {@code ThrowableCharPredicate} throws an exception, the {@code other} predicate will not be
     * evaluated.
     *
     * @param other A {@code ThrowableCharPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableCharPredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(ThrowableCharPredicate)
     * @see #xor(ThrowableCharPredicate)
     */
    @Nonnull
    default ThrowableCharPredicate and(@Nonnull final ThrowableCharPredicate other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) && other.testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableCharPredicate} that represents a short-circuiting logical OR of this predicate
     * and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * If evaluation of this {@code ThrowableCharPredicate} throws an exception, the {@code other} predicate will not be
     * evaluated.
     *
     * @param other A {@code ThrowableCharPredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableCharPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ThrowableCharPredicate)
     * @see #xor(ThrowableCharPredicate)
     */
    @Nonnull
    default ThrowableCharPredicate or(@Nonnull final ThrowableCharPredicate other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) && other.testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowableCharPredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. If evaluation of this {@code ThrowableCharPredicate} throws an exception, the {@code
     * other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableCharPredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableCharPredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(ThrowableCharPredicate)
     * @see #or(ThrowableCharPredicate)
     */
    @Nonnull
    default ThrowableCharPredicate xor(@Nonnull final ThrowableCharPredicate other) {
        Objects.requireNonNull(other);
        return value -> testThrows(value) ^ other.testThrows(value);
    }

    /**
     * Applies this predicate partially to one argument. The result is an operation of arity {@code 0};
     *
     * @param value The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default ThrowableBooleanSupplier partial(char value) {
        return () -> testThrows(value);
    }

    /**
     * Returns a composed {@link ThrowablePredicate} which represents this {@link ThrowableCharPredicate}. Thereby the
     * primitive input argument for this predicate is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ThrowableCharPredicate} with JRE specific methods, only accepting {@code ThrowablePredicate}.
     *
     * @return A composed {@code ThrowablePredicate} which represents this {@code ThrowableCharPredicate}.
     */
    @Nonnull
    default ThrowablePredicate<Character> boxed() {
        return this::testThrows;
    }
}