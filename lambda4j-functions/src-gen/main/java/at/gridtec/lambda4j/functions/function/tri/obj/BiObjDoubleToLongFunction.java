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
package at.gridtec.lambda4j.functions.function.tri.obj;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.tri.obj.BiObjDoubleConsumer;
import at.gridtec.lambda4j.functions.function.BooleanFunction;
import at.gridtec.lambda4j.functions.function.ByteFunction;
import at.gridtec.lambda4j.functions.function.CharFunction;
import at.gridtec.lambda4j.functions.function.FloatFunction;
import at.gridtec.lambda4j.functions.function.ShortFunction;
import at.gridtec.lambda4j.functions.function.bi.obj.ObjDoubleToLongFunction;
import at.gridtec.lambda4j.functions.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToDoubleFunction;
import at.gridtec.lambda4j.functions.function.tri.TriFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriBooleanToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriByteToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriCharToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriDoubleToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriFloatToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriIntToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriShortToLongFunction;
import at.gridtec.lambda4j.functions.function.tri.to.ToLongTriFunction;
import at.gridtec.lambda4j.functions.operator.ternary.LongTernaryOperator;
import at.gridtec.lambda4j.functions.predicate.tri.obj.BiObjDoublePredicate;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ToDoubleFunction;
import java.util.function.ToLongFunction;

/**
 * Represents an operation that accepts two object-valued and one {@code double}-valued input argument and produces a
 * {@code long}-valued result. This is a (reference, reference, double) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsLong(Object, Object, double)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <U> The type of the second argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiObjDoubleToLongFunction<T, U> extends Lambda {

    /**
     * Constructs a {@link BiObjDoubleToLongFunction} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiObjDoubleToLongFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, U> BiObjDoubleToLongFunction<T, U> of(@Nonnull final BiObjDoubleToLongFunction<T, U> expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiObjDoubleToLongFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The result from the given {@code BiObjDoubleToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, U> long call(@Nonnull final BiObjDoubleToLongFunction<? super T, ? super U> function, T t, U u,
            double value) {
        Objects.requireNonNull(function);
        return function.applyAsLong(t, u, value);
    }

    /**
     * Creates a {@link BiObjDoubleToLongFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiObjDoubleToLongFunction} which uses the {@code first} parameter of this one as
     * argument for the given {@code ToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjDoubleToLongFunction<T, U> onlyFirst(@Nonnull final ToLongFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsLong(t);
    }

    /**
     * Creates a {@link BiObjDoubleToLongFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@link ToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiObjDoubleToLongFunction} which uses the {@code second} parameter of this one as
     * argument for the given {@code ToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjDoubleToLongFunction<T, U> onlySecond(@Nonnull final ToLongFunction<? super U> function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsLong(u);
    }

    /**
     * Creates a {@link BiObjDoubleToLongFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@link DoubleToLongFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code BiObjDoubleToLongFunction} which uses the {@code third} parameter of this one as
     * argument for the given {@code DoubleToLongFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U> BiObjDoubleToLongFunction<T, U> onlyThird(@Nonnull final DoubleToLongFunction function) {
        Objects.requireNonNull(function);
        return (t, u, value) -> function.applyAsLong(value);
    }

    /**
     * Creates a {@link BiObjDoubleToLongFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <U> The type of the second argument to the function
     * @param ret The return value for the constant
     * @return A {@code BiObjDoubleToLongFunction} which always returns a given value.
     */
    @Nonnull
    static <T, U> BiObjDoubleToLongFunction<T, U> constant(long ret) {
        return (t, u, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param u The second argument to the function
     * @param value The third argument to the function
     * @return The return value from the function, which is its result.
     */
    long applyAsLong(T t, U u, double value);

    /**
     * Applies this function to the given tuple.
     *
     * @param tuple The tuple to be applied to the function
     * @param value The primitive value to be applied to the function
     * @return The return value from the function, which is its result.
     * @throws NullPointerException If given argument is {@code null}
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default long applyAsLong(@Nonnull Pair<T, U> tuple, double value) {
        Objects.requireNonNull(tuple);
        return applyAsLong(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ToLongTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToLongTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToLongTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final Function<? super B, ? extends U> before2,
            @Nonnull final ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsLong(before1.apply(a), before2.apply(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link TriBooleanToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToLongFunction composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanFunction<? extends U> before2, @Nonnull final BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriByteToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToLongFunction composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteFunction<? extends U> before2, @Nonnull final ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriCharToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToLongFunction composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharFunction<? extends U> before2, @Nonnull final CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriDoubleToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToLongFunction composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleFunction<? extends U> before2, @Nonnull final DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriFloatToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToLongFunction composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatFunction<? extends U> before2, @Nonnull final FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriIntToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntToLongFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntToLongFunction composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntFunction<? extends U> before2, @Nonnull final IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link LongTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code LongTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default LongTernaryOperator composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongFunction<? extends U> before2, @Nonnull final LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriShortToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToLongFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToLongFunction composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortFunction<? extends U> before2, @Nonnull final ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsLong(before1.apply(value1), before2.apply(value2),
                                                       before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link BiObjDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleFunction} that first applies this function to its input, and then applies
     * the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> BiObjDoubleFunction<T, U, S> andThen(@Nonnull final LongFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.apply(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoublePredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation. This method is just convenience, to provide the ability to transform
     * this primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code BiObjDoublePredicate} that first applies this function to its input, and then applies
     * the {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiObjDoublePredicate<T, U> andThenToBoolean(@Nonnull final LongPredicate after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.test(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiObjDoubleToByteFunction<T, U> andThenToByte(@Nonnull final LongToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsByte(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiObjDoubleToCharFunction<T, U> andThenToChar(@Nonnull final LongToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsChar(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiObjDoubleToDoubleFunction<T, U> andThenToDouble(@Nonnull final LongToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsDouble(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiObjDoubleToFloatFunction<T, U> andThenToFloat(@Nonnull final LongToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsFloat(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code int}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleToIntFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiObjDoubleToIntFunction<T, U> andThenToInt(@Nonnull final LongToIntFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsInt(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code BiObjDoubleToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiObjDoubleToLongFunction<T, U> andThenToLong(@Nonnull final LongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsLong(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code BiObjDoubleToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiObjDoubleToShortFunction<T, U> andThenToShort(@Nonnull final LongToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> after.applyAsShort(applyAsLong(t, u, value));
    }

    /**
     * Returns a composed {@link BiObjDoubleConsumer} that fist applies this function to its input, and then consumes
     * the result using the given {@link LongConsumer}. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code BiObjDoubleConsumer} that first applies this function to its input, and then consumes
     * the result using the given {@code LongConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiObjDoubleConsumer<T, U> consume(@Nonnull final LongConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.accept(applyAsLong(t, u, value));
    }

    /**
     * Returns a tupled version of this function.
     *
     * @return A tupled version of this function.
     */
    @Nonnull
    default ObjDoubleToLongFunction<Pair<T, U>> tupled() {
        return this::applyAsLong;
    }

    /**
     * Returns a memoized (caching) version of this {@link BiObjDoubleToLongFunction}. Whenever it is called, the
     * mapping between the input parameters and the return value is preserved in a cache, making subsequent calls
     * returning the memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code BiObjDoubleToLongFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default BiObjDoubleToLongFunction<T, U> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<T, U, Double>, Long> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (BiObjDoubleToLongFunction<T, U> & Memoized) (t, u, value) -> {
                final long returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, u, value),
                                                        key -> applyAsLong(key.getLeft(), key.getMiddle(),
                                                                           key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link BiObjDoubleToLongFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code BiObjDoubleToLongFunction} with JDK specific methods, only accepting {@code TriFunction}.
     *
     * @return A composed {@code TriFunction} which represents this {@code BiObjDoubleToLongFunction}.
     */
    @Nonnull
    default TriFunction<T, U, Double, Long> boxed() {
        return this::applyAsLong;
    }

}
