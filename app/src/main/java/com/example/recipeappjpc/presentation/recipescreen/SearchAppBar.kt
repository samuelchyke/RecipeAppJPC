package com.example.recipeappjpc.presentation.recipescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.recipeappjpc.utils.FoodCategory
import com.example.recipeappjpc.utils.getAllFoodCategories

@Composable
fun SearchAppBar(
    query: String,
    selectedCategory: FoodCategory?,
    selectedChip: Int,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    clearFocus: () -> Unit,
    onClearSelectedCategory: () -> Unit,
    onSelectedCategoryChanged: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary,
        elevation = 8.dp,
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    label = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search Image"
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            onExecuteSearch()
                            clearFocus()
                            onClearSelectedCategory()
                        }
                    ),
                )
            }

            ScrollableTabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp, bottom = 2.dp),
                selectedTabIndex = selectedChip,
                edgePadding = 2.dp,
                indicator = {
                    TabRowDefaults.Indicator(
                        color = Color.White,
                        height = 0.dp,
                        modifier = Modifier.tabIndicatorOffset(it[0])
                    )
                }
            ) {
                for (category in getAllFoodCategories()) {
                    FoodCategoryChip(
                        category = category.value,
                        isSelected = selectedCategory == category,
                        onExecuteSearch = { onExecuteSearch() },
                        onSearchCategoryChanged = {
                            onSelectedCategoryChanged(it)
                        }

                    )
                }
            }
        }
    }
}
