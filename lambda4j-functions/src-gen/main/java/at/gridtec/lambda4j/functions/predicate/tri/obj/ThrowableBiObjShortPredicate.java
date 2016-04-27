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
package at.gridtec.lambda4j.functions.predicate.tri.obj;

import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.obj.ThrowableBiObjShortConsumer;
import at.gridtec.lambda4j.functions.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.functions.function.ThrowableByteFunction;
import at.gridtec.lambda4j.functions.function.ThrowableCharFunction;
import at.gridtec.lambda4j.functions.function.ThrowableDoubleFunction;
import at.gridtec.lambda4j.functions.function.ThrowableFloatFunction;
import at.gridtec.lambda4j.functions.function.ThrowableFunction;
import at.gridtec.lambda4j.functions.function.ThrowableIntFunction;
import at.gridtec.lambda4j.functions.function.ThrowableLongFunction;
import at.gridtec.lambda4j.functions.function.ThrowableShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToLongFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableByteToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableCharToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableDoubleToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableFloatToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableIntToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableLongToShortFunction;
import at.gridtec.lambda4j.functions.function.to.ThrowableToShortFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToByteFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToCharFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToDoubleFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToFloatFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.obj.ThrowableBiObjShortToShortFunction;
import at.gridtec.lambda4j.functions.operator.ternary.ThrowableBooleanTernaryOperator;
import at.gridtec.lambda4j.functions.operator.unary.ThrowableBooleanUnaryOperator;
import at.gridtec.lambda4j.functions.operator.unary.ThrowableShortUnaryOperator;
import at.gridtec.lambda4j.functions.predicate.ThrowablePredicate;
import at.gridtec.lambda4j.functions.predicate.ThrowableShortPredicate;
import at.gridtec.lambda4j.functions.predicate.bi.obj.ThrowableObjShortPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriBytePredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriCharPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriDoublePredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriFloatPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriIntPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriLongPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriPredicate;
import at.gridtec.lambda4j.functions.predicate.tri.ThrowableTriShortPredicate;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents an predicate (boolean-valued function) of two object-valued and one {@code short}-valued input argument
 * which is able to throw any {@link Throwable}. This is a (reference, reference, short) specialization of {@link
 * ThrowableTriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(Object, Object, short)}.
 *
 * @param <T> The type of the first argument to the predicate
 * @param <U> The type of the second argument to the predicate
 * @param <X> The type of the throwable to be thrown by this predicate
 * @see ThrowableTriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjShortPredicate<T, U, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiObjShortPredicate} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiObjShortPredicate} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> of(
            @Nonnull final ThrowableBiObjShortPredicate<T, U, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiObjShortPredicate} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate to be called
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The result from the given {@code ThrowableBiObjShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     */
    static <T, U, X extends Throwable> boolean call(
            @Nonnull final ThrowableBiObjShortPredicate<? super T, ? super U, ? extends X> predicate, T t, U u,
            short value) throws X {
        Objects.requireNonNull(predicate);
        return predicate.testThrows(t, u, value);
    }

    /**
     * Creates a {@link ThrowableBiObjShortPredicate} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiObjShortPredicate} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> onlyFirst(
            @Nonnull final ThrowablePredicate<? super T, ? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.testThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiObjShortPredicate} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowablePredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiObjShortPredicate} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowablePredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> onlySecond(
            @Nonnull final ThrowablePredicate<? super U, ? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.testThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiObjShortPredicate} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableShortPredicate}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param predicate The predicate which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableBiObjShortPredicate} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableShortPredicate}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> onlyThird(
            @Nonnull final ThrowableShortPredicate<? extends X> predicate) {
        Objects.requireNonNull(predicate);
        return (t, u, value) -> predicate.testThrows(value);
    }

    /**
     * Creates a {@link ThrowableBiObjShortPredicate} which always returns a given value.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param ret The return value for the constant
     * @return A {@code ThrowableBiObjShortPredicate} which always returns a given value.
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> constant(boolean ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Returns a {@link ThrowableBiObjShortPredicate} that always returns {@code true}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableBiObjShortPredicate} that always returns {@code true}.
     * @see #alwaysFalse()
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> alwaysTrue() {
        return (t, u, value) -> true;
    }

    /**
     * Returns a {@link ThrowableBiObjShortPredicate} that always returns {@code false}.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @return A {@link ThrowableBiObjShortPredicate} that always returns {@code false}.
     * @see #alwaysTrue()
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> alwaysFalse() {
        return (t, u, value) -> false;
    }

    /**
     * Returns a {@link ThrowableBiObjShortPredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     *
     * @param <T> The type of the first argument to the predicate
     * @param <U> The type of the second argument to the predicate
     * @param <X> The type of the throwable to be thrown by this predicate
     * @param target1 The first reference with which to compare for equality, which may be {@code null}
     * @param target2 The second reference with which to compare for equality, which may be {@code null}
     * @param target3 The third reference with which to compare for equality, which may be {@code null}
     * @return A {@code ThrowableBiObjShortPredicate} that tests if the given arguments are <b>equal</b> to the ones of
     * this predicate.
     * @implNote This implementation checks equality according to {@link Objects#equals(Object)} operation for {@link
     * Object} references and {@code value == target} operation for primitive values.
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjShortPredicate<T, U, X> isEqual(@Nullable Object target1,
            @Nullable Object target2, short target3) {
        return (t, u, value) -> (t == null ? target1 == null : t.equals(target1)) && (u == null
                ? target2 == null
                : u.equals(target2)) && (value == target3);
    }

    /**
     * Applies this predicate to the given arguments.
     *
     * @param t The first argument to the predicate
     * @param u The second argument to the predicate
     * @param value The third argument to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws X Any throwable from this predicates action
     */
    boolean testThrows(T t, U u, short value) throws X;

    /**
     * Applies this predicate to the given tuple.
     *
     * @param tuple The tuple to be applied to the predicate
     * @param value The primitive value to be applied to the predicate
     * @return The return value from the predicate, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this predicates action
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default boolean testThrows(@Nonnull Pair<T, U> tuple, short value) throws X {
        Objects.requireNonNull(tuple);
        return testThrows(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Returns the number of arguments for this predicate.
     *
     * @return The number of arguments for this predicate.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed predicate
     * @param <B> The type of the argument to the second given function, and of composed predicate
     * @param <C> The type of the argument to the third given function, and of composed predicate
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriPredicate<A, B, C, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull final ThrowableToShortFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> testThrows(before1.applyThrows(a), before2.applyThrows(b), before3.applyAsShortThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableBooleanTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableBooleanTernaryOperator} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBooleanTernaryOperator<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableBooleanFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriBytePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriBytePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriBytePredicate<X> composeFromByte(
            @Nonnull final ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableByteFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableByteToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriCharPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharPredicate<X> composeFromChar(
            @Nonnull final ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableCharFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableCharToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriDoublePredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoublePredicate<X> composeFromDouble(
            @Nonnull final ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableDoubleFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableDoubleToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriFloatPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatPredicate<X> composeFromFloat(
            @Nonnull final ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableFloatFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableFloatToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriIntPredicate} that first applies the {@code before} functions to its input,
     * and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntPredicate<X> composeFromInt(
            @Nonnull final ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableIntFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableIntToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code long} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third function to apply before this predicate is applied
     * @return A composed {@code ThrowableTriLongPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongPredicate<X> composeFromLong(
            @Nonnull final ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableLongFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableLongToShortFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive predicate is executed.
     *
     * @param before1 The first function to apply before this predicate is applied
     * @param before2 The second function to apply before this predicate is applied
     * @param before3 The third operator to apply before this predicate is applied
     * @return A composed {@code ThrowableTriShortPredicate} that first applies the {@code before} functions to its
     * input, and then applies this predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortPredicate<X> composeFromShort(
            @Nonnull final ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableShortFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableShortUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> testThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                      before3.applyAsShortThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortFunction} that first applies this predicate to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ThrowableBiObjShortFunction<T, U, S, X> andThen(
            @Nonnull final ThrowableBooleanFunction<? extends S, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortPredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code boolean}.
     *
     * @param after The operator to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortPredicate} that first applies this predicate to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> andThenToBoolean(
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsBooleanThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToByteFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code byte}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToByteFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableBiObjShortToByteFunction<T, U, X> andThenToByte(
            @Nonnull final ThrowableBooleanToByteFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByteThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code char}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToCharFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableBiObjShortToCharFunction<T, U, X> andThenToChar(
            @Nonnull final ThrowableBooleanToCharFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsCharThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToDoubleFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result. This method is just convenience, to provide the
     * ability to transform this primitive predicate to an operation returning {@code double}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToDoubleFunction} that first applies this predicate to its input,
     * and then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableBiObjShortToDoubleFunction<T, U, X> andThenToDouble(
            @Nonnull final ThrowableBooleanToDoubleFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDoubleThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code float}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToFloatFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableBiObjShortToFloatFunction<T, U, X> andThenToFloat(
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloatThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToIntFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code int}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToIntFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableBiObjShortToIntFunction<T, U, X> andThenToInt(
            @Nonnull final ThrowableBooleanToIntFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsIntThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code long}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToLongFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableBiObjShortToLongFunction<T, U, X> andThenToLong(
            @Nonnull final ThrowableBooleanToLongFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLongThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result. This method is just convenience, to provide the ability to
     * transform this primitive predicate to an operation returning {@code short}.
     *
     * @param after The function to apply after this predicate is applied
     * @return A composed {@code ThrowableBiObjShortToShortFunction} that first applies this predicate to its input, and
     * then applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableBiObjShortToShortFunction<T, U, X> andThenToShort(
            @Nonnull final ThrowableBooleanToShortFunction<? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShortThrows(testThrows(t, u, value));
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortConsumer} that fist applies this predicate to its input, and then
     * consumes the result using the given {@link ThrowableBooleanConsumer}.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ThrowableBiObjShortConsumer} that first applies this predicate to its input, and then
     * consumes the result using the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiObjShortConsumer<T, U, X> consume(
            @Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(testThrows(t, u, value));
    }

    /**
     * Returns a {@link ThrowableBiObjShortPredicate} that represents the logical negation of this one.
     *
     * @return A {@code ThrowableBiObjShortPredicate} that represents the logical negation of this one.
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> negate() {
        return (t, u, value) -> !testThrows(t, u, value);
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortPredicate} that represents a short-circuiting logical AND of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code false}, then the
     * {@code other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableBiObjShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableBiObjShortPredicate} that will be logically-ANDed with this one
     * @return A composed {@code ThrowableBiObjShortPredicate} that represents the short-circuiting logical AND of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #or(ThrowableBiObjShortPredicate)
     * @see #xor(ThrowableBiObjShortPredicate)
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> and(
            @Nonnull final ThrowableBiObjShortPredicate<? super T, ? super U, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> testThrows(t, u, value) && other.testThrows(t, u, value);
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortPredicate} that represents a short-circuiting logical OR of this
     * predicate and another. When evaluating the composed predicate, if this predicate is {@code true}, then the {@code
     * other} predicate is not evaluated.
     * <p>
     * Any exceptions thrown during evaluation of either predicate is relayed to the caller; if evaluation of this
     * {@code ThrowableBiObjShortPredicate} throws an exception, the {@code other} predicate will not be evaluated.
     *
     * @param other A {@code ThrowableBiObjShortPredicate} that will be logically-ORed with this one
     * @return A composed {@code ThrowableBiObjShortPredicate} that represents the short-circuiting logical OR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableBiObjShortPredicate)
     * @see #xor(ThrowableBiObjShortPredicate)
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> or(
            @Nonnull final ThrowableBiObjShortPredicate<? super T, ? super U, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> testThrows(t, u, value) || other.testThrows(t, u, value);
    }

    /**
     * Returns a composed {@link ThrowableBiObjShortPredicate} that represents a short-circuiting logical XOR of this
     * predicate and another. Any exceptions thrown during evaluation of either predicate is relayed to the caller; if
     * evaluation of this {@code ThrowableBiObjShortPredicate} throws an exception, the {@code other} predicate will not
     * be evaluated.
     *
     * @param other A {@code ThrowableBiObjShortPredicate} that will be logically-XORed with this one
     * @return A composed {@code ThrowableBiObjShortPredicate} that represents the short-circuiting logical XOR of this
     * predicate and the {@code other} predicate.
     * @throws NullPointerException If given argument is {@code null}
     * @see #and(ThrowableBiObjShortPredicate)
     * @see #or(ThrowableBiObjShortPredicate)
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> xor(
            @Nonnull final ThrowableBiObjShortPredicate<? super T, ? super U, ? extends X> other) {
        Objects.requireNonNull(other);
        return (t, u, value) -> testThrows(t, u, value) ^ other.testThrows(t, u, value);
    }

    /**
     * Returns a tupled version of this predicate.
     *
     * @return A tupled version of this predicate.
     */
    @Nonnull
    default ThrowableObjShortPredicate<Pair<T, U>, X> tupled() {
        return this::testThrows;
    }

    /**
     * Returns a memoized (caching) version of this {@link ThrowableBiObjShortPredicate}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the predicate and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ThrowableBiObjShortPredicate}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized predicate, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized predicate can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ThrowableBiObjShortPredicate<T, U, X> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<T, U, Short>, Boolean> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ThrowableBiObjShortPredicate<T, U, X> & Memoized) (t, u, value) -> {
                final boolean returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, value), ThrowableFunction.of(
                            key -> testThrows(key.getLeft(), key.getMiddle(), key.getRight())));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link ThrowableTriPredicate} which represents this {@link ThrowableBiObjShortPredicate}.
     * Thereby the primitive input argument for this predicate is autoboxed.
     *
     * @return A composed {@code ThrowableTriPredicate} which represents this {@code ThrowableBiObjShortPredicate}.
     */
    @Nonnull
    default ThrowableTriPredicate<T, U, Short, X> boxed() {
        return this::testThrows;
    }

    /**
     * Returns a composed {@link BiObjShortPredicate} that applies this predicate to its input and nests the thrown
     * {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is
     * nested (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown
     * throwables message and the thrown throwable itself.
     *
     * @return A composed {@code BiObjShortPredicate} that applies this predicate to its input and nests the thrown
     * {@code {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BiObjShortPredicate<T, U> nest() {
        return (t, u, value) -> {
            try {
                return this.testThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjShortPredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed predicate behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this predicate in the returned composed
     * predicate by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing predicate.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing predicate variant of this throwable predicate, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed predicate. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed predicate. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed predicate is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed predicate, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed predicate.
     * <p>
     * With all that mentioned, the following example will demonstrate this methods correct use:
     * <pre>{@code
     * // when called with illegal value ClassNotFoundException is thrown
     * public Class<?> sneakyThrowingFunctionalInterface(final String className) throws ClassNotFoundException {
     *     return ThrowableFunction.of(Class::forName) // create the correct throwable functional interface
     *                .sneakyThrow() // create a non-throwable variant which is able to sneaky throw (this method)
     *                .apply(className); // apply non-throwable variant -> may sneaky throw a throwable
     * }
     *
     * // call the the method which surround the sneaky throwing functional interface
     * public void callingMethod() {
     *     try {
     *         final Class<?> sneakyThrowingFunctionalInterface("some illegal class name");
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link BiObjShortPredicate} that applies this predicate to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BiObjShortPredicate<T, U> sneakyThrow() {
        return (t, u, value) -> {
            try {
                return this.testThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
