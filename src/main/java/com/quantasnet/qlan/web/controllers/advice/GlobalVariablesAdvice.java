package com.quantasnet.qlan.web.controllers.advice;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.quantasnet.qlan.web.controllers.editors.DateTimeEditor;

@ControllerAdvice(annotations = Controller.class)
public class GlobalVariablesAdvice {

	@InitBinder
	protected void initBinder(final WebDataBinder binder) {
	    binder.registerCustomEditor(DateTime.class, new DateTimeEditor());
	}
}
