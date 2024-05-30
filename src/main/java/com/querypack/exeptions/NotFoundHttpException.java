package com.querypack.exeptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Entity is not found")
public class NotFoundHttpException extends Exception {

	private static final long serialVersionUID = 6300473855096684387L;
}