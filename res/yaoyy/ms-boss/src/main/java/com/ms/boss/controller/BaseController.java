package com.ms.boss.controller;

import com.ms.tools.editor.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class BaseController {
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(MultipartFile.class, new CustomFileEditor());
		binder.registerCustomEditor(Double.class, new CustomDoubleEditor());
		binder.registerCustomEditor(Float.class, new CustomFloatEditor());
		binder.registerCustomEditor(Integer.class, new CustomIntegerEditor());
		binder.registerCustomEditor(Long.class, new CustomLongEditor());
		binder.registerCustomEditor(Date.class, new CustomDateEditor());
		binder.registerCustomEditor(String.class, new CustomStringEditor());
	}


}
