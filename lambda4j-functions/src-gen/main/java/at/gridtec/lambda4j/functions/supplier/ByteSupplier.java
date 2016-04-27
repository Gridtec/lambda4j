/*
 * Copyright (c) 2016 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.functions.supplier;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.ByteConsumer;
import at.gridtec.lambda4j.functions.consumer.Consumer2;
import at.gridtec.lambda4j.functions.function.ByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToLongFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToShortFunction;
import at.gridtec.lambda4j.functions.operator.unary.ByteUnaryOperator;
import at.gridtec.lambda4j.functions.predicate.BytePredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Represents a supplier of {@code byte}-valued results. This is a primitive specialization of {@link Supplier2}.
 * <p>
 * There is no requirement that a distinct result be returned each time the supplier is invoked.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #getAsByte()}.
 *
 * @see Supplier2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteSupplier extends Lambda {

    /**
     * Constructs a {@link ByteSupplier} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ByteSupplier} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static ByteSupplier of(@Nonnull final ByteSupplier expression) {
        return expression;
    }

    /**
     * Calls the given {@link ByteSupplier} with the given argument and returns its result.
     *
     * @param supplier The supplier to be called
     * @return The result from the given {@code ByteSupplier}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static byte call(@Nonnull final ByteSupplier supplier) {
        Objects.requireNonNull(supplier);
        return supplier.getAsByte();
    }

    /**
     * Creates a {@link ByteSupplier} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ByteSupplier} which always returns a given value.
     */
    @Nonnull
    static ByteSupplier constant(byte ret) {
        return () -> ret;
    }

    /**
     * Applies this supplier to the given argument.
     *
     * @return The return value from the supplier, which is its result.
     */
    byte getAsByte();

    /**
     * Returns the number of arguments for this supplier.
     *
     * @return The number of arguments for this supplier.
     * @implSpec The default implementation always returns {@code 0}.
     */
    @Nonnegative
    default int arity() {
        return 0;
    }

    /**
     * Returns a composed {@link Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result. If evaluation of either operation throws an exception, it is relayed to the caller
     * of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed supplier
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code Supplier2} that first applies this supplier to its input, and then applies the {@code
     * after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> Supplier2<S> andThen(@Nonnull final ByteFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(getAsByte());
    }

    /**
     * Returns a composed {@link BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this supplier is applied
     * @return A composed {@code BooleanSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BooleanSupplier2 andThenToBoolean(@Nonnull final BytePredicate after) {
        Objects.requireNonNull(after);
        return () -> after.test(getAsByte());
    }

    /**
     * Returns a composed {@link ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code byte}.
     *
     * @param after The operator to apply after this supplier is applied
     * @return A composed {@code ByteSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ByteSupplier andThenToByte(@Nonnull final ByteUnaryOperator after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsByte(getAsByte());
    }

    /**
     * Returns a composed {@link CharSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code char}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code CharSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default CharSupplier andThenToChar(@Nonnull final ByteToCharFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsChar(getAsByte());
    }

    /**
     * Returns a composed {@link DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code double}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code DoubleSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default DoubleSupplier2 andThenToDouble(@Nonnull final ByteToDoubleFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsDouble(getAsByte());
    }

    /**
     * Returns a composed {@link FloatSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code float}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code FloatSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default FloatSupplier andThenToFloat(@Nonnull final ByteToFloatFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsFloat(getAsByte());
    }

    /**
     * Returns a composed {@link IntSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code int}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code IntSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntSupplier2 andThenToInt(@Nonnull final ByteToIntFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsInt(getAsByte());
    }

    /**
     * Returns a composed {@link LongSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code long}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code LongSupplier2} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongSupplier2 andThenToLong(@Nonnull final ByteToLongFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsLong(getAsByte());
    }

    /**
     * Returns a composed {@link ShortSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive supplier to an operation returning {@code short}.
     *
     * @param after The function to apply after this supplier is applied
     * @return A composed {@code ShortSupplier} that first applies this supplier to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ShortSupplier andThenToShort(@Nonnull final ByteToShortFunction after) {
        Objects.requireNonNull(after);
        return () -> after.applyAsShort(getAsByte());
    }

    /**
     * Returns a composed {@link Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@link ByteConsumer}. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code Consumer2} that first gets the result from this supplier, and then consumes the result
     * using the given {@code ByteConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote Due to the fact that a {@link ByteSupplier} receives no input, we do not need to pass an argument of a
     * particular type to the resulting {@code Consumer2}. As a result, this method returns a {@code Consumer2} of
     * {@link Void}, whose argument is ignored. Therefore, the input parameter will always be {@code null} when the
     * resulting consumer is called with {@code Consumer#accept(Object)}.
     */
    @Nonnull
    default Consumer2<Void> consume(@Nonnull final ByteConsumer consumer) {
        Objects.requireNonNull(consumer);
        return ignored -> consumer.accept(getAsByte());
    }

    /**
     * Returns a composed {@link Supplier} which represents this {@link ByteSupplier}. Thereby the primitive input
     * argument for this supplier is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ByteSupplier} with JDK specific methods, only accepting {@code Supplier}.
     *
     * @return A composed {@code Supplier} which represents this {@code ByteSupplier}.
     */
    @Nonnull
    default Supplier<Byte> boxed() {
        return this::getAsByte;
    }

}
