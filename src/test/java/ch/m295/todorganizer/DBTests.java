package ch.m295.todorganizer;

import ch.m295.todorganizer.category.Category;
import ch.m295.todorganizer.category.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.SQLOutput;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void insertDepartment() {
        Category catA = this.categoryRepository.save(new Category("Abteilung A"));
        Assertions.assertNotNull(catA.getId());
        Category catB = this.categoryRepository.save(new Category("Abteilung B"));
        Assertions.assertNotNull(catB.getId());
    }

    @Test
    void getCategory(){
        long id = 1;
        Category catA = this.categoryRepository.getReferenceById(id);
        Assertions.assertNotNull(catA);
    }

    @Test
    void updateCategory(){
        Category catA = this.categoryRepository.save(new Category("Abteilung A"));
        long id = catA.getId();
        Category catToUpdate = this.categoryRepository.findById(id).orElse(null);
        Assertions.assertNotNull(catToUpdate, "Fetched category for update should not be null");
        // Change some details
        catToUpdate.setName("Abteilung A Updated");
        Category updatedCat = this.categoryRepository.save(catToUpdate);

        // Assert changes
        Assertions.assertEquals("Abteilung A Updated", updatedCat.getName(), "Category name should be updated in the database");
    }
    @Test
    void deleteCategory() {
        // Create a new category to ensure it exists
        Category catToDelete = new Category("Temporary Category");
        catToDelete = this.categoryRepository.save(catToDelete);
        Assertions.assertNotNull(catToDelete.getId(), "Category should be saved and have a non-null ID");

        // Delete the category
        this.categoryRepository.delete(catToDelete);

        // Verify it's no longer in the database
        Category deletedCat = this.categoryRepository.findById(catToDelete.getId()).orElse(null);
        Assertions.assertNull(deletedCat, "Category should be null, indicating it was deleted");
    }
}