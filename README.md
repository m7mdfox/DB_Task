# ProjectDao Query Types

In `ProjectDao`, we implemented two versions of the same query:

1. **`suspend fun getAllProjectsOnce()`** → Returns a `List<Project>` as a one-time snapshot.  
   This function retrieves the current state of the data from the database and does not react to any future changes.

2. **`fun getAllProjectsFlow(): Flow<List<Project>>`** → Emits updates whenever the data changes.  
   This is a reactive stream that continuously observes the database table and sends a new list whenever a modification occurs.

---

## Test Function

```kotlin
@Test
fun testProjectDaoQueries() = runBlocking {
    // 1) One-time snapshot
    val snapshot = projectDao.getAllProjectsOnce()
    Log.d("DAO_TEST", "Suspend projects: $snapshot")

    // 2) Reactive flow
    withTimeout(5000) { // Collect for 5 seconds
        projectDao.getAllProjectsFlow().collect { projects ->
            Log.d("DAO_TEST", "Flow emission: $projects")
        }
    }
}
