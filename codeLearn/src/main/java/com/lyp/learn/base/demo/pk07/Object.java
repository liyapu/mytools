package com.lyp.learn.base.demo.pk07;

/**
 * java.lang.Object为java所有类的基类，所以一般的类都可用重写或直接使用Object下方法
 * Object类是所有类的根类。
 * 每个类都把Object作为超类。
 * 所有对象（包括数组）都实现了这个类的方法。
 */
public class Object {

    private static native void registerNatives();
    static {
        registerNatives();
    }

    /**
     * 返回此Object的运行时类。 返回的类对象是被表示类的static synchronized方法锁定的对象。
     * 实际结果的类型是Class<? extends |X|>其中|X|是静态类型上其表达的擦除getClass被调用。
     * 例如，在此代码片段中不需要转换：
     *
     * Number n = 0;
     * Class<? extends Number> c = n.getClass();
     *
     * 返回次Object的运行时类类型。
     * 不可重写，要调用的话，一般和getName()联合使用，如getClass().getName()
     */
    public final native Class<?> getClass();

    /**
     * 返回对象的哈希码值。
     * 支持这种方法是为了散列表，如HashMap提供的那样 。
     * hashCode的总合同是：
     *     1.只要在执行Java应用程序时多次在同一个对象上调用该方法，
     *       hashCode方法必须始终返回相同的整数，前提是修改了对象中equals比较中的信息。
     *       该整数不需要从一个应用程序的执行到相同应用程序的另一个执行保持一致。
     *     2.如果根据equals(Object)方法两个对象相等，
     *       则在两个对象中的每个对象上调用hashCode方法必须产生相同的整数结果。
     *    3.不要求如果两个对象根据equals(java.lang.Object)方法不相等，
     *      那么在两个对象中的每个对象上调用hashCode方法必须产生不同的整数结果。
     *      但是，程序员应该意识到，为不等对象生成不同的整数结果可能会提高哈希表的性能。
     *
     * 尽可能多的合理实用，由类别Object定义的hashCode方法确实为不同对象返回不同的整数。
     * （这通常通过将对象的内部地址转换为整数来实现，但Java的编程语言不需要此实现技术。）
     *
     * 该方法用于哈希查找，可以减少在查找中使用equals的次数，重写了equals方法一般都要重写hashCode方法。
     * 这个方法在一些具有哈希功能的Collection中用到。
     *
     * 一般必须满足obj1.equals(obj2)==true。可以推出obj1.hash- Code()==obj2.hashCode()，
     * 但是hashCode相等不一定就满足equals。不过为了提高效率，应该尽量使上面两个条件接近等价。
     *
     * 如果不重写hashcode(),在HashSet中添加两个equals的对象，会将两个对象都加入进去。
     */
    public native int hashCode();

    /**
     * Object中的equals方法是直接判断this和obj本身的值是否相等，
     * 即用来判断调用equals的对象和形参obj所引用的对象是否是同一对象
     *
     * 所谓同一对象就是指内存中同一块存储单元，
     * 如果this和obj指向的hi同一块内存对象，则返回true,
     * 如果this和obj指向的不是同一块内存，则返回false。
     *
     * 注意：即便是内容完全相等的两块不同的内存对象，也返回false。
     *       如果是同一块内存，则object中的equals方法返回true,如果是不同的内存，则返回false
     *       如果希望不同内存但相同内容的两个对象equals时返回true,则我们需要重写父类的equal方法
     *       String类已经重写了object中的equals方法（这样就是比较内容是否相等了）
     *
     *
     * 指示一些其他对象是否等于此。
     * equals方法在非空对象引用上实现等价关系：
     *
     *      自反性 ：对于任何非空的参考值x ， x.equals(x)应该返回true 。
     *      对称的 ：对于任何非空引用值x和y ， x.equals(y)应该返回true当且仅当y.equals(x)回报true 。
     *      传递性 ：对于任何非空引用值x ， y和z ，如果x.equals(y)回报true，y.equals(z)回报true，
     *              然后x.equals(z)应该返回true 。
     *      一致的 ：对于任何非空引用值x和y ，多次调用x.equals(y)始终返回true或始终返回false ，
     *              没有设置中使用的信息equals比较上的对象被修改。
     *
     *     对于任何非空的参考值x ， x.equals(null)应该返回false 。
     *
     *     该equals类方法Object实现对象上差别可能性最大的相等关系;
     *     也就是说，对于任何非空的参考值x和y ，当且仅当x和y引用相同的对象（ x == y具有值true ）时，该方法返回true 。
     *
     *      请注意，无论何时覆盖该方法，通常需要覆盖hashCode方法，以便维护hashCode方法的通用合同，
     *      该方法规定相等的对象必须具有相等的哈希码。
     *
     * @param   obj   与之比较的参考对象。
     * @return true如果此对象与obj参数相同; false否则。
     */
    public boolean equals(Object obj) {
        return (this == obj);
    }

    /**
     * 保护方法，实现对象的浅复制，只有实现了Cloneable接口才可以调用该方法，否则抛出CloneNotSupportedException异常
     *
     * 创建并返回此对象的副本。 “复制”的精确含义可能取决于对象的类。 一般的意图是，对于任何对象x ，表达式：
     *  x.clone() != x
     *  将是真实的，而且表达：
     *  x.clone().getClass() == x.getClass()
     *  将是true ，但这些都不是绝对的要求。 通常情况是：
     *  x.clone().equals(x)
     *  将是true ，这不是一个绝对的要求。
     *
     * 按照惯例，返回的对象应该通过调用super.clone获得。
     * 如果一个类和它的所有超类（除了Object ）遵守这个惯例，那将是x.clone().getClass() == x.getClass()的情况。
     *
     * 按照惯例，此方法返回的对象应该与此对象（正被克隆）无关。
     * 为了实现这一独立性，可能需要修改super.clone返回的对象的一个或多个字段。
     * 通常，这意味着复制构成被克隆的对象的内部“深层结构”的任何可变对象，并通过引用该副本替换对这些对象的引用。
     * 如果一个类仅包含原始字段或对不可变对象的引用，则通常情况下， super.clone返回的对象中的字段通常不需要修改。
     *
     * clone的方法Object执行特定的克隆操作。
     * 首先，如果此对象的类不实现接口Cloneable ，则抛出CloneNotSupportedException 。
     * 请注意，所有数组都被认为是实现接口Cloneable ，并且数组类型T[]的clone方法的返回类型是T[] ，其中T是任何引用或原始类型。
     * 否则，该方法将创建该对象的类的新实例，并将其所有字段初始化为完全符合该对象的相应字段的内容，就像通过赋值一样。
     * 这些字段的内容本身不被克隆。 因此，该方法执行该对象的“浅拷贝”，而不是“深度拷贝”操作。
     *
     * Object类本身并不实现接口Cloneable ，因此在类别为Object的对象上调用clone方法将导致运行时抛出异常。
     *
     *
     * @return 这个实例的一个克隆。
     * @throws  CloneNotSupportedException  如果对象的类不支持Cloneable接口。
     *                                      覆盖clone方法的子类也可以抛出此异常以指示实例无法克隆。
     */
    protected native Object clone() throws CloneNotSupportedException;

    /**
     * 默认返回对象的名称及引用地址，但一般被子类重写用于说明子类相关属性值描述
     *
     * 返回对象的字符串表示形式。 一般来说， toString方法返回一个“textually代表”这个对象的字符串。
     * 结果应该是一个简明扼要的表达，容易让人阅读。 建议所有子类覆盖此方法。
     *
     * 该toString类方法Object返回一个由其中的对象是一个实例，
     * 该符号字符`的类的名称的字符串@ ”和对象的哈希码的无符号的十六进制表示。
     * 换句话说，这个方法返回一个等于下列值的字符串：
     *  getClass().getName() + '@' + Integer.toHexString(hashCode())
     */
    public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }

    /**
     * 唤醒正在等待对象监视器的单个线程。
     * 如果任何线程正在等待这个对象，其中一个被选择被唤醒。选择是任意的，并且由实施的判断发生。
     * 线程通过调用wait方法之一等待对象的监视器。
     * 唤醒的线程将无法继续，直到当前线程放弃此对象上的锁定为止。
     * 唤醒的线程将以通常的方式与任何其他线程竞争，这些线程可能正在积极地竞争在该对象上进行同步;
     * 例如，唤醒的线程在下一个锁定该对象的线程中没有可靠的权限或缺点。
     *
     * 该方法只能由作为该对象的监视器的所有者的线程调用。
     * 线程以三种方式之一成为对象监视器的所有者：
     *      1.通过执行该对象的同步synchronized实例方法。
     *      2.通过执行在对象上synchronized synchronized语句的正文。
     *      3.对于类型为Class,的对象，通过执行该类的同步静态方法。
     * 一次只能有一个线程可以拥有一个对象的监视器。
     *
     * @throws  IllegalMonitorStateException  如果当前线程不是此对象的监视器的所有者
     */
    public final native void notify();

    /**
     * 唤醒正在等待对象监视器的所有线程。 线程通过调用wait方法之一等待对象的监视器。
     * 唤醒的线程将无法继续，直到当前线程释放该对象上的锁。
     * 唤醒的线程将以通常的方式与任何其他线程竞争，这些线程可能正在积极地竞争在该对象上进行同步;
     * 例如，唤醒的线程在下一个锁定该对象的线程中不会有可靠的特权或缺点。
     *
     * 该方法只能由作为该对象的监视器的所有者的线程调用。
     * 有关线程可以成为监视器所有者的方法的说明，请参阅notify方法。
     * @throws  IllegalMonitorStateException  如果当前线程不是此对象的监视器的所有者
     */
    public final native void notifyAll();

    /**
     * 导致当前线程等待，直到另一个线程调用此对象的notify()方法或notifyAll()方法，或指定的时间已过。
     * 当前的线程必须拥有该对象的监视器。
     *
     * 此方法使当前线程（称为T ）将其放置在该对象的等待集中，然后放弃对该对象的任何和所有同步声明。
     * 线程T变得禁用线程调度目的，并且休眠，直到发生四件事情之一：
     *      1.一些其他线程调用该对象的notify方法，并且线程T恰好被任意选择为被唤醒的线程。
     *      2.某些其他线程调用此对象的notifyAll方法。
     *      3.一些其他线程interrupts线程T。
     *      4.指定的实时数量已经过去，或多或少。 然而，如果timeout为零，则不考虑实时，线程等待直到通知。
     *
     * 然后从该对象的等待集中删除线程T ，并重新启用线程调度。
     * 然后它以通常的方式与其他线程竞争在对象上进行同步的权限;
     * 一旦获得了对象的控制，其对对象的所有同步声明就恢复到现状 - 也就是在调用wait方法之后的情况。线程T然后从调用wait方法返回。
     * 因此，从返回wait方法，对象和线程的同步状态T正是因为它是当wait被调用的方法。
     *
     * 线程也可以唤醒，而不会被通知，中断或超时，即所谓的虚假唤醒 。
     * 虽然这在实践中很少会发生，但应用程序必须通过测试应该使线程被唤醒的条件来防范，并且如果条件不满足则继续等待。
     * 换句话说，等待应该总是出现在循环中，就像这样：
     *      synchronized (obj) {
     *          while (<condition does not hold>)
     *              obj.wait(timeout);
     *          ... // Perform action appropriate to condition
     *      }
     *
     * （有关此主题的更多信息，请参阅Doug Lea的“Java并行编程（第二版）”
     * （Addison-Wesley，2000）中的第3.2.3节或Joshua Bloch的“有效Java编程语言指南”（Addison- Wesley，2001）。
     *
     * 如果当前线程interrupted任何线程之前或在等待时，那么InterruptedException被抛出。
     * 如上所述，在该对象的锁定状态已恢复之前，不会抛出此异常。
     *
     * 请注意， wait方法，因为它将当前线程放入该对象的等待集，仅解锁此对象;
     * 当线程等待时，当前线程可以同步的任何其他对象都将保持锁定状态。
     *
     * 该方法只能由作为该对象的监视器的所有者的线程调用。
     * 有关线程可以成为监视器所有者的方法的说明，请参阅notify方法。
     *
     * wait(0)表示无限等待，直到被notify()或notifyAll()唤醒
     *
     * @param      timeout   以毫秒为单位等待的最长时间。
     * @throws  IllegalArgumentException     如果超时值为负。
     * @throws  IllegalMonitorStateException  如果当前线程不是对象监视器的所有者
     * @throws  InterruptedException  如果任何线程在当前线程等待通知之前或当前线程中断当前线程。
     *                                 当抛出此异常时，当前线程的中断状态将被清除
     */
    public final native void wait(long timeout) throws InterruptedException;

    /**
     * 导致当前线程等待，直到另一个线程调用此对象的notify()方法或notifyAll()方法，或其他一些线程中断当前线程，或一定量的实时时间。
     *
     * 这种方法类似于一个参数的wait方法，但它允许对放弃之前等待通知的时间进行更精细的控制。 以纳秒为单位的实时数量由下式给出：
     *  1000000*timeout+nanos在所有其他方面，该方法与一个参数的方法wait(long)相同。 特别是， wait(0, 0)意味着同样的事情wait(0) 。
     *
     * 当前的线程必须拥有该对象的监视器。 线程释放此监视器的所有权，并等待直到发生以下两种情况之一：
     *      1.另一个线程通知等待该对象的监视器的线程通过调用notify方法或notifyAll方法来唤醒。
     *      2.由timeout毫秒加nanos纳秒参数指定的超时时间已过。
     *
     * 然后线程等待，直到它可以重新获得监视器的所有权并恢复执行。
     *
     * 像在一个参数版本中，中断和虚假唤醒是可能的，并且该方法应该始终在循环中使用：
     *
     *   synchronized (obj) {
     *          while (<condition does not hold>)
     *              obj.wait(timeout, nanos);
     *          ... // Perform action appropriate to condition
     *      }
     *  该方法只能由作为该对象的监视器的所有者的线程调用。 有关线程可以成为监视器所有者的方式的说明，请参阅notify方法
     *
     * @param      timeout   以毫秒为单位等待的最长时间。
     * @param      nanos     额外的时间，以纳秒范围0-999999
     * @throws  IllegalArgumentException   如果超时值为负值或 IllegalArgumentException值不在0-999999范围内。
     * @throws  IllegalMonitorStateException  如果当前线程不是此对象的监视器的所有者。
     * @throws  InterruptedException 如果任何线程在当前线程等待通知之前或当前线程中断当前线程。
     *                              当抛出此异常时，当前线程的中断状态将被清除
     */
    public final void wait(long timeout, int nanos) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout value is negative");
        }

        if (nanos < 0 || nanos > 999999) {
            throw new IllegalArgumentException(
                    "nanosecond timeout value out of range");
        }

        if (nanos > 0) {
            timeout++;
        }

        wait(timeout);
    }

    /**
     * 导致当前线程等待，直到另一个线程调用该对象的notify()方法或notifyAll()方法。
     * 换句话说，这个方法的行为就好像简单地执行呼叫wait(0) 。
     *
     * 当前的线程必须拥有该对象的监视器。
     * 该线程释放此监视器的所有权，并等待另一个线程通知等待该对象监视器的线程通过调用notify方法或notifyAll方法notifyAll 。
     * 然后线程等待，直到它可以重新获得监视器的所有权并恢复执行。
     *
     * 像在一个参数版本中，中断和虚假唤醒是可能的，并且该方法应该始终在循环中使用：
     *   synchronized (obj) {
     *          while (<condition does not hold>)
     *              obj.wait();
     *          ... // Perform action appropriate to condition
     *      }
     *  该方法只能由作为该对象的监视器的所有者的线程调用。 有关线程可以成为监视器所有者的方式的说明，请参阅notify方法。
     *
     * @throws  IllegalMonitorStateException  如果当前线程不是对象监视器的所有者
     * @throws  InterruptedException 如果任何线程在当前线程等待通知之前或当前线程中断当前线程。
     *                                  当抛出此异常时，当前线程的中断状态将被清除
     */
    public final void wait() throws InterruptedException {
        wait(0);
    }

    /**
     * 垃圾回收器在认为该对象是垃圾对象的时候会调用该方法。子类可以通过重写该方法来达到资源释放的目的。
     * 在方法调用过程中出现的异常会被忽略且方法调用会被终止。
     * 任何对象的该方法只会被调用一次。
     *
     * 当垃圾收集确定不再有对该对象的引用时，垃圾收集器在对象上调用该对象。
     * 一个子类覆盖了处理系统资源或执行其他清理的finalize方法。
     *
     * finalize的一般合同是，
     * 如果Java¢虚拟机已经确定不再有任何方法可以被任何尚未死亡的线程访问的方法被调用，
     * 除非是由于最后确定的其他对象或类的准备工作所采取的行动。
     * finalize方法可以采取任何行动，包括使此对象再次可用于其他线程;
     * 然而， finalize的通常目的是在对象不可撤销地丢弃之前执行清除动作。
     * 例如，表示输入/输出连接的对象的finalize方法可能会在对象被永久丢弃之前执行显式I / O事务来中断连接。
     *
     * 所述finalize类的方法Object执行任何特殊操作; 它只是返回正常。 Object的Object可以覆盖此定义。
     *
     * Java编程语言不能保证哪个线程将为任何给定的对象调用finalize方法。
     * 但是，确保调用finalize的线程在调用finalize时不会持有任何用户可见的同步锁。
     * 如果finalize方法抛出未捕获的异常，则会忽略该异常，并终止该对象的定类。
     *
     * 在为对象调用finalize方法之后，
     * 在Java虚拟机再次确定不再有任何方式可以通过任何尚未被死亡的线程访问此对象的任何方法的情况下，
     * 将采取进一步的操作，包括可能的操作由准备完成的其他对象或类别，此时可以丢弃对象。
     *
     * finalize方法从不被任何给定对象的Java虚拟机调用多次。
     *
     * finalize方法抛出的任何异常都会导致该对象的终止被停止，否则被忽略。
     *
     * @throws  Throwable - 这个方法提出的 异常
     */
    protected void finalize() throws Throwable { }
}
