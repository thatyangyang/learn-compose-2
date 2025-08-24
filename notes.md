https://developer.android.com/develop/ui/compose/documentation

UI archetecture
## App layout
*custom Modifier and custom Layout*

### basics
parents measure before their children, but are sized and placed after their children.
measurement , being sized are different 

Compose achieves high performance by measuring children only once

### Modifiers
Order of modifiers matters

requiredSize 不考虑 parent 的限制

offset 不影响 measurement


#### Scope safety in Compose
只有直接 children 可以用
matchParentSize in Box  不影响 parent 的大小
fillMaxSize 

weight in Row and Column


#### Extracting and reusing modifiers
尤其动画等需要多次调用的场景
unscoped modifiers 可以用于所有
scoped modifiers 只能作用于直接的 children
    限定作用的方法不生效

Append
 infix fun then(other: Modifier): Modifier

### Constraints

#### Modifiers in the UI tree
For example, when you chain a clip and a size modifier, the clip modifier node wraps the size modifier node, which then wraps the Image layout node

To summarize:
- Modifiers wrap a single modifier or layout node.
- Layout nodes can lay out multiple child nodes.

#### Constraints in the layout phase
The layout phase follows a three-step algorithm to find each layout node's width, height, and x, y coordinate:

- Measure children: A node measures its children, if any.
- Decide own size: Based on those measurements, a node decides on its own size.
- Place children: Each child node is placed relative to a node's own position.

Type of constraints
- Bounded - a maximum and minimum width and height
- Unbounded - not constrained to any size
- Exact - an exact size requirement. The minimum and maximum bounds are set to the same value
- Combination - width, height respectly constrained type

#### Modifiers that affect constraints
size - preferred size of the content   adapt to match constraints or set constraints to x,y = exact type
requiredSize  -  override incoming constraints - Bounded
width,height -  a fixed width or height - = size, exact type
sizeIn - set exact minimum and maximum constraints for width and height - Bounded

clip does not change constraints

reported size to be perceived by parent
clip act by reported size

padding change the canvas size

### Create custom modifiers
两种方法:
- A modifier factory 
    - wrap existing APIs
    - concatenate with then

1. CompositionLocal 工厂方法 call site 的 local 值, 而不是被拼接的时候的
2. 工厂方法也是 Composable 方法，不能被提升到 out of Composable 

- A modifier element - Modifier.Node
    包含三部分
    - Modifier.Node - stateful - implementation that holds the logic and state of your modifier.
    - ModifierNodeElemen - stateless - that creates and updates modifier node instances.
    - An optional modifier factory as detailed above


#### Common situations using Modifier.Node
    class CircleNode(var color: Color) : DrawModifierNode, Modifier.Node()
    data class CircleElement(val color: Color) : ModifierNodeElement<CircleNode>()
        implement draw() -> drwaCircle()

##### Zero parameters
    class FixedPaddingNode : LayoutModifierNode, Modifier.Node()
    data object FixedPaddingElement : ModifierNodeElement<FixedPaddingNode>()
        implement measure()

##### Referencing composition locals
##### Animating modifier
##### Sharing state between modifiers using delegation
##### Opting out of node auto-invalidation
TODO

### List of modifiers
挨个试

### Pager


### Flow layouts


### Custom layouts
Laying out each node in the UI tree is a three step process. Each node must:
- Measure any children
- Decide its own size
- Place its children

#### methods:
- Use the layout modifier
- Create custom layouts


### Adaptive layouts
TODO

### Alignment lines
TODO

### Instinsic measurements

### ConstraintLayout
TODO


## Components

### App bars
TODO Navigation with Compose




## Theming

### Material Design 3 in Compose
- Color
    - Primary, Secondary, Tertiary, Neutral
    - isLight
    - Dynamic color
        make use of device wallpaper colors
- Typography
    display, headline, title, body, label(Large, Medium, Small)
- Shape
    extraSmall, small, medium, large, extraLarge

### Custom design systems in Compose

### Anatomy of a theme in Compose



## Text and typography

### Text
- style text
- style paragraph
- maxLine and overflow

### TextField
- Value-based -> State-based

### user interaction
- selectable
- clickable

### fonts, emoji, autofill...

## Images and graphics

### Image

Painter
- Bitmap
    BitmapPainter
- Vector
    VectorPainter

painterResource

#### customize an image
- Content scale
- Clip
- border
- aspect ratio
- Color filter
    - tint
    - color matrix
- blur

### customize painter

- Painter
    - DrawModifier
 The above custom Painter can also be implemented using a DrawModifier. If you need to influence measurement or layout, then you should use a Painter. If you are only expecting to render in the bounds you are given, then you should use a DrawModifier instead.
    - onDraw() { drawImage() }


#### Optimizing performance for images
- Only load the size of the bitmap you need
- Use vectors over bitmaps where possible
- Supply alternative resources for different screen sizes
- When using ImageBitmap, call prepareToDraw before drawing
- Prefer passing a Int DrawableRes or URL as parameters into your composable instead of Painter
- Don’t store a bitmap in memory longer than you need it
- Don’t package large images with your AAB/APK file


### Graphics
#### Overview
- modifier
    Modifier.drawWithContent, Modifier.drawBehind, and Modifier.drawWithCache
- DrawScope
    drawRect
- Coordinate system
- Basic transformation
    - scale
    - translate
    - rotate
    - inset
    - Multi transformation
- Common drawing operations
    - draw text
        measure text
    - draw image
    - Draw basic shapes
    - Draw path
- Accessing Canvas object


#### Brush: gradients and shaders


#### Shapes in Compose


## Animation

### Quick guide to Animations in Compose

### Animation modifiers and composables

### Value-based animations

### Animated vector images in Compose

### Advanced animation example: Gestures

### Customize animations - AnimationSpec

### Shared element transitions in Compose



## Touch and input

### Pointer

### Keyboard


### Focus

### User interactions

### Stylus input


### copy and paste

### Input compatibility on large screens





## Accessibility


## Performance

Layout Inspector
Composition tracing

#### Best practices
- Use remember
- Use lazy layout keys
- Use derivedStateOf
- Defer reads as long as possible
- Avoid backwards writes


## Style guidelines
- Default arguments
- Higher-order functions and lambda expressions
- Trailing lambdas
- Scopes and receivers
- Delegated properties
    by 
- Destructing data classes
- Singleton objects
    object class
- Type-safe builders and DSLs
- Kotlin coroutines


## UI testing

## Tools


## System capabilities
- Windown insets
- Cutouts
- Picture-in-picture
- Predictive back

## Create widgets


