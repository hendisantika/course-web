package id.my.hendisantika.courseweb.mapper;

import id.my.hendisantika.courseweb.course.Category;
import id.my.hendisantika.courseweb.course.Course;
import id.my.hendisantika.courseweb.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:35
 * To change this template use File | Settings | File Templates.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CourseMapper {
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    public Course commandToEntity(CreateCourse command) {
        log.debug("Convert 'CreateCourse' command to new course instance. ['userId': {}, 'title', {}]",
                command.getCreatedByUserId(), command.getTitle());

        Category category = this.categoryService.getById(command.getCategoryId());

        Course course = modelMapper.map(command, Course.class);
        course.setId(UUID.randomUUID());
        course.setCategory(category);

        log.debug("Course entity {} with id {} initialized.", course.getTitle(), course.getId());
        return course;
    }

    public CourseDto entityToDto(Course course) {
        log.debug("Convert 'Course' entity to DTO. ['id': {}, 'title', {}]",
                course.getId(), course.getTitle());

        CourseDto dto = modelMapper.map(course, CourseDto.class);

        log.debug("DTO '{}' initialized with id {}", course.getTitle(), course.getId());
        return dto;
    }

}
