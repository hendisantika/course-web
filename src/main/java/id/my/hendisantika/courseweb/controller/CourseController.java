package id.my.hendisantika.courseweb.controller;

import id.my.hendisantika.courseweb.command.CreateCourse;
import id.my.hendisantika.courseweb.course.Course;
import id.my.hendisantika.courseweb.dto.CourseDto;
import id.my.hendisantika.courseweb.event.CourseCreated;
import id.my.hendisantika.courseweb.mapper.CourseMapper;
import id.my.hendisantika.courseweb.service.CategoryService;
import id.my.hendisantika.courseweb.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * Project : course-web
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 6/8/24
 * Time: 06:43
 * To change this template use File | Settings | File Templates.
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CategoryService categoryService;
    private final CourseMapper mapper;
    private final Flux<CourseCreated> events;

    @CrossOrigin
    @PostMapping("/course")
    ResponseEntity<UUID> addCourse(@RequestBody @Valid CreateCourse command) {
        log.info("Create new course request received. [title: {}]", command.getTitle());

        try {
            Course course = this.courseService.createCourse(command);
            return ResponseEntity.created(URI.create("/course/" + course.getId().toString())).body(course.getId());
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin()
    @GetMapping(value = "/course/sse", produces = "text/event-stream;charset=UTF-8")
    public Flux<CourseDto> stream() {
        log.info("Start listening to the course collection.");
        return this.events.map(event -> this.mapper.entityToDto((Course) event.getSource()));
    }
}
