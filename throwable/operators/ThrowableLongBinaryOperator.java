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
package at.gridtec.internals.lang.function.throwable.operators;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link LongBinaryOperator} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The
 * exception is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences:
 * <ol>
 * <li>If the calling code is to handle a thrown {@code Exception}, it MUST be declared in the methods
 * <em>throws</em> clause which uses this lambda. The compiler will not force you to add it.</li>
 * <li>If the calling code already handles a thrown {@code Exception}, it needs to be declared in the methods
 * <em>throws</em> clause which uses this lambda. If not the compiler prints an error that the corresponding {@code
 * try} block never throws the specific exception.</li>
 * <li>In any case, there is no way of explicitly catching the thrown {@code Exception} in the method which uses this
 * lambda. If you try, the compiler prints an error that the corresponding {@code try} block never throws the specific
 * exception.</li>
 * </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java
 * specification to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.BinaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableLongBinaryOperator extends LongBinaryOperator {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableLongBinaryOperator}. This is a
     * convenience method in case the given {@link ThrowableLongBinaryOperator} is ambiguous for the compiler. This
     * might happen for overloaded methods accepting different functional interfaces. The given {@code
     * ThrowableLongBinaryOperator} is returned as-is.
     *
     * @param lambda The {@code ThrowableLongBinaryOperator} which should be returned as-is.
     * @return The given {@code ThrowableLongBinaryOperator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongBinaryOperator wrap(final ThrowableLongBinaryOperator lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableLongBinaryOperator} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableLongBinaryOperator} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableLongBinaryOperator constant(long ret) {
        Objects.requireNonNull(ret);
        return (left, right) -> ret;
    }

    /**
     * The apply method for this {@link LongBinaryOperator} which is able to throw any {@link Exception} type.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @throws Exception Any exception from this functions action
     */
    long applyAsLongThrows(long left, long right) throws Exception;

    /**
     * Overrides the {@link LongBinaryOperator#applyAsLong(long, long)} method by using a redefinition as default
     * method. It calls the {@link #applyAsLongThrows(long, long)} method of this interface and catches the thrown
     * {@link Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other
     * throwable types are sneakily thrown.
     *
     * @param left The first argument for the operator
     * @param right The second argument for the operator
     * @return The return value from the operator.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default long applyAsLong(long left, long right) {
        try {
            return applyAsLongThrows(left, right);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableLongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator} to
     * its input, and if an error occurred, applies the given one. The exception from this {@code
     * ThrowableLongBinaryOperator} is ignored.
     *
     * @param other A {@code ThrowableLongBinaryOperator} to be applied if this one fails
     * @return A composed {@code ThrowableLongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and
     * if an error occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongBinaryOperator orElse(final ThrowableLongBinaryOperator other) {
        Objects.requireNonNull(other);
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception ignored) {
                return other.applyAsLongThrows(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator} to
     * its input, and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableLongBinaryOperator} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableLongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and
     * if an error occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableLongBinaryOperator orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link LongBinaryOperator} that applies this {@link ThrowableLongBinaryOperator} to its
     * input, and if an error occurred, applies the given {@code LongBinaryOperator} representing a fallback. The
     * exception from this {@code ThrowableLongBinaryOperator} is ignored.
     *
     * @param fallback A {@code LongBinaryOperator} to be applied if this one fails
     * @return A composed {@code LongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and if an
     * error occurred, applies the given {@code LongBinaryOperator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongBinaryOperator fallbackTo(final LongBinaryOperator fallback) {
        Objects.requireNonNull(fallback);
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception ignored) {
                return fallback.applyAsLong(left, right);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableLongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator} to
     * its input, additionally performing the provided action to the resulting value. This method exists mainly to
     * support debugging.
     *
     * @param action A {@link LongConsumer} to be applied additionally to this {@code ThrowableLongBinaryOperator}
     * @return A composed {@code ThrowableLongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator},
     * additionally performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableLongBinaryOperator peek(final LongConsumer action) {
        Objects.requireNonNull(action);
        return (left, right) -> {
            final long ret = applyAsLong(left, right);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link LongBinaryOperator} that applies this {@link ThrowableLongBinaryOperator} to its
     * input, and if an error occurred, returns the given value. The exception from this {@code
     * ThrowableLongBinaryOperator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableLongBinaryOperator} fails
     * @return A composed {@code LongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and if an
     * error occurred, returns the given value.
     */
    default LongBinaryOperator orReturn(long value) {
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link LongBinaryOperator} that applies this {@link ThrowableLongBinaryOperator} to its
     * input, and if an error occurred, returns the supplied value from the given {@link Supplier}. The exception from
     * this {@code ThrowableLongBinaryOperator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableLongBinaryOperator} fails
     * @return A composed {@code LongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and if an
     * error occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default LongBinaryOperator orReturn(final Supplier<? extends Long> supplier) {
        Objects.requireNonNull(supplier);
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }

    /**
     * Returns a composed {@link LongBinaryOperator} that applies this {@link ThrowableLongBinaryOperator} to its
     * input, and if an error occurred, returns the left value from this operator. The exception from this {@code
     * ThrowableLongBinaryOperator} is ignored.
     *
     * @return A composed {@code LongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and if an
     * error occurred, returns the left value from this operator.
     */
    default LongBinaryOperator orReturnLeft() {
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception ignored) {
                return left;
            }
        };
    }

    /**
     * Returns a composed {@link LongBinaryOperator} that applies this {@link ThrowableLongBinaryOperator} to its
     * input, and if an error occurred, returns the right value from this operator. The exception from this {@code
     * ThrowableLongBinaryOperator} is ignored.
     *
     * @return A composed {@code LongBinaryOperator} that applies this {@code ThrowableLongBinaryOperator}, and if an
     * error occurred, returns the right value from this operator.
     */
    default LongBinaryOperator orReturnRight() {
        return (left, right) -> {
            try {
                return applyAsLongThrows(left, right);
            } catch (Exception ignored) {
                return right;
            }
        };
    }
}
