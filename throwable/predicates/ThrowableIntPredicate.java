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
package at.gridtec.internals.lang.function.throwable.predicates;

import at.gridtec.internals.lang.util.ThrowableUtils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntPredicate;

/**
 * This functional interface implements a {@link IntPredicate} which is able to throw any {@link Exception}.
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
 * This is a {@link FunctionalInterface} whose functional method is {@link #testThrows(int)}.
 *
 * @apiNote This is a throwable JRE lambda
 * @see java.util.function.Predicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ThrowableIntPredicate extends IntPredicate {

    /**
     * Implicitly casts, and therefore wraps a given lambda as {@link ThrowableIntPredicate}. This is a convenience
     * method in case the given {@link ThrowableIntPredicate} is ambiguous for the compiler. This might happen for
     * overloaded methods accepting different functional interfaces. The given {@code ThrowableIntPredicate} is
     * returned as-is.
     *
     * @param lambda The {@code ThrowableIntPredicate} which should be returned as-is.
     * @return The given {@code ThrowableIntPredicate} as-is.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntPredicate wrap(final ThrowableIntPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda;
    }

    /**
     * Creates a {@link ThrowableIntPredicate} from the given {@link IntPredicate}. This method is just convenience to
     * provide a mapping for the non-throwable/throwable instances of the corresponding functional interface.
     *
     * @param lambda A {@code IntPredicate} which should be mapped to its throwable counterpart
     * @return A {@code ThrowableIntPredicate} from the given {@code IntPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntPredicate from(final IntPredicate lambda) {
        Objects.requireNonNull(lambda);
        return lambda::test;
    }

    /**
     * Creates a {@link ThrowableIntPredicate} which always returns a given value.
     *
     * @param ret The return value for the constant
     * @return A {@code ThrowableIntPredicate} which always returns a given value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    static ThrowableIntPredicate constant(boolean ret) {
        Objects.requireNonNull(ret);
        return value -> ret;
    }

    /**
     * The test method for this {@link IntPredicate} which is able to throw any {@link Exception} type.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     * @throws Exception Any exception from this functions action
     */
    boolean testThrows(int value) throws Exception;

    /**
     * Overrides the {@link IntPredicate#test(int)} method by using a redefinition as default method. It calls the
     * {@link #testThrows(int)} method of this interface and catches the thrown {@link Exception}s from it. If it is of
     * type {@link RuntimeException}, the exception is rethrown. Other exception types are sneakily thrown.
     *
     * @param value The argument for the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}
     * @see ThrowableUtils#sneakyThrow(Throwable)
     */
    @Override
    default boolean test(int value) {
        try {
            return testThrows(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw ThrowableUtils.sneakyThrow(e);
        }
    }

    /**
     * Returns a composed {@link ThrowableIntPredicate} that applies this {@code ThrowableIntPredicate} to its
     * input, and if an error occurred, applies the given one. The exception from this {@code ThrowableIntPredicate}
     * is ignored.
     *
     * @param other A {@code ThrowableIntPredicate} to be applied if this one fails
     * @return A composed {@code ThrowableIntPredicate} that applies this {@code ThrowableIntPredicate}, and if an error
     * occurred, applies the given one.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableIntPredicate orElse(final ThrowableIntPredicate other) {
        Objects.requireNonNull(other);
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return other.testThrows(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableIntPredicate} that applies this {@code ThrowableIntPredicate} to its input,
     * and if an error occurred, throws the given {@link Exception}. The exception from this {@code
     * ThrowableIntPredicate} is added as suppressed to the given one.
     * <p>
     * The given exception must have a no arg constructor for reflection purposes. If not, then appropriate exception
     * as described in {@link Class#newInstance()} is thrown.
     *
     * @param <X> The type for the class extending {@code Exception}
     * @param clazz The exception class to throw if an error occurred
     * @return A composed {@code ThrowableIntPredicate} that applies this {@code ThrowableIntPredicate}, and if an error
     * occurred, throws the given {@code Exception}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default <X extends Exception> ThrowableIntPredicate orThrow(Class<X> clazz) {
        Objects.requireNonNull(clazz);
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception e) {
                X ex = clazz.newInstance();
                ex.addSuppressed(e);
                throw ThrowableUtils.sneakyThrow(ex);
            }
        };
    }

    /**
     * Returns a composed {@link IntPredicate} that applies this {@link ThrowableIntPredicate} to its input, and
     * if an error occurred, applies the given {@code IntPredicate} representing a fallback. The exception from this
     * {@code ThrowableIntPredicate} is ignored.
     *
     * @param fallback A {@code IntPredicate} to be applied if this one fails
     * @return A composed {@code IntPredicate} that applies this {@code ThrowableIntPredicate}, and if an error
     * occurred, applies the given {@code IntPredicate}.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default IntPredicate fallbackTo(final IntPredicate fallback) {
        Objects.requireNonNull(fallback);
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return fallback.test(value);
            }
        };
    }

    /**
     * Returns a composed {@link ThrowableIntPredicate} that applies this {@code ThrowableIntPredicate} to its input,
     * additionally performing the provided action to the resulting value. This method exists mainly to support
     * debugging.
     *
     * @param action A {@link Consumer} to be applied additionally to this {@code ThrowableIntPredicate}
     * @return A composed {@code ThrowableIntPredicate} that applies this {@code ThrowableIntPredicate}, additionally
     * performing the provided action to the resulting value.
     * @throws NullPointerException If the given argument is {@code null}
     */
    default ThrowableIntPredicate peek(final Consumer<? super Boolean> action) {
        Objects.requireNonNull(action);
        return value -> {
            final boolean ret = test(value);
            action.accept(ret);
            return ret;
        };
    }

    /**
     * Returns a composed {@link IntPredicate} that applies this {@link ThrowableIntPredicate} to its input, and
     * if an error occurred, returns {@code true}. The exception from this {@code ThrowableIntPredicate} is ignored.
     *
     * @return A composed {@code IntPredicate} that applies this {@code ThrowableIntPredicate}, and if an error
     * occurred, returns {@code true}.
     */
    default IntPredicate orReturnTrue() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return true;
            }
        };
    }

    /**
     * Returns a composed {@link IntPredicate} that applies this {@link ThrowableIntPredicate} to its input, and if an
     * error occurred, returns {@code false}. The exception from this {@code ThrowableIntPredicate} is ignored.
     *
     * @return A composed {@code IntPredicate} that applies this {@code ThrowableIntPredicate}, and if an error
     * occurred, returns {@code false}.
     */
    default IntPredicate orReturnFalse() {
        return value -> {
            try {
                return testThrows(value);
            } catch (Exception ignored) {
                return false;
            }
        };
    }
}
