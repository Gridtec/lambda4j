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
package at.gridtec.lambda4j.functions.consumer.bi;

import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.function.conversion.BooleanToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.ByteToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.CharToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.FloatToIntFunction;
import at.gridtec.lambda4j.functions.function.conversion.ShortToIntFunction;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntConsumer;
import java.util.function.IntUnaryOperator;
import java.util.function.LongToIntFunction;
import java.util.function.ToIntFunction;

/**
 * Represents an operation that accepts two {@code int}-valued input arguments and returns no result. This is a
 * primitive specialization of {@link BiConsumer2}. Unlike most other functional interfaces, {@code BiIntConsumer} is
 * expected to operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #accept(int, int)}.
 *
 * @see BiConsumer2
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface BiIntConsumer extends Lambda {

    /**
     * Constructs a {@link BiIntConsumer} based on a lambda expression or a method reference. Thereby the given lambda
     * expression or method reference is returned on an as-is basis to implicitly transform it to the desired type. With
     * this method, it is possible to ensure that correct type is used from lambda expression or method reference.
     *
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code BiIntConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static BiIntConsumer of(@Nonnull final BiIntConsumer expression) {
        return expression;
    }

    /**
     * Calls the given {@link BiIntConsumer} with the given arguments and returns its result.
     *
     * @param consumer The consumer to be called
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     */
    static void call(@Nonnull final BiIntConsumer consumer, int value1, int value2) {
        Objects.requireNonNull(consumer);
        consumer.accept(value1, value2);
    }

    /**
     * Creates a {@link BiIntConsumer} which uses the {@code first} parameter of this one as argument for the given
     * {@link IntConsumer}.
     *
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code BiIntConsumer} which uses the {@code first} parameter of this one as argument for the
     * given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiIntConsumer onlyFirst(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value1);
    }

    /**
     * Creates a {@link BiIntConsumer} which uses the {@code second} parameter of this one as argument for the given
     * {@link IntConsumer}.
     *
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code BiIntConsumer} which uses the {@code second} parameter of this one as argument for the
     * given {@code IntConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static BiIntConsumer onlySecond(@Nonnull final IntConsumer consumer) {
        Objects.requireNonNull(consumer);
        return (value1, value2) -> consumer.accept(value2);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param value1 The first argument to the consumer
     * @param value2 The second argument to the consumer
     */
    void accept(int value1, int value2);

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 2}.
     */
    @Nonnegative
    default int arity() {
        return 2;
    }

    /**
     * Returns a composed {@link BiConsumer2} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiConsumer2} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B> BiConsumer2<A, B> compose(@Nonnull final ToIntFunction<? super A> before1,
            @Nonnull final ToIntFunction<? super B> before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (a, b) -> accept(before1.applyAsInt(a), before2.applyAsInt(b));
    }

    /**
     * Returns a composed {@link BiBooleanConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiBooleanConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default BiBooleanConsumer composeFromBoolean(@Nonnull final BooleanToIntFunction before1,
            @Nonnull final BooleanToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiByteConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiByteConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default BiByteConsumer composeFromByte(@Nonnull final ByteToIntFunction before1,
            @Nonnull final ByteToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiCharConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiCharConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default BiCharConsumer composeFromChar(@Nonnull final CharToIntFunction before1,
            @Nonnull final CharToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiDoubleConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to
     * the caller of the composed operation. This method is just convenience, to provide the ability to execute an
     * operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiDoubleConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default BiDoubleConsumer composeFromDouble(@Nonnull final DoubleToIntFunction before1,
            @Nonnull final DoubleToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiFloatConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiFloatConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default BiFloatConsumer composeFromFloat(@Nonnull final FloatToIntFunction before1,
            @Nonnull final FloatToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiIntConsumer} that first applies the {@code before} operators to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before1 The first operator to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @return A composed {@code BiIntConsumer} that first applies the {@code before} operators to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default BiIntConsumer composeFromInt(@Nonnull final IntUnaryOperator before1,
            @Nonnull final IntUnaryOperator before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiLongConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default BiLongConsumer composeFromLong(@Nonnull final LongToIntFunction before1,
            @Nonnull final LongToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiShortConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result. If evaluation of either operation throws an exception, it is relayed to the
     * caller of the composed operation. This method is just convenience, to provide the ability to execute an operation
     * which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @return A composed {@code BiShortConsumer} that first applies the {@code before} functions to its input, and then
     * applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default BiShortConsumer composeFromShort(@Nonnull final ShortToIntFunction before1,
            @Nonnull final ShortToIntFunction before2) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        return (value1, value2) -> accept(before1.applyAsInt(value1), before2.applyAsInt(value2));
    }

    /**
     * Returns a composed {@link BiIntConsumer} that performs, in sequence, this consumer followed by the {@code after}
     * consumer. If evaluation of either operation throws an exception, it is relayed to the caller of the composed
     * operation. If performing this consumer throws an exception, the {@code after} consumer will not be performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link BiIntConsumer} that performs, in sequence, this consumer followed by the {@code after}
     * consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default BiIntConsumer andThen(@Nonnull final BiIntConsumer after) {
        Objects.requireNonNull(after);
        return (value1, value2) -> {
            accept(value1, value2);
            after.accept(value1, value2);
        };
    }

    /**
     * Returns a composed {@link BiConsumer} which represents this {@link BiIntConsumer}. Thereby the primitive input
     * argument for this consumer is autoboxed. This method is just convenience to provide the ability to use this
     * {@code BiIntConsumer} with JDK specific methods, only accepting {@code BiConsumer}.
     *
     * @return A composed {@code BiConsumer} which represents this {@code BiIntConsumer}.
     */
    @Nonnull
    default BiConsumer<Integer, Integer> boxed() {
        return this::accept;
    }

}
