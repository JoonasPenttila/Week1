package fi.joonas.week1tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fi.joonas.week1tasks.domain.*
import java.time.LocalDate

@Composable
fun HomeScreen(tasks: List<Task>, modifier: Modifier = Modifier) {

    // Muuttuva lista, jota napit päivittävät
    var taskList by remember { mutableStateOf(tasks) }

    Column(modifier = modifier.padding(16.dp)) {

        Text(
            text = "Task List",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // NAPIT
        Row {
            Button(onClick = {
                val newTask = Task(
                    id = taskList.size + 1,
                    title = "Uusi tehtävä",
                    description = "Lisätty napista",
                    priority = 1,
                    dueDate = LocalDate.now().plusDays(7),
                    done = false
                )
                taskList = addTask(taskList, newTask)
            }) {
                Text("Add Task")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                if (taskList.isNotEmpty()) {
                    taskList = toggleDone(taskList, taskList.first().id)
                }
            }) {
                Text("Toggle First")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = {
                taskList = filterByDone(taskList, true)
            }) {
                Text("Filter Done")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                taskList = sortByDueDate(taskList)
            }) {
                Text("Sort by Date")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // LISTA
        taskList.forEach { task ->
            Text(
                text = "${task.title} (done: ${task.done})",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
