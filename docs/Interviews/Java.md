## 一、java.util.concurrent-**Java 并发工具包**
#### 1.1 **Java5新增**

#### 1.2 **阻塞队列 BlockingQueue**

> java.util.concurrent 包里的 BlockingQueue 接口表示一个线程安放入和提取实例的队列。本小节我将给你演示如何使用这个 BlockingQueue。 
> 本节不会讨论如何在 Java 中实现一个你自己的 BlockingQueue。如果你对那个感兴趣，参考《 [Java 并发指南](http://tutorials.jenkov.com/java-concurrency/index.html)》 
>
> ## BlockingQueue 用法
>
> BlockingQueue 通常用于一个线程生产对象，而另外一个线程消费这些对象的场景。下图是对这个原理的阐述： 
>
>  ![blocking-queue](https://img-blog.csdn.net/20150302184203260?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvZGVmb25kcw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/Center) 
>  **一个线程往里边放，另外一个线程从里边取的一个 BlockingQueue。** 
> 一个线程将会持续生产新对象并将其插入到队列之中，直到队列达到它所能容纳的临界点。也就是说，它是有限的。如果该阻塞队列到达了其临界点，负责生产的线程将会在往里边插入新对象时发生阻塞。它会一直处于阻塞之中，直到负责消费的线程从队列中拿走一个对象。 
> 负责消费的线程将会一直从该阻塞队列中拿出对象。如果消费线程尝试去从一个空的队列中提取对象的话，这个消费线程将会处于阻塞之中，直到一个生产线程把一个对象丢进队列。 
>
> ## BlockingQueue 的方法
>
> BlockingQueue 具有 4 组不同的方法用于插入、移除以及对队列中的元素进行检查。如果请求的操作不能得到立即执行的话，每个方法的表现也不同。这些方法如下： 
>
> |      | 抛异常     | 特定值   | 阻塞    | 超时                        |
> | :--- | :--------- | :------- | :------ | :-------------------------- |
> | 插入 | add(o)     | offer(o) | put(o)  | offer(o, timeout, timeunit) |
> | 移除 | remove(o)  | poll(o)  | take(o) | poll(timeout, timeunit)     |
> | 检查 | element(o) | peek(o)  |         |                             |
>
> 
> 四组不同的行为方式解释： 
>
> 1. **抛异常**：如果试图的操作无法立即执行，抛一个异常。
> 2. **特定值**：如果试图的操作无法立即执行，返回一个特定的值(常常是 true / false)。
> 3. **阻塞**：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行。
> 4. **超时**：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行，但等待时间不会超过给定值。返回一个特定值以告知该操作是否成功(典型的是 true / false)。
>
> 无法向一个 BlockingQueue 中插入 null。如果你试图插入 null，BlockingQueue 将会抛出一个 NullPointerException。 
> 可以访问到 BlockingQueue 中的所有元素，而不仅仅是开始和结束的元素。比如说，你将一个对象放入队列之中以等待处理，但你的应用想要将其取消掉。那么你可以调用诸如 remove(o) 方法来将队列之中的特定对象进行移除。但是这么干效率并不高(译者注：基于队列的数据结构，获取除开始或结束位置的其他对象的效率不会太高)，因此你尽量不要用这一类的方法，除非你确实不得不那么做。 
>
> ## BlockingQueue 的实现
>
> BlockingQueue 是个接口，你需要使用它的实现之一来使用 BlockingQueue。java.util.concurrent 具有以下 BlockingQueue 接口的实现(Java 6)： 
>
> - [ArrayBlockingQueue](http://blog.csdn.net/defonds/article/details/44021605#t7)
> - [DelayQueue](http://blog.csdn.net/defonds/article/details/44021605#t8)
> - [LinkedBlockingQueue](http://blog.csdn.net/defonds/article/details/44021605#t9)
> - [PriorityBlockingQueue](http://blog.csdn.net/defonds/article/details/44021605#t10)
> - [SynchronousQueue](http://blog.csdn.net/defonds/article/details/44021605#t11)
>
> 
>
> ## Java 中使用 BlockingQueue 的例子
>
> 这里是一个 Java 中使用 BlockingQueue 的示例。本示例使用的是 BlockingQueue 接口的 ArrayBlockingQueue 实现。 
> 首先，BlockingQueueExample 类分别在两个独立的线程中启动了一个 Producer 和 一个 Consumer。Producer 向一个共享的 BlockingQueue 中注入字符串，而 Consumer 则会从中把它们拿出来。
>
> ```java
> public class BlockingQueueExample {
>     public static void main(String[] args) throws Exception {
>         BlockingQueue queue = new ArrayBlockingQueue(1024);
>         Producer producer = new Producer(queue);
>         Consumer consumer = new Consumer(queue);
>         new Thread(producer).start();
>         new Thread(consumer).start();
>         Thread.sleep(4000);
>     }
> }
> ```
>
> 以下是 Producer 类。注意它在每次 put() 调用时是如何休眠一秒钟的。这将导致 Consumer 在等待队列中对象的时候发生阻塞。 
>
> ```java
> public class Producer implements Runnable{
>     protected BlockingQueue queue = null;
>     public Producer(BlockingQueue queue) {
>         this.queue = queue;
>     }
>     public void run() {
>         try {
>             queue.put("1");
>             Thread.sleep(1000);
>             queue.put("2");
>             Thread.sleep(1000);
>             queue.put("3");
>         } catch (InterruptedException e) {
>             e.printStackTrace();
>         }
>     }
> }
> ```
>
> 以下是 Consumer 类。它只是把对象从队列中抽取出来，然后将它们打印到 System.out。
>
> ```java
> public class Consumer implements Runnable{
>     protected BlockingQueue queue = null;
>     public Consumer(BlockingQueue queue) {
>         this.queue = queue;
>     }
>     public void run() {
>         try {
>             System.out.println(queue.take());
>             System.out.println(queue.take());
>             System.out.println(queue.take());
>         } catch (InterruptedException e) {
>             e.printStackTrace();
>         }
>     }
> }
> ```
>
> 



#### 1.3 **数组阻塞队列 ArrayBlockingQueue**

#### 1.4 **延迟队列 DelayQueue**
#### 1.5 **链表阻塞队列 LinkedBlockingQueue**

#### 1.6 **具有优先级的阻塞队列 PriorityBlockingQueue**

#### 1.7 **同步队列 SynchronousQueue**

#### 1.8 **阻塞双端队列 BlockingDeque**

#### 1.9 **链表阻塞双端队列 LinkedBlockingDeque**

#### 1.10 **并发 Map(映射) ConcurrentMap**

#### 1.11 **并发导航映射 ConcurrentNavigableMap**

#### 1.12 **闭锁 CountDownLatch**

#### 1.13  **栅栏 CyclicBarrier**

#### 1.14 **交换机 Exchanger**

#### 1.15 **信号量 Semaphore**

#### 1.16 **执行器服务 ExecutorService**

#### 1.17  **线程池执行者 ThreadPoolExecutor**

#### 1.18 **定时执行者服务 ScheduledExecutorService**

#### 1.19 **使用 ForkJoinPool 进行分叉和合并**

#### 1.20 **锁 Lock**

#### 1.21 **读写锁 ReadWriteLock**

#### 1.22 **原子性布尔 AtomicBoolean**

#### 1.23 **原子性整型 AtomicInteger**

#### 1.24 **原子性长整型 AtomicLong**

#### 1.25 **原子性引用型 AtomicReference**

