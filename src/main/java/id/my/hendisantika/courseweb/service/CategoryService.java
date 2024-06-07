package id.my.hendisantika.courseweb.service;

import id.my.hendisantika.courseweb.course.Category;
import id.my.hendisantika.courseweb.mapper.CategoryMapper;
import id.my.hendisantika.courseweb.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper mapper;

    /**
     * Returns the instance of a category by given id.
     *
     * @param id the given id.
     * @return category instance.
     */
    public Category getById(UUID id) {
        Assert.notNull(id, "The given id must not be null!");

        log.debug("Try to get Category with id {}", id);
        Category category = this.categoryRepository.getOne(id);

        if (category == null) {
            log.error("Category with id {} does not exist.", id);
            return null;
        }

        log.debug("Category {} with id {} was found.", category.getTitle(), id);
        return category;
    }
}
