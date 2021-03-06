<#-- @formatter:off -->
<#import "../utils/helpers.ftl" as helpers>
<#import "../utils/tuple.ftl" as tuple>

<#-- parse only if lambda has arity greater than 2 and at least two generics -->
<#if (lambda.arity >= 2)  && !helpers.isPrimitive(lambda.firstInputType) && !helpers.isPrimitive(lambda.secondInputType)>
    <#-- if lambda has primitive input, then we need to decrement the lambdas arity; if lambdas arity is now 0, then we set it back to 1 -->
    <#-- else lambda has only generic input, then we set arity to 1 -->
    <#assign decrementedArity = (lambda.arity - 1)>
    <#assign arity = helpers.isPrimitiveLambdaInput(lambda)?then((decrementedArity == 0)?then(1, decrementedArity), 1)>
    <#-- search for correct output lambda of tupled method, which is a lambda with all inputs of global lambda and its return -->
    <#assign outputLambda = LambdaUtils.searchByInputTypesAndReturnType(lambda.type, arity, lambda.firstInputType, lambda.thirdInputType!lambda.secondInputType, lambda.thirdInputType, lambda.returnType, lambda.throwable, false)>
    <#-- if lambda does not have primitive return type, generate generic return for tupled method -->
    <#if !helpers.isPrimitive(outputLambda.returnType)>
        <#assign returnString = ", ${lambda.returnType}">
    </#if>
    <#-- if lambda is throwable, then append throwable type -->
    <#if outputLambda.throwable>
        <#assign throwableString = ", ${lambda.throwableType}"/>
    </#if>
    <#-- print tupled method -->
    <@.namespace.tupledMethod returnString!"" throwableString!"" />
</#if>

<#-- a helper macro to centralize tupled method and to avoid unnecessary indenting -->
<#macro tupledMethod returnString = "" throwableString = "">
/**
 * Returns a tupled version of this ${lambda.type.simpleName}.
 *
 * @return A tupled version of this ${lambda.type.simpleName}.
 */
${annotation.nonnull}
default ${outputLambda.name}<${tuple.printTuple()} ${returnString} ${throwableString}> tupled() {
    return this::${lambda.method};
}
</#macro>
<#-- @formatter:on -->