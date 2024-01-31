package com.board.jooboard.vo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@EntityScan
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    private String userName;

    private String userPw;

    private String userEmail;

    private String mobileNo;

    private String birth;

    private String regDate;
    
    private String modDate;

    private String role;

    /**
   * 비밀번호를 암호화
   * @param passwordEncoder 암호화 할 인코더 클래스
   * @return 변경된 유저 Entity
   */
  public Users hashPassword(BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userPw = bCryptPasswordEncoder.encode(this.userPw);
    return this;
  }

  /**
   * 비밀번호 확인
   * @param plainPassword 암호화 이전의 비밀번호
   * @param passwordEncoder 암호화에 사용된 클래스
   * @return true | false
   */
  public boolean checkPassword(String plainPassword, BCryptPasswordEncoder bCryptPasswordEncoder) {
    return bCryptPasswordEncoder.matches(plainPassword, this.userPw);
  }
}
