package id.my.hendisantika.courseweb.service;

import id.my.hendisantika.courseweb.command.CreateCourse;
import id.my.hendisantika.courseweb.course.Course;
import id.my.hendisantika.courseweb.dto.CourseDto;
import id.my.hendisantika.courseweb.event.CourseCreated;
import id.my.hendisantika.courseweb.mapper.CourseMapper;
import id.my.hendisantika.courseweb.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:44
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    private final ApplicationEventPublisher publisher;

    private final CourseMapper mapper;

    /**
     * Creates a new course from the given 'CreateCourse' command.
     *
     * @param command the command.
     * @return an instance of the saved course.
     */
    public Course createCourse(CreateCourse command) {
        Assert.notNull(command, "The given command must not be null!");

        log.debug("Try to create new course {} requested by {}.", command.getTitle(), command.getCreatedByUserId());

        Course course = this.mapper.commandToEntity(command);

        Course savedCourse = this.courseRepository.save(course);
        log.debug("Course {} saved to database. Created timestamp {}", savedCourse.getId(), savedCourse.getDateCreated());

        this.publisher.publishEvent(new CourseCreated(savedCourse));

        return savedCourse;
    }

    /**
     * Fetches all courses.
     *
     * @return a list of courses.
     */
    public List<CourseDto> getCourses() {
        return this.courseRepository.findAll()
                .stream().map(this.mapper::entityToDto)
                .collect(Collectors.toList());
    }
}
