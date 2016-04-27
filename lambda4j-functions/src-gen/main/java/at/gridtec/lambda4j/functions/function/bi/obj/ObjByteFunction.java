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
package at.gridtec.lambda4j.functions.function.bi.obj;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ObjByteConsumer;
import at.gridtec.lambda4j.functions.function.BooleanFunction;
import at.gridtec.lambda4j.functions.function.ByteFunction;
import at.gridtec.lambda4j.functions.function.CharFunction;
import at.gridtec.lambda4j.functions.function.FloatFunction;
import at.gridtec.lambda4j.functions.function.ShortFunction;
import at.gridtec.lambda4j.functions.function.bi.BiBooleanFunction;
import at.gridtec.lambda4j.functions.function.bi.BiByteFunction;
import at.gridtec.lambda4j.functions.function.bi.BiCharFunction;
import at.gridtec.lambda4j.functions.function.bi.BiDoubleFunction;
import at.gridtec.lambda4j.functions.function.bi.BiFloatFunction;
import at.gridtec.lambda4j.functions.function.bi.BiFunction2;
import at.gridtec.lambda4j.functions.function.bi.BiIntFunction;
import at.gridtec.lambda4j.functions.function.bi.BiLongFunction;
import at.gridtec.lambda4j.functions.function.bi.BiShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.BooleanToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.CharToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.DoubleToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.IntToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.LongToByteFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToByteFunction;
import at.gridtec.lambda4j.functions.function.to.ToByteFunction;
import at.gridtec.lambda4j.functions.operator.unary.ByteUnaryOperator;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

/**
 * Represents an operation that accepts one object-valued and one {@code byte}-valued input argument and produces a
 * result. This is a (reference, byte) specialization of {@link BiFunction2}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #apply(Object, byte)}.
 *
 * @param <T> The type of the first argument to the function
 * @param <R> The type of return value from the function
 * @see BiFunction2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ObjByteFunction<T, R> extends Lambda {

    /**
     * Constructs a {@link ObjByteFunction} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ObjByteFunction} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, R> ObjByteFunction<T, R> of(@Nonnull final ObjByteFunction<T, R> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ObjByteFunction} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param function The function to be called
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The result from the given {@code ObjByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    static <T, R> R call(@Nonnull final ObjByteFunction<? super T, ? extends R> function, T t, byte value) {
        Objects.requireNonNull(function);
        return function.apply(t, value);
    }

    /**
     * Creates a {@link ObjByteFunction} which uses the {@code first} parameter of this one as argument for the given
     * {@link Function}.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code first} parameter of this one
     * @return Creates a {@code ObjByteFunction} which uses the {@code first} parameter of this one as argument for the
     * given {@code Function}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjByteFunction<T, R> onlyFirst(@Nonnull final Function<? super T, ? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(t);
    }

    /**
     * Creates a {@link ObjByteFunction} which uses the {@code second} parameter of this one as argument for the given
     * {@link ByteFunction}.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param function The function which accepts the {@code second} parameter of this one
     * @return Creates a {@code ObjByteFunction} which uses the {@code second} parameter of this one as argument for the
     * given {@code ByteFunction}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, R> ObjByteFunction<T, R> onlySecond(@Nonnull final ByteFunction<? extends R> function) {
        Objects.requireNonNull(function);
        return (t, value) -> function.apply(value);
    }

    /**
     * Creates a {@link ObjByteFunction} which always returns a given value.
     *
     * @param <T> The type of the first argument to the function
     * @param <R> The type of return value from the function
     * @param ret The return value for the constant
     * @return A {@code ObjByteFunction} which always returns a given value.
     */
    @Nonnull
    static <T, R> ObjByteFunction<T, R> constant(R ret) {
        return (t, value) -> ret;
    }

    /**
     * Applies this function to the given arguments.
     *
     * @param t The first argument to the function
     * @param value The second argument to the function
     * @return The return value from the function, which is its result.
     */
    R apply(T t, byte value);

    /**
     * Returns the number of arguments for this function.
     *
     * @return The number of arguments for this function.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiFunction2} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed function
     * @param <B> The type of the argument to the second given function, and of composed function
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFunction2} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiFunction2<A, B, R> compose(@Nonnull final Function<? super A, ? extends T> before1,
            @Nonnull final ToByteFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> apply(before1.apply(a), before2.applyAsByte(b));
    }

    /**
     * Returns a composed {@link BiBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiBooleanFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanFunction<R> composeFromBoolean(@Nonnull final BooleanFunction<? extends T> before1,
            @Nonnull final BooleanToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second operator to apply before this function is applied
     * @return A composed {@code BiByteFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteFunction<R> composeFromByte(@Nonnull final ByteFunction<? extends T> before1,
            @Nonnull final ByteUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiCharFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharFunction<R> composeFromChar(@Nonnull final CharFunction<? extends T> before1,
            @Nonnull final CharToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiDoubleFunction} that first applies the {@code before} functions to its input, and
     * then applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleFunction<R> composeFromDouble(@Nonnull final DoubleFunction<? extends T> before1,
            @Nonnull final DoubleToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiFloatFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiFloatFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatFunction<R> composeFromFloat(@Nonnull final FloatFunction<? extends T> before1,
            @Nonnull final FloatToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiIntFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntFunction<R> composeFromInt(@Nonnull final IntFunction<? extends T> before1,
            @Nonnull final IntToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiLongFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongFunction<R> composeFromLong(@Nonnull final LongFunction<? extends T> before1,
            @Nonnull final LongToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link BiShortFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive function is executed.
     *
     * @param before1 The first function to apply before this function is applied
     * @param before2 The second function to apply before this function is applied
     * @return A composed {@code BiShortFunction} that first applies the {@code before} functions to its input, and then
     * applies this function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortFunction<R> composeFromShort(@Nonnull final ShortFunction<? extends T> before1,
            @Nonnull final ShortToByteFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> apply(before1.apply(value1), before2.applyAsByte(value2));
    }

    /**
     * Returns a composed {@link ObjByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <S> The type of return value from the {@code after} function, and of the composed function
     * @param after The function to apply after this function is applied
     * @return A composed {@code ObjByteFunction} that first applies this function to its input, and then applies the
     * {@code after} function to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to return every type.
     */
    @Nonnull
    default <S> ObjByteFunction<T, S> andThen(@Nonnull final Function<? super R, ? extends S> after) {
        Objects.requireNonNull(after);
        return (t, value) -> after.apply(apply(t, value));
    }

    /**
     * Returns a composed {@link ObjByteConsumer} that fist applies this function to its input, and then consumes the
     * result using the given {@link Consumer}. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation.
     *
     * @param consumer The operation which consumes the result from this operation
     * @return A composed {@code ObjByteConsumer} that first applies this function to its input, and then consumes the
     * result using the given {@code Consumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ObjByteConsumer<T> consume(@Nonnull final Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value) -> consumer.accept(apply(t, value));
    }

    /**
     * Returns a memoized (caching) version of this {@link ObjByteFunction}. Whenever it is called, the mapping between
     * the input parameters and the return value is preserved in a cache, making subsequent calls returning the memoized
     * value instead of computing the return value again.
     * <p>
     * Unless the function and therefore the used cache will be garbage-collected, it will keep all memoized values
     * forever.
     *
     * @return A memoized (caching) version of this {@code ObjByteFunction}.
     * @implSpec This implementation does not allow the input parameters or return value to be {@code null} for the
     * resulting memoized function, as the cache used internally does not permit {@code null} keys or values.
     * @implNote The returned memoized function can be safely used concurrently from multiple threads which makes it
     * thread-safe.
     */
    @Nonnull
    default ObjByteFunction<T, R> memoized() {
        if (this instanceof Memoized) {
            return this;
        } else {
            final Map<Pair<T, Byte>, R> cache = new ConcurrentHashMap<>();
            final Object lock = new Object();
            return (ObjByteFunction<T, R> & Memoized) (t, value) -> {
                final R returnValue;
                synchronized (lock) {
                    returnValue = cache.computeIfAbsent(Pair.of(t, value), key -> apply(key.getLeft(), key.getRight()));
                }
                return returnValue;
            };
        }
    }

    /**
     * Converts this function to an equal function, which ensures that its result is not {@code null} using {@link
     * Optional}. This method mainly exists to avoid unnecessary {@code NullPointerException}s through referencing
     * {@code null} from this function.
     *
     * @return An equal function, which ensures that its result is not {@code null}.
     */
    @Nonnull
    default ObjByteFunction<T, Optional<R>> nonNull() {
        return (t, value) -> Optional.ofNullable(apply(t, value));
    }

    /**
     * Returns a composed {@link BiFunction} which represents this {@link ObjByteFunction}. Thereby the primitive input
     * argument for this function is autoboxed. This method is just convenience to provide the ability to use this
     * {@code ObjByteFunction} with JDK specific methods, only accepting {@code BiFunction}.
     *
     * @return A composed {@code BiFunction} which represents this {@code ObjByteFunction}.
     */
    @Nonnull
    default BiFunction<T, Byte, R> boxed() {
        return this::apply;
    }

}
