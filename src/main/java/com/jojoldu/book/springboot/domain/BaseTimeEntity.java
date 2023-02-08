package com.jojoldu.book.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
/** @MappedSuperclass
 * JPA Entity 클래스들이 BaseTimeEntity 를 상송할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 합니다.
 */
@EntityListeners(AuditingEntityListener.class)
/** @EntityListeners(AuditingEntityListener.class)
 * BaseTimeEntity 클래스에 Auditing 기능을 포함시킵니다.
 */
public class BaseTimeEntity {
    @CreatedDate
    /** @CreatedDate
     * Entity 가 생성되어 저장될 때 시간이 자동 저장됩니다.
     */
    private LocalDateTime createdDate;

    @LastModifiedDate
    /** @LastModifiedDate
     * 조회산 Entity 의 값을 변경할 때 자동으로 저장됩니다.
     */
    private LocalDateTime modifiedDate;
}
