# Derivative Calculator

## Support Syntax

`Expression` => `BlankItem` [`PlusOrMinus` `BlankItem`] `Term` `BlankItem` | `Expression` `PlusOrMinus` `BlankItem` `Term` `BlankItem`
`Term` => [`PlusOrMinus` `Whitespace`] `Factor` | `Term` `BlankItem` `*` `BlankItem` `Factor`
`Factor` => `VariableFactor` | `ConstantFactor` | `ExpressionFactor`
`VariableFactor` => `PowerFunction` | `Trigonometric`
`ConstantFactor` => `SignedInteger`
`ExpressionFactor` => `(` `Expression` `)`
`Trigonometric` => `sin` `BlankItem` `(` `BlankItem` `Factor` `BlankItem` `)` [`BlankItem` `Exponent`] | `cos`  `BlankItem` `(` `BlankItem` `Factor` `BlankItem` `)` [`BlankItem` `Exponent`]
`PowerFunction` => `x` [`BlankItem` `Exponent`]
`Exponent` => `**` [`BlankItem` `SignedInteger`]
`SignedInteger` => [`PlusOrMinus`] `IntegersThatAllowLeadingZeros`
`IntegersThatAllowLeadingZeros` => (`0`|`1`|`2`|...|`9`){`0`|`1`|`2`|...|`9`}
`Whitespace` => ` ` | `\t`
`BlankItem` => {`Whitespace`}
`PlusOrMinus` => `+` | `-`

## Support Derivative Method

$$
\begin{align}
f(x)&= c,&f'(x)&=0\\
f(x)&= x^n,&f'(x)&=nx^{n-1}\\
f(x)&=\sin x,&f'(x)&=\cos x\\
f(x)&=\cos x,&f'(x)&=-\sin x\\
\end{align}\\
[f(g(x))]'=f'(g(x))g'(x)\\
[f(x)g(x)]'=f'(x)g(x) + f(x)g'(x)
$$

*Note that if no input expression is provided, the supported formats will return an error message indicating `WRONG FORMAT!`*

## Example

`x**2 + sin(x)` => `(2*x+(1*cos((x))))`
