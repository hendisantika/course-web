package id.my.hendisantika.courseweb.repository;

import id.my.hendisantika.courseweb.course.Category;
import id.my.hendisantika.courseweb.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    // add custom queries here
    List<Course> findCoursesByCategory(Category category);
}
