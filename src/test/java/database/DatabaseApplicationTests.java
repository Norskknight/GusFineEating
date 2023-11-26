package database;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;




@SpringBootTest

class DatabaseApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void adminBean() {
		ApplicationContext context = StageInitializer.getApplicationContext();
		AdminPageController admin = context.getBean(AdminPageController.class);
		assertNotNull(admin);
	}

	@Test
	public void mainBean() {
		ApplicationContext context = StageInitializer.getApplicationContext();
		DatabaseApplication app = context.getBean(DatabaseApplication.class);
		assertNotNull(app);
	}
		@Test
	public void IngredientStockKeeperControllerBean() {
		ApplicationContext context = StageInitializer.getApplicationContext();
		IngredientStockKeeperController ingredientBean = context.getBean(IngredientStockKeeperController.class);
		assertNotNull(ingredientBean);
	}

}
