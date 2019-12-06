//package com.lyp.learn.base.threads.pk01;
package java.lang;

import sun.nio.ch.Interruptible;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.security.util.SecurityConstants;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * 线程是程序中执行的线程。 Java虚拟机允许应用程序同时执行多个执行线程。
 * 每个线程都有优先权。 具有较高优先级的线程优先于优先级较低的线程执行。
 * 每个线程可能也可能不会被标记为守护程序。
 * 当在某个线程中运行的代码创建一个新的Thread对象时，新线程的优先级最初设置为等于创建线程的优先级，
 * 并且当且仅当创建线程是守护进程时才是守护线程。
 *
 * 当Java虚拟机启动时，通常有一个非守护进程线程（通常调用某些指定类的名为main的方法）。
 * Java虚拟机将继续执行线程，直到发生以下任一情况：
 *    1.已经调用了Runtime类的exit方法，并且安全管理器已经允许进行退出操作。
 *    2. 所有不是守护进程线程的线程都已经死亡，无论是从调用返回到run方法还是抛出超出run方法的run 。
 *
 *
 * 创建一个新的执行线程有两种方法。
 *      一个是将一个类声明为Thread的子类。 这个子类应该重写run类的方法Thread 。
 *      然后可以分配并启动子类的实例。
 *      例如，计算大于规定值的素数的线程可以写成如下：
 *      -------------------------------------------------------
 *     class PrimeThread extends Thread {
 *         long minPrime;
 *         PrimeThread(long minPrime) {
 *             this.minPrime = minPrime;
 *         }
 *
 *         public void run() {
 *             // compute primes larger than minPrime
 *             ..........
 *         }
 *     }
 *     -------------------------------------------------------
 *   然后，以下代码将创建一个线程并启动它运行：
 *      PrimeThread p = new PrimeThread(143);
 *      p.start();
 *
 *   另一种方法来创建一个线程是声明实现类Runnable接口。
 *   那个类然后实现了run方法。 然后可以分配类的实例，在创建Thread时作为参数传递，并启动。
 *   这种其他风格的同一个例子如下所示：
 *    -----------------------------------------------------------------------------
 *      class PrimeRun implements Runnable {
 *          long minPrime;
 *          PrimeRun(long minPrime) {
 *              this.minPrime = minPrime;
 *          }
 *
 *          public void run() {
 *              // compute primes larger than minPrime
 *               . . .
 *          }
 *      }
 *
 *     ----------------------------------------------------------------------------
 *     然后，以下代码将创建一个线程并启动它运行：
 *      PrimeRun p = new PrimeRun(143);
 *      new Thread(p).start();
 *
 *  每个线程都有一个用于识别目的的名称。 多个线程可能具有相同的名称。
 *  如果在创建线程时未指定名称，则会为其生成一个新名称。
 *
 * 除非另有说明，否则将null参数传递给此类中的构造函数或方法将导致抛出NullPointerException 。
 *
 */
public
class Thread implements Runnable {
    /* Make sure registerNatives is the first thing <clinit> does. */
    private static native void registerNatives();
    static {
        registerNatives();
    }

    private volatile String name;
    private int            priority;
    private Thread         threadQ;
    private long           eetop;

    /* Whether or not to single_step this thread. */
    private boolean     single_step;

    /* Whether or not the thread is a daemon thread. */
    private boolean     daemon = false;

    /* JVM state */
    private boolean     stillborn = false;

    /* What will be run. */
    private Runnable target;

    /* The group of this thread */
    private ThreadGroup group;

    /* The context ClassLoader for this thread */
    private ClassLoader contextClassLoader;

    /* The inherited AccessControlContext of this thread */
    private AccessControlContext inheritedAccessControlContext;

    /* For autonumbering anonymous threads. */
    private static int threadInitNumber;
    private static synchronized int nextThreadNum() {
        return threadInitNumber++;
    }

    /* ThreadLocal values pertaining to this thread. This map is maintained
     * by the ThreadLocal class. */
    ThreadLocal.ThreadLocalMap threadLocals = null;

    /*
     * InheritableThreadLocal values pertaining to this thread. This map is
     * maintained by the InheritableThreadLocal class.
     */
    ThreadLocal.ThreadLocalMap inheritableThreadLocals = null;

    /*
     * The requested stack size for this thread, or 0 if the creator did
     * not specify a stack size.  It is up to the VM to do whatever it
     * likes with this number; some VMs will ignore it.
     */
    private long stackSize;

    /*
     * JVM-private state that persists after native thread termination.
     */
    private long nativeParkEventPointer;

    /*
     * Thread ID
     */
    private long tid;

    /* For generating thread ID */
    private static long threadSeqNumber;

    /* Java thread status for tools,
     * initialized to indicate thread 'not yet started'
     */

    private volatile int threadStatus = 0;


    private static synchronized long nextThreadID() {
        return ++threadSeqNumber;
    }

    /**
     * The argument supplied to the current call to
     * java.util.concurrent.locks.LockSupport.park.
     * Set by (private) java.util.concurrent.locks.LockSupport.setBlocker
     * Accessed using java.util.concurrent.locks.LockSupport.getBlocker
     */
    volatile Object parkBlocker;

    /* The object in which this thread is blocked in an interruptible I/O
     * operation, if any.  The blocker's interrupt method should be invoked
     * after setting this thread's interrupt status.
     */
    private volatile Interruptible blocker;
    private final Object blockerLock = new Object();

    /* Set the blocker field; invoked via sun.misc.SharedSecrets from java.nio code
     */
    void blockedOn(Interruptible b) {
        synchronized (blockerLock) {
            blocker = b;
        }
    }

    /**
     * 线程可以有的最小优先级
     */
    public final static int MIN_PRIORITY = 1;

    /**
     * 分配给线程的默认优先级。
     */
    public final static int NORM_PRIORITY = 5;

    /**
     * 线程可以拥有的最大优先级。
     */
    public final static int MAX_PRIORITY = 10;

    /**
     * 返回对当前正在执行的线程对象的引用。 
     */
    public static native Thread currentThread();

    /**
     * 对调度程序的一个暗示，即当前线程愿意让出当前使用的处理器。 调度程序可以自由地忽略这个提示。
     *
     * yield是一种启发式尝试，以改善否则会过度利用CPU的线程之间的相对进度。
     * 其使用应与详细的分析和基准相结合，以确保其具有预期的效果。
     *
     * 很少使用这种方法。它可能对调试或测试有用，可能有助于根据竞争条件重现错误。
     * 在设计并发控制结构（例如java.util.concurrent.locks包中的并行控制结构）时也可能有用。
     */
    public static native void yield();

    /**
     * 使当前正在执行的线程以指定的毫秒数暂停（暂时停止执行），
     * 具体取决于系统定时器和调度程序的精度和准确性。
     * 线程不会丢失任何监视器的所有权。
     * @param  millis  以毫秒为单位的睡眠时间长度
     * @throws  IllegalArgumentException 如果 millis值为负数
     * @throws  InterruptedException 如果任何线程中断当前线程。 当抛出此异常时，当前线程的中断状态将被清除。
     */
    public static native void sleep(long millis) throws InterruptedException;

    /**
     * 导致正在执行的线程以指定的毫秒数加上指定的纳秒数来暂停（临时停止执行），
     * 这取决于系统定时器和调度器的精度和准确性。
     * 线程不会丢失任何监视器的所有权。
     *
     * @param  millis 以毫秒为单位的睡眠时间长度
     * @param  nanos 0-999999额外的纳秒睡眠
     * @throws  IllegalArgumentException 如果值 millis是否定的，或的值 nanos不在范围 0-999999
     * @throws  InterruptedException  如果任何线程中断当前线程。 当抛出此异常时，当前线程的中断状态将被清除。
     */
    public static void sleep(long millis, int nanos)
            throws InterruptedException {
        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                    "nanosecond timeout value out of range");
        }

        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
            millis++;
        }

        sleep(millis);
    }

    /**
     * 初始化具有访问控制的线程
     */
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize) {
        init(g, target, name, stackSize, null, true);
    }

    /**
     * 初始化形成
     *
     * @param g 线程组
     * @param target 调用run方法的对象
     * @param name 新线程的名字
     * @param stackSize 新线程希望的堆大小。参数为0表示忽略此参数
     * @param acc  继承AccessControlContext的,或者 AccessController.getContext() 是null
     * @param inheritThreadLocals  true,继承初始化值从继承的thead-local 从线程构造器
     */
    private void init(ThreadGroup g, Runnable target, String name,
                      long stackSize, AccessControlContext acc,
                      boolean inheritThreadLocals) {
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        this.name = name;

        Thread parent = currentThread();
        SecurityManager security = System.getSecurityManager();
        if (g == null) {
            /* Determine if it's an applet or not */

            /* If there is a security manager, ask the security manager
               what to do. */
            if (security != null) {
                g = security.getThreadGroup();
            }

            /* If the security doesn't have a strong opinion of the matter
               use the parent thread group. */
            if (g == null) {
                g = parent.getThreadGroup();
            }
        }

        /* checkAccess regardless of whether or not threadgroup is
           explicitly passed in. */
        g.checkAccess();

        /*
         * Do we have the required permissions?
         */
        if (security != null) {
            if (isCCLOverridden(getClass())) {
                security.checkPermission(SUBCLASS_IMPLEMENTATION_PERMISSION);
            }
        }

        g.addUnstarted();

        this.group = g;
        /* 设置当前线程是否为守护线程，默认是和当前类的ThreadGroup设置相同。
         * 如果是守护线程的话，当前线程结束会随着主线程的退出而退出。
         * jvm退出的标识是，当前系统没有活跃的非守护线程。
         */
        this.daemon = parent.isDaemon();
        this.priority = parent.getPriority();
        if (security == null || isCCLOverridden(parent.getClass())) {
            this.contextClassLoader = parent.getContextClassLoader();
        } else {
            this.contextClassLoader = parent.contextClassLoader;
        }
        this.inheritedAccessControlContext =
                acc != null ? acc : AccessController.getContext();
        this.target = target;
        setPriority(priority);
        if (inheritThreadLocals && parent.inheritableThreadLocals != null) {
            this.inheritableThreadLocals =
                    ThreadLocal.createInheritedMap(parent.inheritableThreadLocals);
        }
        /* Stash the specified stack size in case the VM cares */
        this.stackSize = stackSize;

        /* Set thread ID */
        tid = nextThreadID();
    }

    /**
     * 将CloneNotSupportedException作为线程抛出无法有意义地克隆。 构造一个新的线程。
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    /**
     * 分配一个新的Thread对象。
     * 此构造具有相同的效果Thread (null, null, gname) ，其中gname是新生成的名字。
     * 自动生成的名称格式为"Thread-"+ n ，其中n为整数。
     */
    public Thread() {
        init(null, null, "Thread-" + nextThreadNum(), 0);
    }

    /**
     * 分配一个新的Thread对象。
     * 该构造函数具有与Thread (null, target, gname)相同的效果，其中gname是新生成的名称。
     * 自动生成的名称格式为"Thread-"+ n ，其中n为整数。
     *
     * @param  target 启动此线程时调用其run方法的对象。 如果null ，这个类run方法什么都不做。
     */
    public Thread(Runnable target) {
        init(null, target, "Thread-" + nextThreadNum(), 0);
    }

    /**
     * 创建一个新线程继承给定的AccessControlContext.
     * 这不是一个公开的构造器
     */
    Thread(Runnable target, AccessControlContext acc) {
        init(null, target, "Thread-" + nextThreadNum(), 0, acc, false);
    }

    /**
     * 分配一个新的Thread对象。
     * 此构造具有相同的效果Thread (group, target, gname) ，其中gname是新生成的名字。
     * 自动生成的名称格式为"Thread-"+ n ，其中n为整数。
     *
     * @param  group 线程组。
     *              如果是null并且有一个安全管理员，那么该组由SecurityManager.getThreadGroup()决定 。
     *              如果没有安全管理员或SecurityManager.getThreadGroup()返回null ，该组将设置为当前线程的线程组。
     *
     * @param  target 启动此线程时调用其run方法的对象。 如果null ，这个线程的run方法被调用。
     *
     * @throws  SecurityException  如果当前线程不能在指定的线程组中创建线程
     */
    public Thread(ThreadGroup group, Runnable target) {
        init(group, target, "Thread-" + nextThreadNum(), 0);
    }

    /**
     * 分配一个新的Thread对象。
     * 此构造具有相同的效果Thread (null, null, name) 。
     * @param   name 新线程的名称
     */
    public Thread(String name) {
        init(null, null, name, 0);
    }

    /**
     * 分配一个新的Thread对象。
     * 此构造具有相同的效果Thread (group, null, name) 。
     *
     * @param  group - 线程组。
     *               如果是null并且有一个安全管理器，则该组由SecurityManager.getThreadGroup()决定 。
     *               如果没有安全管理员或SecurityManager.getThreadGroup()返回null ，则该组将设置为当前线程的线程组。
     * @param  name 新线程的名称
     *
     * @throws  SecurityException 如果当前线程无法在指定的线程组中创建线程
     */
    public Thread(ThreadGroup group, String name) {
        init(group, null, name, 0);
    }

    public Thread(Runnable target, String name) {
        init(null, target, name, 0);
    }

    public Thread(ThreadGroup group, Runnable target, String name) {
        init(group, target, name, 0);
    }

    /**
     * 分配一个新的Thread对象，
     * 以便它具有target作为其运行对象，将指定的name正如其名，
     * 以及属于该线程组由称作group ，并具有指定的堆栈大小 。
     *
     * 这个构造函数与Thread(ThreadGroup,Runnable,String)相同，除了它允许指定线程栈大小的事实之外。
     * 堆栈大小是虚拟机为该线程的堆栈分配的大致的地址空间字节数。 stackSize参数的影响（如果有的话）与平台有关。
     *
     * 在某些平台上，指定了一个较高的值stackSize参数可以允许抛出一个前一个线程来实现更大的递归深度StackOverflowError
     * 类似地，指定较低的值可能允许更多数量的线程同时存在，而不会抛出OutOfMemoryError （或其他内部错误）。
     * 所述stackSize参数的值和最大递归深度和并发水平之间的关系的细节是依赖于平台的。
     * 在某些平台上，该值stackSize参数可能没有任何效果。
     *
     * 虚拟机可以自由地对待stackSize参数作为建议。
     * 如果平台的指定值不合理地低，虚拟机可能会改为使用一些平台特定的最小值;
     * 如果指定的值不合理地高，虚拟机可能会使用一些平台特定的最大值。
     * 同样，虚拟机可以自由地按照合适的方式向上或向下舍入指定的值（或完全忽略它）。
     *
     * 对于指定的值为零stackSize参数将使这种构造的行为酷似Thread(ThreadGroup, Runnable, String)构造。
     *
     * 由于此构造函数的行为依赖于平台依赖性质，因此在使用时应特别小心。
     * 执行给定计算所需的线程栈大小可能会因JRE实现而异。
     * 鉴于这种变化，可能需要仔细调整堆栈大小参数，并且可能需要对要运行应用程序的每个JRE实现重复调整。
     *
     * 实现注意事项：鼓励Java平台实现者的记录其实施的行为stackSize参数。
     *
     * @param  group 线程组。
     *              如果null并且有一个安全管理器，该组由SecurityManager.getThreadGroup()确定 。
     *              如果没有安全管理员或SecurityManager.getThreadGroup()返回null ，该组将设置为当前线程的线程组
     * @param  target 启动此线程时调用其run方法的对象。 如果null ，这个线程的run方法被调用
     * @param  name 新线程的名称
     * @param  stackSize 新线程所需的堆栈大小，或为零表示此参数将被忽略
     * @throws  SecurityException 如果当前线程无法在指定线程组中创建线程
      */
    public Thread(ThreadGroup group, Runnable target, String name,
                  long stackSize) {
        init(group, target, name, stackSize);
    }

    /**
     * 导致此线程开始执行; Java虚拟机调用此线程的run方法。
     * 结果是两个线程同时运行：当前线程（从调用返回到start方法）和另一个线程（执行其run方法）。
     *
     * 不止一次启动线程是不合法的。
     * 特别地，一旦线程完成执行就可能不会重新启动
     *
     * @exception  IllegalThreadStateException  如果线程已经启动
     */
    public synchronized void start() {
        /**
         *  0 表示线程NEW状态
         *  如果线程不是"就绪状态"，则抛出异常！
         */
        if (threadStatus != 0) {
            throw new IllegalThreadStateException();
        }

        /* 将线程添加到ThreadGroup中
        通知线程组此线程启动，所以可以加到此线程的线程组，此线程组中未启动的数量减一
         */
        group.add(this);

        boolean started = false;
        try {
            //通过native 方法 start0()启动线程
            //说明：start()实际上是通过本地方法start0()启动线程的。
            // 而start0()会新运行一个线程，新线程会调用run()方法。
            start0();
            // 设置started标记
            started = true;
        } finally {
            try {
                if (!started) {
                    group.threadStartFailed(this);
                }
            } catch (Throwable ignore) {
                /* do nothing. If start0 threw a Throwable then
                  it will be passed up the call stack */
            }
        }
    }

    private native void start0();

    /**
     * 如果这个线程是使用单独的Runnable运行对象构造的，则Runnable对象的run方法; 否则，此方法不执行任何操作并返回。
     * Thread的Thread应该覆盖此方法。
     *
     * 说明：target是一个Runnable对象。
     * run()就是直接调用Thread线程的Runnable成员的run()方法，并不会新建一个线程。
     */
    @Override
    public void run() {
        if (target != null) {
            target.run();
        }
    }

    /**
     * 在真正退出之前，系统调用此方法给线程一个机会去清除一些资源
     */
    private void exit() {
        if (group != null) {
            group.threadTerminated(this);
            group = null;
        }
        /* Aggressively null out all reference fields: see bug 4006245 */
        target = null;
        /* Speed the release of some of these resources */
        threadLocals = null;
        inheritableThreadLocals = null;
        inheritedAccessControlContext = null;
        blocker = null;
        uncaughtExceptionHandler = null;
    }

    /**
     * 强制线程停止执行。
     * 如果安装了一个安全管理器，它的checkAccess方法this作为参数。 这可能导致SecurityException被提升（在当前线程中）。
     *
     * 如果此线程与当前线程不同（即当前线程正试图停止除本身线程之外的线程），
     * 则另外还调用安全管理器的checkPermission方法（具有RuntimePermission("stopThread")参数）。
     * 再次，这可能会导致抛出SecurityException （在当前线程中）。
     *
     * 由该线程表示的线程被强制停止，它正在异常进行，并抛出一个新创建的ThreadDeath对象作为例外。
     *
     * 允许停止尚未启动的线程。 如果线程最终启动，它将立即终止。
     *
     * 一个应用程序通常不应该尝试捕获ThreadDeath ，
     * 除非它必须做一些非凡的清理操作（请注意，抛出ThreadDeath导致finally语句try语句在线程正式死亡之前执行）。
     * 如果一个catch子句捕获一个ThreadDeath对象，重要的是重新抛出该对象，使线程实际上死亡。
     *
     * 该反应否则捕获的异常不打印出消息，或者如果未捕获的异常是一个实例，否则通知应用程序的顶级错误处理程序ThreadDeath 。
     *
     * @exception  SecurityException  如果当前线程不能修改此线程
     * @deprecated 已弃用
     *              这种方法本质上是不安全的。
     *              使用Thread.stop停止线程可以解锁所有已锁定的监视器
     *              （由于未ThreadDeath ThreadDeath异常在堆栈中ThreadDeath的自然结果）。
     *
     *              如果先前受这些监视器保护的任何对象处于不一致的状态，则损坏的对象将变得对其他线程可见，可能导致任意行为。
     *              stop许多用途应该被代替，只需修改一些变量来指示目标线程应该停止运行。
     *              目标线程应该定期检查此变量，如果变量表示要停止运行，则以有序方式从其运行方法返回。
     *              如果目标线程长时间等待（例如，在interrupt变量上），则应该使用interrupt方法来中断等待。
     *              有关详细信息，请参阅Why are Thread.stop, Thread.suspend and Thread.resume Deprecated? 。
     */
    @Deprecated
    public final void stop() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            checkAccess();
            if (this != Thread.currentThread()) {
                security.checkPermission(SecurityConstants.STOP_THREAD_PERMISSION);
            }
        }
        // A zero status value corresponds to "NEW", it can't change to
        // not-NEW because we hold the lock.
        if (threadStatus != 0) {
            resume(); // Wake up thread if it was suspended; no-op otherwise
        }

        // The VM can handle all thread states
        stop0(new ThreadDeath());
    }

    /**
     * 抛出 UnsupportedOperationException
     *
     * @deprecated 已弃用
     *            该方法最初设计为强制线程停止并抛出一个给定的Throwable作为例外。
     *            它本质上是不安全的（详见stop() ），此外还可用于生成目标线程未准备处理的异常。
     *            有关详细信息，请参阅Why are Thread.stop, Thread.suspend and Thread.resume Deprecated? 。
     */
    @Deprecated
    public final synchronized void stop(Throwable obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * interrupt()的作用是中断本线程。
     * 本线程中断自己是被允许的；
     * 其它线程调用本线程的interrupt()方法时，会通过checkAccess()检查权限。这有可能抛出SecurityException异常。
     *
     * 如果本线程是处于阻塞状态：
     * 调用线程的wait(), wait(long)或wait(long, int)会让它进入等待(阻塞)状态，
     * 或者调用线程的join(), join(long), join(long, int), sleep(long), sleep(long, int)也会让它进入阻塞状态。
     * 若线程在阻塞状态时，调用了它的interrupt()方法，那么它的“中断状态”会被清除并且会收到一个InterruptedException异常。
     * 例如，线程通过wait()进入阻塞状态，此时通过interrupt()中断该线程；调用interrupt()会立即将线程的中断标记设为“true”，
     * 但是由于线程处于阻塞状态，所以该“中断标记”会立即被清除为“false”，同时，会产生一个InterruptedException的异常。
     *
     * 如果线程被阻塞在一个Selector选择器中，那么通过interrupt()中断它时；线程的中断标记会被设置为true，并且它会立即从选择操作中返回。
     * 如果不属于前面所说的情况，那么通过interrupt()中断线程时，它的中断标记会被设置为“true”。
     *
     * 中断一个“已终止的线程”不会产生任何操作。
     */
    public void interrupt() {
        if (this != Thread.currentThread()) {
            checkAccess();
        }

        synchronized (blockerLock) {
            Interruptible b = blocker;
            if (b != null) {
                interrupt0();           // Just to set the interrupt flag
                b.interrupt(this);
                return;
            }
        }
        interrupt0();
    }

    /**
     * 测试当前线程是否中断。 该方法可以清除线程的中断状态 。
     * 换句话说，如果这个方法被连续调用两次，那么第二个调用将返回false
     * （除非当前线程再次中断，在第一个调用已经清除其中断状态之后，在第二个调用之前已经检查过）。
     * 忽略线程中断，因为线程在中断时不存在将被该方法返回false所反映
     *
     * 是一个静态方法，用于测试当前线程是否已经中断，并将线程的中断状态 清除。
     * 所以如果线程已经中断，调用两次interrupted，第二次时会返回false，因为第一次返回true后会清除中断状态
     *
     * @return true如果当前线程已被中断; false否则。
     */
    public static boolean interrupted() {
        return currentThread().isInterrupted(true);
    }

    /**
     * 测试这个线程是否被中断。 线程的中断状态不受此方法的影响。
     * 忽略线程中断，因为线程在中断时不存在将被该方法返回false所反映。
     * @return true如果这个线程已被中断; false否则。
     */
    public boolean isInterrupted() {
        return isInterrupted(false);
    }

    /**
     * 检查线程是否已经中断
     * 此中断状态重新设置或者基于ClearInterrupted的值
     */
    private native boolean isInterrupted(boolean ClearInterrupted);

    /**
     * 抛出 NoSuchMethodError
     *
     * @deprecated 已弃用
     *              这种方法最初是为了销毁这个线程而没有任何清理。
     *              它所持有的任何监视器都将保持锁定。
     *              但是，该方法从未实现。
     *              如果要实施，那么suspend()的方式会是僵死的 。
     *              如果目标线程在销毁时保护关键系统资源的锁，则无法再次访问该资源。
     *              如果另一个线程曾尝试锁定此资源，将导致死锁。 这种僵局通常表现为“冻结”过程。
     *              有关更多信息，请参阅Why are Thread.stop, Thread.suspend and Thread.resume Deprecated? 。
     */
    @Deprecated
    public void destroy() {
        throw new NoSuchMethodError();
    }

    /**
     * 测试这个线程是否活着。
     * 如果一个线程已经启动并且尚未死亡，那么线程是活着的。
     *
     * @return true如果这个线程还活着; false否则。
     */
    public final native boolean isAlive();

    /**
     * 暂停这个线程。
     * 首先，这个线程的checkAccess方法被调用，没有参数。 这可能会导致SecurityException （在当前线程中）。
     *
     * 如果线程活着，它将被暂停，并且不会进一步进行，除非和直到恢复
     *
     * @exception  SecurityException 如果当前线程不能修改此线程
     * @deprecated 已弃用
     *              这种方法已被弃用，因为它本身就是死锁的。
     *              如果目标线程在挂起时保护关键系统资源的监视器上的锁定，则在目标线程恢复之前，线程不能访问该资源。
     *              如果要恢复目标线程的线程在调用resume之前尝试锁定此监视器， resume导致死锁。
     *              这种僵局通常表现为“冻结”过程。
     *              有关详细信息，请参阅Why are Thread.stop, Thread.suspend and Thread.resume Deprecated? 。
     */
    @Deprecated
    public final void suspend() {
        checkAccess();
        suspend0();
    }

    /**
     * 恢复挂起的线程
     * @deprecated 已弃用 此方法仅适用于suspend() ，由于它是死锁倾向，因此已被弃用。
     *              有关更多信息，请参阅Why are Thread.stop, Thread.suspend and Thread.resume Deprecated? 。
     *              恢复挂起的线程
     */
    @Deprecated
    public final void resume() {
        checkAccess();
        resume0();
    }

    /**
     * 更改此线程的优先级。
     * 首先调用这个线程的checkAccess方法，没有参数。 这可能会导致投掷SecurityException 。
     * 否则，该线程的优先级设置为指定的小newPriority和最大允许的线程的线程组的优先级。
     *
     * @param newPriority 设置此线程的优先级
     * @exception  IllegalArgumentException  如果优先级不在 MIN_PRIORITY到 MAX_PRIORITY
     * @exception  SecurityException  如果当前线程不能修改此线程
     */
    public final void setPriority(int newPriority) {
        ThreadGroup g;
        checkAccess();
        if (newPriority > MAX_PRIORITY || newPriority < MIN_PRIORITY) {
            throw new IllegalArgumentException();
        }
        if((g = getThreadGroup()) != null) {
            if (newPriority > g.getMaxPriority()) {
                newPriority = g.getMaxPriority();
            }
            setPriority0(priority = newPriority);
        }
    }

    /**
     * 获取线程的优先级
     */
    public final int getPriority() {
        return priority;
    }

    /**
     * 将此线程的名称更改为等于参数name 。
     * 首先调用这个线程的checkAccess方法，没有参数。 这可能会导致投掷SecurityException 。
     */
    public final synchronized void setName(String name) {
        checkAccess();
        if (name == null) {
            throw new NullPointerException("name cannot be null");
        }

        this.name = name;
        if (threadStatus != 0) {
            setNativeName(name);
        }
    }

    /**
     * 获取线程的名字
     */
    public final String getName() {
        return name;
    }

    /**
     * 返回此线程所属的线程组。
     * 如果线程已经死机（停止），此方法返回null。
     */
    public final ThreadGroup getThreadGroup() {
        return group;
    }

    /**
     * 返回当前线程的thread group及其子组中活动线程数的估计。
     * 递归地遍历当前线程的线程组中的所有子组。
     *
     * 返回的值只是一个估计，因为线程数可能会在此方法遍历内部数据结构时动态更改，并且可能受某些系统线程的存在的影响。
     * 此方法主要用于调试和监视。
     */
    public static int activeCount() {
        return currentThread().getThreadGroup().activeCount();
    }

    /**
     * 当前线程的线程组及其子组中的每个活动线程复制到指定的数组中。
     * 该方法简单地调用当前线程的线程组的ThreadGroup.enumerate(Thread[])方法。
     *
     * 应用程序可能会使用activeCount方法来估计数组的大小，但是如果数组太短而不能容纳所有线程，则会忽略额外的线程。
     * 如果在当前线程组及其子组中获取每个活动线程至关重要，则调用者应验证返回的int值是否严格小于tarray的长度。
     *
     * 由于本方法中固有的竞争条件，建议该方法仅用于调试和监控。
     */
    public static int enumerate(Thread tarray[]) {
        return currentThread().getThreadGroup().enumerate(tarray);
    }


    @Deprecated
    public native int countStackFrames();

    /**
     * 等待这个线程死亡的时间最多为millis毫秒。
     * 0的超时意味着永远等待。
     *
     * 此实现使用this.wait调用的循环，条件为this.isAlive 。
     * 当线程终止时，调用this.notifyAll方法。
     * 建议应用程序不使用wait ， notify ，或notifyAll上Thread实例。
     *
     * @param  millis 以毫秒为单位等待的时间
     * @throws  IllegalArgumentException 如果 millis值为负
     * @throws  InterruptedException 如果任何线程中断当前线程。
     *                              当抛出此异常时，当前线程的中断状态将被清除
     */
    public final synchronized void join(long millis)
            throws InterruptedException {
        long base = System.currentTimeMillis();
        long now = 0;

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        //当millis==0时，会进入while(isAlive())循环；即只要子线程是活的，主线程就不停的等待。
        if (millis == 0) {
            while (isAlive()) {
                wait(0);
            }
        } else {
            while (isAlive()) {
                long delay = millis - now;
                if (delay <= 0) {
                    break;
                }
                wait(delay);
                now = System.currentTimeMillis() - base;
            }
        }
    }

    /**
     * 等待最多millis毫秒加上这个线程死亡的nanos纳秒。
     *
     * 此实现使用this.wait调用的循环，条件为this.isAlive 。
     * 当线程终止时，调用this.notifyAll方法。 建议应用程序不使用wait ， notify ，或notifyAll上Thread实例。
     *
     * @param  millis 等待毫秒的时间
     * @param  nanos  0-999999等待的额外纳秒
     *
     * @throws  IllegalArgumentException 如果值 millis是否定的，或的值 nanos不在范围 0-999999
     *
     * @throws  InterruptedException 如果任何线程已中断当前线程。
     *                                当抛出此异常时，当前线程的中断状态将被清除。
     */
    public final synchronized void join(long millis, int nanos)
            throws InterruptedException {

        if (millis < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                    "nanosecond timeout value out of range");
        }

        if (nanos >= 500000 || (nanos != 0 && millis == 0)) {
            millis++;
        }

        join(millis);
    }

    /**
     * 让父线程等待子线程结束之后才能继续运行。
     *
     * 当我们调用某个线程的这个方法时，这个方法会挂起调用线程，
     * 直到  被调用线程结束执行，调用线程才会继续执行。
     *
     * 等待这个线程死亡。
     * 调用此方法的行为方式与调用完全相同
     * join (0)
     *
     * @throws  InterruptedException 如果任何线程中断当前线程。
     *                                当抛出此异常时，当前线程的中断状态将被清除
     */
    public final void join() throws InterruptedException {
        join(0);
    }

    /**
     * 将当前线程的堆栈跟踪打印到标准错误流。 此方法仅用于调试。
     */
    public static void dumpStack() {
        new Exception("Stack trace").printStackTrace();
    }

    /**
     * 将此线程标记为daemon线程或用户线程。
     * 当运行的唯一线程都是守护进程线程时，Java虚拟机将退出。
     *
     * 线程启动前必须调用此方法。
     *
     * @param  on 如果 true ，将此线程标记为守护线程
     *
     * @throws  IllegalThreadStateException 如果这个线程是 alive
     *
     * @throws  SecurityException 如果 checkAccess()确定当前线程不能修改此线程
     */
    public final void setDaemon(boolean on) {
        checkAccess();
        if (isAlive()) {
            throw new IllegalThreadStateException();
        }
        daemon = on;
    }

    /**
     * 测试这个线程是否是守护线程。
     *
     * @return true如果这个线程是一个守护线程; false否则。
     */
    public final boolean isDaemon() {
        return daemon;
    }

    /**
     * 确定当前正在运行的线程是否有权限修改此线程。
     * 如果有一个安全管理器，它的checkAccess方法被调用这个线程作为它的参数。 这可能会导致投掷SecurityException
     */
    public final void checkAccess() {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkAccess(this);
        }
    }

    /**
     * 返回此线程的字符串表示，包括线程的名称，优先级和线程组。
     */
    @Override
    public String toString() {
        ThreadGroup group = getThreadGroup();
        if (group != null) {
            return "Thread[" + getName() + "," + getPriority() + "," +
                    group.getName() + "]";
        } else {
            return "Thread[" + getName() + "," + getPriority() + "," +
                    "" + "]";
        }
    }

    /**
     * 返回此Thread的上下文ClassLoader。
     */
    @CallerSensitive
    public ClassLoader getContextClassLoader() {
        if (contextClassLoader == null) {
            return null;
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            ClassLoader.checkClassLoaderPermission(contextClassLoader,
                    Reflection.getCallerClass());
        }
        return contextClassLoader;
    }

    /**
     * 设置此线程的上下文ClassLoader。
     * 当创建线程时，可以设置上下文ClassLoader，并允许线程的创建者通过getContextClassLoader提供相应的类加载器，
     * 以便在加载类和资源时在线程中运行代码。
     */
    public void setContextClassLoader(ClassLoader cl) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(new RuntimePermission("setContextClassLoader"));
        }
        contextClassLoader = cl;
    }

    /**
     * 返回true当且仅当当前线程在指定的对象上保持监视器锁。
     * 该方法旨在允许程序断言当前线程已经保存指定的锁：
     *   assert Thread.holdsLock(obj);
     */
    public static native boolean holdsLock(Object obj);

    private static final StackTraceElement[] EMPTY_STACK_TRACE
            = new StackTraceElement[0];

    /**
     * 返回表示此线程的堆栈转储的堆栈跟踪元素数组。
     * 该方法将返回一个零长度的数组，如果该线程尚未启动，已启动但尚未被计划运行，或已终止。
     * 如果返回的数组非零长度，则数组的第一个元素表示堆栈的顶部，这是序列中最近的方法调用。
     * 数组的最后一个元素表示堆栈的底部，这是序列中最近最少的方法调用。
     *
     * 如果有一个安全管理器，并且这个线程不是当前的线程，
     * 那么安全管理器的checkPermission方法被调用一个RuntimePermission("getStackTrace")权限来查看是否可以获取堆栈跟踪。
     *
     * 在某些情况下，某些虚拟机可能从堆栈跟踪中省略一个或多个堆栈帧。
     * 在极端情况下，允许没有关于该线程的堆栈跟踪信息的虚拟机从该方法返回零长度数组。
     */
    public StackTraceElement[] getStackTrace() {
        if (this != Thread.currentThread()) {
            // check for getStackTrace permission
            SecurityManager security = System.getSecurityManager();
            if (security != null) {
                security.checkPermission(
                        SecurityConstants.GET_STACK_TRACE_PERMISSION);
            }
            // optimization so we do not call into the vm for threads that
            // have not yet started or have terminated
            if (!isAlive()) {
                return EMPTY_STACK_TRACE;
            }
            StackTraceElement[][] stackTraceArray = dumpThreads(new Thread[] {this});
            StackTraceElement[] stackTrace = stackTraceArray[0];
            // a thread that was alive during the previous isAlive call may have
            // since terminated, therefore not having a stacktrace.
            if (stackTrace == null) {
                stackTrace = EMPTY_STACK_TRACE;
            }
            return stackTrace;
        } else {
            // Don't need JVM help for current thread
            return (new Exception()).getStackTrace();
        }
    }

    /**
     * 返回所有活动线程的堆栈跟踪图。 
     * Map键是线程，每个Map值是StackTraceElement数组，表示对应的Thread的堆栈转储。
     * 返回的堆栈跟踪格式为getStackTrace方法指定的格式。
     *
     * 线程可能正在执行，而此方法被调用。
     * 每个线程的堆栈跟踪仅表示快照，并且可以在不同时间获取每个堆栈跟踪。
     * 如果虚拟机没有关于线程的堆栈跟踪信息，则将在Map值中返回零长度的数组。
     *
     * 如果有一个安全管理员，那么安全管理员的checkPermission方法被调用一个RuntimePermission("getStackTrace")权限
     * 以及RuntimePermission("modifyThreadGroup")权限来查看是否可以获取所有线程的堆栈跟踪。
     *
     */
    public static Map<Thread, StackTraceElement[]> getAllStackTraces() {
        // check for getStackTrace permission
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkPermission(
                    SecurityConstants.GET_STACK_TRACE_PERMISSION);
            security.checkPermission(
                    SecurityConstants.MODIFY_THREADGROUP_PERMISSION);
        }

        // Get a snapshot of the list of all threads
        Thread[] threads = getThreads();
        StackTraceElement[][] traces = dumpThreads(threads);
        Map<Thread, StackTraceElement[]> m = new HashMap<>(threads.length);
        for (int i = 0; i < threads.length; i++) {
            StackTraceElement[] stackTrace = traces[i];
            if (stackTrace != null) {
                m.put(threads[i], stackTrace);
            }
            // else terminated so we don't put it in the map
        }
        return m;
    }


    private static final RuntimePermission SUBCLASS_IMPLEMENTATION_PERMISSION =
            new RuntimePermission("enableContextClassLoaderOverride");

    /** cache of subclass security audit results */
    /* Replace with ConcurrentReferenceHashMap when/if it appears in a future
     * release */
    private static class Caches {
        /** cache of subclass security audit results */
        static final ConcurrentMap<WeakClassKey,Boolean> subclassAudits =
                new ConcurrentHashMap<>();

        /** queue for WeakReferences to audited subclasses */
        static final ReferenceQueue<Class<?>> subclassAuditsQueue =
                new ReferenceQueue<>();
    }

    /**
     * Verifies that this (possibly subclass) instance can be constructed
     * without violating security constraints: the subclass must not override
     * security-sensitive non-final methods, or else the
     * "enableContextClassLoaderOverride" RuntimePermission is checked.
     */
    private static boolean isCCLOverridden(Class<?> cl) {
        if (cl == Thread.class) {
            return false;
        }

        processQueue(Caches.subclassAuditsQueue, Caches.subclassAudits);
        WeakClassKey key = new WeakClassKey(cl, Caches.subclassAuditsQueue);
        Boolean result = Caches.subclassAudits.get(key);
        if (result == null) {
            result = Boolean.valueOf(auditSubclass(cl));
            Caches.subclassAudits.putIfAbsent(key, result);
        }

        return result.booleanValue();
    }

    /**
     * Performs reflective checks on given subclass to verify that it doesn't
     * override security-sensitive non-final methods.  Returns true if the
     * subclass overrides any of the methods, false otherwise.
     */
    private static boolean auditSubclass(final Class<?> subcl) {
        Boolean result = AccessController.doPrivileged(
                new PrivilegedAction<Boolean>() {
                    @Override
                    public Boolean run() {
                        for (Class<?> cl = subcl;
                             cl != Thread.class;
                             cl = cl.getSuperclass())
                        {
                            try {
                                cl.getDeclaredMethod("getContextClassLoader", new Class<?>[0]);
                                return Boolean.TRUE;
                            } catch (NoSuchMethodException ex) {
                            }
                            try {
                                Class<?>[] params = {ClassLoader.class};
                                cl.getDeclaredMethod("setContextClassLoader", params);
                                return Boolean.TRUE;
                            } catch (NoSuchMethodException ex) {
                            }
                        }
                        return Boolean.FALSE;
                    }
                }
        );
        return result.booleanValue();
    }

    private native static StackTraceElement[][] dumpThreads(Thread[] threads);
    private native static Thread[] getThreads();

    /**
     * 返回此线程的标识符。
     * 线程ID是创建此线程时生成的正数long号。
     * 线程ID是唯一的，并且在其生命周期内保持不变。
     * 当线程被终止时，该线程ID可以被重用。
     */
    public long getId() {
        return tid;
    }

    /**
     * 线程状态
     * 一个线程可以是下面的一个状态
     * NEW : 线程还没开始
     * RUNNABLE ： 线程正在虚拟机中执行
     * BLOCKED： 线程正在被监视器锁阻塞
     * WAITING： 一个线程被无限期等待被另一个线程执行的动作
     * TIMED_WAITING: 一个线程等待另一个线程被指定指定时间
     * TERMINATED： 线程结束退出
     *
     * 1. 初始(NEW)：新创建了一个线程对象，但还没有调用start()方法。
     * 2. 运行(RUNNABLE)：Java线程中将就绪（ready）和运行中（running）两种状态笼统的称为“运行”。
     *                      线程对象创建后，其他线程(比如main线程）调用了该对象的start()方法。
     *                      该状态的线程位于可运行线程池中，等待被线程调度选中，获取CPU的使用权，此时处于就绪状态（ready）。
     *                      就绪状态的线程在获得CPU时间片后变为运行中状态（running）。
     * 3.阻塞(BLOCKED)：表示线程阻塞于锁。
     * 4.等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通知或中断）。
     * 5.超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后自行返回。
     * 6. 终止(TERMINATED)：表示该线程已经执行完毕。
     *
     * 线程在给定时间点只能处于一种状态。
     * 这些状态是不反映的虚拟机状态
     * 任何操作系统线程状态
     */
    public enum State {
        NEW,

        RUNNABLE,

        BLOCKED,

        WAITING,

        TIMED_WAITING,

        TERMINATED;
    }

    /**
     * 返回此线程的状态。
     * 该方法设计用于监视系统状态，不用于同步控制。
     */
    public State getState() {
        return sun.misc.VM.toThreadState(threadStatus);
    }

    // Added in JSR-166

    /**
     * Interface for handlers invoked when a <tt>Thread</tt> abruptly
     * terminates due to an uncaught exception.
     * <p>When a thread is about to terminate due to an uncaught exception
     * the Java Virtual Machine will query the thread for its
     * <tt>UncaughtExceptionHandler</tt> using
     * {@link #getUncaughtExceptionHandler} and will invoke the handler's
     * <tt>uncaughtException</tt> method, passing the thread and the
     * exception as arguments.
     * If a thread has not had its <tt>UncaughtExceptionHandler</tt>
     * explicitly set, then its <tt>ThreadGroup</tt> object acts as its
     * <tt>UncaughtExceptionHandler</tt>. If the <tt>ThreadGroup</tt> object
     * has no
     * special requirements for dealing with the exception, it can forward
     * the invocation to the {@linkplain #getDefaultUncaughtExceptionHandler
     * default uncaught exception handler}.
     *
     * @see #setDefaultUncaughtExceptionHandler
     * @see #setUncaughtExceptionHandler
     * @see ThreadGroup#uncaughtException
     * @since 1.5
     */
    @FunctionalInterface
    public interface UncaughtExceptionHandler {
        /**
         * Method invoked when the given thread terminates due to the
         * given uncaught exception.
         * <p>Any exception thrown by this method will be ignored by the
         * Java Virtual Machine.
         * @param t the thread
         * @param e the exception
         */
        void uncaughtException(Thread t, Throwable e);
    }

    // null unless explicitly set
    private volatile UncaughtExceptionHandler uncaughtExceptionHandler;

    // null unless explicitly set
    private static volatile UncaughtExceptionHandler defaultUncaughtExceptionHandler;

    /**
     * Set the default handler invoked when a thread abruptly terminates
     * due to an uncaught exception, and no other handler has been defined
     * for that thread.
     *
     * <p>Uncaught exception handling is controlled first by the thread, then
     * by the thread's {@link ThreadGroup} object and finally by the default
     * uncaught exception handler. If the thread does not have an explicit
     * uncaught exception handler set, and the thread's thread group
     * (including parent thread groups)  does not specialize its
     * <tt>uncaughtException</tt> method, then the default handler's
     * <tt>uncaughtException</tt> method will be invoked.
     * <p>By setting the default uncaught exception handler, an application
     * can change the way in which uncaught exceptions are handled (such as
     * logging to a specific device, or file) for those threads that would
     * already accept whatever &quot;default&quot; behavior the system
     * provided.
     *
     * <p>Note that the default uncaught exception handler should not usually
     * defer to the thread's <tt>ThreadGroup</tt> object, as that could cause
     * infinite recursion.
     *
     * @param eh the object to use as the default uncaught exception handler.
     * If <tt>null</tt> then there is no default handler.
     *
     * @throws SecurityException if a security manager is present and it
     *         denies <tt>{@link RuntimePermission}
     *         (&quot;setDefaultUncaughtExceptionHandler&quot;)</tt>
     *
     * @see #setUncaughtExceptionHandler
     * @see #getUncaughtExceptionHandler
     * @see ThreadGroup#uncaughtException
     * @since 1.5
     */
    public static void setDefaultUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(
                    new RuntimePermission("setDefaultUncaughtExceptionHandler")
            );
        }

        defaultUncaughtExceptionHandler = eh;
    }

    /**
     * Returns the default handler invoked when a thread abruptly terminates
     * due to an uncaught exception. If the returned value is <tt>null</tt>,
     * there is no default.
     * @since 1.5
     * @see #setDefaultUncaughtExceptionHandler
     * @return the default uncaught exception handler for all threads
     */
    public static UncaughtExceptionHandler getDefaultUncaughtExceptionHandler(){
        return defaultUncaughtExceptionHandler;
    }

    /**
     * Returns the handler invoked when this thread abruptly terminates
     * due to an uncaught exception. If this thread has not had an
     * uncaught exception handler explicitly set then this thread's
     * <tt>ThreadGroup</tt> object is returned, unless this thread
     * has terminated, in which case <tt>null</tt> is returned.
     * @since 1.5
     * @return the uncaught exception handler for this thread
     */
    public UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return uncaughtExceptionHandler != null ?
                uncaughtExceptionHandler : group;
    }

    /**
     * Set the handler invoked when this thread abruptly terminates
     * due to an uncaught exception.
     * <p>A thread can take full control of how it responds to uncaught
     * exceptions by having its uncaught exception handler explicitly set.
     * If no such handler is set then the thread's <tt>ThreadGroup</tt>
     * object acts as its handler.
     * @param eh the object to use as this thread's uncaught exception
     * handler. If <tt>null</tt> then this thread has no explicit handler.
     * @throws  SecurityException  if the current thread is not allowed to
     *          modify this thread.
     * @see #setDefaultUncaughtExceptionHandler
     * @see ThreadGroup#uncaughtException
     * @since 1.5
     */
    public void setUncaughtExceptionHandler(UncaughtExceptionHandler eh) {
        checkAccess();
        uncaughtExceptionHandler = eh;
    }

    /**
     * Dispatch an uncaught exception to the handler. This method is
     * intended to be called only by the JVM.
     */
    private void dispatchUncaughtException(Throwable e) {
        getUncaughtExceptionHandler().uncaughtException(this, e);
    }

    /**
     * Removes from the specified map any keys that have been enqueued
     * on the specified reference queue.
     */
    static void processQueue(ReferenceQueue<Class<?>> queue,
                             ConcurrentMap<? extends
                                     WeakReference<Class<?>>, ?> map)
    {
        Reference<? extends Class<?>> ref;
        while((ref = queue.poll()) != null) {
            map.remove(ref);
        }
    }

    /**
     *  Weak key for Class objects.
     **/
    static class WeakClassKey extends WeakReference<Class<?>> {
        /**
         * saved value of the referent's identity hash code, to maintain
         * a consistent hash code after the referent has been cleared
         */
        private final int hash;

        /**
         * Create a new WeakClassKey to the given object, registered
         * with a queue.
         */
        WeakClassKey(Class<?> cl, ReferenceQueue<Class<?>> refQueue) {
            super(cl, refQueue);
            hash = System.identityHashCode(cl);
        }

        /**
         * Returns the identity hash code of the original referent.
         */
        @Override
        public int hashCode() {
            return hash;
        }

        /**
         * Returns true if the given object is this identical
         * WeakClassKey instance, or, if this object's referent has not
         * been cleared, if the given object is another WeakClassKey
         * instance with the identical non-null referent as this one.
         */
        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }

            if (obj instanceof WeakClassKey) {
                Object referent = get();
                return (referent != null) &&
                        (referent == ((WeakClassKey) obj).get());
            } else {
                return false;
            }
        }
    }


    // The following three initially uninitialized fields are exclusively
    // managed by class java.util.concurrent.ThreadLocalRandom. These
    // fields are used to build the high-performance PRNGs in the
    // concurrent code, and we can not risk accidental false sharing.
    // Hence, the fields are isolated with @Contended.

    /** The current seed for a ThreadLocalRandom */
    @sun.misc.Contended("tlr")
    long threadLocalRandomSeed;

    /** Probe hash value; nonzero if threadLocalRandomSeed initialized */
    @sun.misc.Contended("tlr")
    int threadLocalRandomProbe;

    /** Secondary seed isolated from public ThreadLocalRandom sequence */
    @sun.misc.Contended("tlr")
    int threadLocalRandomSecondarySeed;

    /* Some private helper methods */
    private native void setPriority0(int newPriority);
    private native void stop0(Object o);
    private native void suspend0();
    private native void resume0();
    private native void interrupt0();
    private native void setNativeName(String name);
}
