Design decisions explained:

Employee objects are deemed equivalent if they share the same ID. This makes comparison of objects with incomplete JSON descriptions workable eg. delete
this object with ID 3 but with no other information works.

deleteEmployee in EmployeeController class acts as a deletion and decrements all ID's following the delete employee.
This is done because ID's are assigned in the addEmployee method as size of employeeList + 1, essentially making ID's indexes.