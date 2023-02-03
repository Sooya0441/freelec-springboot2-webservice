package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
/** @Getter
 *
 */
@NoArgsConstructor
/**
 *
 */
@Entity
/** @Entity
 * 테이블과 링크될 클래스임을 나타냅니다.
 * 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭합니다.
 * ex) SalesManager.java -> sales_manager table
 */
class Posts {
    @Id
    /** @Id
     * 해당 테이블의 PK 필드를 나타냅니다.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /** @GeneratedValue
     * PK 의 생성 규칙을 나타냅니다.
     * 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 됩니다.
     * 스프링 부트 2.0 버전과 1.5 버전의 차이는 https://jojoldu.tistory.com/295 에 정리했으니 참고하세요.
     */
    private Long id;

    @Column (length = 500, nullable = false)
    /**
     *
     */
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    /**
     *
     */
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
