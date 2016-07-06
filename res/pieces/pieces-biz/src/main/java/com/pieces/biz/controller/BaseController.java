package com.pieces.biz.controller;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.multipart.MultipartFile;

import com.pieces.biz.controller.editor.CustomDateEditor;
import com.pieces.biz.controller.editor.CustomDoubleEditor;
import com.pieces.biz.controller.editor.CustomFileEditor;
import com.pieces.biz.controller.editor.CustomFloatEditor;
import com.pieces.biz.controller.editor.CustomIntegerEditor;
import com.pieces.biz.controller.editor.CustomLongEditor;

public class BaseController {
	
	   @InitBinder
	    public void initBinder(WebDataBinder binder) {
	        binder.registerCustomEditor(MultipartFile.class, new CustomFileEditor());
	        binder.registerCustomEditor(Double.class, new CustomDoubleEditor());
	        binder.registerCustomEditor(Float.class, new CustomFloatEditor());
	        binder.registerCustomEditor(Integer.class, new CustomIntegerEditor());
	        binder.registerCustomEditor(Long.class, new CustomLongEditor());
	        binder.registerCustomEditor(Date.class, new CustomDateEditor());
	    }


}
