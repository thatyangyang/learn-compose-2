https://developer.android.com/develop/ui/compose/documentation

UI archetecture
****App layout****
***basics***
parents measure before their children, but are sized and placed after their children.
measurement , being sized are different 

Compose achieves high performance by measuring children only once

***Modifiers***
Order of modifiers matters

requiredSize 不考虑 parent 的限制

offset 不影响 measurement


**Scope safety in Compose**
只有直接 children 可以用
matchParentSize in Box  不影响 parent 的大小
fillMaxSize 

weight in Row and Column


**Extracting and reusing modifiers**
尤其动画等需要多次调用的场景
unscoped modifiers 可以用于所有
scoped modifiers 只能作用于直接的 children
    限定作用的方法不生效

Append
 infix fun then(other: Modifier): Modifier

***Constraints***


****Components****


Theming
Text and typography
Images and graphics
Animation
Accessibility
Touch and input
Performance
Style guidelines
UI testing
Tools
