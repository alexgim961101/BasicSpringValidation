package com.alexgim.validation.validator;

import com.alexgim.validation.annotaion.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// <사용할 annotation, 입력받을 값의 타입>
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String patten;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.patten = constraintAnnotation.pattern();           // annotation에 지정된 패턴을 가져옴
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // value : Dto에서 입력받은 값
        try {
            LocalDate localDate = LocalDate.parse(value+"01", DateTimeFormatter.ofPattern(this.patten));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
