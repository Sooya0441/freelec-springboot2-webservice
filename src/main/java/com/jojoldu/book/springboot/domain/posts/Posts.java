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
 * 클래스 내 모든 모든 필드의 Getter 메소드를 자동 생성
 */
@NoArgsConstructor
/** @NoArgsConstructor
 * 기본 생성자 자동 추가
 * public Posts(){} 와 같은 효과
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
    /** @Column
     * 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됩니다.
     * 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용합니다.
     * 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나(ex: title), 타입을 TEXT로 변경하고 싶거나(ex: content) 등의 경우에 사용됩니다.
     */
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    /** @Builder
     * 해당 클래스의 빌더 패턴 클래스를 생성
     * 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
     */
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
