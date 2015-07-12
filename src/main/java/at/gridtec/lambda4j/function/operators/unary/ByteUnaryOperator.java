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

package at.gridtec.lambda4j.function.operators.unary;

import java.util.function.UnaryOperator;

/**
 * Represents an operation on a single {@code byte}-valued operand that produces a {@code byte}-valued result. This is
 * the primitive type specialization of {@link UnaryOperator} for {@code byte}.
 * <p>
 * This is a {@link FunctionalInterface} whose functional method is {@link #applyAsByte(byte)}.
 *
 * @see java.util.function.UnaryOperator
 */
@SuppressWarnings("unused")
@FunctionalInterface
public interface ByteUnaryOperator {

    /**
     * Applies this operator to the given operand argument.
     *
     * @param operand The argument to the operator
     * @return The result of this operator.
     */
    byte applyAsByte(byte operand);
}