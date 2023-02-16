package com.jojoldu.book.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
/** @Target(ElementType.PARAMETER)
 * 이 어노테이션이 생성될 수 있는 위치를 지정합니다.
 * PARAMETER 로 지정했으니 메소드의 파라미터로 선언된 객체에만 사용할 수 있습니다.
 * 이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있습니다.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
    /** @interface
     * 이 파일은 어노테이션 클래스로 지정합니다.
     * LoginUser 라는 이름을 가진 어노테이션이 생성디었다고 보면 됩니다.
     */
}
