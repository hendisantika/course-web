package id.my.hendisantika.courseweb.course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.OffsetDateTime;
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
public class Course {
    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;
    @Column(nullable = false, length = 25)
    private String title;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @Column(nullable = false, updatable = false)
    private UUID createdByUserId;
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;
    /* Gives a short description. */
    @Column()
    private String teaser;
    /* A detailed description of course contents. */
    @Column()
    private String description;
    /* The duration of the course. */
    @Column(nullable = false)
    private long duration = Duration.ofMinutes(45L).toMinutes();

    public Course(String title, Category category, UUID createdByUserId, Long duration) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.category = category;
        this.createdByUserId = createdByUserId;
        this.duration = duration;
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
