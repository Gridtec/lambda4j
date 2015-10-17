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
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.consumer.primitives.ByteConsumer;
import at.gridtec.lambda4j.function.primitives.ByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.function.primitives.to.ToByteFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code byte}-valued operand that produces a {@code byte}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code byte}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteUnaryOperator {

    /**
     * Creates a {@link ByteUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static ByteUnaryOperator constant(byte ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link ByteUnaryOperator} that always returns its input argument.
     *
     * @return A {@code ByteUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static ByteUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    byte applyAsByte(byte operand);

    /**
     * Returns the number of arguments for this operation.
     *
     * @return The number of arguments for this operation.
     * @implSpec The default implementation always returns {@code 1}.
     */
    @Nonnegative
    default int arity() {
        return 1;
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code ByteUnaryOperator} that first applies the {@code before} operator and then applies this
     * operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code byte}.
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default ByteUnaryOperator compose(@Nonnull final ByteUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsByte(before.applyAsByte(operand));
    }

    /**
     * Returns a composed {@link ToByteFunction} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code ToByteFunction} that first applies the {@code before} operation and then applies this
     * operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(ByteUnaryOperator)
     * @see #andThen(ByteFunction)
     */
    @Nonnull
    default <T> ToByteFunction<T> compose(@Nonnull final ToByteFunction<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsByte(before.applyAsByte(t));
    }

    /**
     * Returns a composed {@link ByteUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ByteUnaryOperator} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link UnaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code byte}.
     * @see #compose(ByteUnaryOperator)
     * @see #compose(ToByteFunction)
     */
    @Nonnull
    default ByteUnaryOperator andThen(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsByte(applyAsByte(operand));
    }

    /**
     * Returns a composed {@link ByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code ByteFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     * @see #compose(ByteUnaryOperator)
     * @see #compose(ToByteFunction)
     */
    @Nonnull
    default <R> ByteFunction<R> andThen(@Nonnull final ByteFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToBooleanFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code boolean}, using the {@code byte}-to-{@code boolean} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToBooleanFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToBooleanFunction toBoolean(@Nonnull final ByteToBooleanFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsBoolean(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToCharFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code char}, using the {@code byte}-to-{@code char} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToCharFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToCharFunction toChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToDoubleFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code double}, using the {@code byte}-to-{@code double} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToDoubleFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToDoubleFunction toDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code float}, using the {@code byte}-to-{@code float} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToFloatFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToFloatFunction toFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToIntFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code int}, using the {@code byte}-to-{@code int} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToIntFunction} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToIntFunction toInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToLongFunction} that first applies this operator to its input, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operator to {@code long}, using the {@code byte}-to-{@code long} primitive specialization of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToLongFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToLongFunction toLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code short}, using the {@code byte}-to-{@code short} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteToShortFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteToShortFunction toShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsByte(value));
    }

    /**
     * Returns a composed {@link ByteConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code ByteConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ByteConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteConsumer consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsByte(value));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link ByteUnaryOperator}. Thereby the primitive
     * input argument for this operator is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code ByteUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Byte> boxed() {
        return this::applyAsByte;
    }
}