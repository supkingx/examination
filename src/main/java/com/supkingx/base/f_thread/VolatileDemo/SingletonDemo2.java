package com.supkingx.base.f_thread.VolatileDemo;

/**
 * @description:
 * @Author: wangchao
 * @Date: 2021/7/16
 */
public class SingletonDemo2 {
    private static SingletonDemo2 singletonDemo = null;

    private SingletonDemo2() {
        // 对象常见的时候，构造器执行
        System.out.println(Thread.currentThread().getName() + "-我是构造器!");
    }

    // DCL (double check lock双端检索机制)
    // 这种写法有风险（指令重排），在高并发下出现的概率可能是千万分之一
    /**
     * 至于为什么会出现上述所说的情况，是因为某一个线程执行到第一次检测，读取到的instance不为null时，instance的引用对象可能没有完成初始化。
     * instance=new SingletonDemo();可以分为以下三个步骤；
     * 1、Memory = allocate() // 分配对象内存空间
     * 2、instance(Memory) // 初始化对象
     * 3、instance = Memory；设置instance指向刚分配的内存地址，此时instance！=null
     * 上面的三步的2、3并没有数据依赖关系，所以当指令重排的时候，步骤会变成1、3、2，此时
     * 当A线程走完第一步时，此时B线程走到下面代码的if(null == singletonDemo)就是false，则会直接返回null
     * @return
     */
    public static SingletonDemo2 getSingletonDemo() {
        if (null == singletonDemo) {
            synchronized (SingletonDemo2.class) {
                // 作次判断是为了只创建一次
                if (null == singletonDemo) {
                    singletonDemo = new SingletonDemo2();
                }
            }
        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        // 单线程环境下，只会创建一个对象，只会执行一次构造器
//        System.out.println(SingletonDemo.getSingletonDemo() == SingletonDemo.getSingletonDemo());

        // 多线程情况下，一切皆有肯
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                SingletonDemo2.getSingletonDemo();
            }).start();
        }
    }
}
