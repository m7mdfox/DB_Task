| Query Type  | Execution Time (ns) | Execution Time (ms) | Notes                                      |
|-------------|---------------------|---------------------|---------------------------------------------|
| Room Query  | 130,762,085         | 130.76              | Uses @Query with Room’s type mapping & safety checks |
| Raw Query   | 104,818,384         | 104.82              | Uses @RawQuery with direct SQL execution   |
