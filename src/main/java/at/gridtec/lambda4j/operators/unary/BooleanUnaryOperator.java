/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
 *
 * This program is proprietary software; all information contained herein is, and
 * remains, the property of Gridtec and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Gridtec and its suppliers
 * and may be covered by Austrian and Foreign Patents, patents in process, and are
 * protected by trade secret or copyright law.
 *
 * Dissemination of this information or reproduction of this material is strictly
 * forbidden unless prior written permission is obtained from Gridtec company.
 *
 * This software consists of voluntary contributions made by individuals on behalf
 * of Gridtec. For more information on Gridtec, please refer to www.gridtec.at homepage.
 */
package at.gridtec.lambda4j.operators.unary;

import at.gridtec.lambda4j.consumer.primitives.BooleanConsumer;
import at.gridtec.lambda4j.function.primitives.BooleanFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToFloatFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToLongFunction;
import at.gridtec.lambda4j.function.primitives.conversion.BooleanToShortFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code boolean}-valued operand that produces a {@code boolean}-valued result.
 * This is the primitive type specialization of {@link UnaryOperator} for {@code boolean}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsBoolean(boolean)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BooleanUnaryOperator {

    /**
     * Creates a {@link BooleanUnaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code BooleanUnaryOperator} which always returns a given value.
     */
    @Nonnull
    static BooleanUnaryOperator constant(boolean ret) {
        return operand -> ret;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that always returns its input argument.
     *
     * @return A {@code BooleanUnaryOperator} that always returns its input argument
     */
    @Nonnull
    static BooleanUnaryOperator identity() {
        return operand -> operand;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that tests if the given argument is equal to the one of this operator
     * according to {@code value == target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code BooleanUnaryOperator} that tests if the given argument is equal to the one of this operator.
     * @see #isNotEqual(boolean)
     */
    static BooleanUnaryOperator isEqual(boolean target) {
        return value -> value == target;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} that tests if the given argument is not equal to the one of this operator
     * according to {@code value != target} operation.
     *
     * @param target The target value with which to compare for equality
     * @return A {@code BooleanUnaryOperator} that tests if the given argument is not equal to the one of this operator.
     * @see #isEqual(boolean)
     */
    static BooleanUnaryOperator isNotEqual(boolean target) {
        return value -> value != target;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} the always returns {@code true}.
     *
     * @return A {@link BooleanUnaryOperator} the always returns {@code true}.
     * @see #alwaysFalse()
     */
    static BooleanUnaryOperator alwaysTrue() {
        return value -> true;
    }

    /**
     * Returns a {@link BooleanUnaryOperator} the always returns {@code false}.
     *
     * @return A {@link BooleanUnaryOperator} the always returns {@code false}.
     * @see #alwaysTrue()
     */
    static BooleanUnaryOperator alwaysFalse() {
        return value -> false;
    }

    /**
     * Applies this operator to the given argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    boolean applyAsBoolean(boolean operand);

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
     * Returns a {@link BooleanUnaryOperator} that represents the logical negation of this one.
     *
     * @return A {@code BooleanUnaryOperator} that represents the logical negation of this one.
     * @see Predicate#negate()
     */
    default BooleanUnaryOperator negate() {
        return value -> !applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that represents a short-circuiting logical AND of this operator
     * and another. When evaluating the composed operator, if this operator is {@code false}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanUnaryOperator} throws an exception, the {@code other} {@code BooleanUnaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanUnaryOperator} that will be logically-ANDed with this one
     * @return A composed {@code BooleanUnaryOperator} that represents the short-circuiting logical AND of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #or(BooleanUnaryOperator)
     * @see #xor(BooleanUnaryOperator)
     * @see Predicate#and(Predicate)
     */
    default BooleanUnaryOperator and(final BooleanUnaryOperator other) {
        Objects.requireNonNull(other);
        return value -> applyAsBoolean(value) && other.applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that represents a short-circuiting logical OR of this operator
     * and another. When evaluating the composed operator, if this operator is {@code true}, then the {@code other}
     * operator is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation of this
     * {@code BooleanUnaryOperator} throws an exception, the {@code other} {@code BooleanUnaryOperator} will not be
     * evaluated.
     *
     * @param other A {@code BooleanUnaryOperator} that will be logically-ORed with this one
     * @return A composed {@code BooleanUnaryOperator} that represents the short-circuiting logical OR of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanUnaryOperator)
     * @see #xor(BooleanUnaryOperator)
     * @see Predicate#or(Predicate)
     */
    default BooleanUnaryOperator or(final BooleanUnaryOperator other) {
        Objects.requireNonNull(other);
        return value -> applyAsBoolean(value) && other.applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that represents a short-circuiting logical XOR of this operator
     * and another. Any exceptions thrown during evaluation of either operator are relayed to the caller; if evaluation
     * of this {@code BooleanUnaryOperator} throws an exception, the {@code other} {@code BooleanUnaryOperator} will not
     * be evaluated.
     *
     * @param other A {@code BooleanUnaryOperator} that will be logically-XORed with this one
     * @return A composed {@code BooleanUnaryOperator} that represents the short-circuiting logical XOR of this operator
     * and the {@code other} operator.
     * @throws NullPointerException If the given argument is {@code null}
     * @see #and(BooleanUnaryOperator)
     * @see #or(BooleanUnaryOperator)
     */
    default BooleanUnaryOperator xor(final BooleanUnaryOperator other) {
        Objects.requireNonNull(other);
        return value -> applyAsBoolean(value) ^ other.applyAsBoolean(value);
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies the {@code before} operator to its input, and
     * then applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies the {@code before} operator and then applies
     * this operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is the primitive specialization of {@link UnaryOperator}. Therefore
     * the given operation handles primitive types. In this case this is {@code boolean}.
     * @see #andThen(BooleanUnaryOperator)
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default BooleanUnaryOperator compose(@Nonnull final BooleanUnaryOperator before) {
        Objects.requireNonNull(before);
        return operand -> applyAsBoolean(before.applyAsBoolean(operand));
    }

    /**
     * Returns a composed {@link Predicate} that first applies the {@code before} operation to its input, and then
     * applies this operator to the result. If evaluation of either operator throws an exception, it is relayed to the
     * caller of the composed operator.
     *
     * @param <T> The type of the argument to the before operation
     * @param before The operator to apply before this operator is applied
     * @return A composed {@code Predicate} that first applies the {@code before} operation and then applies this
     * operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     * @see #andThen(BooleanUnaryOperator)
     * @see #andThen(BooleanFunction)
     */
    @Nonnull
    default <T> Predicate<T> compose(@Nonnull final Predicate<? super T> before) {
        Objects.requireNonNull(before);
        return t -> applyAsBoolean(before.test(t));
    }

    /**
     * Returns a composed {@link BooleanUnaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operator throws an exception, it is relayed to
     * the caller of the composed operator.
     *
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BooleanUnaryOperator} that first applies this operator to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The result of this method is the primitive specialization of {@link UnaryOperator}. Therefore the
     * returned operation handles primitive types. In this case this is {@code boolean}.
     * @see #compose(BooleanUnaryOperator)
     * @see #compose(Predicate)
     */
    @Nonnull
    default BooleanUnaryOperator andThen(@Nonnull final BooleanUnaryOperator after) {
        Objects.requireNonNull(after);
        return operand -> after.applyAsBoolean(applyAsBoolean(operand));
    }

    /**
     * Returns a composed {@link BooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <R> The type of return value from the {@code after} operation, and of the composed operation
     * @param after The operator to apply after this operator is applied
     * @return A composed {@code BooleanFunction} that first applies this operator to its input, and then applies the
     * {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The returned operation is able to handle every type.
     * @see #compose(BooleanUnaryOperator)
     * @see #compose(Predicate)
     */
    @Nonnull
    default <R> BooleanFunction<R> andThen(@Nonnull final BooleanFunction<? extends R> after) {
        Objects.requireNonNull(after);
        return value -> after.apply(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToByteFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code byte}, using the {@code boolean}-to-{@code byte} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToByteFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToByteFunction toByte(@Nonnull final BooleanToByteFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsByte(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToCharFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code char}, using the {@code boolean}-to-{@code char} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToCharFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToCharFunction toChar(@Nonnull final BooleanToCharFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsChar(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToDoubleFunction} that first applies this operator to its input, and then
     * applies the {@code after} operation to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this operator to {@code double}, using the {@code boolean}-to-{@code double} primitive specialization
     * of {@link Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToDoubleFunction} that first gets the result from this operation, and then
     * applies the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToDoubleFunction toDouble(@Nonnull final BooleanToDoubleFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsDouble(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToFloatFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code float}, using the {@code boolean}-to-{@code float} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToFloatFunction} that first gets the result from this operation, and then
     * applies the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToFloatFunction toFloat(@Nonnull final BooleanToFloatFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsFloat(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToIntFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code int}, using the {@code boolean}-to-{@code int} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToIntFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToIntFunction toInt(@Nonnull final BooleanToIntFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsInt(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToLongFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code long}, using the {@code boolean}-to-{@code long} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToLongFunction} that first gets the result from this operation, and then applies
     * the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToLongFunction toLong(@Nonnull final BooleanToLongFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsLong(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanToShortFunction} that first applies this operator to its input, and then applies
     * the {@code after} operation to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this operator to {@code short}, using the {@code boolean}-to-{@code short} primitive specialization of {@link
     * Function}.
     *
     * @param after The operation to apply after this operation is applied
     * @return A composed {@code BooleanToShortFunction} that first gets the result from this operation, and then
     * applies the {@code after} operation to the result.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanToShortFunction toShort(@Nonnull final BooleanToShortFunction after) {
        Objects.requireNonNull(after);
        return value -> after.applyAsShort(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link BooleanConsumer} that fist applies this operator to its input, and then consumes the
     * result using the given {@code BooleanConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BooleanConsumer} that first applies this operation to its input, and then consumes the
     * result using the given {@code BooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BooleanConsumer consume(@Nonnull final BooleanConsumer consumer) {
        Objects.requireNonNull(consumer);
        return value -> consumer.accept(applyAsBoolean(value));
    }

    /**
     * Returns a composed {@link UnaryOperator} which represents this {@link BooleanUnaryOperator}. Thereby the
     * primitive input argument for this operator is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BooleanUnaryOperator} with JRE specific methods, only accepting {@code UnaryOperator}.
     *
     * @return A composed {@code UnaryOperator} which represents this {@code BooleanUnaryOperator}.
     */
    @Nonnull
    default UnaryOperator<Boolean> boxed() {
        return this::applyAsBoolean;
    }
}