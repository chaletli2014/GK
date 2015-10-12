package com.goodsquick.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class GoodsQuickMenuTag implements Tag{

	private PageContext pageContext;
	  private Tag parent;

	  public GoodsQuickMenuTag(){
	    super();
	  }

	  /**
	   * 设置标签的页面上下文
	   * @param pageContext
	   */
	  public void setPageContext(PageContext pageContext) {
	    this.pageContext=pageContext;
	  }

	  /**
	   * 设置上一级标签
	   * @param t
	   */
	  public void setParent(Tag parent) {
	    this.parent=parent;
	  }

	  public Tag getParent() {
	    return this.parent;
	  }

	  /**
	   * 开始标签时的操作
	   * @return
	   * @throws JspException
	   */
	  public int doStartTag() throws JspException {
	    return this.SKIP_BODY;//返回SKIP_BODY，表示不计算标签体
	  }

	  /**
	   * 结束标签时的操作
	   * @return
	   * @throws JspException
	   */
	  public int doEndTag() throws JspException {
	    try {
	      pageContext.getOut().write("Hello World!");
	    }
	    catch (IOException ex) {
	      throw new JspTagException("IO Error:"+ex.getMessage());
	    }

	    return this.EVAL_PAGE;
	  }

	  /**
	   * Release 用于释放标签程序占用的资源，比如使用了数据库连接，那么应该关闭这个连接
	   */
	  public void release() {
	  }

}
