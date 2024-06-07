package id.my.hendisantika.courseweb.repository;

import id.my.hendisantika.courseweb.course.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:33
 * To change this template use File | Settings | File Templates.
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
