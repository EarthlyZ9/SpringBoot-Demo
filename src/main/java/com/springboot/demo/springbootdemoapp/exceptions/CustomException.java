package com.springboot.demo.springbootdemoapp.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {

  final ErrorType errorType;
  String message;
}
