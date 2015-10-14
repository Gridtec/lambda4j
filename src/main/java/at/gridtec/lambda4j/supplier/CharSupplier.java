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

package at.gridtec.lambda4j.supplier;

import at.gridtec.lambda4j.function.primitives.CharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToBooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.CharToShortFunction;
import at.gridtec.lambda4j.operators.unary.CharUnaryOperator;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

/**
 * Represents a supplier of {@code char}-valued results.  This is the {@code char}-producing primitive specialization of
 * {@link Supplier}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsChar()}.
 *
 * @see java.util.function.Supplier
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface CharSupplier {

    /**
     * Creates a {@link CharSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code CharSupplier} which always returns a given value.
     */
    @Nonnull
    static CharSupplier constant(char ret) {
        return () -> ret;
    }

    /**
     * Calls the given {@link CharSupplier} and returns its result. If evaluation of the given operation throws an
     * exception, it is relayed to the caller of this operation.
     *
     * @param supplier The supplier to be called
     * @return The result of the given {@code CharSupplier}.
     */
    static char call(final @Nonnull CharSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsChar();
    }

    /**
     * Gets the supplied result from this supplier.
     *
     * @return The supplied result.
     */
    char getAsChar();

    /**
     * Returns the number of this operations arguments.
     *
     * @return The number of this operations arguments.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link CharSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code CharSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link Supplier}. Therefore the returned
     * operation handles primitive types. In this case this is {@code char}.
     */
    @Nonnull
    default CharSupplier andThen(@Nonnull final CharUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsChar());
    }

    /**
     * Returns a composed {@link Supplier} that first gets the result from this operation, and then applies the {@code
     * after} operation to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code Supplier} that first gets the result from this operation, and then applies the {@code
     * after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     */
    @Nonnull
    default <R> Supplier<R> andThen(@Nonnull final CharFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsChar());
    }

    /**
     * Returns a composed {@link BooleanSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code boolean}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanSupplier toBoolean(@Nonnull final CharToBooleanFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsBoolean(getAsChar());
    }

    /**
     * Returns a composed {@link ByteSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code byte}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ByteSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ByteSupplier toByte(@Nonnull final CharToByteFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsChar());
    }

    /**
     * Returns a composed {@link DoubleSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code double}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code DoubleSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default DoubleSupplier toDouble(@Nonnull final CharToDoubleFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsChar());
    }

    /**
     * Returns a composed {@link FloatSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code float}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code FloatSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default FloatSupplier toFloat(@Nonnull final CharToFloatFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsChar());
    }

    /**
     * Returns a composed {@link IntSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code int}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code IntSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default IntSupplier toInt(@Nonnull final CharToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsChar());
    }

    /**
     * Returns a composed {@link LongSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code long}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code LongSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default LongSupplier toLong(@Nonnull final CharToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsChar());

    }

    /**
     * Returns a composed {@link ShortSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * operation to the {@code short}-producing primitive specialization of {@link Supplier}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code ShortSupplier} that first gets the result from this operation, and then applies the
     * {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ShortSupplier toShort(@Nonnull final CharToShortFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsChar());
    }

    /**
     * Returns a composed {@link Supplier} which represents this {@link ShortSupplier}. Thereby the primitive input
     * argument for this operation is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ShortSupplier} with JRE specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code ShortSupplier}.
     */
    @Nonnull
    default Supplier<Character> boxed() {
        return this::getAsChar;
    }
}
