> 视频资料：https://www.bilibili.com/video/BV1Eb411P7bP?t=46

# 一、运算

```java
public class Demo01 {
    public static void main(String[] args) {
        int i = 1;
        i = i++;    // i = 1
        int j = i++;   // j=1
        int k = i + ++i * i++; // k=2+3*3 = 11  i=4
        System.out.println("i=" + i);
        System.out.println("j=" + j);
        System.out.println("k=" + k);
    }
}
```

结果

```
i=4
j=1
k=11
```

# 二、单例模式

> ```
> 在某个类或者整个系统中只能有一个实例对象可被获取和使用的代码模式
> ```

## 1、几个重点

- 只能有一个实例
  - 构造器私有化
- 它必须自己创建这个实例
  - 含有一个该类的静态变量来保存这个唯一的实例对象
- 它必须自行向整个系统提供这个实例
  - 对外提供获取该实例对象的方式（直接暴露、提供get方法）

## 2、几种常见形式

- 饿汉式：直接创建对象，不存在线程安全问题
  - 直接实例化饿汉式（简洁直观）
  - 枚举式（最简洁）
  - 静态代码块饿汉式（适合复杂实例化）
- 懒汉式：延迟创建对象
  - 线程不安全（适用于单线程）
  - 线程安全（适用于多线程）
  - 静态内部类形式（适用于多线程）

### 2.1 代码描述

#### 2.1.1 饿汉式

##### 直接实例化饿汉式

```java
/**
 * @description:
 * 饿汉式：直接创建，不管你需不需要
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton1 {
    public static final Singleton1 SINGLETON = new Singleton1();

    private Singleton1() {
    }
}
```

测试

```java
public class Test1 {
    public static void main(String[] args) {
        Singleton1 singleton1 = Singleton1.SINGLETON;
        System.out.println(singleton1);
    }
}
```

##### 枚举式（最简洁）

```java
/**
 * @description:
 * 枚举类型：表示该类型的对象是有限的几个，我们可以限定为一个，就成了单例
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public enum Singleton2 {
    SINGLETON
}
```

测试

```java
public class Test2 {
    public static void main(String[] args) {
        Singleton2 singleton2 = Singleton2.SINGLETON;
        System.out.println(singleton2);
    }
}
```

##### 静态代码块饿汉式（适合复杂实例化）

```java
public class Singleton3 {

    public static final Singleton3 SINGLETON;

    private String info;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Singleton3.class.getClassLoader().getResourceAsStream("singleton.properties"));
            SINGLETON = new Singleton3(properties.getProperty("info"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Singleton3(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
```

测试

```java
public class Test3 {
    public static void main(String[] args) {
        Singleton3 singleton3 = Singleton3.SINGLETON;
        System.out.println(singleton3.getInfo());
    }
}
```

#### 2.1.2 懒汉式

##### 线程不安全（适用于单线程）

```java
public class Singleton4 {
    private static Singleton4 singleton;

    private Singleton4() {

    }

    public static Singleton4 getSingleton() {
        if (singleton == null) {
            singleton = new Singleton4();
        }
        return singleton;
    }
}
```

**测试**

```java
// 简单测试
public class Test4 {
    public static void main(String[] args) {
        Singleton4 singleton = Singleton4.getSingleton();
        Singleton4 singleton2 = Singleton4.getSingleton();
        System.out.println(singleton);
        System.out.println(singleton2);
    }
}

结果 是同一个对象
com.supkingx.base.b_singleton.Singleton4@5cad8086
com.supkingx.base.b_singleton.Singleton4@5cad8086
```

```java
// 展示线程不安全
public class Singleton4 {
    private static Singleton4 singleton;

    private Singleton4() {

    }

    public static Singleton4 getSingleton() {
        if (singleton == null) {
            try {
                // 为了展示线程不安全，这是睡眠100ms
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            singleton = new Singleton4();
        }
        return singleton;
    }
}


public class Test4_1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton4> callable = new Callable<Singleton4>() {
            @Override
            public Singleton4 call() throws Exception {
                return Singleton4.getSingleton();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton4> submit1 = executorService.submit(callable);
        Future<Singleton4> submit2 = executorService.submit(callable);
        System.out.println(submit1.get());
        System.out.println(submit2.get());
    }
}

结果，不是同一个对象了
com.supkingx.base.b_singleton.Singleton4@5e2de80c
com.supkingx.base.b_singleton.Singleton4@1d44bcfa
```

##### 线程安全（适用于多线程）

```java
public class Singleton5 {
    private static Singleton5 singleton;

    private Singleton5() {

    }

    public static Singleton5 getSingleton() {
        // 外层的这个判断完全是为了性能考虑
        if (singleton == null) {
            // 与Singleton4线程不安全锁区别，加了synchronized后线程安全
            synchronized (Singleton5.class) {
                if (singleton == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    singleton = new Singleton5();
                }
            }
        }
        return singleton;
    }
}

// 测试
public class Test5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Singleton5> callable = new Callable<Singleton5>() {
            @Override
            public Singleton5 call() throws Exception {
                return Singleton5.getSingleton();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton5> submit1 = executorService.submit(callable);
        Future<Singleton5> submit2 = executorService.submit(callable);
        System.out.println(submit1.get());
        System.out.println(submit2.get());
    }
}

结果
com.supkingx.base.b_singleton.Singleton5@5e2de80c
com.supkingx.base.b_singleton.Singleton5@5e2de80c
```

##### 静态内部类形式（适用于多线程）

```java
/**
 * @description: 懒汉式
 * 在内部类被加载和初始化时，才创建SINGLETON实例对象
 * 静态内部类不会自动随着外部类的加载和初始化而初始化，它是要单独加载和初始化的。
 * 因为是在内部类加载和初始化时创建的，所以是线程安全的。
 * @Author: wangchao
 * @Date: 2021/7/11
 */
public class Singleton6 {
    private Singleton6() {
    }

    private static class Inner {
        private static final Singleton6 SINGLETON = new Singleton6();
    }

    private static Singleton6 getInstance() {
        return Inner.SINGLETON;
    }
}

// 测试
public class Test6 {
    public static void main(String[] args) {
        Singleton6 singleton = Singleton6.getInstance();
        System.out.println(singleton);
    }
}
```

### 总结

- 如果是饿汉式，枚举形式最简单
- 如果是懒汉式，静态内部类形式最简单



# 三、类初始化和实例初始化

## 1、 代码展示

```java
public class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(1)");
    }

    Father() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}
```

```java
public class Son extends Father{
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    Son() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
```

输出结果？为什么是这样子的 讲解：https://www.bilibili.com/video/BV1Eb411P7bP?p=3&spm_id_from=pageDriver

```
(5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
(9)(3)(2)(9)(8)(7)
```

调用顺序

> 静态方法->父类被调用的静态方法->父类的静态代码块-->子类被调用的静态方法-->子类的静态代码块-->子类被调用的非静态方法-->父类的非静态代码块-->父类构造方法-->父类中被子类重写的方法（在这里就是指父类中的 int i = test();）-->子类的非静态代码块-->子类的构造方法

为什么（9）出现了两次？

> 因为非静态方法前面其实有一个默认的对象this,this在构造器（或<init>）它表示的是正在创建的对象，因为此时是子类的test()运行，这里父类的i=test() 执行的是子类重写的test()方法

为什么第二次执行少了几个步骤

> 因为(5)(1)(10)(6)是静态方法，只会执行一次



## 2、类初始化过程

- 一个类要创建实例需要先加载并初始化该类
  - main方法所在的类需要先加载和初始化
- 一个子类要初始化的时候需要先初始化父类
- 一个类初始化就是要执行<clinit>()方法
  - <clinit>()方法由静态类变量显示赋值代码和静态代码块组成
  - **类 变量显示赋值代码和静态代码块从上到下顺序执行**
  - <clinit>()方法只执行一次
- 基本执行流程
  - 主要是按照代码顺序执行
    静态方法->父类被调用的静态方法->父类的静态代码块-->子类被调用的静态方法-->子类的静态代码块-->子类被调用的非静态方法-->父类的非静态代码块-->父类构造方法-->父类中被子类重写的方法（在这里就是指父类中的 int i = test();）-->子类的非静态代码块-->子类的构造方法

## 3、实例初始化过程

实例初始化就是执行<init>()方法

下面所指的实例对象可以理解为 A a = new A()，这就是创建一个实例对象

1、<init>() 方法可能重载有多个，有几个构造器就有几个<init>方法

2、<init>()方法由非静态实例变量显示赋值代码和非静态代码块、对应构造器代码组成

3、非静态实例变量显示赋值代码和非静态代码块 代码从上到下顺序执行，而对应构造器的代码最后执行

4、每次创建实例对象，调用对应构造器，执行的就是对应的<init>()方法

5、<init>方法的首行是super()或super（参数列表），即对应父类的<init>方法



## 4、方法的重写

override

- 哪些方法不可以被重写
  - final方法
  - 静态方法
  - pricate等子类中不可见的方法
- 对象的多态
  - 子类如果重写的父类的方法，那么通过子类调用的一定是子类重写过的代码
  - 非静态方法默认的调用对象是this
  - this对象在构造器或者说<init>方法中就是正在创建的对象

## 进阶要求：

- Override和overload的区别
- Override重写的要求？
  - 方法名
  - 形参列表
  - 返回值类型
  - 抛出异常列表
  - 修饰符
- 了解《JVM虚拟机规范》中关于<clinit>和<init>方法的说明，invokespecial指令。



# 四、方法的参数传递机制

> 方法的参数传递机制
>
> String、包装类等对象的不可变性

话不多说，先上代码，猜一猜哪些值变了

```java
public class Exam4 {
    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData my = new MyData();

        change(i, str, num, arr, my);

        System.out.println("i= " + i);
        System.out.println("str= " + str);
        System.out.println("num= " + num);
        System.out.println("arr= " + Arrays.toString(arr));
        System.out.println("my.a= " + my.a);

    }

    public static void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }
}

class MyData {
    int a = 10;

}
```

结果

```
i= 1
str= hello
num= 200
arr= [2, 2, 3, 4, 5]
my.a= 11
```

## 1、分析

<img src="examination.assets/image-20210711171018582.png" alt="image-20210711171018582" style="zoom:50%;" />

```java
 public static void change(int j, String s, Integer n, int[] a, MyData m) {
        j += 1;
        s += "world";
        n += 1;
        a[0] += 1;
        m.a += 1;
    }

 // 分析
j = 2 （这里的j已经是新的地址的，不是原来的i）
s = helloword(但是由于String类型是不可变的，所以会产生一个新的对象，指向新的引用)，已经不在是原来的str了
n = 201（Integer类型的值不可变，所以会产生一个新的对象，指向新的引用)，已经不在是原来的num了
a[0]= 2 (这里的数组还是之前的数组，改了原来的值) 
m.a = 11（这里的MyData还是之前的地址，改了原来的值）
```

<img src="examination.assets/image-20210711171714933.png" alt="image-20210711171714933" style="zoom:50%;" />

## 2、结论

**所以结论是 a[0]和m.a改变了，其他的都没变**

- 形参是基本数据类型
  - 传递数据值

- 实参是引用数据类型
  - 传递地址
  - 特殊类型：String、包装类等对象不可变

# 五、编程题

## 1、递归与循环迭代

1、有n步台阶，一次只能上1步或2步，共有几种走法？

（1）递归

设f(n)=x，表示有n个台阶时，有x中走法

- n=1，一步   f(1)=1
- n=2，一步一步 或者 直接两步    f(2)=2
- n=3，
  - 先到达f(1)，然后f(1)直接跨2步。 
  - 先到达f(2)，然后f(2)跨1步。        
  - 即 f(3) = f(1)+f(2)，表示，到f(1)的走法+到f(2)的走法
- n=4，
  - 先到达f(2)，然后从f(2)直接跨2步 
  - 先到达f(3)，然后f(3)跨1步             
  - 即 f(4) = f(2)+f(3)，表示到f(2)的走法+到f(3)的走法
- ........
- n=x
  - 先到达f(x-2)，然后从f(x-2)直接跨2步 
  - 先到达f(x-1)，然后f(x-1)直接跨1步    
  - 即f(x)=f(x-2)+f(x-1)，表示到f(x-2)的走法+到f(x-1)的走法

```java
public class Recursion {
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        System.out.println("共有走法 " + fun(40) + " 种");
        final long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - start) + "ms");
    }

    private static int fun(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        return fun(n - 2) + fun(n - 1);
    }
}

// 结果
共有走法 165580141 种
总共耗时303ms
```

（2）循环迭代

设f(n)=x，表示有n个台阶时，有x中走法

令 one保存最后走一步的方法，two保存最后走两步的方法

- n=1，一步   f(1)=1
- n=2，一步一步 或者 直接两步    f(2)=2
- n=3，
  - 先到达f(1)，然后f(1)直接跨2步。 
  - 先到达f(2)，然后f(2)跨1步。        
  - 即 f(3) = f(1)+f(2)，表示，到f(1)的走法+到f(2)的走法
    two=f(1)，one=f(2)
- n=4，
  - 先到达f(2)，然后从f(2)直接跨2步 
  - 先到达f(3)，然后f(3)跨1步             
  - 即 f(4) = f(2)+f(3)，表示到f(2)的走法+到f(3)的走法
    two=f(2)；one=f(3)
- ........
- n=x
  - 先到达f(x-2)，然后从f(x-2)直接跨2步 
  - 先到达f(x-1)，然后f(x-1)直接跨1步    
  - 即f(x)=f(x-2)+f(x-1)，表示到f(x-2)的走法+到f(x-1)的走法
    two=f(x-2)，one=f(x-1)

```java
public class LoopIterate {

    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        System.out.println("共有走法 " + loop(40) + " 种");
        final long end = System.currentTimeMillis();
        System.out.println("总共耗时" + (end - start) + "ms");
    }

    private static int loop(int n) {
        if (n < 1) {
            throw new IllegalArgumentException(n + "不能小于1");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        // 最后走一步的方法是f(2)，其有2种走法 即 初始化为走到第二级台阶的走法
        int one = 2;
        // 最后走两步的方法是f(1)，其有1种走法 即 初始化为走到第一级台阶的走法
        int two = 1;
        // 一共的走法
        int sum = 0;

        // 最后走1步 + 最后走2步的走法
        for (int i = 3; i <= n; i++) {
            sum = one + two;
            two = one;
            one = sum;
        }
        return sum;
    }
}

结果
共有走法 165580141 种
总共耗时0ms
```

**总结**

- 方法调用自身称其为递归，利用变量的原值推出新值称为迭代
- 递归
  - 优点：大问题转化为小问题，减少代码量，可读性好
  - 缺点：递归浪费空间，递归太深容易造成堆栈溢出。
- 迭代
  - 优点：代码运行效率好，因为时间只因循环次数增加而增加，而且没有额外的空间开销；
  - 缺点：代码可读性没有递归强，不够简洁
