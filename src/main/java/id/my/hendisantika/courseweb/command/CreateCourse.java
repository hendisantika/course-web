package id.my.hendisantika.courseweb.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:39
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class CreateCourse {
    @NotNull
    @Size(min = 5, max = 50, message = "The title must be between 5 and 50 characters long.")
    private String title;

    @Size(max = 250, message = "The description must be a maximum of 250 characters long.")
    private String description;

    @NotNull
    private UUID categoryId;

    @NotNull
    private UUID createdByUserId;

    @NotNull
    @Range(min = 15L, max = 480L)
    private long duration = 45L;
}
