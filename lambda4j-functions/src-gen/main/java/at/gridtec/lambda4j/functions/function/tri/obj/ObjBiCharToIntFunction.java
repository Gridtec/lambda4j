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
import at.gridtec.lambda4j.functions.consumer.tri.obj.ObjBiCharConsumer;
import at.gridtec.lambda4j.functions.function.BooleanFunction;
import at.gridtec.lambda4j.functions.function.ByteFunction;
import at.gridtec.lambda4j.functions.function.CharFunction;
import at.gridtec.lambda4j.functions.function.FloatFunction;
import at.gridtec.lambda4j.functions.function.ShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.BooleanToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.CharToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.DoubleToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToCharFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToCharFunction;
import at.gridtec.lambda4j.functions.function.to.ToCharFunction;
import at.gridtec.lambda4j.functions.function.tri.TriFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriBooleanToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriByteToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriCharToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriDoubleToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriFloatToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriLongToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.conversion.TriShortToIntFunction;
import at.gridtec.lambda4j.functions.function.tri.to.ToIntTriFunction;
import at.gridtec.lambda4j.functions.operator.ternary.IntTernaryOperator;
import at.gridtec.lambda4j.functions.operator.unary.CharUnaryOperator;
import at.gridtec.lambda4j.functions.predicate.tri.obj.ObjBiCharPredicate;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.ToIntFunction;

/**
 * Represents an operation that accepts one object-valued and two {@code char}-valued input arguments and produces a
 * {@code int}-valued result. This is a (reference, char, char) specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsInt(Object, char, char)}.
 *
 * @param <T> The type of the first argument to the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjBiCharToIntFunction<T> extends Lambda {

    /**
     * Constructs a {@link ObjBiCharToIntFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <T> The type of the first argument to the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjBiCharToIntFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T> ObjBiCharToIntFunction<T> of(@Nonnull final ObjBiCharToIntFunction<T> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjBiCharToIntFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The result from the given {@code ObjBiCharToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T> int call(@Nonnull final ObjBiCharToIntFunction<? super T> function, T t, char value1, char value2) {
        Objects.requireNonNull(function);
        return function.applyAsInt(t, value1, value2);
    }

    /**
     * Creates a {@link ObjBiCharToIntFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@link ToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjBiCharToIntFunction} which uses the {@code first} parameter of this one as argument
     * for the given {@code ToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToIntFunction<T> onlyFirst(@Nonnull final ToIntFunction<? super T> function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsInt(t);
    }

    /**
     * Creates a {@link ObjBiCharToIntFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@link CharToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjBiCharToIntFunction} which uses the {@code second} parameter of this one as argument
     * for the given {@code CharToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToIntFunction<T> onlySecond(@Nonnull final CharToIntFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsInt(value1);
    }

    /**
     * Creates a {@link ObjBiCharToIntFunction} which uses the {@code third} parameter of this one as argument for the
     * given {@link CharToIntFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code ObjBiCharToIntFunction} which uses the {@code third} parameter of this one as argument
     * for the given {@code CharToIntFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T> ObjBiCharToIntFunction<T> onlyThird(@Nonnull final CharToIntFunction function) {
        Objects.requireNonNull(function);
        return (t, value1, value2) -> function.applyAsInt(value2);
    }

    /**
     * Creates a {@link ObjBiCharToIntFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param ret The return value for the constant
     * @return A {@code ObjBiCharToIntFunction} which always returns a given value.
     */
    @Nonnull
    static <T> ObjBiCharToIntFunction<T> constant(int ret) {
        return (t, value1, value2) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value1 The second argument to the function
     * @param value2 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    int applyAsInt(T t, char value1, char value2);

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
     * Returns a composed {@link ToIntTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code ToIntTriFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ToIntTriFunction<A, B, C> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final ToCharFunction<? super B> before2, @Nonnull final ToCharFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> applyAsInt(before1.apply(a), before2.applyAsChar(b), before3.applyAsChar(c));
    }

    /**
     * Returns a composed {@link TriBooleanToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanToIntFunction composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanToCharFunction before2, @Nonnull final BooleanToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriByteToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteToIntFunction composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteToCharFunction before2, @Nonnull final ByteToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriCharToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriCharToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharToIntFunction composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharUnaryOperator before2, @Nonnull final CharUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriDoubleToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriDoubleToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleToIntFunction composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleToCharFunction before2, @Nonnull final DoubleToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriFloatToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatToIntFunction composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatToCharFunction before2, @Nonnull final FloatToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link IntTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code IntTernaryOperator} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default IntTernaryOperator composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntToCharFunction before2, @Nonnull final IntToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriLongToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongToIntFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongToIntFunction composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongToCharFunction before2, @Nonnull final LongToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link TriShortToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortToIntFunction} that first applies the {@code before} functions to its input,
     * and then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortToIntFunction composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortToCharFunction before2, @Nonnull final ShortToCharFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> applyAsInt(before1.apply(value1), before2.applyAsChar(value2),
                                                      before3.applyAsChar(value3));
    }

    /**
     * Returns a composed {@link ObjBiCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjBiCharFunction<T, S> andThen(@Nonnull final IntFunction<? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.apply(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code boolean}.
     *
     * @param after The predicate to apply after this function is applied
     * @return A composed {@code ObjBiCharPredicate} that first applies this function to its input, and then applies the
     * {@code after} predicate to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ObjBiCharPredicate<T> andThenToBoolean(@Nonnull final IntPredicate after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.test(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code byte}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToByteFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ObjBiCharToByteFunction<T> andThenToByte(@Nonnull final IntToByteFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsByte(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code char}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToCharFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ObjBiCharToCharFunction<T> andThenToChar(@Nonnull final IntToCharFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsChar(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code double}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToDoubleFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ObjBiCharToDoubleFunction<T> andThenToDouble(@Nonnull final IntToDoubleFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsDouble(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code float}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToFloatFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ObjBiCharToFloatFunction<T> andThenToFloat(@Nonnull final IntToFloatFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsFloat(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to transform this
     * primitive function to an operation returning {@code int}.
     *
     * @param after The operator to apply after this function is applied
     * @return A composed {@code ObjBiCharToIntFunction} that first applies this function to its input, and then applies
     * the {@code after} operator to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ObjBiCharToIntFunction<T> andThenToInt(@Nonnull final IntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsInt(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code long}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToLongFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ObjBiCharToLongFunction<T> andThenToLong(@Nonnull final IntToLongFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsLong(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result. If evaluation of either operation throws an exception, it is
     * relayed to the caller of the composed operation. This method is just convenience, to provide the ability to
     * transform this primitive function to an operation returning {@code short}.
     *
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjBiCharToShortFunction} that first applies this function to its input, and then
     * applies the {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to return primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ObjBiCharToShortFunction<T> andThenToShort(@Nonnull final IntToShortFunction after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> after.applyAsShort(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a composed {@link ObjBiCharConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link IntConsumer}. If evaluation of either operation throws an exception, it is relayed
     * to the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjBiCharConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjBiCharConsumer<T> consume(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.accept(applyAsInt(t, value1, value2));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjBiCharToIntFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjBiCharToIntFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjBiCharToIntFunction<T> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<T, Character, Character>, Integer> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ObjBiCharToIntFunction<T> & Memoized) (t, value1, value2) -> {
                final int returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(t, value1, value2),
                                                        key -> applyAsInt(key.getLeft(), key.getMiddle(),
                                                                          key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link ObjBiCharToIntFunction}. Thereby the
     * primitive input argument for this function is autoboxed. This method is just convenience to provide the ability
     * to use this {@code ObjBiCharToIntFunction} with JDK specific methods, only accepting {@code TriFunction}.
     *
     * @return A composed {@code TriFunction} which represents this {@code ObjBiCharToIntFunction}.
     */
    @Nonnull
    default TriFunction<T, Character, Character, Integer> boxed() {
        return this::applyAsInt;
    }

}
