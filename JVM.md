# 一、JVM部分零碎知识

![image-20210726223237966](JVM.assets/image-20210726223237966.png)

灰色部分（java stack、Native Method Stack、Program counter register）线程私有，几乎不存在垃圾回收

黄色部分（Method area、head）**存在垃圾回收**

<img src="JVM.assets/image-20210726224438464.png" alt="image-20210726224438464" style="zoom:33%;" />



## 1、栈、堆

<img src="file:///Users/superking/Documents/project/examination/examination.assets/image-20210712210015091.png?lastModify=1627302516" alt="image-20210712210015091" style="zoom:33%;" />

> 堆（heap）：存储对象、实例，和数组
>
> 栈（stack）：虚拟机栈。用于存储局部变量表。局部变量表存放了编译期可以知道长度的各种基本数据类型（boolean、byte、short、char、int、float、long、double）、对象引用（reference类型，存储对象在堆内存的首地址）。方法执行完自动释放。
>
> 方法区（Method Area）用于存储已被虚拟机加载的类信息、常量、静态常量、即时编译器编译后的代码等数据。

例如Demo02 obj1 = new Demo02()，obj1在栈里面，new Demo02()在堆里面



## 1、GC是什么

GC主要发生在JVM体系结构图中的亮色部分（Method area、heap）

GC是指分代收集算法

- 在次数上频繁收集Young区的是Minor GC
- 在次数上较少收集Old区   Full GC
- 基本不动Perm区



### （1）什么是垃圾？

内存中已经不再被使用到的空间就是垃圾



### （2）如何判断一个对象是否被回收

两种方法，如下（关注GC root）

1、引用计数法

（参考后面的四大算法）

2、GC Root
	枚举根节点做可达性分析(根搜索路径)

<img src="JVM.assets/image-20210726233649885.png" alt="image-20210726233649885" style="zoom:33%;" />

<img src="JVM.assets/image-20210726234032792.png" alt="image-20210726234032792" style="zoom:33%;" />

问题：哪些节点可以作为GC ROOT

> 虚拟机栈（栈帧中的局部变量区，也叫做局部变量表）中引用的对象。
>
> 2、方法区中的类静态属性引用的对象。
>
> 3、方法区中常量引用的对象。
>
> 4、本地方法栈中JNI（Native方法）引用的对象

```java
public class GCRootDemo {
    private byte[] bytes = new byte[8 * 1024 * 1024 * 100];
//    private static GCRootDemo2 t2;
  //    private static final GCRootDemo3 t3;
    public static void m1(){
        GCRootDemo t = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
```

m1()方法在栈中，即符合第一条要求，称为引用对象，然后t1指向了 new GCRootDemo()，这就是对象可达。

t2，就符合第二条，类静态属性引用的对象。

t3，符合第三条，常量引用的对象。

线程里面的start()，就是native方法，符合第四条







## 2、GC 发生在JVM哪部分

> 发生在堆里，堆就是内存里面

![image-20210712225800870](file:///Users/superking/Documents/project/examination/examination.assets/image-20210712225800870.png?lastModify=1627302516)





## 3、GC 4大算法

### 1、引用计数法

有对象引用就+1、没对象用就-1，即有对象被引用就不回收

缺点：每次对象赋值均要维护计数器，且计数器本身也有一定的消耗，较难处理循环引用

JVM实现不采用这种方式了

### 2、复制算法（Copying）

年轻代中使用的是Minor GC，这种GC算法采用的是复制算法（Copying）

<img src="JVM.assets/image-20210726225543000.png" alt="image-20210726225543000" style="zoom:33%;" />

#### 原理

- 从根集合（GC Root）开始，通过Tracing从From中找到存活的对象，拷贝到To中。
- From、To交换身份，下次内存分类从To开始

#### 缺点

需要双倍空间

#### 优点

没有标记清除过程，效率高

没有内存碎片，可以实现快速内存分配（bump-the-pointer）

### 3、标记清除（Mark-Sweep）

老年代回收使用

![image-20210712225210793](file:///Users/superking/Documents/project/examination/examination.assets/image-20210712225210793.png?lastModify=1627302516)





### 4、标记压缩（Mark-Compact）

老年代回收使用

![image-20210712225320150](file:///Users/superking/Documents/project/examination/examination.assets/image-20210712225320150.png?lastModify=1627302516)

### 5、标记清除压缩（Mark-Sweep-Compact）

两种算法(Mark-Sweep和Mark-Compact)结合使用的，先进行标记清除，清除一些产生一些碎片后再进行压缩

老年代常用这种方式回收对象

## 4、老年代和新生代