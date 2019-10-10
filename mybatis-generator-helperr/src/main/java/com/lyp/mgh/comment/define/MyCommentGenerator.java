package com.lyp.mgh.comment.define;


import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;
import org.mybatis.generator.config.PropertyRegistry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

/**
 * 自定义实现 注释生成器 CommentGenerator 接口
 *
 * 有了自定义的CommentGenerator实现类之后，我们要使用它，只需要在配置文件中的generatorConfig.xml中，
 * 给commentGenerator元素设置type属性值为我们自定义类的全类名就可以了
 *       <commentGenerator type="com.lyp.mgh.comment.define.MyCommentGenerator">
 *          <property name="suppressAllComments" value="false" />  打开注释
 *
 */
public class MyCommentGenerator implements CommentGenerator {

    private Properties properties;
    private Properties systemPro;
    private boolean suppressDate;
    private boolean suppressAllComments;
    private String nowTime;

    public MyCommentGenerator() {
        super();
        properties = new Properties();
        systemPro = System.getProperties();
        suppressDate = false;
        suppressAllComments = false;
        nowTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }

    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (suppressAllComments) {
            return;
        }
        return;
    }

    /**
     * Adds a suitable comment to warn users that the element was generated, and
     * when it was generated.
     */
    public void addComment(XmlElement xmlElement) {
        return;
    }

    public void addRootComment(XmlElement rootElement) {
        // add no document level comments by default
        return;
    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {

    }

    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        innerClass.addAnnotation("@Data");
        innerClass.addAnnotation("@EqualsAndHashCode(callSuper = false)");
    }

    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);
        suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));
        suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }

    /**
     * 判断传入参数是否为true
     */
    private boolean isTrue(String property) {
        if ("true".equals(property)) {
            return true;
        }
        return false;
    }

    /**
     * This method adds the custom javadoc tag for. You may do nothing if you do
     * not wish to include the Javadoc tag - however, if you do not include the
     * Javadoc tag then the Java merge capability of the eclipse plugin will
     * break.
     *
     * @param javaElement the java element
     */
    protected void addJavadocTag(JavaElement javaElement, boolean markAsDoNotDelete) {
        javaElement.addJavaDocLine(" *");
        StringBuilder sb = new StringBuilder();
        sb.append(" * ");
        sb.append(MergeConstants.NEW_ELEMENT_TAG);
        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }
        String s = getDateString();
        if (s != null) {
            sb.append(' ');
            sb.append(s);
        }
        javaElement.addJavaDocLine(sb.toString());
    }

    /**
     * This method returns a formated date string to include in the Javadoc tag
     * and XML comments. You may return null if you do not want the date in
     * these documentation elements.
     *
     * @return a string representing the current timestamp, or null
     */
    protected String getDateString() {
        String result = null;
        if (!suppressDate) {
            result = nowTime;
        }
        return result;
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
//        if (suppressAllComments) {
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
//        innerClass.addJavaDocLine("/**");
//        sb.append(" * ");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        sb.append(" ");
//        sb.append(getDateString());
//        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
//        innerClass.addJavaDocLine(" */");
    }

    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
//        if (suppressAllComments) {
//            return;
//        }
//        StringBuilder sb = new StringBuilder();
//        innerClass.addJavaDocLine("/**");
//        sb.append(" * ");
//        sb.append(introspectedTable.getFullyQualifiedTable());
//        innerClass.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//        sb.append(" * @author ");
////        sb.append(systemPro.getProperty("user.name"));
//        sb.append(" ");
//        sb.append(nowTime);
//        innerClass.addJavaDocLine(" */");
    }

    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        innerEnum.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        innerEnum.addJavaDocLine(sb.toString().replace("\n", " "));
        innerEnum.addJavaDocLine(" */");
    }

    /**
     * 设置字段注释
     */
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        // 获取列注释
        String remarks = introspectedColumn.getRemarks();
        if (StringUtils.isBlank(remarks)) {
            field.addJavaDocLine("\n");
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(remarks);
//        sb.append(introspectedColumn.getRemarks() + " " + introspectedColumn.getActualColumnName());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");

    }

    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        field.addJavaDocLine("/**");
        sb.append(" * ");
        sb.append(introspectedTable.getFullyQualifiedTable());
        field.addJavaDocLine(sb.toString().replace("\n", " "));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
//        String author = properties.getProperty("author");
        String author = "liyapu";
        String dateFormat = properties.getProperty("dateFormat", "yyyy-MM-dd hh:mm:ss");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        // 获取表注释
        String remarks = introspectedTable.getRemarks();
        System.out.println("=========="+introspectedTable.getRecordWithBLOBsType());
        System.out.println("=========="+introspectedTable.getFullyQualifiedTableNameAtRuntime());
        System.out.println("=========="+introspectedTable.getFullyQualifiedTable());
        System.out.println("=========="+introspectedTable.getRemarks());
        System.out.println("=========="+introspectedTable.getBaseRecordType());
        System.out.println("=========="+introspectedTable.getRemarks());

        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + remarks);
        topLevelClass.addJavaDocLine(" *");
        topLevelClass.addJavaDocLine(" * @author " + author);
        topLevelClass.addJavaDocLine(" * @date " + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine(" */");

    }

    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
//        if (suppressAllComments) {
//            return;
//        }
//        method.addJavaDocLine("/**");
//        addJavadocTag(method, false);
//        method.addJavaDocLine(" */");
    }

    /**
     * 设置getter方法注释
     */
    public void addGetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
//        if (suppressAllComments) {
//            return;
//        }
//        method.addJavaDocLine("/**");
//        StringBuilder sb = new StringBuilder();
//        sb.append(" * ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//
//        //加入系统用户
//        sb.append(" * @author ");
//        sb.append(systemPro.getProperty("user.name"));
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//
//        //是否加入时间戳
//        if (suppressDate) {
//            sb.append(" * @date " + nowTime);
//            method.addJavaDocLine(sb.toString().replace("\n", " "));
//            sb.setLength(0);
//        }
//
//        sb.append(" * @return ");
//        sb.append(introspectedColumn.getActualColumnName());
//        sb.append(" ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        method.addJavaDocLine(" */");
    }

    /**
     * 设置setter方法注释
     */
    public void addSetterComment(Method method, IntrospectedTable introspectedTable,
                                 IntrospectedColumn introspectedColumn) {
//        if (suppressAllComments) {
//            return;
//        }
//        method.addJavaDocLine("/**");
//        StringBuilder sb = new StringBuilder();
//        sb.append(" * ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//
//        //加入系统用户
//        sb.append(" * @author ");
//        sb.append(systemPro.getProperty("user.name"));
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        sb.setLength(0);
//
//        //是否加入时间戳
//        if (suppressDate) {
//            sb.append(" * @date " + nowTime);
//            method.addJavaDocLine(sb.toString().replace("\n", " "));
//            sb.setLength(0);
//        }
//
//        Parameter parm = method.getParameters().get(0);
//        sb.append(" * @param ");
//        sb.append(parm.getName());
//        sb.append(" ");
//        sb.append(introspectedColumn.getRemarks());
//        method.addJavaDocLine(sb.toString().replace("\n", " "));
//        method.addJavaDocLine(" */");
    }



}