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



## 5、如何查看本机的JVM

```shell
# Jps：查看当前机器的java运行进程
superking@wangchaodeMacBook-Pro examination % jps -l
18658 com.supkingx.base.l_jvm.gc.HelloGC
18693 jdk.jcmd/sun.tools.jps.Jps
3750 org.jetbrains.idea.maven.server.RemoteMavenServer36
2342 
16154 org.jetbrains.jps.cmdline.Launcher
18654 org.jetbrains.jps.cmdline.Launcher

# jinfo 查看当前进程的信息（是否打印GC细节）
superking@wangchaodeMacBook-Pro examination % jinfo -flag PrintGCDetails 18658
-XX:-PrintGCDetails

# jinfo 查看当前进程的信息（MetaspaceSize）
superking@wangchaodeMacBook-Pro examination % jinfo  -flag MetaspaceSize 19212
-XX:MetaspaceSize=21807104
```

## 6、java7和java8的区别

在java8中，永久代已经被删除，被一个叫元空间的区域所取代。元空间的本质和永久代类似。

元空间（java8）与永久代（java7）之间最大的区别在于：永久代使用的JVM的堆内存，但是java8以后的元空间并不是在虚拟机中，**而是使用本机的物理内存**。因此，默认情况下，元空间的大小仅受本地内存限制。类的元数据放入native memory，字符串池和类的静态变量放入java堆中，这样可以加载多少类的元数据就不再由MaxPermSize控制，而是由系统的实际可用空间来控制

## 9、GC/FullGC

利用参数 -XX:+PrintGCDetails 输出GC的详细信息

```java
// 此时的jvm参数配置为 -Xms20m -Xmx30m -XX:+PrintGCDetails
public class HelloGC {
    public static void main(String[] args) throws InterruptedException {
        // 人为制造GC
        byte[] bytes = new byte[50 * 1024 * 1024];
    }
}
```

输出GC信息

```
[GC (Allocation Failure) [PSYoungGen: 1545K->496K(6144K)] 1545K->528K(26624K), 0.0009955 secs] [Times: user=0.01 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [PSYoungGen: 496K->512K(6144K)] 528K->568K(26624K), 0.0006948 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[Full GC (Allocation Failure) [PSYoungGen: 512K->0K(6144K)] [ParOldGen: 56K->405K(13824K)] 568K->405K(19968K), [Metaspace: 2995K->2995K(1056768K)], 0.0043182 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
[GC (Allocation Failure) [PSYoungGen: 0K->0K(6144K)] 405K->405K(26624K), 0.0003394 secs] [Times: user=0.00 sys=0.01, real=0.00 secs] 
[Full GC (Allocation Failure) [PSYoungGen: 0K->0K(6144K)] [ParOldGen: 405K->388K(13824K)] 405K->388K(19968K), [Metaspace: 2995K->2995K(1056768K)], 0.0036971 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 6144K, used 281K [0x00000007bf600000, 0x00000007c0000000, 0x00000007c0000000)
  eden space 5632K, 5% used [0x00000007bf600000,0x00000007bf6467f8,0x00000007bfb80000)
  from space 512K, 0% used [0x00000007bfb80000,0x00000007bfb80000,0x00000007bfc00000)
  to   space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
 ParOldGen       total 20480K, used 388K [0x00000007be200000, 0x00000007bf600000, 0x00000007bf600000)
  object space 20480K, 1% used [0x00000007be200000,0x00000007be261030,0x00000007bf600000)
 Metaspace       used 3045K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 334K, capacity 388K, committed 512K, reserved 1048576K
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	at com.supkingx.base.l_jvm.gc.HelloGC.main(HelloGC.java:15)

```

解析

GC在新生区，FullGC 在养老区

GC日志解释

<img src="JVM.assets/image-20210727232626762.png" alt="image-20210727232626762" style="zoom:33%;" />

FUll GC 日志解释

<img src="JVM.assets/image-20210727233027277.png" alt="image-20210727233027277" style="zoom:50%;" />

<img src="JVM.assets/image-20210727233042945.png" alt="image-20210727233042945" style="zoom:33%;" />

选取一条Full GC思考

```
[Full GC (Allocation Failure) [PSYoungGen: 0K->0K(6144K)] [ParOldGen: 405K->388K(13824K)] 405K->388K(19968K), [Metaspace: 2995K->2995K(1056768K)], 0.0036971 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
```

可以看到[ParOldGen: 405K->388K(13824K)] 405K->388K(19968K), [Metaspace: 2995K->2995K(1056768K)]。老年代回收内存前后差距不大，Metaspace内存回收前后不变，说明GC扛不住了，老年代也不行了，所以报错了，java.lang.OutOfMemoryError: Java heap space





# 二、JVM参数

-Xms：初始堆空间

-Xmx：最大堆空间

-Xss：最初始的栈空间

## 1、标配参数

java -version    
java -help  
 java -showversion

## 2、X参数（了解）

-Xint   解释执行

-Xcomp   第一次使用就编译成本地代码

-Xmixed  混合模式

## 3、XX参数

### Boolean类型

#### 公式

```
	-XX:+(-)某个属性
	+：表示开启
	-：表示关闭
```

#### 举例

1、是否打印GC收集细节：

​		-XX:-PrintGCDetails （开启）
​	    -XX:+PrintGCDetails   （关闭）

2、是否使用串行垃圾回收器

​       -XX:-UseSerialGC（开启）

​	   -XX:+UseSerialGC（关闭）



### KV设值类型

#### 公式

```
-XX:属性key=属性值value
```

#### 举例

1、-XX:MetaspaceSize=128m 

2、-XX:MaxTenuringThreshold=15

​	young区到老年区，需要活过15次

3、-XX:SurvivorRatio=8

​		设置新生代中Eden 和 S0/S1空间的比例，默认-XX:SurvivorRatio=8，Eden:S0:S1 = 8:1:1
​	    假如：-XX:SurvivorRatio=4，那么Eden:S0:S1 = 4:1:1
​		即：SurvivorRatio值就是设置Eden区比例占多少，S0/S1相同

​		以下是-XX:SurvivorRatio=4的示例，很明显Eden的容量是from区和 to区的四倍

<img src="JVM.assets/image-20210731102846458.png" alt="image-20210731102846458" style="zoom:33%;" />



4、-XX:NewRatio=2

​		设置新生代与老年代在 堆结构中的占比，默认-XX:NewRatio=2，年轻代占整个堆的1/3
​		假如：-XX:NewRatio=4：新生代占1，老年代占4，年轻代占整个堆的1/5
​		NewRatio就是设置老年代的占比，剩下的1给新生代

​		下图就是-XX:NewRatio=4的示例

​		<img src="JVM.assets/image-20210731104618180.png" alt="image-20210731104618180" style="zoom:33%;" />

### 经典参数

-Xms：等价于 -XX:InitialHeapSize，初始堆空间、默认是物理内存的1/64

-Xmx：等价于 -XX:MaxHeapSize，最大堆空间、默认为物理内存的1/4

-Xss：等价于 -XX:ThreadStackSize，设置单个线程栈的大小，默认为512K~1024K

-Xmn：设置年轻代大小

-XX:MetaspaceSize



### 常用参数

```
-Xms10m -Xmx10m -Xss1024k -XX:MetaspaceSize=512m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
-Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8
```



## 4、盘点家底查看JVM默认值

1、查看默认初始值

java -XX:+PrintFlagsInitial -version 或 java -XX:+PrintFlagsInitial

<img src="JVM.assets/image-20210727214648237.png" alt="image-20210727214648237" style="zoom:33%;" />

=表示初始值

:= 表示修改之后的

调整参数：
java -XX:+PrintFlagsInitial +XX:MetaspaceSize=512m



2、打印命令行参数

-XX:+PrintCommandLineFlags

Java -XX:+PrintCommandLineFlags -version (参数+版本号)



# 三、引用

> 强、软、弱、虚引用分别是什么

## 强引用

当内存不足的时候，JVM开始垃圾回收，对于强引用的对象，就算出现了OOM也不会对该对象进行回收，死都不回收。强引用是我们最常见的普通对象引用，只要还有强引用指向一个对象，就能表明对象还活着，垃圾收集器不会碰这种对象。在JAVA中最常见的就是强引用，把一个对象赋值给另外一个引用变量，这个引用变量就是一个强引用。当一个对象被强引用变量引用时，它处于可达状态，它是不可能被GC的，即使该对象以后永远都不会被用到JVM，也不会回收。因此强引用时造成java内存泄漏（占用内存过大）的主要原因之一。

对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显示的将相应的强引用赋值为null，一般认为就是可以被GC的。（当然具体GC还要看JVM的策略）。

```java
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        System.gc();
        // o2依然有值，即使o1变为null了，但是o2是强引用。
        System.out.println(o2);
    }
}
```

## 软引用

需要用java.lang.ref.SoftReference类来实现，内存足够的情况下，不收你，内存不够了，就收你。软引用一般用在对内存敏感的程序中，比如高速缓存就用到了软引用，内存够用就保留，内存不够就回收！（MyBatis里的缓存用到过软引用），当自己做缓存的时候，也可以使用软引用，

1、代码示例，内存够用的场景，代码可见objectSoftReference不会被回收

```java
public class SoftReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1= null;
        System.gc();
        System.out.println(o1);
        System.out.println(objectSoftReference.get());
    }
}

输出
  
java.lang.Object@511d50c0
java.lang.Object@511d50c0
null
java.lang.Object@511d50c0
```

2、代码示例，内存不用够用的场景，发现OOM后，objectSoftReference被回收了

```java
/**
 * @description: 软引用（内存 不够用场景）
 * 配置小内存 -Xms10m -Xmx10m
 * 大对象new byte[30 * 1024 * 1024] 使其OOM
 */
public class SoftReferenceDemo2 {
    public static void main(String[] args) {
        Object o1 = new Object();
        SoftReference<Object> objectSoftReference = new SoftReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1= null;
        try{
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(o1);
            // 内存不够用，会被回收
            System.out.println(objectSoftReference.get());
        }
    }
}

输出
java.lang.Object@71bc1ae4
java.lang.Object@71bc1ae4
java.lang.OutOfMemoryError: Java heap space
	at com.supkingx.base.l_jvm.gc.SoftReferenceDemo2.main(SoftReferenceDemo2.java:21)
null
null
```

### 使用场景分析

<img src="JVM.assets/image-20210731122339235.png" alt="image-20210731122339235" style="zoom:50%;" />



## 弱引用

需要使用java.lang.WeakReference类来实现，它比软引用的生存期更短，对于只有弱引用的对象来说，只要垃圾回收机制一运行，不管JVM的内存空间是否足够，都会回收对象占用的内存。

代码如下，GC之后，objectSoftReference被回收

```java
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        WeakReference<Object> objectSoftReference = new WeakReference<>(o1);
        System.out.println(o1);
        System.out.println(objectSoftReference.get());

        o1 = null;
        System.gc();
        System.out.println(o1);
        // 内存不够用，会被回收
        System.out.println(objectSoftReference.get());
    }
}
```

### WeakHashMap

HashMap和WeakHashMap的比较

```java
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("=========================");
        myWeakHashMap();
    }

    private static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(2);
        String value = "HashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        // 正常输出
        System.out.println(map);
    }
    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";
        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        // 输出为空，一GC就回收
        System.out.println(map);
    }
}
```

**注意，当WeakHashMap中，Integer key = 1时，则不会被回收。因为此时key是常量，存储在方法区，即metaspace**

## 虚引用

需要用到包java.lang.ref.PhantomReference。

股顾名思义，虚引用就是形同虚设，与其他几种引用不同，虚引用并不会决定对象的生命周期。如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收，它不能单独使用也不能通过它访问对象，虚引用必须和引用队列（ReferenceQueue）联合使用。

虚引用 的主要作用是跟踪对象被垃圾回收的状态。仅提供了一种确保对象被finalize以后，做某些事情的机制。PhantomReference的get方法总是返回null，因此无法访问对应的引用对象。其意义在于说明一个对象已经进入finalization阶段，可以被gc回收，用来实现比finalization机制更灵活的回收操作。

换句话说，设置虚引用关联的唯一目的，就是在这个对象被GC的时候收到一个系统通知或者后续添加进一步的处理。
java技术允许使用finalize()方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。

1、以下是虚引用的展示，GC将要开始的时候，对象会被放入虚引用队列：

```java
public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o, referenceQueue);

        System.out.println(o);
        System.out.println(weakReference.get());
        // 弱引用
        System.out.println("gc之前的弱引用队列" + referenceQueue.poll());
        o = null;
        System.gc();
        Thread.sleep(1000);
        System.out.println("gc之后的对象" + o);
        System.out.println("gc之后的虚引用" + weakReference.get());
        System.out.println("gc之后的弱引用队列" + referenceQueue.poll());
    }
}

输出：
java.lang.Object@511d50c0
java.lang.Object@511d50c0
gc之前的弱引用队列null
  
gc之后的对象null
gc之后的虚引用null
gc之后的弱引用队列java.lang.ref.WeakReference@60e53b93
```

2、使用PhantomReference实现虚引用

```java
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1,referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("==========GC之后=======================");
        o1=null;
        System.gc();
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}

输出：
java.lang.Object@511d50c0
null
null
==========GC之后=======================
null
null
java.lang.ref.PhantomReference@60e53b93
```

