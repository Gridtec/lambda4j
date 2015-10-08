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
package at.gridtec.lambda4j.function.primitives;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * This functional interface implements a {@link ByteFunction} which is able to throw any {@link Exception}.
 * <p>
 * The thrown {@link Exception} is sneakily thrown unless its a {@link RuntimeException}. This means that there is no
 * need to catch the thrown exception, nor to declare that you throw it using the <em>throws</em> keyword. The exception
 * is still thrown, but the Java compiler stops warning about it.
 * <p>
 * However, when using this throwing lambda, be aware of the following consequences: <ol> <li>If the calling code is to
 * handle a thrown {@code Exception}, it MUST be declared in the methods <em>throws</em> clause which uses this lambda.
 * The compiler will not force you to add it.</li> <li>If the calling code already handles a thrown {@code Exception},
 * it needs to be declared in the methods <em>throws</em> clause which uses this lambda. If not the compiler prints an
 * error that the corresponding {@code try} block never throws the specific exception.</li> <li>In any case, there is no
 * way of explicitly catching the thrown {@code Exception} in the method which uses this lambda. If you try, the
 * compiler prints an error that the corresponding {@code try} block never throws the specific exception.</li> </ol>
 * <p>
 * When the calling code never throws the specific exception that it declares, you should omit it. For example: {@code
 * new String(byteArr, "UTF-8") throws UnsupportedEncodingException}, but UTF-8 is guaranteed by the Java specification
 * to be always present. The exception should therefore be omitted.
 * <p>
 * Moreover, if no checked exception should be used at all or its use is inappropriate for any reasons, omit the
 * declaration in the <em>throws</em> clause. The checked exception will behave just like a normal <b>unchecked</b>
 * exception due to sneaky throwing.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyThrows(byte)}.
 *
 * @param <R> The type of return value from the function
 * @see java.util.function.Function
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableByteFunction<R> extends ByteFunction<R> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableByteFunction}. This is a convenience
     * method in case the given {@link ThrowableByteFunction} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableByteFunction} is returned
     * as-is.
     *
     * @param <R> The type of return value from the function
     * @param lambda The {@code ThrowableByteFunction} which should be returned as-is.
     * @return The given {@code ThrowableByteFunction} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ThrowableByteFunction<R> wrap(final ThrowableByteFunction<R> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableByteFunction} from the given {@link ByteFunction}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <R> The type of return value from the function
     * @param lambda A {@code ByteFunction} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableByteFunction} from the given {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <R> ThrowableByteFunction<R> from(final ByteFunction<R> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::apply;
    }

    /**
     * Creates a {@link ThrowableByteFunction} which always returns a given value.
     *
     * @param <R> The type of return value from the function
     * @param r The return value for the constant
     * @return A {@code ThrowableByteFunction} which always returns a given value.
     */
    static <R> ThrowableByteFunction<R> constant(R r) {
        return value -> r;
    }

    /**
     * The apply method for this {@link ByteFunction} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @throws Exception Any exception from this functions action
     */
    R applyThrows(byte value) throws Exception;

    /**
     * Overrides the {@link ByteFunction#apply(byte)} method by using a redefinition as default method. It calls the
     * {@link #applyThrows(byte)} method of this interface and catches the thrown {@link Exception}s from it. If it is
     * of type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param value The argument for the function
     * @return The return value from the function, which is its result.
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default R apply(byte value) {
        try {
            return applyThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableByteFunction} that applies this {@code ThrowableByteFunction} to its input,
     * and if an error occurred, applies the given one. The exception from this {@code ThrowableByteFunction} is
     * ignored.
     *
     * @param other A {@code ThrowableByteFunction} to be applied if this one fails
     * @return A composed {@code ThrowableByteFunction} that applies this {@code ThrowableByteFunction}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableByteFunction<R> orElse(final ThrowableByteFunction<? extends R> other) {
        Objects.requireNonNull(other);
        return value -> {
            try {
                return applyThrows(value);
            } catch (Exception ignored) {
                return other.applyThrows(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableByteFunction} that applies this {@code ThrowableByteFunction} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableByteFunction} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception as
     * described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableByteFunction} that applies this {@code ThrowableByteFunction}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableByteFunction<R> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return applyThrows(value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link ByteFunction} that applies this {@link ThrowableByteFunction} to its input, and if an
     * error occurred, applies the given {@code ByteFunction} representing a fallback. The exception from this {@code
     * ThrowableByteFunction} is ignored.
     *
     * @param fallback A {@code ByteFunction} to be applied if this one fails
     * @return A composed {@code ByteFunction} that applies this {@code ThrowableByteFunction}, and if an error
     * occurred, applies the given {@code ByteFunction}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ByteFunction<R> fallbackTo(final ByteFunction<? extends R> fallback) {
        Objects.requireNonNull(fallback);
        return value -> {
            try {
                return applyThrows(value);
            } catch (Exception ignored) {
                return fallback.apply(value);
            }
        };
    }

    /**
     * Returns a composed {@link ByteFunction} that applies this {@link ThrowableByteFunction} to its input, and if an
     * error occurred, returns the given value. The exception from this {@code ThrowableByteFunction} is ignored.
     *
     * @param retVal The value to be returned if this {@code ThrowableByteFunction} fails
     * @return A composed {@code ByteFunction} that applies this {@code ThrowableByteFunction}, and if an error
     * occurred, returns the given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ByteFunction<R> orReturn(final R retVal) {
        Objects.requireNonNull(retVal);
        return value -> {
            try {
                return applyThrows(value);
            } catch (Exception ignored) {
                return retVal;
            }
        };
    }

    /**
     * Returns a composed {@link ByteFunction} that applies this {@link ThrowableByteFunction} to its input, and if an
     * error occurred, returns the supplied value from the given {@link Supplier}. The exception from this {@code
     * ThrowableByteFunction} is ignore.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableByteFunction} fails
     * @return A composed {@code ByteFunction} that applies this {@code ThrowableByteFunction}, and if an error
     * occurred, the supplied value from the given {@code Supplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ByteFunction<R> orReturn(final Supplier<? extends R> supplier) {
        Objects.requireNonNull(supplier);
        return value -> {
            try {
                return applyThrows(value);
            } catch (Exception ignored) {
                return supplier.get();
            }
        };
    }
}