package com.lyp.learn.base.reflect;


import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-11 21:31
 */
public class MethodDemo {
    public static void main(String[] args) throws Exception {
        Class studentClazz = new Student().getClass();
        Class personClazz = Class.forName("com.lyp.learn.reflect.Person");

        /**
         * 获取指定方法
         */
        Method getStudentNameScopeMethod = studentClazz.getDeclaredMethod("getStudentNameScope",String.class,Integer.class);
        System.out.println(getStudentNameScopeMethod.getName());
        System.out.println(getStudentNameScopeMethod.getReturnType().getName());
        System.out.println(getStudentNameScopeMethod.getParameterCount());
        System.out.println(getStudentNameScopeMethod.getParameterTypes()[0].getName());
        System.out.println(getStudentNameScopeMethod.getParameterTypes()[1].getName());
        //想要运行指定方法，当然是方法对象最清楚，为了让方法运行，调用方法对象的invoke方法即可，
        //但是方法运行必须要明确所属的对象和具体的实际参数。
        Object studentObj = studentClazz.newInstance();
        //执行一个方法
        getStudentNameScopeMethod.invoke(studentObj,"李四",99);
        Object strNameScope = getStudentNameScopeMethod.invoke(studentObj,"李四",99);
        System.out.println(strNameScope);
        System.out.println();

        /**
         * 运行无参方法
         */
        Method printMessageMethod = studentClazz.getDeclaredMethod("printMessage");
        Object printMessageObjStr = printMessageMethod.invoke(studentClazz.newInstance());
        System.out.println(printMessageObjStr);


        /**
         * 想要运行私有方法
         */

        //想要获取私有方法。必须用getDeclearMethod();
        Method getStudentInfoMethod = studentClazz.getDeclaredMethod("getStudentInfo");
        // 私有方法不能直接访问，因为权限不够。非要访问，可以通过暴力的方式。
        //一般很少用，因为私有就是隐藏起来，所以尽量不要访问
        getStudentInfoMethod.setAccessible(true);
        Object strStudentInfo = getStudentInfoMethod.invoke(studentClazz.newInstance());
        System.out.println(strStudentInfo);
        System.out.println();

        /**
         * 想要运行静态方法
         */
        Method studentStaticInfoMethod = studentClazz.getDeclaredMethod("studentStaticInfo");
        Object studentStaticInfoStr =  studentStaticInfoMethod.invoke(null,null);
        System.out.println(studentStaticInfoStr);


        /**
         * JAVA 反射机制中，Field的getModifiers()方法返回int类型值表示该字段的修饰符。
         * 其中，该修饰符是java.lang.reflect.Modifier的静态属性。
         * 对应表如下：
         *
         * PUBLIC: 1
         * PRIVATE: 2
         * PROTECTED: 4
         * STATIC: 8
         * FINAL: 16
         * SYNCHRONIZED: 32
         * VOLATILE: 64
         * TRANSIENT: 128
         * NATIVE: 256
         * INTERFACE: 512
         * ABSTRACT: 1024
         * STRICT: 2048
         */
        int modifyInt = studentStaticInfoMethod.getModifiers();
        System.out.println(modifyInt);
        System.out.println(Modifier.toString(modifyInt));
        System.out.println();

        /**
         * 方法参数要与类型完全一致
         * 比如 参数是 int ,类型需要写 int.class
         *     参数是 Integer,类型需要些 Integer.class
         */
        Method setStartHourMethod = studentClazz.getDeclaredMethod("setStartHour", Integer.class);
        System.out.println("setStartHourMethod :" + setStartHourMethod);

//        Method setStartHourMethod2 = studentClazz.getDeclaredMethod("setStartHour", int.class);
//        System.out.println("setStartHourMethod2 :" + setStartHourMethod2);
//        System.out.println();
//
//        Method setEndHourMethod = studentClazz.getDeclaredMethod("setEndHour", Integer.class);
//        System.out.println("setEndHourMethod :" + setEndHourMethod);

        Method setEndHourMethod2 = studentClazz.getDeclaredMethod("setEndHour", int.class);
        System.out.println("setEndHourMethod2 :" + setEndHourMethod2);

        //2.执行方法
        //  invoke第一个参数表示执行哪个对象的方法，剩下的参数是执行方法时需要传入的参数
        Student student2 = (Student) studentClazz.newInstance();
        System.out.println("student2 ::" + student2.getEndHour());
        setEndHourMethod2.invoke(student2,24);
        System.out.println("student2 ::" + student2.getEndHour());

        //如果一个方法是私有方法，第三步是可以获取到的，但是这一步却不能执行
        //私有方法的执行，必须在调用invoke之前加上一句method.setAccessible（true）;

    }


    /**
     * 把类对象和类方法名作为参数，执行方法
     *
     * public String getStudentNameScope(String name,Integer score){}
     * 这样可以
     * 若是把 Integer 换成 int
     * 就会报错
     * Object obj = new Student();
     * int score =100;
     * Object objStr = invoke(obj,"getStudentNameScope","李四",score);
     * System.out.println(objStr);
     * 原因是 args[i].getClass()  获取的是 Integer
     */
    @Test
    public void test1() throws Exception{
        Object obj = new Student();
        Object objStr = invoke1(obj,"getStudentNameScope","李四",100);
        System.out.println(objStr);
    }

    /**
     *
     * @param obj: 方法执行的那个对象.
     * @param methodName: 类的一个方法的方法名. 该方法也可能是私有方法.
     * @param args: 调用该方法需要传入的参数
     * @return: 调用方法后的返回值
     *
     */
    public Object invoke1(Object obj, String methodName, Object ... args) throws Exception{
        //1. 获取 Method 对象
        //   因为getMethod的参数为Class列表类型，所以要把参数args转化为对应的Class类型。

        Class [] parameterTypes = new Class[args.length];
        for(int i = 0; i < args.length; i++){
            parameterTypes[i] = args[i].getClass();
            System.out.println(parameterTypes[i]);
        }

        Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
        //如果使用getDeclaredMethod，就不能获取父类方法，如果使用getMethod，就不能获取私有方法
        //2. 执行 Method 方法
        //3. 返回方法的返回值
        return method.invoke(obj, args);
    }



    /**
     * 把全类名和方法名作为参数，执行方法
     *
     * 这种反射实现的主要功能是可配置和低耦合。
     * 只需要类名和方法名，而不需要一个类对象就可以执行一个方法
     * 如果我们把全类名和方法名放在一个配置文件中，就可以根据调用配置文件来执行方法
     */
    @Test
    public void test2(){
        Object objStr = invoke2("com.lyp.learn.reflect.Student", "getStudentNameScope","test", 12);
        System.out.println(objStr);
    }

    /**
     * @param className: 某个类的全类名
     * @param methodName: 类的一个方法的方法名. 该方法也可能是私有方法.
     * @param args: 调用该方法需要传入的参数
     * @return: 调用方法后的返回值
     */
    public Object invoke2(String className, String methodName, Object ... args){
        Object obj = null;

        try {
            obj = Class.forName(className).newInstance();
            //调用上一个方法
            return invoke1(obj, methodName, args);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }




    @Test
    public void test3(){
        Object studentObj = new Student();
        String studentObjStr = (String) invoke3(studentObj,"getSchoolName","河南大学大学");
        System.out.println(studentObjStr);

        Object personObj = new Person();
        Object personObjStr = invoke3(personObj,"getPersonInfo",null);
        System.out.println(personObjStr);
    }

    /**
     * 定义一个方法，不但能访问当前类的私有方法，还要能父类的私有方法
     */

    /**
     *
     * @param obj: 某个类的一个对象
     * @param methodName: 类的一个方法的方法名.
     * 该方法也可能是私有方法, 还可能是该方法在父类中定义的(私有)方法
     * @param args: 调用该方法需要传入的参数
     * @return: 调用方法后的返回值
     */
    public Object invoke3(Object obj, String methodName, Object ... args){
        //1. 获取 Method 对象
        Class [] parameterTypes = new Class[args.length];
        for(int i = 0; i < args.length; i++){
            parameterTypes[i] = args[i].getClass();
        }

        try {
            Method method = getMethod(obj.getClass(), methodName, parameterTypes);
            method.setAccessible(true);
            //2. 执行 Method 方法
            //3. 返回方法的返回值
            return method.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取 clazz 的 methodName 方法. 该方法可能是私有方法, 还可能在父类中(私有方法)
     * 如果在该类中找不到此方法，就向他的父类找，一直到Object类为止
     * 这个方法的另一个作用是根据一个类名，一个方法名，追踪到并获得此方法
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     */
    public Method getMethod(Class clazz, String methodName, Class ... parameterTypes){
        for(;clazz != Object.class; clazz = clazz.getSuperclass()){
            try {
                Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {

            }
        }

        return null;
    }


    //那么我们在给Person类对象的age赋值时，是感觉不到注解的存在的
    @Test
    public void testAnnotation1() throws Exception{
        Person person = new Person();
        person.setAge(10);
        System.out.println(person.getAge());
    }

    //必须通过反射的方式为属性赋值，才能获取到注解
    /** Annotation 和 反射:
     * 1. 获取 Annotation
     *
     * getAnnotation(Class<T> annotationClass)
     * getDeclaredAnnotations()
     *
     * 如果在程序中要获取注解，
     * 然后获取注解的值进而判断我们赋值是否合法，
     * 那么类对象的创建和方法的创建必须是通过反射而来的
     *
     */
    @Test
    public void testAnnotation2() throws Exception{
        String className = "com.lyp.learn.reflect.Person";

        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();

        Method method = clazz.getDeclaredMethod("setAge", int.class);
        int val = 6;

        //获取指定名称的注解
        Annotation annotation = method.getAnnotation(AgeValidator.class);
        if(annotation != null){
            if(annotation instanceof AgeValidator){
                AgeValidator ageValidator = (AgeValidator) annotation;
                if(val < ageValidator.min() || val > ageValidator.max()){
                    throw new RuntimeException("年龄非法");
                }
            }
        }
        method.invoke(obj, 20);
        System.out.println(obj);
        System.out.println(((Person)obj).getAge());
    }
}
