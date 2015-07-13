/*
 * Copyright (c) 2015 Gridtec. All rights reserved.
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
package at.gridtec.lambda4j.function.throwable.comparator;

import at.gridtec.lambda4j.util.ThrowableUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.IntSupplier;

/**
 * This functional interface implements a {@link Comparator} which is able to throw any {@link Exception}.
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
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #compareThrows(Object, Object)}.
 *
 * @param <T> The type of the compared values
 * @apiNote This is a throwable JRE lambda.
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableComparator<T> extends Comparator<T> {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableComparator}. This is a convenience
     * method in case the given {@link ThrowableComparator} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableComparator} is returned
     * as-is.
     *
     * @param <T> The type of the compared values
     * @param lambda The {@code ThrowableComparator} which should be returned as-is.
     * @return The given {@code ThrowableComparator} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableComparator<T> wrap(final ThrowableComparator<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableComparator} from the given {@link Comparator}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param <T> The type of the compared values
     * @param lambda A {@code Comparator} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableComparator} from the given {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static <T> ThrowableComparator<T> from(final Comparator<T> lambda) {
        Objects.requireNonNull(lambda);
        return lambda::compare;
    }

    /**
     * The compare method for this {@link Comparator} which is able to throw any {@link Exception} type.
     *
     * @param o1 The first object to be compared
     * @param o2 The second object to be compared
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
     * than the second.
     * @throws Exception Any exception from this functions action
     */
    int compareThrows(T o1, T o2) throws Exception;

    /**
     * Overrides the {@link Comparator#compare(Object, Object)} method by using a redefinition as default method. It
     * calls the {@link #compareThrows(Object, Object)} method of this interface and catches the thrown {@link
     * Exception}s from it. If it is of type {@link RuntimeException}, the exception is rethrown. Other exception types
     * are sneakily thrown.
     *
     * @param o1 The first object to be compared
     * @param o2 The second object to be compared
     * @return A negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater
     * than the second.
     * @see at.gridtec.lambda4j.util.ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default int compare(T o1, T o2) {
        try {
            return compareThrows(o1, o2);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableComparator} that applies this {@code ThrowableComparator} to its input, and
     * if an error occurred, applies the given one. The exception from this {@code ThrowableComparator} is ignored.
     *
     * @param other A {@code ThrowableComparator} to be applied if this one fails
     * @return A composed {@code ThrowableComparator} that applies this {@code ThrowableComparator}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableComparator<T> orElse(final ThrowableComparator<? super T> other) {
        Objects.requireNonNull(other);
        return (o1, o2) -> {
            try {
                return compareThrows(o1, o2);
            } catch (Exception ignored) {
                return other.compareThrows(o1, o2);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableComparator} that applies this {@code ThrowableComparator} to its input, and
     * if an error occurred, throws the given {@link Exception}. The exception from this {@code ThrowableComparator} is
     * added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableComparator} that applies this {@code ThrowableComparator}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableComparator<T> orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return (o1, o2) -> {
            try {
                return compareThrows(o1, o2);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link Comparator} that applies this {@link ThrowableComparator} to its input, and if an
     * error occurred, applies the given {@code Comparator} representing a fallback. The exception from this {@code
     * ThrowableComparator} is ignored.
     *
     * @param fallback A {@code Comparator} to be applied if this one fails
     * @return A composed {@code Comparator} that applies this {@code ThrowableComparator}, and if an error occurred,
     * applies the given {@code Comparator}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default Comparator<T> fallbackTo(final Comparator<? super T> fallback) {
        Objects.requireNonNull(fallback);
        return (o1, o2) -> {
            try {
                return compareThrows(o1, o2);
            } catch (Exception ignored) {
                return fallback.compare(o1, o2);
            }
        };
    }

    /**
     * Returns a composed {@link Comparator} that applies this {@link ThrowableComparator} to its input, and if an
     * error occurred, returns the given value. The exception from this {@code ThrowableComparator} is ignored.
     *
     * @param value The value to be returned if this {@code ThrowableComparator} fails
     * @return A composed {@code Comparator} that applies this {@code ThrowableComparator}, and if an error occurred,
     * returns the given value.
     */
    default Comparator<T> orReturn(int value) {
        return (o1, o2) -> {
            try {
                return compareThrows(o1, o2);
            } catch (Exception ignored) {
                return value;
            }
        };
    }

    /**
     * Returns a composed {@link Comparator} that applies this {@link ThrowableComparator} to its input, and if an
     * error occurred, returns the supplied value from the given {@link IntSupplier}. The exception from this {@code
     * ThrowableComparator} is ignored.
     *
     * @param supplier A {@code Supplier} to return a supplied value if this {@code ThrowableComparator} fails
     * @return A composed {@code Comparator} that applies this {@code ThrowableComparator}, and if an error occurred,
     * the supplied value from the given {@code IntSupplier}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default Comparator<T> orReturn(final IntSupplier supplier) {
        Objects.requireNonNull(supplier);
        return (o1, o2) -> {
            try {
                return compareThrows(o1, o2);
            } catch (Exception ignored) {
                return supplier.getAsInt();
            }
        };
    }
}