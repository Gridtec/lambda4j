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
package at.gridtec.lambda4j.consumer.tri.obj;

import at.gridtec.lambda4j.Lambda;
import at.gridtec.lambda4j.consumer.ThrowableBooleanConsumer;
import at.gridtec.lambda4j.consumer.ThrowableConsumer;
import at.gridtec.lambda4j.consumer.bi.ThrowableBiBooleanConsumer;
import at.gridtec.lambda4j.consumer.bi.obj.ThrowableObjBooleanConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriBooleanConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriByteConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriCharConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriDoubleConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriFloatConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriIntConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriLongConsumer;
import at.gridtec.lambda4j.consumer.tri.ThrowableTriShortConsumer;
import at.gridtec.lambda4j.core.exception.ThrownByFunctionalInterfaceException;
import at.gridtec.lambda4j.core.util.ThrowableUtils;
import at.gridtec.lambda4j.function.ThrowableBooleanFunction;
import at.gridtec.lambda4j.function.ThrowableByteFunction;
import at.gridtec.lambda4j.function.ThrowableCharFunction;
import at.gridtec.lambda4j.function.ThrowableDoubleFunction;
import at.gridtec.lambda4j.function.ThrowableFloatFunction;
import at.gridtec.lambda4j.function.ThrowableFunction;
import at.gridtec.lambda4j.function.ThrowableIntFunction;
import at.gridtec.lambda4j.function.ThrowableLongFunction;
import at.gridtec.lambda4j.function.ThrowableShortFunction;
import at.gridtec.lambda4j.operator.unary.ThrowableBooleanUnaryOperator;
import at.gridtec.lambda4j.predicate.ThrowableBytePredicate;
import at.gridtec.lambda4j.predicate.ThrowableCharPredicate;
import at.gridtec.lambda4j.predicate.ThrowableDoublePredicate;
import at.gridtec.lambda4j.predicate.ThrowableFloatPredicate;
import at.gridtec.lambda4j.predicate.ThrowableIntPredicate;
import at.gridtec.lambda4j.predicate.ThrowableLongPredicate;
import at.gridtec.lambda4j.predicate.ThrowablePredicate;
import at.gridtec.lambda4j.predicate.ThrowableShortPredicate;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Function;

/**
 * Represents an operation that accepts one object-valued and two {@code boolean}-valued input arguments and returns no
 * result which is able to throw any {@link Throwable}. This is a (reference, boolean, boolean) specialization of {@link
 * ThrowableTriConsumer}. Unlike most other functional interfaces, {@code ThrowableObjBiBooleanConsumer} is expected to
 * operate via side-effects.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #acceptThrows(Object, boolean, boolean)}.
 *
 * @param <T> The type of the first argument to the consumer
 * @param <X> The type of the throwable to be thrown by this consumer
 * @see ThrowableTriConsumer
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableObjBiBooleanConsumer<T, X extends Throwable> extends Lambda {

    /**
     * Constructs a {@link ThrowableObjBiBooleanConsumer} based on a lambda expression or a method reference. Thereby
     * the given lambda expression or method reference is returned on an as-is basis to implicitly transform it to the
     * desired type. With this method, it is possible to ensure that correct type is used from lambda expression or
     * method reference.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param expression A lambda expression or (typically) a method reference, e.g. {@code this::method}
     * @return A {@code ThrowableObjBiBooleanConsumer} from given lambda expression or method reference.
     * @implNote This implementation allows the given argument to be {@code null}, but only if {@code null} given,
     * {@code null} will be returned.
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html#syntax">Lambda
     * Expression</a>
     * @see <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">Method Reference</a>
     */
    static <T, X extends Throwable> ThrowableObjBiBooleanConsumer<T, X> of(
            @Nullable final ThrowableObjBiBooleanConsumer<T, X> expression) {
        return expression;
    }

    /**
     * Calls the given {@link ThrowableObjBiBooleanConsumer} with the given arguments and returns its result.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer to be called
     * @param t The first argument to the consumer
     * @param value1 The second argument to the consumer
     * @param value2 The third argument to the consumer
     * @throws NullPointerException If given argument is {@code null}
     * @throws X Any throwable from this consumers action
     */
    static <T, X extends Throwable> void call(
            @Nonnull final ThrowableObjBiBooleanConsumer<? super T, ? extends X> consumer, T t, boolean value1,
            boolean value2) throws X {
        Objects.requireNonNull(consumer);
        consumer.acceptThrows(t, value1, value2);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanConsumer} which uses the {@code first} parameter of this one as argument
     * for the given {@link ThrowableConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code first} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanConsumer} which uses the {@code first} parameter of this one as
     * argument for the given {@code ThrowableConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanConsumer<T, X> onlyFirst(
            @Nonnull final ThrowableConsumer<? super T, ? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(t);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanConsumer} which uses the {@code second} parameter of this one as argument
     * for the given {@link ThrowableBooleanConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code second} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanConsumer} which uses the {@code second} parameter of this one as
     * argument for the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanConsumer<T, X> onlySecond(
            @Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(value1);
    }

    /**
     * Creates a {@link ThrowableObjBiBooleanConsumer} which uses the {@code third} parameter of this one as argument
     * for the given {@link ThrowableBooleanConsumer}.
     *
     * @param <T> The type of the first argument to the consumer
     * @param <X> The type of the throwable to be thrown by this consumer
     * @param consumer The consumer which accepts the {@code third} parameter of this one
     * @return Creates a {@code ThrowableObjBiBooleanConsumer} which uses the {@code third} parameter of this one as
     * argument for the given {@code ThrowableBooleanConsumer}.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    static <T, X extends Throwable> ThrowableObjBiBooleanConsumer<T, X> onlyThird(
            @Nonnull final ThrowableBooleanConsumer<? extends X> consumer) {
        Objects.requireNonNull(consumer);
        return (t, value1, value2) -> consumer.acceptThrows(value2);
    }

    /**
     * Applies this consumer to the given arguments.
     *
     * @param t The first argument to the consumer
     * @param value1 The second argument to the consumer
     * @param value2 The third argument to the consumer
     * @throws X Any throwable from this consumers action
     */
    void acceptThrows(T t, boolean value1, boolean value2) throws X;

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link ThrowableBiBooleanConsumer} as
     * result.
     *
     * @param t The first argument to this consumer used to partially apply this function
     * @return A {@code ThrowableBiBooleanConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default ThrowableBiBooleanConsumer<X> pacceptThrows(T t) {
        return (value1, value2) -> this.acceptThrows(t, value1, value2);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link ThrowableBooleanConsumer} as
     * result.
     *
     * @param t The first argument to this consumer used to partially apply this function
     * @param value1 The second argument to this consumer used to partially apply this function
     * @return A {@code ThrowableBooleanConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default ThrowableBooleanConsumer<X> pacceptThrows(T t, boolean value1) {
        return (value2) -> this.acceptThrows(t, value1, value2);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link ThrowableObjBooleanConsumer} as
     * result.
     *
     * @param value1 The second argument to this consumer used to partially apply this function
     * @return A {@code ThrowableObjBooleanConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default ThrowableObjBooleanConsumer<T, X> pacceptThrows(boolean value1) {
        return (t, value2) -> this.acceptThrows(t, value1, value2);
    }

    /**
     * Applies this consumer partially to some arguments of this one, producing a {@link ThrowableConsumer} as result.
     *
     * @param value1 The second argument to this consumer used to partially apply this function
     * @param value2 The third argument to this consumer used to partially apply this function
     * @return A {@code ThrowableConsumer} that represents this consumer partially applied the some arguments.
     */
    @Nonnull
    default ThrowableConsumer<T, X> pacceptThrows(boolean value1, boolean value2) {
        return (t) -> this.acceptThrows(t, value1, value2);
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
     * @param <B> The type of the argument to the second given predicate, and of composed consumer
     * @param <C> The type of the argument to the third given predicate, and of composed consumer
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriConsumer} that first applies the {@code before} functions to its input, and
     * then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is able to handle every type.
     */
    @Nonnull
    default <A, B, C> ThrowableTriConsumer<A, B, C, X> compose(
            @Nonnull final ThrowableFunction<? super A, ? extends T, ? extends X> before1,
            @Nonnull final ThrowablePredicate<? super B, ? extends X> before2,
            @Nonnull final ThrowablePredicate<? super C, ? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (a, b, c) -> acceptThrows(before1.applyThrows(a), before2.testThrows(b), before3.testThrows(c));
    }

    /**
     * Returns a composed {@link ThrowableTriBooleanConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code boolean} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second operator to apply before this consumer is applied
     * @param before3 The third operator to apply before this consumer is applied
     * @return A composed {@code ThrowableTriBooleanConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * boolean}.
     */
    @Nonnull
    default ThrowableTriBooleanConsumer<X> composeFromBoolean(
            @Nonnull final ThrowableBooleanFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before2,
            @Nonnull final ThrowableBooleanUnaryOperator<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1),
                                                        before2.applyAsBooleanThrows(value2),
                                                        before3.applyAsBooleanThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriByteConsumer} that first applies the {@code before} functions to
     * its input, and then applies this consumer to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code byte} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriByteConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * byte}.
     */
    @Nonnull
    default ThrowableTriByteConsumer<X> composeFromByte(
            @Nonnull final ThrowableByteFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableBytePredicate<? extends X> before2,
            @Nonnull final ThrowableBytePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriCharConsumer} that first applies the {@code before} functions to
     * its input, and then applies this consumer to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code char} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriCharConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * char}.
     */
    @Nonnull
    default ThrowableTriCharConsumer<X> composeFromChar(
            @Nonnull final ThrowableCharFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableCharPredicate<? extends X> before2,
            @Nonnull final ThrowableCharPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriDoubleConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code double} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriDoubleConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * double}.
     */
    @Nonnull
    default ThrowableTriDoubleConsumer<X> composeFromDouble(
            @Nonnull final ThrowableDoubleFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableDoublePredicate<? extends X> before2,
            @Nonnull final ThrowableDoublePredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriFloatConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code float} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriFloatConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * float}.
     */
    @Nonnull
    default ThrowableTriFloatConsumer<X> composeFromFloat(
            @Nonnull final ThrowableFloatFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableFloatPredicate<? extends X> before2,
            @Nonnull final ThrowableFloatPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriIntConsumer} that first applies the {@code before} functions to
     * its input, and then applies this consumer to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code int} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriIntConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * int}.
     */
    @Nonnull
    default ThrowableTriIntConsumer<X> composeFromInt(
            @Nonnull final ThrowableIntFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableIntPredicate<? extends X> before2,
            @Nonnull final ThrowableIntPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriLongConsumer} that first applies the {@code before} functions to
     * its input, and then applies this consumer to the result.
     * This method is just convenience, to provide the ability to execute an operation which accepts {@code long} input,
     * before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriLongConsumer} that first applies the {@code before} functions to its input,
     * and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * long}.
     */
    @Nonnull
    default ThrowableTriLongConsumer<X> composeFromLong(
            @Nonnull final ThrowableLongFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableLongPredicate<? extends X> before2,
            @Nonnull final ThrowableLongPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableTriShortConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result. This method is just convenience, to provide the ability to
     * execute an operation which accepts {@code short} input, before this primitive consumer is executed.
     *
     * @param before1 The first function to apply before this consumer is applied
     * @param before2 The second predicate to apply before this consumer is applied
     * @param before3 The third predicate to apply before this consumer is applied
     * @return A composed {@code ThrowableTriShortConsumer} that first applies the {@code before} functions to its
     * input, and then applies this consumer to the result.
     * @throws NullPointerException If given argument is {@code null}
     * @implSpec The input argument of this method is a able to handle primitive values. In this case this is {@code
     * short}.
     */
    @Nonnull
    default ThrowableTriShortConsumer<X> composeFromShort(
            @Nonnull final ThrowableShortFunction<? extends T, ? extends X> before1,
            @Nonnull final ThrowableShortPredicate<? extends X> before2,
            @Nonnull final ThrowableShortPredicate<? extends X> before3) {
        Objects.requireNonNull(before1);
        Objects.requireNonNull(before2);
        Objects.requireNonNull(before3);
        return (value1, value2, value3) -> acceptThrows(before1.applyThrows(value1), before2.testThrows(value2),
                                                        before3.testThrows(value3));
    }

    /**
     * Returns a composed {@link ThrowableObjBiBooleanConsumer} that performs, in sequence, this consumer followed by
     * the {@code after} consumer. If performing this consumer throws an exception, the {@code after} consumer will not
     * be performed.
     *
     * @param after The consumer to apply after this consumer is applied
     * @return A composed {@link ThrowableObjBiBooleanConsumer} that performs, in sequence, this consumer followed by
     * the {@code after} consumer.
     * @throws NullPointerException If given argument is {@code null}
     */
    @Nonnull
    default ThrowableObjBiBooleanConsumer<T, X> andThen(
            @Nonnull final ThrowableObjBiBooleanConsumer<? super T, ? extends X> after) {
        Objects.requireNonNull(after);
        return (t, value1, value2) -> {
            acceptThrows(t, value1, value2);
            after.acceptThrows(t, value1, value2);
        };
    }

    /**
     * Returns a composed {@link ThrowableTriConsumer} which represents this {@link ThrowableObjBiBooleanConsumer}.
     * Thereby the primitive input argument for this consumer is autoboxed. This method provides the possibility to use
     * this {@code ThrowableObjBiBooleanConsumer} with methods provided by the {@code JDK}.
     *
     * @return A composed {@code ThrowableTriConsumer} which represents this {@code ThrowableObjBiBooleanConsumer}.
     */
    @Nonnull
    default ThrowableTriConsumer<T, Boolean, Boolean, X> boxed() {
        return this::acceptThrows;
    }

    /**
     * Returns a composed {@link ObjBiBooleanConsumer} that applies this consumer to its input and nests the thrown
     * {@link Throwable} from it. The {@code Throwable} is nested (wrapped) in a {@link
     * ThrownByFunctionalInterfaceException}, which is constructed from the thrown {@code Throwable}s message and the
     * thrown {@code Throwable} itself.
     *
     * @return A composed {@link ObjBiBooleanConsumer} that applies this consumer to its input and nests the thrown
     * {@code Throwable} from it.
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nestWith(Function)
     * @see ThrownByFunctionalInterfaceException
     */
    @Nonnull
    default ObjBiBooleanConsumer<T> nest() {
        return nestWith(throwable -> new ThrownByFunctionalInterfaceException(throwable.getMessage(), throwable));
    }

    /**
     * Returns a composed {@link ObjBiBooleanConsumer} that applies this consumer to its input and nests the thrown
     * {@link Throwable} from it using {@code mapper} operation. Thereby {@code mapper} may modify the thrown {@code
     * Throwable}, regarding its implementation, and returns it nested (wrapped) in a {@link RuntimeException}.
     *
     * @param mapper The operation to map the thrown {@code Throwable} to {@code RuntimeException}
     * @return A composed {@link ObjBiBooleanConsumer} that applies this consumer to its input and nests the thrown
     * {@code Throwable} from it using {@code mapper} operation.
     * @throws NullPointerException If given argument is {@code null}
     * @implNote If thrown {@code Throwable} is of type {@link Error} it is thrown as-is and thus not nested.
     * @see #nest()
     */
    @Nonnull
    default ObjBiBooleanConsumer<T> nestWith(
            @Nonnull final Function<? super Throwable, ? extends RuntimeException> mapper) {
        return recover(throwable -> {
            throw mapper.apply(throwable);
        });
    }

    /**
     * Returns a composed {@link ObjBiBooleanConsumer} that applies this consumer to its input and sneakily throws the
     * thrown {@link Throwable} from it, if it is not of type {@link RuntimeException} or {@link Error}. This means that
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
     *         final Class<?> clazz = sneakyThrowingFunctionalInterface("some illegal class name");
     *         // ... do something with clazz ...
     *     } catch(ClassNotFoundException e) {
     *         // ... do something with e ...
     *     }
     * }
     * }</pre>
     * In conclusion, this somewhat contentious ability should be used carefully, of course, with the advantages,
     * disadvantages and limitations described above kept in mind.
     *
     * @return A composed {@link ObjBiBooleanConsumer} that applies this consumer to its input and sneakily throws the
     * thrown {@link Throwable} from it, unless it is of type {@link RuntimeException} or {@link Error}.
     * @implNote If thrown {@link Throwable} is of type {@link RuntimeException} or {@link Error}, it is thrown as-is
     * and thus not sneakily thrown.
     */
    @Nonnull
    default ObjBiBooleanConsumer<T> sneakyThrow() {
        return (t, value1, value2) -> {
            try {
                this.acceptThrows(t, value1, value2);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable throwable) {
                throw ThrowableUtils.sneakyThrow(throwable);
            }
        };
    }

    /**
     * Returns a composed {@link ObjBiBooleanConsumer} that first applies this consumer to its input, and then applies
     * the {@code recover} operation if a {@link Throwable} is thrown from this one. The {@code recover} operation is
     * represented by a curried operation which is called with throwable information and same arguments of this
     * consumer.
     *
     * @param recover The operation to apply if this consumer throws a {@code Throwable}
     * @return A composed {@link ObjBiBooleanConsumer} that first applies this consumer to its input, and then applies
     * the {@code recover} operation if a {@code Throwable} is thrown from this one.
     * @throws NullPointerException If given argument or the returned enclosing consumer is {@code null}
     * @implSpec The implementation checks that the returned enclosing consumer from {@code recover} operation is not
     * {@code null}. If it is, then a {@link NullPointerException} with appropriate message is thrown.
     * @implNote If thrown {@code Throwable} is of type {@link Error}, it is thrown as-is and thus not passed to {@code
     * recover} operation.
     */
    @Nonnull
    default ObjBiBooleanConsumer<T> recover(
            @Nonnull final Function<? super Throwable, ? extends ObjBiBooleanConsumer<? super T>> recover) {
        Objects.requireNonNull(recover);
        return (t, value1, value2) -> {
            try {
                this.acceptThrows(t, value1, value2);
            } catch (Error e) {
                throw e;
            } catch (Throwable throwable) {
                final ObjBiBooleanConsumer<? super T> consumer = recover.apply(throwable);
                Objects.requireNonNull(consumer, () -> "recover returned null for " + throwable.getClass() + ": "
                        + throwable.getMessage());
                consumer.accept(t, value1, value2);
            }
        };
    }

}