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
package at.gridtec.lambda4j.function.primitives.to;

import at.gridtec.lambda4j.consumer.primitives.CharConsumer;
import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.*;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import java.util.Objects;
import java.util.function.*;

/**
 * Represents a function that produces a char-valued result from one argument. This is the {@code char}-producing
 * primitive specialization for {@link Function}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsChar(Object)}.
 *
 * @param <T> The type of argument to the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ToCharFunction<T> {

    /**
     * Creates a {@link ToCharFunction} which always returns a given value.
     *
     * @param <T> The type of argument to the function
     * @param value The return value for the constant
     * @return A {@code ToCharFunction} which always returns a given value.
     */
    static <T> ToCharFunction<T> constant(char value) {
        return t -> value;
    }

    /**
     * Applies this {@link ToCharFunction} to the given argument.
     *
     * @param t The argument to the function
     * @return The return value from the function, which is its result.
     */
    char applyAsChar(T t);

    /**
     * Returns a composed {@link ToCharFunction} that first applies the {@code before} {@link UnaryOperator} to its
     * input, and then applies this operation to the result. If evaluation of either operation throws an exception, it
     * is relayed to the caller of the composed function.
     *
     * @param before The {@code UnaryOperator} to apply before this operation is applied
     * @return A composed {@code ToCharFunction} that first applies the {@code before} {@code UnaryOperator} to its
     * input, and then applies this operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #andThen(CharFunction)
     */
    default ToCharFunction<T> compose(final UnaryOperator<T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsChar(before.apply(t));
    }

    /**
     * Returns a composed {@link ToCharFunction} that applies the given {@code before} {@link Function} to its input,
     * and then applies this operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed function.
     *
     * @param <V> The type of the argument to the before operation
     * @param before The before {@code Function} to apply before this operation is applied
     * @return A composed {@code ToCharFunction} that applies the given {@code before} {@code Function} to its input,
     * and then applies this operation to the result.
     * @throws NullPointerException If one of the given functions are {@code null}
     * @see #andThen(CharFunction)
     */
    default <V> ToCharFunction<V> compose(final Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return v -> applyAsChar(before.apply(v));
    }

    /**
     * Returns a composed {@link ToCharFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharUnaryOperator} to apply after this operation is applied
     * @return A composed {@code ToCharFunction} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default ToCharFunction<T> andThen(final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsChar(applyAsChar(t));
    }

    /**
     * Returns a composed {@link Function} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of output of the {@code after} function, and of the composed function
     * @param after The {@code CharFunction} to apply after this operation is applied
     * @return A composed {@code Function} that first applies this operation, and then applies the {@code after}
     * operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @see #compose(UnaryOperator)
     * @see #compose(Function)
     */
    default <R> Function<T, R> andThen(final CharFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return t -> after.apply(this.applyAsChar(t));
    }

    /**
     * Returns a composed {@link Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result. If evaluation of either operations throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param after The {@code CharToBooleanFunction} to apply after this operation is applied
     * @return A composed {@code Predicate} that first applies this operation to its input, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Predicate<T> toBoolean(final CharToBooleanFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsBoolean(applyAsChar(t));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharToByteFunction} to apply after this operation is applied
     * @return A composed {@code ToByteFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToByteFunction<T> toByte(final CharToByteFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsByte(applyAsChar(t));
    }

    /**
     * Returns a composed {@link ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharToDoubleFunction} to apply after this operation is applied
     * @return A composed {@code ToDoubleFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToDoubleFunction<T> toDouble(final CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsDouble(applyAsChar(t));
    }

    /**
     * Returns a composed {@link ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharToFloatFunction} to apply after this operation is applied
     * @return A composed {@code ToFloatFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToFloatFunction<T> toFloat(final CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsFloat(applyAsChar(t));
    }

    /**
     * Returns a composed {@link ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharToIntFunction} to apply after this operation is applied
     * @return A composed {@code ToIntFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToIntFunction<T> toInt(final CharToIntFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsInt(applyAsChar(t));
    }

    /**
     * Returns a composed {@link ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharToLongFunction} to apply after this operation is applied
     * @return A composed {@code ToLongFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToLongFunction<T> toLong(final CharToLongFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsLong(applyAsChar(t));

    }

    /**
     * Returns a composed {@link ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operations throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The {@code CharToShortFunction} to apply after this operation is applied
     * @return A composed {@code ToShortFunction} that first applies this operation to its input, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    default ToShortFunction<T> toShort(final CharToShortFunction after) {
        Objects.requireNonNull(after);
        return t -> after.applyAsShort(applyAsChar(t));
    }

    /**
     * Returns a composed {@link Consumer} that fist applies this operation to its input, and then consumes the result
     * using the given {@code CharConsumer}. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The {@code CharConsumer} which consumes the result from this operation
     * @return A composed {@code Consumer} that first applies this operation to its input, and then consumes the result
     * using the given {@code CharConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    default Consumer<T> consume(CharConsumer consumer) {
        Objects.requireNonNull(consumer);
        return t -> consumer.accept(this.applyAsChar(t));
    }
}
