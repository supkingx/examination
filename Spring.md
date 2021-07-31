# 一、Spring

## 1、Sping Bean作用域

> 默认情况下Sping只为每个在IOC容器里面声明的bean创建唯一一个实例，整个IOC容器范围内只能共享该实例，该作用域被称为singleton

- singleton 在SpingIOC容器中仅存在一个bean实例，Bean以单实例的方式存在
- prototype 每次调用getBean()时都会返回一个新的实例
- request 每次Http请求都会创建一个新的Bean，该作用域仅适用于WebApplicationContext环境
- session 同一个HTTP Session共享一个bean，不同的HTTP Session适用不同的Bean。该作用域仅适用于WebApplicationContext环境。

## 2、IOC 

> 控制反转，把创建对象的过程交给Spring进行管理









## 3、AOP

> 面向切面：不修改源代码进行功能的增强

### 优先级

- 前置通知 (@Before) 。
- 返回通知 (@AfterReturning) 。
- 异常通知 (@AfterThrowing) 。
- 后置通知 (@After)。
- 环绕通知 (@Around) :（优先级最高）

```java
@Aspect
@Component
public class SysTimeAspect {

 /**
  * 切入点
  */
 @Pointcut("bean(sysMenuServiceImpl)")
 public void doTime(){}

 @Before("doTime()")
 public void doBefore(JoinPoint jp){
  System.out.println("time doBefore()");
 }
 @After("doTime()")
 public void doAfter(){//类似于finally{}代码块
  System.out.println("time doAfter()");
 }
 /**核心业务正常结束时执行
  * 说明：假如有after，先执行after,再执行returning*/
 @AfterReturning("doTime()")
 public void doAfterReturning(){
  System.out.println("time doAfterReturning");
 }
 /**核心业务出现异常时执行
  * 说明：假如有after，先执行after,再执行Throwing*/
 @AfterThrowing("doTime()")
 public void doAfterThrowing(){
  System.out.println("time doAfterThrowing");
 }
 @Around("doTime()")
 public Object doAround(ProceedingJoinPoint jp)
   throws Throwable{
  System.out.println("doAround.before");
  try {
  Object obj=jp.proceed();
  return obj;
  }catch(Throwable e) {
  System.out.println("doAround.error-->"+e.getMessage());
  throw e;
  }finally {
  System.out.println("doAround.after");
  }
 }
}
```

![image-20210725163748073](file:///Users/superking/Documents/project/examination/examination.assets/image-20210725163748073.png?lastModify=1627179382)



![image-20210725163806831](file:///Users/superking/Documents/project/examination/examination.assets/image-20210725163806831.png?lastModify=1627179382)





总结

> around优先级最高，afterThrowing在after之后

### boot1和boot2对AOP顺序的影响

> boot1对应spring4，boot2对应spring5

![image-20210725170614051](file:///Users/superking/Documents/project/examination/examination.assets/image-20210725170614051.png?lastModify=1627179382)

![image-20210725170658608](file:///Users/superking/Documents/project/examination/examination.assets/image-20210725170658608.png?lastModify=1627179382)

spring4和spring5的AOP顺序对比结果

![image-20210725171103538](file:///Users/superking/Documents/project/examination/examination.assets/image-20210725171103538.png?lastModify=1627179382)

综上图中可以看到，spring5之后，@After的优先级被放到（@afterReturn和@AfterThrowing）之后了，@Around的环绕后通知被置到最后



## 4、TX（事务）





## 5、循环依赖

多个bea之间相互依赖，形成了一个闭环，构造器的循环依赖是无法解决的，spring的singleton支持框架自动解决循环依赖，而prototype不支持解决，

Spring内部是通过三级缓存来解决循环依赖的。（DefaultSingletonBeanFactory）

### 构造器注入的循环依赖

先创建连个互相依赖的类

```java
public class ServiceA{
    private ServiceB b;
    // 构造方法
    public ServiceA(ServiceB b){
       this.b = b;
    }
}

public class ServiceB{
    private ServiceA a;
    // 构造方法
    public ServiceB(ServiceA a){
       this.a = a;
    }
}
```

然后main函数中调用;发现该依赖无法解决

```java
new ServiceA(new ServiceB(new ServiceA(.......)))
```

### set方式注入

设置两个循环依赖的类

```java
public class ServiceBB{
    private ServiceAA aa;
    // set方法
    public void setServiceAA(ServiceAA serviceAA){
       this.aa = serviceAA;
    }
}

public class ServiceAA{
    private ServiceBB bb;
    // set方法
    public void setServiceBB(ServiceBB servicebb){
       this.bb = servicebb;
    }
}
```

然后main函数调用，set可以解决循环调用，因为new的时候是调用一个空的无参构造方法，此时并没有依赖。

```java
public class test{
   public static void main(String[] args){
     ServiceAA a = new ServiceAA();
     ServiceBB b = new ServiceBB();
     b.setServiceAA(a);
     a.setServiceBB(b);
   }
}
```

### Spring源码分析

org.springframework.beans.factory.support.DefaultSingletonBeanRegistry

所谓的三级缓存

```java
/** Cache of singleton objects: bean name to bean instance. */
// 一级
// 单例池：存放了已经经历了完整生命周期的bean对象
private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

/** Cache of singleton factories: bean name to ObjectFactory. */
// 三级 存放可以生成Bean的工厂
private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);

/** Cache of early singleton objects: bean name to bean instance. */
// 二级
// 存放早期暴露出来的bean对象，Bean的生命周期未结束（属性还没有填充完整）
private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);
```

