package id.my.hendisantika.courseweb.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:32
 * To change this template use File | Settings | File Templates.
 */
@Data
@NoArgsConstructor
public class CategoryDto {
    @NonNull
    private UUID id;

    @NonNull
    private String title;
}
