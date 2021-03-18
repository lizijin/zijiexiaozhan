最近实现了一个从点击处弹出的对话框，最终效果如下：
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/rv/cachetouch.gif)

# 1. 接招
实现对话框功能可选择的技术有 Dialog、PopupWindow、Activity。它们都支持自定义弹出和消失动画。它们都能实现自定义动画。

|        | WindowAnimation |  Transition |ShareElement Transition |
| :---------: | :--: | :-----------: |:-----------: |
| Dialog     |  支持    | 不支持 |不支持|
| PopupWindow   |  支持  |   支持 |不支持|
| Activity |  支持  | 支持 |支持|

![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dialog/popuppic4.png)

WindowAnimation动画必须要在styles.xml中定义，不能通过代码动态设置windowEnterAnimation、windowExitAnimation。意味着windowAnimation一旦设置好了
动画是固定不变的，显然无法满足从点击处做弹出动画的功能。排除Dialog。Activity同时支持过渡动画和共享元素过渡动画。经过一番尝试，发现虽然Activity支持丰富
的过渡动画，但是并不适合完成该场景的功能，最终决定采用PopupWindow实现该功能。

# 2. 拆招

该动画的特征是：从手指点击处做放大动画同时将对话框的中心点平移到屏幕中心。核心代码如下
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dialog/popuppic3.png)


# 3. 解招

最终代码
![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/dialog/popuppic1.png)

# 4. 获取完整源码

关注微信公众号"字节小站"，回复"对话框"，获取完整源码