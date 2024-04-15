package ch.m295.todorganizer.category;

import ch.m295.todorganizer.base.MessageResponse;
import org.springframework.stereotype.Service;
import ch.m295.todorganizer.storage.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getCategories() {
        return repository.findByOrderByNameAsc();
    }

    public Category getCategory(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Category.class));
    }

    public Category insertCategory(Category category) {
        return repository.save(category);
    }

    public Category updateCategory(Category category, Long id) {
        return repository.findById(id)
                .map(categoryOrig -> {
                    categoryOrig.setName(category.getName());
                    return repository.save(categoryOrig);
                })
                .orElseGet(() -> repository.save(category));
    }

    public MessageResponse deleteCategory(Long id) {
        repository.deleteById(id);
        return new MessageResponse("Category " + id + " deleted");
    }
}


