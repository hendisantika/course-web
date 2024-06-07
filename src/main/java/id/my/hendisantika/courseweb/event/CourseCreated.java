package id.my.hendisantika.courseweb.event;

import id.my.hendisantika.courseweb.course.Course;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:38
 * To change this template use File | Settings | File Templates.
 */
@Getter
public class CourseCreated extends ApplicationEvent {
    public CourseCreated(Course course) {
        super(course);
    }
}
