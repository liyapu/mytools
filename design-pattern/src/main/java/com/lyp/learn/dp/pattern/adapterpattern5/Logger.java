package com.lyp.learn.dp.pattern.adapterpattern5;

/**
 * @author liyapu
 * @date 2023-03-13 09:42
 * @description
 */
public interface Logger {
//剖析适配器模式在Java日志中的应用
//Java中有很多日志框架，在项目开发中，我们常常用它们来打印日志信息。其中，比较常用的有log4j、logback，以及JDK提供的JUL(java.util.logging)和Apache的JCL(Jakarta Commons Logging)等。
//
//大部分日志框架都提供了相似的功能，比如按照不同级别（debug、info、warn、erro……）打印日志等，但它们却并没有实现统一的接口。这主要可能是历史的原因，它不像JDBC那样，一开始就制定了数据库操作的接口规范。
//
//如果我们只是开发一个自己用的项目，那用什么日志框架都可以，log4j、logback随便选一个就好。但是，如果我们开发的是一个集成到其他系统的组件、框架、类库等，那日志框架的选择就没那么随意了。
//
//比如，项目中用到的某个组件使用log4j来打印日志，而我们项目本身使用的是logback。将组件引入到项目之后，我们的项目就相当于有了两套日志打印框架。每种日志框架都有自己特有的配置方式。所以，我们要针对每种日志框架编写不同的配置文件（比如，日志存储的文件地址、打印日志的格式）。如果引入多个组件，每个组件使用的日志框架都不一样，那日志本身的管理工作就变得非常复杂。所以，为了解决这个问题，我们需要统一日志打印框架。
//
//如果你是做Java开发的，那Slf4j这个日志框架你肯定不陌生，它相当于JDBC规范，提供了一套打印日志的统一接口规范。不过，它只定义了接口，并没有提供具体的实现，需要配合其他日志框架（log4j、logback……）来使用。
//
//不仅如此，Slf4j的出现晚于JUL、JCL、log4j等日志框架，所以，这些日志框架也不可能牺牲掉版本兼容性，将接口改造成符合Slf4j接口规范。Slf4j也事先考虑到了这个问题，所以，它不仅仅提供了统一的接口定义，还提供了针对不同日志框架的适配器。对不同日志框架的接口进行二次封装，适配成统一的Slf4j接口定义。具体的代码示例如下所示：

//// slf4j统一的接口定义
//package org.slf4j;
//public interface Logger {
//  public boolean isTraceEnabled();
//  public void trace(String msg);
//  public void trace(String format, Object arg);
//  public void trace(String format, Object arg1, Object arg2);
//  public void trace(String format, Object[] argArray);
//  public void trace(String msg, Throwable t);
//
//  public boolean isDebugEnabled();
//  public void debug(String msg);
//  public void debug(String format, Object arg);
//  public void debug(String format, Object arg1, Object arg2)
//  public void debug(String format, Object[] argArray)
//  public void debug(String msg, Throwable t);
//
//  //...省略info、warn、error等一堆接口
//}
//
//// log4j日志框架的适配器
//// Log4jLoggerAdapter实现了LocationAwareLogger接口，
//// 其中LocationAwareLogger继承自Logger接口，
//// 也就相当于Log4jLoggerAdapter实现了Logger接口。
//package org.slf4j.impl;
//public final class Log4jLoggerAdapter extends MarkerIgnoringBase
//  implements LocationAwareLogger, Serializable {
//  final transient org.apache.log4j.Logger logger; // log4j
//
//  public boolean isDebugEnabled() {
//    return logger.isDebugEnabled();
//  }
//
//  public void debug(String msg) {
//    logger.log(FQCN, Level.DEBUG, msg, null);
//  }
//
//  public void debug(String format, Object arg) {
//    if (logger.isDebugEnabled()) {
//      FormattingTuple ft = MessageFormatter.format(format, arg);
//      logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
//    }
//  }
//
//  public void debug(String format, Object arg1, Object arg2) {
//    if (logger.isDebugEnabled()) {
//      FormattingTuple ft = MessageFormatter.format(format, arg1, arg2);
//      logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
//    }
//  }
//
//  public void debug(String format, Object[] argArray) {
//    if (logger.isDebugEnabled()) {
//      FormattingTuple ft = MessageFormatter.arrayFormat(format, argArray);
//      logger.log(FQCN, Level.DEBUG, ft.getMessage(), ft.getThrowable());
//    }
//  }
//
//  public void debug(String msg, Throwable t) {
//    logger.log(FQCN, Level.DEBUG, msg, t);
//  }
//  //...省略一堆接口的实现...
//}
//所以，在开发业务系统或者开发框架、组件的时候，我们统一使用Slf4j提供的接口来编写打印日志的代码，具体使用哪种日志框架实现（log4j、logback……），是可以动态地指定的（使用Java的SPI技术，这里我不多解释，你自行研究吧），只需要将相应的SDK导入到项目中即可。
//
//不过，你可能会说，如果一些老的项目没有使用Slf4j，而是直接使用比如JCL来打印日志，那如果想要替换成其他日志框架，比如log4j，该怎么办呢？实际上，Slf4j不仅仅提供了从其他日志框架到Slf4j的适配器，还提供了反向适配器，也就是从Slf4j到其他日志框架的适配。我们可以先将JCL切换为Slf4j，然后再将Slf4j切换为log4j。经过两次适配器的转换，我们就能成功将log4j切换为了logback。
}
