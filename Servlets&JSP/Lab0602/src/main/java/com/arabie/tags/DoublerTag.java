package com.arabie.tags;

import jakarta.servlet.jsp.tagext.*;
import jakarta.servlet.*;
import jakarta.servlet.jsp.*;
import java.io.*;


public class DoublerTag extends SimpleTagSupport {
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void doTag() throws JspException {
		try{
			JspWriter out = getJspContext().getOut();
			out.println("Double of " + number + " is " + (2 * number));	
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
}