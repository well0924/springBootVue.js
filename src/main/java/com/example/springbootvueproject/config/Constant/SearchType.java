package com.example.springbootvueproject.config.Constant;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SearchType {
    t("제목"),
    c("내용"),
    w("작성자"),
    i("회원 아이디"),
    e("회원 이메일"),
    n("회원 이름"),
    all("전부");

    private final String searchType;

    SearchType(String s){
        this.searchType = s;
    }
}
