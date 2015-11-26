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
package at.gridtec.lambda4j.predicates.primitives.bi;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.LongPredicate;

/**
 * Represents a predicate (boolean-valued function) of two {@code long}-valued argument. This is the {@code
 * long}-consuming primitive type specialization of {@link BiPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(long, long)}.
 *
 * @see BiPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface LongBiPredicate {

    /**
     * Calls the given {@link LongBiPredicate} with the given arguments and returns its result.
     *
     * @param predicate The predicate to be called
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return The result from the given {@code LongBiPredicate}.
     * @throws NullPointerException If the given predicate is {@code null}
     */
    static boolean call(@Nonnull final LongBiPredicate predicate, long value1, long value2) {
        Objects.requireNonNull(predicate);
        return predicate.test(value1, value2);
    }

    /**
     * Creates a {@link LongBiPredicate} which uses the {@code first} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code LongBiPredicate} which uses the {@code first} parameter of this one as argument for the
     * given {@code LongPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongBiPredicate onlyFirst(@Nonnull final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.test(value1);
    }

    /**
     * Creates a {@link LongBiPredicate} which uses the {@code second} parameter of this one as argument for the given
     * {@link LongPredicate}.
     *
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code LongBiPredicate} which uses the {@code second} parameter of this one as argument for the
     * given {@code LongPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    @Nonnull
    static LongBiPredicate onlySecond(@Nonnull final LongPredicate predicate) {
        Objects.requireNonNull(predicate);
        return (value1, value2) -> predicate.test(value2);
    }

    /**
     * Creates a {@link LongBiPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code LongBiPredicate} which always returns a given value.
     */
    @Nonnull
    static LongBiPredicate constant(boolean ret) {
        return (value1, value2) -> ret;
    }

    /**
     * Returns a {@link LongBiPredicate} that tests if the given arguments are equal to the ones of this predicate
     * according to {@code value == target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code LongBiPredicate} that tests if the given arguments are equal to the ones of this predicate.
     * @see #isNotEqual(long, long)
     */
    @Nonnull
    static LongBiPredicate isEqual(long target1, long target2) {
        return (value1, value2) -> (value1 == target1) && (value2 == target2);
    }

    /**
     * Returns a {@link LongBiPredicate} that tests if the given arguments are not equal to the ones of this predicate
     * according to {@code value != target} method.
     *
     * @param target1 The first target value with which to compare for equality
     * @param target2 The second target value with which to compare for equality
     * @return A {@code LongBiPredicate} that tests if the given arguments are not equal to the ones of this predicate.
     * @see #isEqual(long, long)
     */
    @Nonnull
    static LongBiPredicate isNotEqual(long target1, long target2) {
        return (value1, value2) -> (value1 != target1) || (value2 != target2);
    }

    /**
     * Returns a {@link LongBiPredicate} that always returns {@code true}.
     *
     * @return A {@link LongBiPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static LongBiPredicate alwaysTrue() {
        return (value1, value2) -> true;
    }

    /**
     * Returns a {@link LongBiPredicate} the always returns {@code false}.
     *
     * @return A {@link LongBiPredicate} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static LongBiPredicate alwaysFalse() {
        return (value1, value2) -> false;
    }

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @return {@code true} if the input arguments match the predicate, otherwise {@code false}.
     */
    boolean test(long value1, long value2);

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a {@link LongBiPredicate} that represents the logical negation of this one.
     *
     * @return A {@code LongBiPredicate} that represents the logical negation of this one.
     * @see BiPredicate#negate()
     */
    @Nonnull
    default LongBiPredicate negate() {
        return (value1, value2) -> !test(value1, value2);
    }

    /**
     * Returns a composed {@link LongBiPredicate} that represents a short-circuiting logical AND of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code LongBiPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code LongBiPredicate} that will be logically-ANDed with this one
     * @return A composed {@code LongBiPredicate} that represents the short-circuiting logical AND of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(LongBiPredicate)
     * @see #xor(LongBiPredicate)
     * @see BiPredicate#and(BiPredicate)
     */
    @Nonnull
    default LongBiPredicate and(@Nonnull final LongBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link LongBiPredicate} that represents a short-circuiting logical OR of this predicate and
     * another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of this
     * {@code LongBiPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code LongBiPredicate} that will be logically-ORed with this one
     * @return A composed {@code LongBiPredicate} that represents the short-circuiting logical OR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(LongBiPredicate)
     * @see #xor(LongBiPredicate)
     * @see BiPredicate#or(BiPredicate)
     */
    @Nonnull
    default LongBiPredicate or(@Nonnull final LongBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) && other.test(value1, value2);
    }

    /**
     * Returns a composed {@link LongBiPredicate} that represents a short-circuiting logical XOR of this predicate and
     * another. Any exceptions thrown during evaluation of either predicate are relayed to the caller; if evaluation of
     * this {@code LongBiPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code LongBiPredicate} that will be logically-XORed with this one
     * @return A composed {@code LongBiPredicate} that represents the short-circuiting logical XOR of this predicate and
     * the {@code other} predicate.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(LongBiPredicate)
     * @see #or(LongBiPredicate)
     */
    @Nonnull
    default LongBiPredicate xor(@Nonnull final LongBiPredicate other) {
        Objects.requireNonNull(other);
        return (value1, value2) -> test(value1, value2) ^ other.test(value1, value2);
    }

    /**
     * Applies this predicate partially to one argument. The result is a predicate of arity {@code 1}.
     *
     * @param value1 The argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default LongPredicate partial(long value1) {
        return value2 -> test(value1, value2);
    }

    /**
     * Applies this predicate partially to two arguments. The result is an operation of arity {@code 0}.
     *
     * @param value1 The first argument to partially apply to the predicate
     * @param value2 The second argument to partially apply to the predicate
     * @return A partial application of this predicate.
     */
    @Nonnull
    default BooleanSupplier partial(long value1, long value2) {
        return () -> test(value1, value2);
    }

    /**
     * Returns a composed {@link BiPredicate} which represents this {@link LongBiPredicate}. Thereby the primitive input
     * argument for this predicate is autoboxed. This method is just convenience to provide the ability to use this
     * {@code LongBiPredicate} with JRE specific methods, only accepting {@code BiPredicate}.
     *
     * @return A composed {@code BiPredicate} which represents this {@code LongBiPredicate}.
     */
    @Nonnull
    default BiPredicate<Long, Long> boxed() {
        return this::test;
    }
}