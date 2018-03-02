package com.drayy.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT,reason = "ssddd not found")
public class SpittleNotFoundException extends RuntimeException {
}
