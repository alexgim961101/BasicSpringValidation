package com.alexgim.validation.dto;

import com.alexgim.validation.annotaion.YearMonth;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter @Setter
@ToString
public class User {

    @NotBlank                   // null, "", " " 불가
    private String name;

    @Max(value = 90)            // 최댓값
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다.")
    private String phoneNumber;

    @YearMonth(pattern = "yyyyMM")
    private String reqYearMonth;    // yyyyMM

    @Valid
    private List<Car> cars;


    // custom validation 작성이 가능하나 이 방법은 재활용 불가능
//    @AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")           // true를 반환하면 정상
//    public boolean isReqYearMonthValidation() {
//        this.reqYearMonth = getReqYearMonth() + "01";            // localDate는 dd까지 붙기 때문의 임의의 날짜를 추가해준 것!
//
//        try {
//            LocalDate localDate = LocalDate.parse(this.reqYearMonth, DateTimeFormatter.ofPattern("yyyyMMdd"));
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
}
