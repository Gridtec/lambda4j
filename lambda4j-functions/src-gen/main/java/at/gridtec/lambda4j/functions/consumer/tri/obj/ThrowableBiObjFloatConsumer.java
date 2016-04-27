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
package at.gridtec.lambda4j.functions.consumer.tri.obj;

import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.functions.Lambda;
import at.gridtec.lambda4j.functions.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.functions.consumer.ThrowableFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.bi.obj.ThrowableObjFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriBooleanConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriByteConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriCharConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriDoubleConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriFloatConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriIntConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriLongConsumer;
import at.gridtec.lambda4j.functions.consumer.tri.ThrowableTriShortConsumer;
import at.gridtec.lambda4j.functions.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.functions.function.ThrowableByteFunction;
import at.gridtec.lambda4j.functions.function.ThrowableCharFunction;
import at.gridtec.lambda4j.functions.function.ThrowableDoubleFunction;
import at.gridtec.lambda4j.functions.function.ThrowableFloatFunction;
import at.gridtec.lambda4j.functions.function.ThrowableFunction;
import at.gridtec.lambda4j.functions.function.ThrowableIntFunction;
import at.gridtec.lambda4j.functions.function.ThrowableLongFunction;
import at.gridtec.lambda4j.functions.function.ThrowableShortFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableBooleanToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableByteToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableCharToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableDoubleToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableIntToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableLongToFloatFunction;
import at.gridtec.lambda4j.functions.function.conversion.ThrowableShortToFloatFunction;
import at.gridtec.lambda4j.functions.function.to.ThrowableToFloatFunction;
import at.gridtec.lambda4j.functions.operator.unary.ThrowableFloatUnaryOperator;

import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Represents an operation that accepts two object-valued and one {@code float}-valued input argument and returns no
 * result which is able to throw any {@link Throwable}. This is a (reference, reference, float) specialization of {@link
 * ThrowableTriConsumer}. Unlike most other functional interfaces, {@code ThrowableBiObjFloatConsumer} is expected to
 * operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(Object, Object, float)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @param <U> The type of the second argument to the consumer
 * @param <X> The type of the throwable to be thrown by this consumer
 * @see ThrowableTriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableBiObjFloatConsumer<T, U, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableBiObjFloatConsumer} based on a lambda expression or a method reference. Thereby the
     * given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableBiObjFloatConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but if {@code null} given, {@code
     * null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjFloatConsumer<T, U, X> of(
            @Nonnull final ThrowableBiObjFloatConsumer<T, U, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableBiObjFloatConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @param value The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this consumers action
     */
    static <T, U, X extends Throwable> void call(
            @Nonnull final ThrowableBiObjFloatConsumer<? super T, ? super U, ? extends X> consumer, T t, U u,
            float value) throws X {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(t, u, value);
    }

    /**
     * Creates a {@link ThrowableBiObjFloatConsumer} which uses the {@code first} parameter of this one as argument for
     * the given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableBiObjFloatConsumer} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjFloatConsumer<T, U, X> onlyFirst(
            @Nonnull final ThrowableConsumer<? super T, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(t);
    }

    /**
     * Creates a {@link ThrowableBiObjFloatConsumer} which uses the {@code second} parameter of this one as argument for
     * the given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableBiObjFloatConsumer} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjFloatConsumer<T, U, X> onlySecond(
            @Nonnull final ThrowableConsumer<? super U, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(u);
    }

    /**
     * Creates a {@link ThrowableBiObjFloatConsumer} which uses the {@code third} parameter of this one as argument for
     * the given {@link ThrowableFloatConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <U> The type of the second argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableBiObjFloatConsumer} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableFloatConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, U, X extends Throwable> ThrowableBiObjFloatConsumer<T, U, X> onlyThird(
            @Nonnull final ThrowableFloatConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, u, value) -> consumer.acceptThrows(value);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param u The second argument to the consumer
     * @param value The third argument to the consumer
     * @throws X Any throwable from this consumers action
     */
    void acceptThrows(T t, U u, float value) throws X;

    /**
     * Applies this consumer to the given tuple.
     *
     * @param tuple The tuple to be applied to the consumer
     * @param value The primitive value to be applied to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this consumers action
     * @see org.apache.commons.lang3.tuple.Pair
     */
    default void acceptThrows(@Nonnull Pair<T, U> tuple, float value) throws X {
        Objects.requireNonNull(tuple);
        acceptThrows(tuple.getLeft(), tuple.getRight(), value);
    }

    /**
     * Returns the number of arguments for this consumer.
     *
     * @return The number of arguments for this consumer.
     * @implSpec The default implementation always returns {@code 3}.
     */
    @Nonnegative
    default int arity() {
        return 3;
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     *
     * @param <A> The type of the argument to the first given function, and of composed consumer
     * @param <B> The type of the argument to the second given function, and of composed consumer
     * @param <C> The type of the argument to the third given function, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriConsumer<A, B, C, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowableFunction<? super B, ? extends U, ? extends X> before2,
            @Nonnull final ThrowableToFloatFunction<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> acceptThrows(before1.applyThrows(a), before2.applyThrows(b), before3.applyAsFloatThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriBooleanConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanConsumer<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableBooleanFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableBooleanToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code byte} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriByteConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteConsumer<X> composeFromByte(
            @Nonnull final ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableByteFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableByteToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code char} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriCharConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharConsumer<X> composeFromChar(
            @Nonnull final ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableCharFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableCharToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriDoubleConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleConsumer<X> composeFromDouble(
            @Nonnull final ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableDoubleFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableDoubleToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third operator to apply before this consumer is applied
     * @return A composed {@code ThrowableTriFloatConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatConsumer<X> composeFromFloat(
            @Nonnull final ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableFloatFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableFloatUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code int} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriIntConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntConsumer<X> composeFromInt(
            @Nonnull final ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableIntFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableIntToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result. This method is just convenience, to provide the ability to execute
     * an operation which accepts {@code long} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriLongConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongConsumer<X> composeFromLong(
            @Nonnull final ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableLongFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableLongToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second function to apply before this consumer is applied
     * @param before3 The third function to apply before this consumer is applied
     * @return A composed {@code ThrowableTriShortConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortConsumer<X> composeFromShort(
            @Nonnull final ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableShortFunction<? extends U, ? extends X> before2,
            @Nonnull final ThrowableShortToFloatFunction<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.applyThrows(value2),
                                                        before3.applyAsFloatThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableBiObjFloatConsumer} that performs, in sequence, this consumer followed by the
     * {@code after} consumer. If performing this consumer throws an exception, the {@code after} consumer will not be
     * performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ThrowableBiObjFloatConsumer} that performs, in sequence, this consumer followed by the
     * {@code after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableBiObjFloatConsumer<T, U, X> andThen(
            @Nonnull final ThrowableBiObjFloatConsumer<? super T, ? super U, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, u, value) -> {
            acceptThrows(t, u, value);
            after.acceptThrows(t, u, value);
        };
    }

    /**
     * Returns a tupled version of this consumer.
     *
     * @return A tupled version of this consumer.
     */
    @Nonnull
    default ThrowableObjFloatConsumer<Pair<T, U>, X> tupled() {
        return this::acceptThrows;
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} which represents this {@link ThrowableBiObjFloatConsumer}.
     * Thereby the primitive input argument for this consumer is autoboxed.
     *
     * @return A composed {@code ThrowableTriConsumer} which represents this {@code ThrowableBiObjFloatConsumer}.
     */
    @Nonnull
    default ThrowableTriConsumer<T, U, Float, X> boxed() {
        return this::acceptThrows;
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that applies this consumer to its input and nests the thrown {@link
     * Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. The throwable is nested
     * (wrapped) in a {@link ThrownByFunctionalInterfaceException}, which is constructed from the thrown throwables
     * message and the thrown throwable itself.
     *
     * @return A composed {@code BiObjFloatConsumer} that applies this consumer to its input and nests the thrown {@code
     * {@code Throwable} from it, unless it is of type {@code RuntimeException} or {@code Error}.
     */
    @Nonnull
    default BiObjFloatConsumer<T, U> nest() {
        return (t, u, value) -> {
            try {
                this.acceptThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable);
            }
        };
    }

    /**
     * Returns a composed {@link BiObjFloatConsumer} that applies this consumer to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}. This means that
     * each throwable thrown from the returned composed consumer behaves exactly the same as an <em>unchecked</em>
     * throwable does. As a result, there is no need to handle the throwable of this consumer in the returned composed
     * consumer by either wrapping it in an <em>unchecked</em> throwable or to declare it in the {@code throws} clause,
     * as it would be done in a non sneaky throwing consumer.
     * <p>
     * What sneaky throwing simply does, is to fake out the compiler and thus it bypasses the principle of
     * <em>checked</em> throwables. On the JVM (class file) level, all throwables, checked or not, can be thrown
     * regardless of the {@code throws} clause of methods, which is why this works at all.
     * <p>
     * However, when using this method to get a sneaky throwing consumer variant of this throwable consumer, the
     * following advantages, disadvantages and limitations will apply:
     * <p>
     * If the calling-code is to handle the sneakily thrown throwable, it is required to add it to the {@code throws}
     * clause of the method that applies the returned composed consumer. The compiler will not force the declaration in
     * the {@code throws} clause anymore.
     * <p>
     * If the calling-code already handles the sneakily thrown throwable, the compiler requires it to be added to the
     * {@code throws} clause of the method that applies the returned composed consumer. If not added, the compiler will
     * error that the caught throwable is never thrown in the corresponding {@code try} block.
     * <p>
     * If the returned composed consumer is directly surrounded by a {@code try}-{@code catch} block to catch the
     * sneakily thrown throwable from it, the compiler will error that the caught throwable is never thrown in the
     * corresponding {@code try} block.
     * <p>
     * In any case, if the throwable is not added to the to the {@code throws} clause of the method that applies the
     * returned composed consumer, the calling-code won't be able to catch the throwable by name. It will bubble and
     * probably be caught in some {@code catch} statement, catching a base type such as {@code try { ... }
     * catch(RuntimeException e) { ... }} or {@code try { ... } catch(Exception e) { ... }}, but perhaps this is
     * intended.
     * <p>
     * When the called code never throws the specific throwable that it declares, it should obviously be omitted. For
     * example: {@code new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but {@code UTF-8} is
     * guaranteed by the Java specification to be always present. Here, the {@code throws} declaration is a nuisance and
     * any solution to silence it with minimal boilerplate is welcome. The throwable should therefore be omitted in the
     * {@code throws} clause of the method that applies the returned composed consumer.
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
     * @return A composed {@link BiObjFloatConsumer} that applies this consumer to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     */
    @Nonnull
    default BiObjFloatConsumer<T, U> sneakyThrow() {
        return (t, u, value) -> {
            try {
                this.acceptThrows(t, u, value);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

}
