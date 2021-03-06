package com.quantasnet.qlan.web.advice;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.quantasnet.qlan.web.editors.DateTimeEditor;

@ControllerAdvice(annotations = Controller.class)
public class GlobalVariablesAdvice {

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
	    binder.registerCustomEditor(DateTime.class, new DateTimeEditor());
	}
}
