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
- modifier, DrawScope
    Modifier.drawWithContent, Modifier.drawBehind, and Modifier.drawWithCache
    Canvas = Spacer(modifier.drawBehind(onDraw))
    drawWithCache<CacheDrawScope> 是 Modifier 的方法
    CacheDrawScope 中还有 onDrawContent, onDrawBehind()

- Coordinate system


Modifier.drawWithContent, Modifier.drawBehind 包含 DrawScope, DrawScope 有下列方法
画基本图形和基本的形变

below is DrawScopes
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
- Accessing Canvas object - to native canvas
    DrawScope.drawIntoCanvas()

> interface DrawScope : Density

> DrawScope 是用于绘制图形的 API, GraphicsLayerScope 是与图层变换和效果相关的 API. DrawScope 可以对不同的内容进行不同的变换, GraphicsLayerScope 的变换是应用于整体内容.


#### Graphics modifiers
- Drawing modifiers
    - Modifier.drawWithContent: Choose drawing order - 绘制顺序
        call drawContent() in it
        把 drawConent() 放在自定义绘制的内容上或下
    - Modifier.drawBehind: Drawing behind a composable
        Canvas:  a convenient wrapper around Modifier.drawBehind
    - Modifier.drawWithCache: Drawing and caching draw objects
        如果要 draw 中创建对象, 使用这个方法; 对象被cached, 只要大小不变, 对象不会重新创建
            drawWithCache 的接收者 CacheDrawScope 并没有绘制方法/变形方法;
            需要调用 onDrawBehind, onDrawContent 并在里面进行绘制
        or using remember, outside of the modifier
        或者自己使用 remember

TODO
- Graphics modifiers
    - Modifier.graphicsLayer
        通过属性来实现
        - scale, translate, rotation, Origin, 
        - clip and shape, alpha
            fun Modifier.clip(shape: Shape) = graphicsLayer(shape = shape, clip = true)
            clip() 与 clip = ture, shap=   可以拼接

    - Compositing strategy   // TODO
        - Auto (default), Offscreen, ModulateAlpha

> interface GraphicsLayerScope : Density


- Write contents of a composable to a bitmap
    - create a GraphicsLayer using rememberGraphicsLayer()
    - using drawWithContent() and graphicsLayer.record{}

- Custom drawing modifier
    - DrawModifier 
        重写 ContentDrawScope.draw()


#### Brush: gradients and shaders 渐变和着色器 - 渐变和image
it determines the color(s) that are drawn in the drawing area (i.e. a circle, square, path)
such as LinearGradient, RadialGradient or a plain SolidColor brush;
Brushes can be used with Modifier.background(), TextStyle, or DrawScope draw calls
- Gradient brushes
    - horizontalGradient(), verticalGradient(), linearGradient(), sweepGradient(), radialGradient()
    - colorStops, Change distribution of colors
    - TileMode, Repeat a pattern
    - Change brush Size, custom Brush
- Use an image as a brush
    - background, TextStyle, drawXX(shaderBrush)
- Advanced example: Custom brush
    - AGSL RuntimeShader brush  // TODO


#### Shapes in Compose 多边形以及多边形见的转换动画
graphics-shapes library: morphing between these polygon shapes. 
- Create polygons
    RoundedPolygon
- Morph shapes
    A Morph object is a new shape representing an animation between two polygonal shapes.
- Use polygon as clip
- Morph button on click
- Animate shape morphing infinitely
- Custom polygons



## Animation
参考 rengwuxian 的结构

### Quick guide to Animations in Compose

### Animation modifiers and composables
- Built-in animated composables
    - Animate appearance and disappearance with AnimatedVisibility
        visible
        enter
        exit
    - Animate based on target state with AnimatedContent
        对整个 content 做动画
    - Animate between two layouts with Crossfade
- Built-in animation modifiers
    - Animate composable size changes with animateContentSize
- List item animations


### Value-based animations
- Animate a single value with animate*AsState

- Animate multiple properties simultaneously with a transition
    - updateTransition, transition.animateXX
- Create an infinitely repeating animation with rememberInfiniteTransition
- Low-level animation APIs
    - Animatable: Coroutine-based single value animation
    - Animation: Manually controlled animation


### Animated vector images in Compose
- AnimatedVectorDrawable

### Advanced animation example: Gestures

### Customize animations - AnimationSpec
- Customize animations with the AnimationSpec parameter
    - Create physics-based animation with spring
    - Animate between start and end values with easing curve with tween
    - Animate to specific values at certain timings with keyframes
    - Animate between keyframes smoothly with keyframesWithSpline
    - Repeat an animation with repeatable
    - Repeat an animation infinitely with infiniteRepeatable
    - Immediately snap to end value with snap
- Set a custom easing function  -  Easing
- Animate custom data types by converting to and from AnimationVector


### Shared element transitions in Compose



## Touch and input

### Pointer input
#### Understand gestures
##### Different levels of abstraction
- Component support
    onClick = {}, Modifier.clickable {}
- Add specific gestures to arbitrary composables with modifiers
    - taps and presses
    - Modifier.horizontalScroll,
    - draggable, swipable
    - multi-touch gestures
- Add custom gesture to arbitrary composables with pointerInput modifier
    - Modifier.pointerInput() {}
##### Event dispatching and hit-testing
##### Event consumption
##### Event propagation

#### Tap and press

#### Scoll
#### Drag, swipe, and fling
#### Multi-touch gesture

### Keyboard input


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


