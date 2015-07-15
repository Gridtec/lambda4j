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

package at.gridtec.lambda4j.predicates.primitives.tri;

import at.gridtec.lambda4j.predicates.TriPredicate;

/**
 * Represents a predicate (boolean-valued function) of three {@code double}-valued argument. This is the {@code
 * double}-consuming primitive type specialization of {@link TriPredicate}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #test(double, double, double)}.
 *
 * @see TriPredicate
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface DoubleTriPredicate {

    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param value1 The first argument to the predicate
     * @param value2 The second argument to the predicate
     * @param value3 The third argument to the predicate
     * @return {@code true} if the input argument matches the predicate, otherwise {@code false}.
     */
    boolean test(double value1, double value2, double value3);
}