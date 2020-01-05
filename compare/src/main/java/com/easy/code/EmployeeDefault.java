//调试表原始对象
{
  "title" : "调试：com.intellij.database.psi.DbTableImpl",
  "methodList" : [ {
    "name" : "getDocumentation",
    "desc" : "public java.lang.CharSequence com.intellij.database.psi.DbTableImpl.getDocumentation()",
    "value" : "<html><body><b>Data Source:</b> mybatisplus@localhost<br><b>Schema:</b> mybatisplus<br><b>Table:</b> employee<br><br><font color=\"#808080\">-- 职员表</font><br><code><pre><font color=\"#808080\">-- auto-generated definition</font>\n<font color=\"#cc7832\">create</font> <font color=\"#cc7832\">table</font> employee\n(\n    id          <font color=\"#cc7832\">bigint</font> <font color=\"#cc7832\">auto_increment</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;主键&#39;</font>\n        <font color=\"#cc7832\">primary</font> <font color=\"#cc7832\">key</font><font color=\"#cc7832\">,</font>\n    <font color=\"#cc7832\">name</font>        <font color=\"#cc7832\">varchar</font>(<font color=\"#6897bb\">30</font>) <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;姓名&#39;</font><font color=\"#cc7832\">,</font>\n    age         <font color=\"#cc7832\">int</font>         <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;年龄&#39;</font><font color=\"#cc7832\">,</font>\n    email       <font color=\"#cc7832\">varchar</font>(<font color=\"#6897bb\">50</font>) <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;邮箱&#39;</font><font color=\"#cc7832\">,</font>\n    manager_id  <font color=\"#cc7832\">bigint</font>      <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;直属上级id&#39;</font><font color=\"#cc7832\">,</font>\n    create_time <font color=\"#cc7832\">datetime</font>    <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;创建时间&#39;</font>\n)\n    <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;职员表&#39;</font> <font color=\"#cc7832\">charset</font> = utf8<font color=\"#cc7832\">;</font>\n\n<font color=\"#cc7832\">create</font> <font color=\"#cc7832\">index</font> manager_fk\n    <font color=\"#cc7832\">on</font> employee (manager_id)<font color=\"#cc7832\">;</font></pre></code>"
  }, {
    "name" : "getDocumentation",
    "desc" : "public java.lang.StringBuilder com.intellij.database.psi.DbTableImpl.getDocumentation()",
    "value" : "<html><body><b>Data Source:</b> mybatisplus@localhost<br><b>Schema:</b> mybatisplus<br><b>Table:</b> employee<br><br><font color=\"#808080\">-- 职员表</font><br><code><pre><font color=\"#808080\">-- auto-generated definition</font>\n<font color=\"#cc7832\">create</font> <font color=\"#cc7832\">table</font> employee\n(\n    id          <font color=\"#cc7832\">bigint</font> <font color=\"#cc7832\">auto_increment</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;主键&#39;</font>\n        <font color=\"#cc7832\">primary</font> <font color=\"#cc7832\">key</font><font color=\"#cc7832\">,</font>\n    <font color=\"#cc7832\">name</font>        <font color=\"#cc7832\">varchar</font>(<font color=\"#6897bb\">30</font>) <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;姓名&#39;</font><font color=\"#cc7832\">,</font>\n    age         <font color=\"#cc7832\">int</font>         <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;年龄&#39;</font><font color=\"#cc7832\">,</font>\n    email       <font color=\"#cc7832\">varchar</font>(<font color=\"#6897bb\">50</font>) <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;邮箱&#39;</font><font color=\"#cc7832\">,</font>\n    manager_id  <font color=\"#cc7832\">bigint</font>      <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;直属上级id&#39;</font><font color=\"#cc7832\">,</font>\n    create_time <font color=\"#cc7832\">datetime</font>    <font color=\"#cc7832\">null</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;创建时间&#39;</font>\n)\n    <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;职员表&#39;</font> <font color=\"#cc7832\">charset</font> = utf8<font color=\"#cc7832\">;</font>\n\n<font color=\"#cc7832\">create</font> <font color=\"#cc7832\">index</font> manager_fk\n    <font color=\"#cc7832\">on</font> employee (manager_id)<font color=\"#cc7832\">;</font></pre></code>"
  }, {
    "name" : "getColumnAttrs",
    "desc" : "public java.util.Set<com.intellij.database.model.DasColumn$Attribute> com.intellij.database.psi.DbTableImpl.getColumnAttrs(com.intellij.database.model.DasColumn)",
    "value" : null
  }, {
    "name" : "isTemporary",
    "desc" : "public boolean com.intellij.database.psi.DbTableImpl.isTemporary()",
    "value" : "false"
  }, {
    "name" : "isSystem",
    "desc" : "public boolean com.intellij.database.psi.DbTableImpl.isSystem()",
    "value" : "false"
  }, {
    "name" : "getName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getName()",
    "value" : "employee"
  }, {
    "name" : "getName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getName(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "getTypeName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getTypeName()",
    "value" : "table"
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.psi.PsiFileSystemItem com.intellij.database.psi.DbElementImpl.getParent()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.database.psi.DbElementImpl.getParent()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.database.psi.DbElement com.intellij.database.psi.DbElementImpl.getParent()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "init",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.init(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "setName",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.database.psi.DbElementImpl.setName(java.lang.String) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "getLanguage",
    "desc" : "public com.intellij.lang.Language com.intellij.database.psi.DbElementImpl.getLanguage()",
    "value" : "Language: SQL"
  }, {
    "name" : "createDeclarationProcessor",
    "desc" : "public com.intellij.util.Processor<com.intellij.database.psi.DbElement> com.intellij.database.psi.DbElementImpl.createDeclarationProcessor(com.intellij.psi.scope.PsiScopeProcessor,com.intellij.psi.ResolveState,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "newDeclarationProcessor",
    "desc" : "public static com.intellij.util.Processor<com.intellij.database.psi.DbElement> com.intellij.database.psi.DbElementImpl.newDeclarationProcessor(com.intellij.psi.scope.PsiScopeProcessor,com.intellij.psi.ResolveState,com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "getLocationString",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getLocationString()",
    "value" : "mybatisplus [mybatisplus@localhost]"
  }, {
    "name" : "getDataSource",
    "desc" : "public com.intellij.database.psi.DbDataSource com.intellij.database.psi.DbElementImpl.getDataSource()",
    "value" : "root:mybatisplus@localhost"
  }, {
    "name" : "getDataSource",
    "desc" : "public com.intellij.database.psi.DbDataSourceImpl com.intellij.database.psi.DbElementImpl.getDataSource()",
    "value" : "root:mybatisplus@localhost"
  }, {
    "name" : "acceptChildren",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.acceptChildren(com.intellij.psi.PsiElementVisitor)",
    "value" : null
  }, {
    "name" : "processDeclarations",
    "desc" : "public final boolean com.intellij.database.psi.DbElementImpl.processDeclarations(com.intellij.psi.scope.PsiScopeProcessor,com.intellij.psi.ResolveState,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "checkSetName",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.checkSetName(java.lang.String) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "canNavigate",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.canNavigate()",
    "value" : null
  }, {
    "name" : "canNavigateToSource",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.canNavigateToSource()",
    "value" : null
  }, {
    "name" : "getBaseIcon",
    "desc" : "public javax.swing.Icon com.intellij.database.psi.DbElementImpl.getBaseIcon()",
    "value" : "jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/DatabaseTools/lib/database-openapi.jar!/icons/table.svg"
  }, {
    "name" : "getDasChildren",
    "desc" : "public com.intellij.util.containers.JBIterable<com.intellij.database.psi.DbElement> com.intellij.database.psi.DbElementImpl.getDasChildren(com.intellij.database.model.ObjectKind)",
    "value" : null
  }, {
    "name" : "getDasParent",
    "desc" : "public P com.intellij.database.psi.DbElementImpl.getDasParent()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "getDasParent",
    "desc" : "public com.intellij.database.model.DasObject com.intellij.database.psi.DbElementImpl.getDasParent()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "navigate",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.navigate(boolean)",
    "value" : null
  }, {
    "name" : "navigate",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.navigate(boolean,java.lang.String[],com.intellij.database.util.DbImplUtil$TriConsumer<com.intellij.database.util.DdlBuilder, java.util.List<com.intellij.database.model.DasColumn>, com.intellij.database.Dbms>)",
    "value" : null
  }, {
    "name" : "navigate",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.navigate(boolean,java.lang.String[],boolean,com.intellij.database.util.DbImplUtil$TriConsumer<com.intellij.database.util.DdlBuilder, java.util.List<com.intellij.database.model.DasColumn>, com.intellij.database.Dbms>)",
    "value" : null
  }, {
    "name" : "getText",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getText()",
    "value" : "create table employee\n(\n    id          bigint auto_increment comment '主键'\n        primary key,\n    name        varchar(30) null comment '姓名',\n    age         int         null comment '年龄',\n    email       varchar(50) null comment '邮箱',\n    manager_id  bigint      null comment '直属上级id',\n    create_time datetime    null comment '创建时间'\n)\n    comment '职员表' charset = utf8;\n\ncreate index manager_fk\n    on employee (manager_id);\n\n"
  }, {
    "name" : "getKind",
    "desc" : "public com.intellij.database.model.ObjectKind com.intellij.database.psi.DbElementImpl.getKind()",
    "value" : "table"
  }, {
    "name" : "getProject",
    "desc" : "public com.intellij.openapi.project.Project com.intellij.database.psi.DbElementImpl.getProject()",
    "value" : "Project '/Users/liyapu/myGitRepository/atguiguspringboot' atguiguspringboot"
  }, {
    "name" : "getDelegate",
    "desc" : "public D com.intellij.database.psi.DbElementImpl.getDelegate()",
    "value" : "employee: table"
  }, {
    "name" : "processChildren",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.processChildren(com.intellij.psi.search.PsiElementProcessor<com.intellij.psi.PsiFileSystemItem>)",
    "value" : null
  }, {
    "name" : "getMetaData",
    "desc" : "public com.intellij.psi.meta.PsiMetaData com.intellij.database.psi.DbElementImpl.getMetaData()",
    "value" : "table:employee"
  }, {
    "name" : "isCaseSensitive",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isCaseSensitive()",
    "value" : "false"
  }, {
    "name" : "getVirtualFile",
    "desc" : "public com.intellij.openapi.vfs.VirtualFile com.intellij.database.psi.DbElementImpl.getVirtualFile()",
    "value" : "DB VirtualFile: table, mybatisplus.employee [mybatisplus@localhost] (Valid)"
  }, {
    "name" : "getIcon",
    "desc" : "public javax.swing.Icon com.intellij.database.psi.DbElementImpl.getIcon()",
    "value" : "Deferred. Base=Row icon. myIcons=[jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/DatabaseTools/lib/database-openapi.jar!/icons/table.svg, null]"
  }, {
    "name" : "getIcon",
    "desc" : "public javax.swing.Icon com.intellij.database.psi.DbElementImpl.getIcon(boolean)",
    "value" : null
  }, {
    "name" : "getManager",
    "desc" : "public com.intellij.psi.PsiManager com.intellij.database.psi.DbElementImpl.getManager()",
    "value" : "com.intellij.psi.impl.PsiManagerImpl@3722d0ff"
  }, {
    "name" : "getComment",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getComment()",
    "value" : "职员表"
  }, {
    "name" : "getChildren",
    "desc" : "public com.intellij.psi.PsiElement[] com.intellij.database.psi.DbElementImpl.getChildren()",
    "value" : "[Lcom.intellij.psi.PsiElement;@25540ce"
  }, {
    "name" : "isWritable",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isWritable()",
    "value" : "true"
  }, {
    "name" : "getPosition",
    "desc" : "public short com.intellij.database.psi.DbElementImpl.getPosition()",
    "value" : "0"
  }, {
    "name" : "isValid",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isValid()",
    "value" : "true"
  }, {
    "name" : "getWeight",
    "desc" : "public int com.intellij.database.psi.DbElementImpl.getWeight()",
    "value" : "10000"
  }, {
    "name" : "isDirectory",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isDirectory()",
    "value" : "false"
  }, {
    "name" : "getDeclaration",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.database.psi.DbElementImpl.getDeclaration()",
    "value" : "table:employee"
  }, {
    "name" : "getNode",
    "desc" : "public com.intellij.lang.ASTNode com.intellij.psi.impl.FakePsiElement.getNode()",
    "value" : null
  }, {
    "name" : "getTextRange",
    "desc" : "public com.intellij.openapi.util.TextRange com.intellij.psi.impl.FakePsiElement.getTextRange()",
    "value" : null
  }, {
    "name" : "getStartOffsetInParent",
    "desc" : "public int com.intellij.psi.impl.FakePsiElement.getStartOffsetInParent()",
    "value" : "0"
  }, {
    "name" : "getTextOffset",
    "desc" : "public int com.intellij.psi.impl.FakePsiElement.getTextOffset()",
    "value" : "0"
  }, {
    "name" : "textToCharArray",
    "desc" : "public char[] com.intellij.psi.impl.FakePsiElement.textToCharArray()",
    "value" : null
  }, {
    "name" : "textContains",
    "desc" : "public boolean com.intellij.psi.impl.FakePsiElement.textContains(char)",
    "value" : null
  }, {
    "name" : "getPresentableText",
    "desc" : "public java.lang.String com.intellij.psi.impl.FakePsiElement.getPresentableText()",
    "value" : "employee"
  }, {
    "name" : "isPhysical",
    "desc" : "public boolean com.intellij.psi.impl.FakePsiElement.isPhysical()",
    "value" : "false"
  }, {
    "name" : "getPresentation",
    "desc" : "public com.intellij.navigation.ItemPresentation com.intellij.psi.impl.FakePsiElement.getPresentation()",
    "value" : "table:employee"
  }, {
    "name" : "getPrevSibling",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getPrevSibling()",
    "value" : null
  }, {
    "name" : "getFirstChild",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getFirstChild()",
    "value" : null
  }, {
    "name" : "getNextSibling",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getNextSibling()",
    "value" : null
  }, {
    "name" : "findElementAt",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.findElementAt(int)",
    "value" : null
  }, {
    "name" : "getTextLength",
    "desc" : "public int com.intellij.psi.impl.FakePsiElement.getTextLength()",
    "value" : "0"
  }, {
    "name" : "getIcon",
    "desc" : "public final javax.swing.Icon com.intellij.psi.impl.FakePsiElement.getIcon(int)",
    "value" : null
  }, {
    "name" : "getLastChild",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getLastChild()",
    "value" : null
  }, {
    "name" : "add",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.add(com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "replace",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.replace(com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "checkDelete",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.checkDelete() throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "delete",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.delete() throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "accept",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.accept(com.intellij.psi.PsiElementVisitor)",
    "value" : null
  }, {
    "name" : "getContext",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.getContext()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "copy",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.copy()",
    "value" : null
  }, {
    "name" : "findReferenceAt",
    "desc" : "public com.intellij.psi.PsiReference com.intellij.psi.impl.PsiElementBase.findReferenceAt(int)",
    "value" : null
  }, {
    "name" : "getNavigationElement",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.getNavigationElement()",
    "value" : "table:employee"
  }, {
    "name" : "getOriginalElement",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.getOriginalElement()",
    "value" : "table:employee"
  }, {
    "name" : "textMatches",
    "desc" : "public boolean com.intellij.psi.impl.PsiElementBase.textMatches(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "textMatches",
    "desc" : "public boolean com.intellij.psi.impl.PsiElementBase.textMatches(java.lang.CharSequence)",
    "value" : null
  }, {
    "name" : "addRangeBefore",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addRangeBefore(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "addRangeAfter",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addRangeAfter(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "deleteChildRange",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.deleteChildRange(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "getReference",
    "desc" : "public com.intellij.psi.PsiReference com.intellij.psi.impl.PsiElementBase.getReference()",
    "value" : null
  }, {
    "name" : "getResolveScope",
    "desc" : "public com.intellij.psi.search.GlobalSearchScope com.intellij.psi.impl.PsiElementBase.getResolveScope()",
    "value" : "Project and Libraries"
  }, {
    "name" : "getUseScope",
    "desc" : "public com.intellij.psi.search.SearchScope com.intellij.psi.impl.PsiElementBase.getUseScope()",
    "value" : "Project and Libraries"
  }, {
    "name" : "isEquivalentTo",
    "desc" : "public boolean com.intellij.psi.impl.PsiElementBase.isEquivalentTo(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "checkAdd",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.checkAdd(com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "addBefore",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addBefore(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "addAfter",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addAfter(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "getContainingFile",
    "desc" : "public com.intellij.psi.PsiFile com.intellij.psi.impl.PsiElementBase.getContainingFile()",
    "value" : null
  }, {
    "name" : "getReferences",
    "desc" : "public com.intellij.psi.PsiReference[] com.intellij.psi.impl.PsiElementBase.getReferences()",
    "value" : "[Lcom.intellij.psi.PsiReference;@7c6ccb1b"
  }, {
    "name" : "addRange",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addRange(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "isNativeFileType",
    "desc" : "public static boolean com.intellij.psi.impl.ElementBase.isNativeFileType(com.intellij.openapi.fileTypes.FileType)",
    "value" : null
  }, {
    "name" : "overlayIcons",
    "desc" : "public static javax.swing.Icon com.intellij.psi.impl.ElementBase.overlayIcons(javax.swing.Icon...)",
    "value" : null
  }, {
    "name" : "buildRowIcon",
    "desc" : "public static com.intellij.ui.icons.RowIcon com.intellij.psi.impl.ElementBase.buildRowIcon(javax.swing.Icon,javax.swing.Icon)",
    "value" : null
  }, {
    "name" : "iconWithVisibilityIfNeeded",
    "desc" : "public static javax.swing.Icon com.intellij.psi.impl.ElementBase.iconWithVisibilityIfNeeded(int,javax.swing.Icon,javax.swing.Icon)",
    "value" : null
  }, {
    "name" : "transformFlags",
    "desc" : "public static int com.intellij.psi.impl.ElementBase.transformFlags(com.intellij.psi.PsiElement,int)",
    "value" : null
  }, {
    "name" : "createLayeredIcon",
    "desc" : "public static com.intellij.ui.RowIcon com.intellij.psi.impl.ElementBase.createLayeredIcon(com.intellij.openapi.util.Iconable,javax.swing.Icon,int)",
    "value" : null
  }, {
    "name" : "registerIconLayer",
    "desc" : "public static void com.intellij.psi.impl.ElementBase.registerIconLayer(int,javax.swing.Icon)",
    "value" : null
  }, {
    "name" : "replace",
    "desc" : "public <T> boolean com.intellij.openapi.util.UserDataHolderBase.replace(com.intellij.openapi.util.Key<T>,T,T)",
    "value" : null
  }, {
    "name" : "getUserData",
    "desc" : "public <T> T com.intellij.openapi.util.UserDataHolderBase.getUserData(com.intellij.openapi.util.Key<T>)",
    "value" : null
  }, {
    "name" : "putUserData",
    "desc" : "public <T> void com.intellij.openapi.util.UserDataHolderBase.putUserData(com.intellij.openapi.util.Key<T>,T)",
    "value" : null
  }, {
    "name" : "getUserDataString",
    "desc" : "public java.lang.String com.intellij.openapi.util.UserDataHolderBase.getUserDataString()",
    "value" : "{COLOR_KEY=com.intellij.database.view.DatabaseColorManager$CachedColor@59750e44, CACHED_SMART_POINTER_KEY=com.intellij.reference.SoftReference@216bd440, lastComputedIcon={0=Row icon. myIcons=[jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/DatabaseTools/lib/database-openapi.jar!/icons/table.svg, null]}}"
  }, {
    "name" : "copyUserDataTo",
    "desc" : "public void com.intellij.openapi.util.UserDataHolderBase.copyUserDataTo(com.intellij.openapi.util.UserDataHolderBase)",
    "value" : null
  }, {
    "name" : "getCopyableUserData",
    "desc" : "public <T> T com.intellij.openapi.util.UserDataHolderBase.getCopyableUserData(com.intellij.openapi.util.Key<T>)",
    "value" : null
  }, {
    "name" : "putCopyableUserData",
    "desc" : "public <T> void com.intellij.openapi.util.UserDataHolderBase.putCopyableUserData(com.intellij.openapi.util.Key<T>,T)",
    "value" : null
  }, {
    "name" : "putUserDataIfAbsent",
    "desc" : "public <T> T com.intellij.openapi.util.UserDataHolderBase.putUserDataIfAbsent(com.intellij.openapi.util.Key<T>,T)",
    "value" : null
  }, {
    "name" : "copyCopyableDataTo",
    "desc" : "public void com.intellij.openapi.util.UserDataHolderBase.copyCopyableDataTo(com.intellij.openapi.util.UserDataHolderBase)",
    "value" : null
  }, {
    "name" : "isUserDataEmpty",
    "desc" : "public boolean com.intellij.openapi.util.UserDataHolderBase.isUserDataEmpty()",
    "value" : "false"
  }, {
    "name" : "getTextRangeInParent",
    "desc" : "public default com.intellij.openapi.util.TextRange com.intellij.psi.PsiElement.getTextRangeInParent()",
    "value" : "(0,0)"
  }, {
    "name" : "getDbParent",
    "desc" : "public default com.intellij.database.model.DasObject com.intellij.database.model.DasObject.getDbParent()",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "getDbChildren",
    "desc" : "public default <C> com.intellij.util.containers.JBIterable<C> com.intellij.database.model.DasObject.getDbChildren(java.lang.Class<C>,com.intellij.database.model.ObjectKind)",
    "value" : null
  }, {
    "name" : "getDependences",
    "desc" : "public default java.lang.Object[] com.intellij.psi.meta.PsiMetaData.getDependences()",
    "value" : "[Ljava.lang.Object;@990b393"
  }, {
    "name" : "getDependencies",
    "desc" : "public default java.lang.Object[] com.intellij.psi.meta.PsiMetaData.getDependencies()",
    "value" : "[Ljava.lang.Object;@990b393"
  } ],
  "----" : "-----------------我是一条华丽的分割线-----------------",
  "fieldList" : [ {
    "name" : "LOG",
    "type" : "com.intellij.openapi.diagnostic.Logger",
    "value" : "com.intellij.idea.IdeaLogger@768ac725"
  }, {
    "name" : "WEIGHT_BASE",
    "type" : "int",
    "value" : "10000"
  }, {
    "name" : "WEIGHT_INC",
    "type" : "int",
    "value" : "1000"
  }, {
    "name" : "myParent",
    "type" : "com.intellij.database.psi.DbElement",
    "value" : "schema:mybatisplus"
  }, {
    "name" : "myDelegate",
    "type" : "java.lang.Object",
    "value" : "employee: table"
  }, {
    "name" : "myTransactionalVersion",
    "type" : "long",
    "value" : "13"
  }, {
    "name" : "ASYNC_DOC_CUT",
    "type" : "java.lang.String",
    "value" : "<!-- async-doc-cut -->"
  }, {
    "name" : "LOG",
    "type" : "com.intellij.openapi.diagnostic.Logger",
    "value" : "com.intellij.idea.IdeaLogger@39fd0272"
  }, {
    "name" : "LOG",
    "type" : "com.intellij.openapi.diagnostic.Logger",
    "value" : "com.intellij.idea.IdeaLogger@5cbddbd8"
  }, {
    "name" : "FLAGS_LOCKED",
    "type" : "int",
    "value" : "2048"
  }, {
    "name" : "ICON_COMPUTE",
    "type" : "java.util.function.Function",
    "value" : "com.intellij.psi.impl.ElementBase$$Lambda$1445/0x00000008013fac40@7cae8c85"
  }, {
    "name" : "VISIBILITY_ICON_PLACEHOLDER",
    "type" : "com.intellij.openapi.util.NotNullLazyValue",
    "value" : "com.intellij.psi.impl.ElementBase$1@2aba5b44"
  }, {
    "name" : "ICON_PLACEHOLDER",
    "type" : "com.intellij.openapi.util.NotNullLazyValue",
    "value" : "com.intellij.psi.impl.ElementBase$2@7b8522f3"
  }, {
    "name" : "COPYABLE_USER_MAP_KEY",
    "type" : "com.intellij.openapi.util.Key",
    "value" : "COPYABLE_USER_MAP_KEY"
  }, {
    "name" : "myUserMap",
    "type" : "com.intellij.util.keyFMap.KeyFMap",
    "value" : "{COLOR_KEY=com.intellij.database.view.DatabaseColorManager$CachedColor@59750e44, CACHED_SMART_POINTER_KEY=com.intellij.reference.SoftReference@216bd440, lastComputedIcon={0=Row icon. myIcons=[jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/DatabaseTools/lib/database-openapi.jar!/icons/table.svg, null]}}"
  }, {
    "name" : "updater",
    "type" : "com.intellij.util.concurrency.AtomicFieldUpdater",
    "value" : "com.intellij.util.concurrency.AtomicFieldUpdater@49544540"
  } ]
}

//调试列原始对象
{
  "title" : "调试：com.intellij.database.psi.DbColumnImpl",
  "methodList" : [ {
    "name" : "getDefault",
    "desc" : "public java.lang.String com.intellij.database.psi.DbColumnImpl.getDefault()",
    "value" : null
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.database.psi.DbTable com.intellij.database.psi.DbColumnImpl.getParent()",
    "value" : "table:employee"
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.database.psi.DbColumnImpl.getParent()",
    "value" : "table:employee"
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.database.psi.DbElement com.intellij.database.psi.DbColumnImpl.getParent()",
    "value" : "table:employee"
  }, {
    "name" : "getParent",
    "desc" : "public com.intellij.psi.PsiFileSystemItem com.intellij.database.psi.DbColumnImpl.getParent()",
    "value" : "table:employee"
  }, {
    "name" : "getDocumentation",
    "desc" : "public java.lang.CharSequence com.intellij.database.psi.DbColumnImpl.getDocumentation()",
    "value" : "<html><body><b>Data Source:</b> mybatisplus@localhost<br><b>Schema:</b> mybatisplus<br><b>Table:</b> employee<br><b>Column:</b> id<br><br><font color=\"#808080\">-- 主键</font><br><code><pre><font color=\"#cc7832\">alter</font> <font color=\"#cc7832\">table</font> employee\n    <font color=\"#cc7832\">add</font> id <font color=\"#cc7832\">bigint</font> <font color=\"#cc7832\">auto_increment</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;主键&#39;</font><font color=\"#cc7832\">;</font>\n\n</pre></code>"
  }, {
    "name" : "getDocumentation",
    "desc" : "public java.lang.StringBuilder com.intellij.database.psi.DbColumnImpl.getDocumentation()",
    "value" : "<html><body><b>Data Source:</b> mybatisplus@localhost<br><b>Schema:</b> mybatisplus<br><b>Table:</b> employee<br><b>Column:</b> id<br><br><font color=\"#808080\">-- 主键</font><br><code><pre><font color=\"#cc7832\">alter</font> <font color=\"#cc7832\">table</font> employee\n    <font color=\"#cc7832\">add</font> id <font color=\"#cc7832\">bigint</font> <font color=\"#cc7832\">auto_increment</font> <font color=\"#cc7832\">comment</font> <font color=\"#6a8759\">&#39;主键&#39;</font><font color=\"#cc7832\">;</font>\n\n</pre></code>"
  }, {
    "name" : "getTableName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbColumnImpl.getTableName()",
    "value" : "employee"
  }, {
    "name" : "isNotNull",
    "desc" : "public boolean com.intellij.database.psi.DbColumnImpl.isNotNull()",
    "value" : "true"
  }, {
    "name" : "getDataType",
    "desc" : "public com.intellij.database.model.DataType com.intellij.database.psi.DbColumnImpl.getDataType()",
    "value" : "bigint(20)"
  }, {
    "name" : "getWeight",
    "desc" : "public int com.intellij.database.psi.DbColumnImpl.getWeight()",
    "value" : "10030"
  }, {
    "name" : "getTable",
    "desc" : "public com.intellij.database.psi.DbTable com.intellij.database.psi.DbColumnImpl.getTable()",
    "value" : "table:employee"
  }, {
    "name" : "getTable",
    "desc" : "public com.intellij.database.model.DasTable com.intellij.database.psi.DbColumnImpl.getTable()",
    "value" : "table:employee"
  }, {
    "name" : "getName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getName()",
    "value" : "id"
  }, {
    "name" : "getName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getName(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "getTypeName",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getTypeName()",
    "value" : "column"
  }, {
    "name" : "init",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.init(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "setName",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.database.psi.DbElementImpl.setName(java.lang.String) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "getLanguage",
    "desc" : "public com.intellij.lang.Language com.intellij.database.psi.DbElementImpl.getLanguage()",
    "value" : "Language: SQL"
  }, {
    "name" : "createDeclarationProcessor",
    "desc" : "public com.intellij.util.Processor<com.intellij.database.psi.DbElement> com.intellij.database.psi.DbElementImpl.createDeclarationProcessor(com.intellij.psi.scope.PsiScopeProcessor,com.intellij.psi.ResolveState,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "newDeclarationProcessor",
    "desc" : "public static com.intellij.util.Processor<com.intellij.database.psi.DbElement> com.intellij.database.psi.DbElementImpl.newDeclarationProcessor(com.intellij.psi.scope.PsiScopeProcessor,com.intellij.psi.ResolveState,com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "getLocationString",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getLocationString()",
    "value" : "mybatisplus.employee [mybatisplus@localhost]"
  }, {
    "name" : "getDataSource",
    "desc" : "public com.intellij.database.psi.DbDataSource com.intellij.database.psi.DbElementImpl.getDataSource()",
    "value" : "root:mybatisplus@localhost"
  }, {
    "name" : "getDataSource",
    "desc" : "public com.intellij.database.psi.DbDataSourceImpl com.intellij.database.psi.DbElementImpl.getDataSource()",
    "value" : "root:mybatisplus@localhost"
  }, {
    "name" : "acceptChildren",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.acceptChildren(com.intellij.psi.PsiElementVisitor)",
    "value" : null
  }, {
    "name" : "processDeclarations",
    "desc" : "public final boolean com.intellij.database.psi.DbElementImpl.processDeclarations(com.intellij.psi.scope.PsiScopeProcessor,com.intellij.psi.ResolveState,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "checkSetName",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.checkSetName(java.lang.String) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "canNavigate",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.canNavigate()",
    "value" : null
  }, {
    "name" : "canNavigateToSource",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.canNavigateToSource()",
    "value" : null
  }, {
    "name" : "getBaseIcon",
    "desc" : "public javax.swing.Icon com.intellij.database.psi.DbElementImpl.getBaseIcon()",
    "value" : "jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/DatabaseTools/lib/database-openapi.jar!/icons/col.svg"
  }, {
    "name" : "getDasChildren",
    "desc" : "public com.intellij.util.containers.JBIterable<com.intellij.database.psi.DbElement> com.intellij.database.psi.DbElementImpl.getDasChildren(com.intellij.database.model.ObjectKind)",
    "value" : null
  }, {
    "name" : "getDasParent",
    "desc" : "public P com.intellij.database.psi.DbElementImpl.getDasParent()",
    "value" : "table:employee"
  }, {
    "name" : "getDasParent",
    "desc" : "public com.intellij.database.model.DasObject com.intellij.database.psi.DbElementImpl.getDasParent()",
    "value" : "table:employee"
  }, {
    "name" : "navigate",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.navigate(boolean)",
    "value" : null
  }, {
    "name" : "navigate",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.navigate(boolean,java.lang.String[],com.intellij.database.util.DbImplUtil$TriConsumer<com.intellij.database.util.DdlBuilder, java.util.List<com.intellij.database.model.DasColumn>, com.intellij.database.Dbms>)",
    "value" : null
  }, {
    "name" : "navigate",
    "desc" : "public void com.intellij.database.psi.DbElementImpl.navigate(boolean,java.lang.String[],boolean,com.intellij.database.util.DbImplUtil$TriConsumer<com.intellij.database.util.DdlBuilder, java.util.List<com.intellij.database.model.DasColumn>, com.intellij.database.Dbms>)",
    "value" : null
  }, {
    "name" : "getText",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getText()",
    "value" : "alter table employee\n    add id bigint auto_increment comment '主键';\n\n"
  }, {
    "name" : "getKind",
    "desc" : "public com.intellij.database.model.ObjectKind com.intellij.database.psi.DbElementImpl.getKind()",
    "value" : "column"
  }, {
    "name" : "getProject",
    "desc" : "public com.intellij.openapi.project.Project com.intellij.database.psi.DbElementImpl.getProject()",
    "value" : "Project '/Users/liyapu/myGitRepository/atguiguspringboot' atguiguspringboot"
  }, {
    "name" : "getDelegate",
    "desc" : "public D com.intellij.database.psi.DbElementImpl.getDelegate()",
    "value" : "id: column"
  }, {
    "name" : "processChildren",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.processChildren(com.intellij.psi.search.PsiElementProcessor<com.intellij.psi.PsiFileSystemItem>)",
    "value" : null
  }, {
    "name" : "getMetaData",
    "desc" : "public com.intellij.psi.meta.PsiMetaData com.intellij.database.psi.DbElementImpl.getMetaData()",
    "value" : "column:id"
  }, {
    "name" : "isCaseSensitive",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isCaseSensitive()",
    "value" : "false"
  }, {
    "name" : "getVirtualFile",
    "desc" : "public com.intellij.openapi.vfs.VirtualFile com.intellij.database.psi.DbElementImpl.getVirtualFile()",
    "value" : "DB VirtualFile: column, mybatisplus.employee.id [mybatisplus@localhost] (Valid)"
  }, {
    "name" : "getIcon",
    "desc" : "public javax.swing.Icon com.intellij.database.psi.DbElementImpl.getIcon()",
    "value" : "Deferred. Base=jar:file:/Applications/IntelliJ%20IDEA.app/Contents/plugins/DatabaseTools/lib/database-openapi.jar!/icons/col.svg"
  }, {
    "name" : "getIcon",
    "desc" : "public javax.swing.Icon com.intellij.database.psi.DbElementImpl.getIcon(boolean)",
    "value" : null
  }, {
    "name" : "getManager",
    "desc" : "public com.intellij.psi.PsiManager com.intellij.database.psi.DbElementImpl.getManager()",
    "value" : "com.intellij.psi.impl.PsiManagerImpl@3722d0ff"
  }, {
    "name" : "getComment",
    "desc" : "public java.lang.String com.intellij.database.psi.DbElementImpl.getComment()",
    "value" : "主键"
  }, {
    "name" : "getChildren",
    "desc" : "public com.intellij.psi.PsiElement[] com.intellij.database.psi.DbElementImpl.getChildren()",
    "value" : "[Lcom.intellij.psi.PsiElement;@25540ce"
  }, {
    "name" : "isWritable",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isWritable()",
    "value" : "true"
  }, {
    "name" : "getPosition",
    "desc" : "public short com.intellij.database.psi.DbElementImpl.getPosition()",
    "value" : "1"
  }, {
    "name" : "isValid",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isValid()",
    "value" : "true"
  }, {
    "name" : "isDirectory",
    "desc" : "public boolean com.intellij.database.psi.DbElementImpl.isDirectory()",
    "value" : "false"
  }, {
    "name" : "getDeclaration",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.database.psi.DbElementImpl.getDeclaration()",
    "value" : "column:id"
  }, {
    "name" : "getNode",
    "desc" : "public com.intellij.lang.ASTNode com.intellij.psi.impl.FakePsiElement.getNode()",
    "value" : null
  }, {
    "name" : "getTextRange",
    "desc" : "public com.intellij.openapi.util.TextRange com.intellij.psi.impl.FakePsiElement.getTextRange()",
    "value" : null
  }, {
    "name" : "getStartOffsetInParent",
    "desc" : "public int com.intellij.psi.impl.FakePsiElement.getStartOffsetInParent()",
    "value" : "0"
  }, {
    "name" : "getTextOffset",
    "desc" : "public int com.intellij.psi.impl.FakePsiElement.getTextOffset()",
    "value" : "0"
  }, {
    "name" : "textToCharArray",
    "desc" : "public char[] com.intellij.psi.impl.FakePsiElement.textToCharArray()",
    "value" : null
  }, {
    "name" : "textContains",
    "desc" : "public boolean com.intellij.psi.impl.FakePsiElement.textContains(char)",
    "value" : null
  }, {
    "name" : "getPresentableText",
    "desc" : "public java.lang.String com.intellij.psi.impl.FakePsiElement.getPresentableText()",
    "value" : "id"
  }, {
    "name" : "isPhysical",
    "desc" : "public boolean com.intellij.psi.impl.FakePsiElement.isPhysical()",
    "value" : "false"
  }, {
    "name" : "getPresentation",
    "desc" : "public com.intellij.navigation.ItemPresentation com.intellij.psi.impl.FakePsiElement.getPresentation()",
    "value" : "column:id"
  }, {
    "name" : "getPrevSibling",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getPrevSibling()",
    "value" : null
  }, {
    "name" : "getFirstChild",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getFirstChild()",
    "value" : null
  }, {
    "name" : "getNextSibling",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getNextSibling()",
    "value" : null
  }, {
    "name" : "findElementAt",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.findElementAt(int)",
    "value" : null
  }, {
    "name" : "getTextLength",
    "desc" : "public int com.intellij.psi.impl.FakePsiElement.getTextLength()",
    "value" : "0"
  }, {
    "name" : "getIcon",
    "desc" : "public final javax.swing.Icon com.intellij.psi.impl.FakePsiElement.getIcon(int)",
    "value" : null
  }, {
    "name" : "getLastChild",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.FakePsiElement.getLastChild()",
    "value" : null
  }, {
    "name" : "add",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.add(com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "replace",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.replace(com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "checkDelete",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.checkDelete() throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "delete",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.delete() throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "accept",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.accept(com.intellij.psi.PsiElementVisitor)",
    "value" : null
  }, {
    "name" : "getContext",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.getContext()",
    "value" : "table:employee"
  }, {
    "name" : "copy",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.copy()",
    "value" : null
  }, {
    "name" : "findReferenceAt",
    "desc" : "public com.intellij.psi.PsiReference com.intellij.psi.impl.PsiElementBase.findReferenceAt(int)",
    "value" : null
  }, {
    "name" : "getNavigationElement",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.getNavigationElement()",
    "value" : "column:id"
  }, {
    "name" : "getOriginalElement",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.getOriginalElement()",
    "value" : "column:id"
  }, {
    "name" : "textMatches",
    "desc" : "public boolean com.intellij.psi.impl.PsiElementBase.textMatches(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "textMatches",
    "desc" : "public boolean com.intellij.psi.impl.PsiElementBase.textMatches(java.lang.CharSequence)",
    "value" : null
  }, {
    "name" : "addRangeBefore",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addRangeBefore(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "addRangeAfter",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addRangeAfter(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "deleteChildRange",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.deleteChildRange(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "getReference",
    "desc" : "public com.intellij.psi.PsiReference com.intellij.psi.impl.PsiElementBase.getReference()",
    "value" : null
  }, {
    "name" : "getResolveScope",
    "desc" : "public com.intellij.psi.search.GlobalSearchScope com.intellij.psi.impl.PsiElementBase.getResolveScope()",
    "value" : "Project and Libraries"
  }, {
    "name" : "getUseScope",
    "desc" : "public com.intellij.psi.search.SearchScope com.intellij.psi.impl.PsiElementBase.getUseScope()",
    "value" : "Project and Libraries"
  }, {
    "name" : "isEquivalentTo",
    "desc" : "public boolean com.intellij.psi.impl.PsiElementBase.isEquivalentTo(com.intellij.psi.PsiElement)",
    "value" : null
  }, {
    "name" : "checkAdd",
    "desc" : "public void com.intellij.psi.impl.PsiElementBase.checkAdd(com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "addBefore",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addBefore(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "addAfter",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addAfter(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "getContainingFile",
    "desc" : "public com.intellij.psi.PsiFile com.intellij.psi.impl.PsiElementBase.getContainingFile()",
    "value" : null
  }, {
    "name" : "getReferences",
    "desc" : "public com.intellij.psi.PsiReference[] com.intellij.psi.impl.PsiElementBase.getReferences()",
    "value" : "[Lcom.intellij.psi.PsiReference;@7c6ccb1b"
  }, {
    "name" : "addRange",
    "desc" : "public com.intellij.psi.PsiElement com.intellij.psi.impl.PsiElementBase.addRange(com.intellij.psi.PsiElement,com.intellij.psi.PsiElement) throws com.intellij.util.IncorrectOperationException",
    "value" : null
  }, {
    "name" : "isNativeFileType",
    "desc" : "public static boolean com.intellij.psi.impl.ElementBase.isNativeFileType(com.intellij.openapi.fileTypes.FileType)",
    "value" : null
  }, {
    "name" : "overlayIcons",
    "desc" : "public static javax.swing.Icon com.intellij.psi.impl.ElementBase.overlayIcons(javax.swing.Icon...)",
    "value" : null
  }, {
    "name" : "buildRowIcon",
    "desc" : "public static com.intellij.ui.icons.RowIcon com.intellij.psi.impl.ElementBase.buildRowIcon(javax.swing.Icon,javax.swing.Icon)",
    "value" : null
  }, {
    "name" : "iconWithVisibilityIfNeeded",
    "desc" : "public static javax.swing.Icon com.intellij.psi.impl.ElementBase.iconWithVisibilityIfNeeded(int,javax.swing.Icon,javax.swing.Icon)",
    "value" : null
  }, {
    "name" : "transformFlags",
    "desc" : "public static int com.intellij.psi.impl.ElementBase.transformFlags(com.intellij.psi.PsiElement,int)",
    "value" : null
  }, {
    "name" : "createLayeredIcon",
    "desc" : "public static com.intellij.ui.RowIcon com.intellij.psi.impl.ElementBase.createLayeredIcon(com.intellij.openapi.util.Iconable,javax.swing.Icon,int)",
    "value" : null
  }, {
    "name" : "registerIconLayer",
    "desc" : "public static void com.intellij.psi.impl.ElementBase.registerIconLayer(int,javax.swing.Icon)",
    "value" : null
  }, {
    "name" : "replace",
    "desc" : "public <T> boolean com.intellij.openapi.util.UserDataHolderBase.replace(com.intellij.openapi.util.Key<T>,T,T)",
    "value" : null
  }, {
    "name" : "getUserData",
    "desc" : "public <T> T com.intellij.openapi.util.UserDataHolderBase.getUserData(com.intellij.openapi.util.Key<T>)",
    "value" : null
  }, {
    "name" : "putUserData",
    "desc" : "public <T> void com.intellij.openapi.util.UserDataHolderBase.putUserData(com.intellij.openapi.util.Key<T>,T)",
    "value" : null
  }, {
    "name" : "getUserDataString",
    "desc" : "public java.lang.String com.intellij.openapi.util.UserDataHolderBase.getUserDataString()",
    "value" : "{CACHED_SMART_POINTER_KEY=com.intellij.reference.SoftReference@412ba727}"
  }, {
    "name" : "copyUserDataTo",
    "desc" : "public void com.intellij.openapi.util.UserDataHolderBase.copyUserDataTo(com.intellij.openapi.util.UserDataHolderBase)",
    "value" : null
  }, {
    "name" : "getCopyableUserData",
    "desc" : "public <T> T com.intellij.openapi.util.UserDataHolderBase.getCopyableUserData(com.intellij.openapi.util.Key<T>)",
    "value" : null
  }, {
    "name" : "putCopyableUserData",
    "desc" : "public <T> void com.intellij.openapi.util.UserDataHolderBase.putCopyableUserData(com.intellij.openapi.util.Key<T>,T)",
    "value" : null
  }, {
    "name" : "putUserDataIfAbsent",
    "desc" : "public <T> T com.intellij.openapi.util.UserDataHolderBase.putUserDataIfAbsent(com.intellij.openapi.util.Key<T>,T)",
    "value" : null
  }, {
    "name" : "copyCopyableDataTo",
    "desc" : "public void com.intellij.openapi.util.UserDataHolderBase.copyCopyableDataTo(com.intellij.openapi.util.UserDataHolderBase)",
    "value" : null
  }, {
    "name" : "isUserDataEmpty",
    "desc" : "public boolean com.intellij.openapi.util.UserDataHolderBase.isUserDataEmpty()",
    "value" : "false"
  }, {
    "name" : "getTextRangeInParent",
    "desc" : "public default com.intellij.openapi.util.TextRange com.intellij.psi.PsiElement.getTextRangeInParent()",
    "value" : "(0,0)"
  }, {
    "name" : "getDbParent",
    "desc" : "public default com.intellij.database.model.DasObject com.intellij.database.model.DasObject.getDbParent()",
    "value" : "table:employee"
  }, {
    "name" : "getDbChildren",
    "desc" : "public default <C> com.intellij.util.containers.JBIterable<C> com.intellij.database.model.DasObject.getDbChildren(java.lang.Class<C>,com.intellij.database.model.ObjectKind)",
    "value" : null
  }, {
    "name" : "getDependences",
    "desc" : "public default java.lang.Object[] com.intellij.psi.meta.PsiMetaData.getDependences()",
    "value" : "[Ljava.lang.Object;@990b393"
  }, {
    "name" : "getDependencies",
    "desc" : "public default java.lang.Object[] com.intellij.psi.meta.PsiMetaData.getDependencies()",
    "value" : "[Ljava.lang.Object;@990b393"
  } ],
  "----" : "-----------------我是一条华丽的分割线-----------------",
  "fieldList" : [ {
    "name" : "LOG",
    "type" : "com.intellij.openapi.diagnostic.Logger",
    "value" : "com.intellij.idea.IdeaLogger@768ac725"
  }, {
    "name" : "WEIGHT_BASE",
    "type" : "int",
    "value" : "10000"
  }, {
    "name" : "WEIGHT_INC",
    "type" : "int",
    "value" : "1000"
  }, {
    "name" : "myParent",
    "type" : "com.intellij.database.psi.DbElement",
    "value" : "table:employee"
  }, {
    "name" : "myDelegate",
    "type" : "java.lang.Object",
    "value" : "id: column"
  }, {
    "name" : "myTransactionalVersion",
    "type" : "long",
    "value" : "13"
  }, {
    "name" : "ASYNC_DOC_CUT",
    "type" : "java.lang.String",
    "value" : "<!-- async-doc-cut -->"
  }, {
    "name" : "LOG",
    "type" : "com.intellij.openapi.diagnostic.Logger",
    "value" : "com.intellij.idea.IdeaLogger@39fd0272"
  }, {
    "name" : "LOG",
    "type" : "com.intellij.openapi.diagnostic.Logger",
    "value" : "com.intellij.idea.IdeaLogger@5cbddbd8"
  }, {
    "name" : "FLAGS_LOCKED",
    "type" : "int",
    "value" : "2048"
  }, {
    "name" : "ICON_COMPUTE",
    "type" : "java.util.function.Function",
    "value" : "com.intellij.psi.impl.ElementBase$$Lambda$1445/0x00000008013fac40@7cae8c85"
  }, {
    "name" : "VISIBILITY_ICON_PLACEHOLDER",
    "type" : "com.intellij.openapi.util.NotNullLazyValue",
    "value" : "com.intellij.psi.impl.ElementBase$1@2aba5b44"
  }, {
    "name" : "ICON_PLACEHOLDER",
    "type" : "com.intellij.openapi.util.NotNullLazyValue",
    "value" : "com.intellij.psi.impl.ElementBase$2@7b8522f3"
  }, {
    "name" : "COPYABLE_USER_MAP_KEY",
    "type" : "com.intellij.openapi.util.Key",
    "value" : "COPYABLE_USER_MAP_KEY"
  }, {
    "name" : "myUserMap",
    "type" : "com.intellij.util.keyFMap.KeyFMap",
    "value" : "{CACHED_SMART_POINTER_KEY=com.intellij.reference.SoftReference@412ba727}"
  }, {
    "name" : "updater",
    "type" : "com.intellij.util.concurrency.AtomicFieldUpdater",
    "value" : "com.intellij.util.concurrency.AtomicFieldUpdater@49544540"
  } ]
}

//调试列原始列类型
{
  "title" : "调试：com.intellij.database.model.DataType",
  "methodList" : [ {
    "name" : "getLength",
    "desc" : "public int com.intellij.database.model.DataType.getLength()",
    "value" : "20"
  }, {
    "name" : "getSpecification",
    "desc" : "public java.lang.String com.intellij.database.model.DataType.getSpecification(boolean,boolean)",
    "value" : null
  }, {
    "name" : "getSpecification",
    "desc" : "public java.lang.String com.intellij.database.model.DataType.getSpecification()",
    "value" : "bigint(20)"
  }, {
    "name" : "getScale",
    "desc" : "public int com.intellij.database.model.DataType.getScale()",
    "value" : "0"
  }, {
    "name" : "equalsWithoutJdbc",
    "desc" : "public boolean com.intellij.database.model.DataType.equalsWithoutJdbc(com.intellij.database.model.DataType)",
    "value" : null
  }, {
    "name" : "getPrecision",
    "desc" : "public int com.intellij.database.model.DataType.getPrecision()",
    "value" : "20"
  } ],
  "----" : "-----------------我是一条华丽的分割线-----------------",
  "fieldList" : [ {
    "name" : "MAX_SIZE",
    "type" : "int",
    "value" : "2147483647"
  }, {
    "name" : "STAR_SIZE",
    "type" : "int",
    "value" : "2147483646"
  }, {
    "name" : "NO_SIZE",
    "type" : "int",
    "value" : "-1"
  }, {
    "name" : "NO_SCALE",
    "type" : "int",
    "value" : "0"
  }, {
    "name" : "UNKNOWN",
    "type" : "com.intellij.database.model.DataType",
    "value" : "unknown"
  }, {
    "name" : "schemaName",
    "type" : "java.lang.String",
    "value" : null
  }, {
    "name" : "packageName",
    "type" : "java.lang.String",
    "value" : null
  }, {
    "name" : "typeName",
    "type" : "java.lang.String",
    "value" : "bigint"
  }, {
    "name" : "size",
    "type" : "int",
    "value" : "20"
  }, {
    "name" : "scale",
    "type" : "int",
    "value" : "0"
  }, {
    "name" : "sizeUnit",
    "type" : "com.intellij.database.model.LengthUnit",
    "value" : "com.intellij.database.model.LengthUnit@5bc9ef68"
  }, {
    "name" : "vagueArg",
    "type" : "java.lang.String",
    "value" : null
  }, {
    "name" : "suffix",
    "type" : "java.lang.String",
    "value" : null
  }, {
    "name" : "sizeUnitExplicit",
    "type" : "boolean",
    "value" : "false"
  }, {
    "name" : "custom",
    "type" : "boolean",
    "value" : "false"
  }, {
    "name" : "enumValues",
    "type" : "java.util.List",
    "value" : null
  }, {
    "name" : "jdbcType",
    "type" : "int",
    "value" : "0"
  } ]
}

//获取原始列类型中的字段
sqlType = bigint

//执行原始列类型中的方法
sqlTypeLen = 20