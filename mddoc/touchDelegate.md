# 1. 前言
早些时候我发了一篇关于对话框动画的文章[全网独一份的对话框弹出样式](https://mp.weixin.qq.com/s/mjTf8ASW2MEBJPB5ZmaWCA)。
用户阅读量，分享量，阅读后关注量等数据比较好。与之前偏重Android源码解析的文章不同，那是一篇更偏实战的文章。美中不足的是，  
代码注释量很少，有读者反馈阅读体验不佳。如果这个问题也曾困扰过你，借此向您真诚道歉，由于行文仓促，没有投入更多精力，今后在文章润色上投入更多时间，  
争取为读者带来更好的阅读体验。如果您觉得我的文章写的还行，帮我点个在看，或者分享出去，您的支持是我最大的动力。  
如果您有好的建议，欢迎您在留言区留言。

# 2. 关于TouchDelegate

本文内容不仅限于TouchDelegate的简单使用，也会涉及到原理讲解，它的局限性以及解决方案。

工作中，可能会遇到这种情况

> 设计师："呀，你这个按钮点击太不灵敏了，能不能把点击区域放大一点？"
>
> 开发："我这个按钮大小，跟你设计稿上标注的一样大呀！要不然你把图片切大一点？"

修改按钮大小，当然可以扩大按钮的点击区域，在不修改按钮大小的前提下，能否做到扩大按钮的点击区域呢？  
当然可以，用TouchDelegate就能做到。

## 2.1 核心方法
1. View#setTouchDelegate(TouchDelegate delegate)
2. TouchDelegate(Rect bounds, View delegateView)

### View#setTouchDelegate(TouchDelegate delegate)
 ![](https://cdn.jsdelivr.net/gh/lizijin/bytestation@master/expandtouch/pic1.png)

