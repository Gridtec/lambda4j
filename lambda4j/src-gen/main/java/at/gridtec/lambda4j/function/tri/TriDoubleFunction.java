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
package at.gridtec.lambda4j.function.tri;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.tri.TriDoubleConsumer;
import at.gridtec.lambda4j.function.conversion.BooleanToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ByteToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.CharToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.FloatToDoubleFunction;
import at.gridtec.lambda4j.function.conversion.ShortToDoubleFunction;

import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * Represents an operation that accepts three {@code double}-valued input arguments and produces a result. This is a
 * primitive specialization of {@link TriFunction}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(double, double, double)}.
 *
 * @param <R> The type of return value from the function
 * @see TriFunction
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface TriDoubleFunction<R> extends Lambda {

    /**
     * Constructs a {@link TriDoubleFunction} based on a lambda expression or a method reference. Thereby the given
     * lambda expression or method reference is returned on an as-is basis to implicitly transform it to the desired
     * type. With this method, it is possible to ensure that correct type is used from lambda expression or method
     * reference.
     *
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code TriDoubleFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <R> TriDoubleFunction<R> of(@Nullable final TriDoubleFunction<R> expression) {
        return expression;
    }

    /**
     * Calls the given {@link TriDoubleFunction} with the given arguments and returns its result.
     *
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The result from the given {@code TriDoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <R> R call(@Nonnull final TriDoubleFunction<? extends R> function, double value1, double value2,
            double value3) {
        Objects.requireNonNull(function);
        return function.apply(value1, value2, value3);
    }

    /**
     * Creates a {@link TriDoubleFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link DoubleFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code TriDoubleFunction} which uses the {@code first} parameter of this one as argument for
     * the given {@code DoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriDoubleFunction<R> onlyFirst(@Nonnull final DoubleFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value1);
    }

    /**
     * Creates a {@link TriDoubleFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link DoubleFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code TriDoubleFunction} which uses the {@code second} parameter of this one as argument for
     * the given {@code DoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriDoubleFunction<R> onlySecond(@Nonnull final DoubleFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value2);
    }

    /**
     * Creates a {@link TriDoubleFunction} which uses the {@code third} parameter of this one as argument for the given
     * {@link DoubleFunction}.
     *
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code third} parameter of this one
     * @return Creates a {@code TriDoubleFunction} which uses the {@code third} parameter of this one as argument for
     * the given {@code DoubleFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <R> TriDoubleFunction<R> onlyThird(@Nonnull final DoubleFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (value1, value2, value3) -> function.apply(value3);
    }

    /**
     * Creates a {@link TriDoubleFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code TriDoubleFunction} which always returns a given value.
     */
    @Nonnull
    static <R> TriDoubleFunction<R> constant(R ret) {
        return (value1, value2, value3) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param value1 The first argument to the function
     * @param value2 The second argument to the function
     * @param value3 The third argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(double value1, double value2, double value3);

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
     * Returns a composed {@link TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param <C> The type of the argument to the third given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> TriFunction<A, B, C, R> compose(@Nonnull final ToDoubleFunction<? super A> before1,
            @Nonnull final ToDoubleFunction<? super B> before2, @Nonnull final ToDoubleFunction<? super C> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> apply(before1.applyAsDouble(a), before2.applyAsDouble(b), before3.applyAsDouble(c));
    }

    /**
     * Returns a composed {@link TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default TriBooleanFunction<R> composeFromBoolean(@Nonnull final BooleanToDoubleFunction before1,
            @Nonnull final BooleanToDoubleFunction before2, @Nonnull final BooleanToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default TriByteFunction<R> composeFromByte(@Nonnull final ByteToDoubleFunction before1,
            @Nonnull final ByteToDoubleFunction before2, @Nonnull final ByteToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default TriCharFunction<R> composeFromChar(@Nonnull final CharToDoubleFunction before1,
            @Nonnull final CharToDoubleFunction before2, @Nonnull final CharToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies the {@code before} operators to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first operator to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @param before3 The third operator to apply before this function is applied
     * @return A composed {@code TriDoubleFunction} that first applies the {@code before} operators to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default TriDoubleFunction<R> composeFromDouble(@Nonnull final DoubleUnaryOperator before1,
            @Nonnull final DoubleUnaryOperator before2, @Nonnull final DoubleUnaryOperator before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriFloatFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default TriFloatFunction<R> composeFromFloat(@Nonnull final FloatToDoubleFunction before1,
            @Nonnull final FloatToDoubleFunction before2, @Nonnull final FloatToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default TriIntFunction<R> composeFromInt(@Nonnull final IntToDoubleFunction before1,
            @Nonnull final IntToDoubleFunction before2, @Nonnull final IntToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default TriLongFunction<R> composeFromLong(@Nonnull final LongToDoubleFunction before1,
            @Nonnull final LongToDoubleFunction before2, @Nonnull final LongToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @param before3 The third function to apply before this function is applied
     * @return A composed {@code TriShortFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default TriShortFunction<R> composeFromShort(@Nonnull final ShortToDoubleFunction before1,
            @Nonnull final ShortToDoubleFunction before2, @Nonnull final ShortToDoubleFunction before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> apply(before1.applyAsDouble(value1), before2.applyAsDouble(value2),
                                                 before3.applyAsDouble(value3));
    }

    /**
     * Returns a composed {@link TriDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code TriDoubleFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> TriDoubleFunction<S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (value1, value2, value3) -> after.apply(apply(value1, value2, value3));
    }

    /**
     * Returns a composed {@link TriDoubleConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code TriDoubleConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default TriDoubleConsumer consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2, value3) -> consumer.accept(apply(value1, value2, value3));
    }

    /**
     * Returns a memoized (caching) version of this {@link TriDoubleFunction}. Whenever it is called, the mapping
     * between the input parameters and the return value is preserved in a cache, making subsequent calls returning the
     * memoized value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code TriDoubleFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default TriDoubleFunction<R> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Triple<Double, Double, Double>, R> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (TriDoubleFunction<R> & Memoized) (value1, value2, value3) -> {
                final R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Triple.of(value1, value2, value3),
                                                        key -> apply(key.getLeft(), key.getMiddle(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Returns a composed {@link TriFunction} which represents this {@link TriDoubleFunction}. Thereby the primitive
     * input argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code TriDoubleFunction} with JDK specific methods, only accepting {@code TriFunction}.
     *
     * @return A composed {@code TriFunction} which represents this {@code TriDoubleFunction}.
     */
    @Nonnull
    default TriFunction<Double, Double, Double, R> boxed() {
        return this::apply;
    }

}