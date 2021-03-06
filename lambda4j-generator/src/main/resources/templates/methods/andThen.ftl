<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/types.ftl" as types>

<#-- Consumers or Runnables will get special macro, as they will only sequence lambda calls; all other lambdas will use normal macro -->
<#if LambdaUtils.isOfTypeConsumer(lambda) || LambdaUtils.isOfTypeRunnable(lambda)>
    <#-- search for correct input lambda which represents the same lambda as this one, but may be from jdk if such one exists -->
    <#assign inputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable, true)>
    <#-- print andThen method, only for consumers -->
    <@.namespace.andThenMethodOnlyConsumers inputLambda/>
<#else>
    <#-- search for function lambdas from global lambda return type as input lambdas input argument and only with object (generical) return -->
    <#assign inputLambda = LambdaUtils.searchByFirstInputAndReturnType(LambdaUtils.getFunctionType(), 1, lambda.returnType, Object, lambda.throwable, true)>
    <#-- search for correct lambda which gets global lambda inputs and returns object output, unless suppliers which do not have inputs -->
    <#if LambdaUtils.isOfTypeSupplier(lambda)>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(LambdaUtils.getSupplierType(), lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, Object, lambda.throwable, false)>
    <#else>
        <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(LambdaUtils.getFunctionType(), lambda.arity, lambda.firstInputType, lambda.secondInputType, lambda.thirdInputType, Object, lambda.throwable, false)>
    </#if>
    <#-- print andThen method -->
    <@.namespace.andThenMethod inputLambda outputLambda/>
</#if>

<#-- a helper macro to centralize andThen method and to avoid unnecessary indenting -->
<#macro andThenMethod inputLambda outputLambda>
/**
 * Returns a composed {@link ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies the
 * {@code after} ${inputLambda.type.simpleName} to the result.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 *
 * @param <S> The type of return value from the {@code after} ${inputLambda.type.simpleName}, and of the composed ${outputLambda.type.simpleName}
 * @param after The ${inputLambda.type.simpleName} to apply after this ${lambda.type.simpleName} is applied
 * @return A composed {@code ${outputLambda.name}} that first applies this ${lambda.type.simpleName} to its input, and then applies the
 * {@code after} ${inputLambda.type.simpleName} to the result.
<#include "../javadoc/throwsNullPointerException.ftl">
 * @implSpec The input argument of this method is able to return every type.
 */
${annotation.nonnull}
default <S> ${outputLambda.name}${types.buildGenericParameterTypeString(outputLambda, "", "", "", "S")} andThen(${annotation.nonnull} final ${inputLambda.name}${types.buildGenericParameterTypeStringWithErasure(inputLambda, lambda.returnType, "", "", "S")} after) {
    Objects.requireNonNull(after);
    return (${parameterNameString}) -> after.${inputLambda.method}(${lambda.method}(${parameterNameString}));
}
</#macro>

<#-- a helper macro to centralize andThen method and to avoid unnecessary indenting but only for consumers -->
<#macro andThenMethodOnlyConsumers inputLambda>
 /**
 * Returns a composed {@link ${lambda.name}} that performs, in sequence, this ${lambda.type.simpleName} followed by the {@code after}
 * ${inputLambda.type.simpleName}.
<#if !lambda.throwable>
 * If evaluation of either operation throws an exception, it is relayed to the caller of the composed operation.
</#if>
 * If performing this ${lambda.type.simpleName} throws an exception, the {@code after} ${inputLambda.type.simpleName} will not be performed.
 *
 * @param after The ${inputLambda.type.simpleName} to apply after this ${lambda.type.simpleName} is applied
 * @return A composed {@link ${lambda.name}} that performs, in sequence, this ${lambda.type.simpleName} followed by the {@code after}
 * ${inputLambda.type.simpleName}.
<#include "../javadoc/throwsNullPointerException.ftl">
 */
${annotation.nonnull}
default ${lambda.name}${genericParameterTypeString} andThen(${annotation.nonnull} final ${inputLambda.name}${genericParameterTypeStringWithErasure} after) {
    Objects.requireNonNull(after);
    return (${parameterNameString}) -> {
        ${lambda.method}(${parameterNameString});
        after.${lambda.method}(${parameterNameString});
    };
}
</#macro>
<#-- @formatter:on -->