package id.my.hendisantika.courseweb.util;

import id.my.hendisantika.courseweb.course.Category;
import id.my.hendisantika.courseweb.course.Course;
import id.my.hendisantika.courseweb.repository.CategoryRepository;
import id.my.hendisantika.courseweb.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:48
 * To change this template use File | Settings | File Templates.
 */
@Component
@Slf4j
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final CategoryRepository repository;
    private final CourseRepository courseRepository;

    public DataInitializer(CategoryRepository repository, CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
        log.info("Run data initializer...");
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        if (this.repository.count() > 0) {
            log.info("Category items already created.");
            return;
        }

        List<Category> categories = new ArrayList<>() {
            {
                add(new Category("Bootcamp"));
                add(new Category("Circuit Training"));
                add(new Category("Gymnastics"));
                add(new Category("Outdoor"));
                add(new Category("Weight Training"));
            }
        };

        categories.forEach(category -> {
            this.repository.save(category);
            log.info("Category '{}' saved. ID: {}", category.getTitle(), category.getId());
        });

        this.createExampleCourses(categories);
    }

    private void createExampleCourses(List<Category> categories) {
        List<Course> courses = new ArrayList<>() {
            {
                add(new Course("Outdoor Bootcamp", categories.get(0), UUID.randomUUID(), 60L));
                add(new Course("Hurricane Bootcamp", categories.get(0), UUID.randomUUID(), 45L));
                add(new Course("Six Pack Workout", categories.get(3), UUID.randomUUID(), 45L));
                add(new Course("XXL Legs Workout", categories.get(4), UUID.randomUUID(), 90L));
            }
        };

        this.courseRepository.saveAll(courses);
        log.debug("Sample courses created.");
    }
}
