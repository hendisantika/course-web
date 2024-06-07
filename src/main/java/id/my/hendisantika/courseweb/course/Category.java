package id.my.hendisantika.courseweb.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:31
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Data
@NoArgsConstructor
public class Category {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;
    @Column(nullable = false, unique = true, length = 50)
    private String title;
    @OneToMany(mappedBy = "category", targetEntity = Course.class,
            fetch = FetchType.LAZY)
    private Set<Course> courses;
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Category(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
    }

    @PrePersist
    public void prePersist() {
        this.dateCreated = OffsetDateTime.now();
        this.lastUpdated = this.dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdated = OffsetDateTime.now();
    }
}
