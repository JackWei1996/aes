/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: jetty/9.4.12.v20180830
 * Generated at: 2018-12-20 09:13:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class upload_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("jar:file:/D:/Data/repository/javax/servlet/jstl/1.2/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153356282000L));
    _jspx_dependants.put("file:/D:/Data/repository/javax/servlet/jstl/1.2/jstl-1.2.jar", Long.valueOf(1545269983139L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");

      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
                  + request.getServerName() + ":" + request.getServerPort()
                  + path + "/";

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>导入收件人</title>\r\n");
      out.write("<!-- 引用layui插件 -->\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"");
      out.print(basePath );
      out.write("frame/layui/css/layui.css\" media=\"all\">\r\n");
      out.write("  <script src=\"");
      out.print(basePath );
      out.write("frame/layui/layui.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<!-- 上传多文件列表 -->\r\n");
      out.write("<div class=\"layui-upload\">\r\n");
      out.write("  <button type=\"button\" class=\"layui-btn layui-btn-normal\" id=\"testList\">请选择收件人Excle</button> \r\n");
      out.write("  <div class=\"layui-upload-list\">\r\n");
      out.write("    <table class=\"layui-table\">\r\n");
      out.write("      <thead>\r\n");
      out.write("        <tr><th>文件名</th>\r\n");
      out.write("        <th>大小</th>\r\n");
      out.write("        <th>状态</th>\r\n");
      out.write("        <th>操作</th>\r\n");
      out.write("      </tr></thead>\r\n");
      out.write("      <tbody id=\"demoList\"></tbody>\r\n");
      out.write("    </table>\r\n");
      out.write("  </div>\r\n");
      out.write("  <button type=\"button\" class=\"layui-btn\" id=\"testListAction\">一键导入</button>\r\n");
      out.write("</div> \r\n");
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("layui.use('upload', function(){\r\n");
      out.write("  var $ = layui.jquery\r\n");
      out.write("  ,upload = layui.upload;\r\n");
      out.write("  //多文件列表示例\r\n");
      out.write("  var demoListView = $('#demoList')\r\n");
      out.write("  ,uploadListIns = upload.render({\r\n");
      out.write("    elem: '#testList'\r\n");
      out.write("    ,url: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/uploadAjax'\r\n");
      out.write("    ,accept: 'file'\r\n");
      out.write("    ,multiple: true\r\n");
      out.write("    ,auto: false\r\n");
      out.write("    ,bindAction: '#testListAction'\r\n");
      out.write("    ,choose: function(obj){   \r\n");
      out.write("      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列\r\n");
      out.write("      //读取本地文件\r\n");
      out.write("      obj.preview(function(index, file, result){\r\n");
      out.write("        var tr = $(['<tr id=\"upload-'+ index +'\">'\r\n");
      out.write("          ,'<td>'+ file.name +'</td>'\r\n");
      out.write("          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'\r\n");
      out.write("          ,'<td>等待导入</td>'\r\n");
      out.write("          ,'<td>'\r\n");
      out.write("            ,'<button class=\"layui-btn layui-btn-xs demo-reload layui-hide\">重传</button>'\r\n");
      out.write("            ,'<button class=\"layui-btn layui-btn-xs layui-btn-danger demo-delete\">删除</button>'\r\n");
      out.write("          ,'</td>'\r\n");
      out.write("        ,'</tr>'].join(''));\r\n");
      out.write("        \r\n");
      out.write("        //单个重传\r\n");
      out.write("        tr.find('.demo-reload').on('click', function(){\r\n");
      out.write("          obj.upload(index, file);\r\n");
      out.write("        });\r\n");
      out.write("        \r\n");
      out.write("        //删除\r\n");
      out.write("        tr.find('.demo-delete').on('click', function(){\r\n");
      out.write("          delete files[index]; //删除对应的文件\r\n");
      out.write("          tr.remove();\r\n");
      out.write("          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选\r\n");
      out.write("        });\r\n");
      out.write("        \r\n");
      out.write("        demoListView.append(tr);\r\n");
      out.write("      });\r\n");
      out.write("    }\r\n");
      out.write("    ,done: function(res, index, upload){\r\n");
      out.write("      if(res.code == 0){ //上传成功\r\n");
      out.write("        var tr = demoListView.find('tr#upload-'+ index)\r\n");
      out.write("        ,tds = tr.children();\r\n");
      out.write("        tds.eq(2).html('<span style=\"color: #5FB878;\">导入成功</span>');\r\n");
      out.write("        tds.eq(3).html(''); //清空操作\r\n");
      out.write("        layer.msg('导入成功');\r\n");
      out.write("        return delete this.files[index]; //删除文件队列已经上传成功的文件\r\n");
      out.write("      }\r\n");
      out.write("      this.error(index, upload);\r\n");
      out.write("    }\r\n");
      out.write("    ,error: function(index, upload){\r\n");
      out.write("      var tr = demoListView.find('tr#upload-'+ index)\r\n");
      out.write("      ,tds = tr.children();\r\n");
      out.write("      tds.eq(2).html('<span style=\"color: #FF5722;\">导入失败</span>');\r\n");
      out.write("      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传\r\n");
      out.write("      layer.msg('导入失败');\r\n");
      out.write("    }\r\n");
      out.write("  });\r\n");
      out.write(" \r\n");
      out.write("});\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
