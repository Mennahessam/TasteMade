package com.example.test123

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardItem(title: String, image: Painter, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = image,
                contentDescription = title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun RecipeList(foodCategory: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = foodCategory,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(getRecipesForCategory(foodCategory)) { recipe ->
                RecipeRow(recipe = recipe, onClick = {
                    // Handle recipe row click here
                })
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Go Back")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeRow(recipe: Recipe, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(recipe.imageResId),
                contentDescription = recipe.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(80.dp)
                    .width(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = recipe.title,
                    style = MaterialTheme.typography.titleMedium
                )
                recipe.ingredients.forEach { ingredient ->
                    Text(
                        text = "- $ingredient",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

data class Recipe(val title: String, val ingredients: List<String>, val imageResId: Int)


fun getCardItems(): List<Recipe> {
    return listOf(
        Recipe(
            title = "Breakfast",
            ingredients = listOf(),
            imageResId = R.drawable.breakfast
        ),
        Recipe(
            title = "Lunch",
            ingredients = listOf(),
            imageResId = R.drawable.lunch
        ),
        Recipe(
            title = "Dinner",
            ingredients = listOf(),
            imageResId = R.drawable.dinner
        ),
        Recipe(
            title = "Smoothies",
            ingredients = listOf(),
            imageResId = R.drawable.red
        ),
        Recipe(
            title = "Desserts",
            ingredients = listOf(),
            imageResId = R.drawable.dessert
        )
    )
}


fun getRecipesForCategory(category: String): List<Recipe> {
    return when (category) {
        "Breakfast" -> listOf(
            Recipe(
                title = "Scrambled Eggs",
                ingredients = listOf("Eggs", "Salt", "Pepper", "Milk", "Butter"),
                imageResId = R.drawable.breakfast
            ),
            Recipe(
                title = "Pancakes",
                ingredients = listOf("Flour", "Sugar", "Baking Powder", "Salt", "Milk", "Egg", "Butter"),
                imageResId = R.drawable.dessert
            ),
            Recipe(
                title = "Avocado Toast",
                ingredients = listOf("Bread", "Avocado", "Lemon Juice", "Salt", "Red Pepper Flakes"),
                imageResId = R.drawable.red
            ),
            Recipe(
                title = "Oatmeal",
                ingredients = listOf("Oats", "Milk", "Water", "Salt", "Honey", "Berries"),
                imageResId = R.drawable.lunch
            ),
            Recipe(
                title = "Fruit Parfait",
                ingredients = listOf("Yogurt", "Granola", "Mixed Berries", "Honey"),
                imageResId = R.drawable.dinner
            )
        )
        "Lunch" -> listOf(
            Recipe(
                title = "Chicken Salad",
                ingredients = listOf("Chicken Breast", "Lettuce", "Tomatoes", "Cucumber", "Avocado", "Dressing"),
                imageResId = R.drawable.breakfast
            ),
            Recipe(
                title = "Caprese Sandwich",
                ingredients = listOf("Bread", "Tomatoes", "Mozzarella", "Basil", "Balsamic Glaze"),
                imageResId = R.drawable.red
            ),
            Recipe(
                title = "Quinoa Salad",
                ingredients = listOf("Quinoa", "Mixed Greens", "Cucumber", "Cherry Tomatoes", "Red Onion", "Feta Cheese", "Dressing"),
                imageResId = R.drawable.dessert
            ),
            Recipe(
                title = "Veggie Wrap",
                ingredients = listOf("Tortilla", "Hummus", "Lettuce", "Tomatoes", "Cucumber", "Avocado"),
                imageResId = R.drawable.lunch
            ),
            Recipe(
                title = "Buddha Bowl",
                ingredients = listOf("Grains", "Mixed Greens", "Roasted Vegetables", "Avocado", "Tofu", "Dressing"),
                imageResId = R.drawable.breakfast
            ),
            Recipe(
                title = "Tomato Soup",
                ingredients = listOf("Tomatoes", "Onion", "Garlic", "Vegetable Broth", "Cream", "Basil", "Croutons"),
                imageResId = R.drawable.dinner
            )
        )
        "Dinner" -> listOf(
            Recipe(
                title = "Spaghetti Bolognese",
                ingredients = listOf("Spaghetti", "Ground Beef", "Onion", "Garlic", "Tomatoes", "Tomato Paste", "Italian Seasoning", "Parmesan Cheese"),
                imageResId = R.drawable.dinner
            ),
            Recipe(
                title = "Grilled Salmon",
                ingredients = listOf("Salmon Fillet", "Lemon", "Dill", "Salt", "Pepper"),
                imageResId = R.drawable.breakfast
            ),
            Recipe(
                title = "Mushroom Risotto",
                ingredients = listOf("Arborio Rice", "Mushrooms", "Onion", "Garlic", "Vegetable Broth", "Parmesan Cheese"),
                imageResId = R.drawable.red
            ),
            Recipe(
                title = "Stuffed Bell Peppers",
                ingredients = listOf("Bell Peppers", "Rice", "Ground Beef", "Onion", "Garlic", "Tomato Sauce", "Cheese"),
                imageResId = R.drawable.dessert
            ),
            Recipe(
                title = "Teriyaki Chicken",
                ingredients = listOf("Chicken Thighs", "Teriyaki Sauce", "Soy Sauce", "Honey", "Garlic", "Ginger", "Green Onions"),
                imageResId = R.drawable.breakfast
            ),
            Recipe(
                title = "Roasted Vegetables",
                ingredients = listOf("Assorted Vegetables", "Olive Oil", "Salt", "Pepper", "Herbs"),
                imageResId = R.drawable.red
            )
        )
        else -> emptyList()
    }
}

@Composable
fun CardList(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Taste Made",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(getCardItems()) { recipe ->
                CardItem(
                    title = recipe.title,
                    image = painterResource(recipe.imageResId),
                    onClick = {
                        navController.navigate("recipeList/${recipe.title}")
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "cardList") {
        composable("cardList") {
            CardList(navController = navController)
        }
        composable(
            "recipeList/{foodCategory}",
            arguments = listOf(navArgument("foodCategory") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("foodCategory")?.let { foodCategory ->
                RecipeList(
                    foodCategory = foodCategory,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
