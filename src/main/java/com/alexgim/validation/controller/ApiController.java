package com.alexgim.validation.controller;

import com.alexgim.validation.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api")
@Validated          // 파라미터 validation 가능하게 해줌
public class ApiController {
    // validation

    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();

                System.out.println("field : " + field.getField());
                System.out.println(message);

                sb.append("field : " + field.getField());
                sb.append("message : " + message);
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }
        System.out.println(user);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // exception
    @GetMapping
    public com.alexgim.validation.exception.dto.User get(@Size(min = 2) @RequestParam(required = false) String name, @NotNull @Min(1) @RequestParam(required = false) Integer age) {
        com.alexgim.validation.exception.dto.User user = new com.alexgim.validation.exception.dto.User();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;  // nullPointerException을 터트리기 위한 장치

        return user;
    }

    @PostMapping
    public com.alexgim.validation.exception.dto.User post(@Valid @RequestBody com.alexgim.validation.exception.dto.User user) {
        System.out.println(user.toString());
        return user;
    }

    // 해당 컨트롤러의 예외만 처리해줌 (우선순위가 높음)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
