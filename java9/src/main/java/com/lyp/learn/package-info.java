/**
 * @author: liyapu
 * @description:
 * @date 2020-04-27 21:47
 *
 *   模块
 *     模块将由通常的类和新的模块声明文件(module-info.java)组成
 *     该文件是位于 java 代码结构的顶层，该模块描述符明确地定义了我们的模块需要什么依赖关系，以及哪些模块被外部使用。
 *
 *     在exports 子 句中未提及的所有包默认情况下将封装在模块中，不能在外部使用
 *     exports:控制着哪些包可以被其它模块访问到。所有不被导出的包 默认都被封装在模块里面
 *
 *     requires:指明对其它模块的依赖
 *
 */
package com.lyp.learn;