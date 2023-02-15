package com.jojoldu.book.springboot.config.auth.dto;

import com.jojoldu.book.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
/** 왜 User 클래스를 사용하면 안 되나요?
 * 만약 User 클래스를 그대로 사용했다면 다음과 같은 에러가 발생합니다. 다음 내용을 읽기 전에 먼저 생각해보면 좋겠습니다.
 * /- Failed to convert from type [java.lang.Object] to type [byte[]] for value 'com.jojoldu.book.springboot.domain.user.User@4a43d6' -/
 * 이는 세션에 저장하기 위해 User 클래스를 세션에 저장하려고 하니, User 클래스에 |직렬화를 구현하지 않았다|는 의미의 에러입니다.
 * 그럼 오류를 해결하기 위해 User 클래스에 직렬화 코드를 넣으면 될까요?
 * 그것에 대해선 생각해 볼 것이 많습니다.
 * 이유는 |User 클래스가 엔티티|이기 때문입니다.
 * 엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모릅니다.
 * 예를 들어 @OneToMany, @ManyToMany 등 자식 엔티티를 가지고 있다면 직렬화 대상에 자식들까지 포함되니 |성능 이슈, 부수 효과|가 발생할 확률이 높습니다.
 * 그래서 |직렬화 기능을 가진 세션 Dto|를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 됩니다.
 */