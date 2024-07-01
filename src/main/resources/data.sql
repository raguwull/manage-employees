INSERT INTO EMPLOYEE (name, age, address, department, position, salary, email, phone_number, date_of_joining, employee_status) VALUES
('John Doe', 30, '123 Main St', 'IT', 'Developer', 60000, 'john.doe@example.com', 1234567890, CURRENT_TIMESTAMP, 'Active'),
('Jane Smith', 25, '456 Elm St', 'HR', 'Manager', 70000, 'jane.smith@example.com', 2345678901, CURRENT_TIMESTAMP, 'Active'),
('Robert Johnson', 28, '789 Oak St', 'Finance', 'Analyst', 55000, 'robert.johnson@example.com', 3456789012, CURRENT_TIMESTAMP, 'Active');

INSERT INTO project (name, description) VALUES ('Project Alpha', 'A project to develop a new product.');
INSERT INTO project (name, description) VALUES ('Project Beta', 'A project to improve customer service.');
INSERT INTO project (name, description) VALUES ('Project Gamma', 'A project to enhance the internal IT infrastructure.');

-- Project Alpha with Employee 1 and Employee 2
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE_ID, PROJECT_ID) VALUES (1, 1);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE_ID, PROJECT_ID) VALUES (2, 1);

-- Project Beta with Employee 2 and Employee 3
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE_ID, PROJECT_ID) VALUES (2, 2);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE_ID, PROJECT_ID) VALUES (3, 2);

-- Project Gamma with Employee 1 and Employee 3
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE_ID, PROJECT_ID) VALUES (1, 3);
INSERT INTO EMPLOYEE_PROJECT (EMPLOYEE_ID, PROJECT_ID) VALUES (3, 3);