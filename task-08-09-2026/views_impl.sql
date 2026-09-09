CREATE DATABASE company2_db;

USE company2_db;

CREATE TABLE Departments (
    dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50),
    location VARCHAR(50)
);

INSERT INTO Departments (dept_id, dept_name, location)
VALUES
(10, 'Engineering', 'Chennai'),
(20, 'Sales', 'Hyderabad'),
(30, 'HR', 'Bengaluru'),
(40, 'Support', 'Pune');

CREATE TABLE Employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100),
    dept_id INT,
    salary DECIMAL(10,2),
    status VARCHAR(20),
    
    FOREIGN KEY (dept_id)
    REFERENCES Departments(dept_id)
);


INSERT INTO Employees (emp_id, emp_name, dept_id, salary, status)
VALUES
(101, 'Arun Kumar', 10, 85000, 'ACTIVE'),
(102, 'Priya Shah', 10, 95000, 'ACTIVE'),
(103, 'Rahul Das', 20, 62000, 'ACTIVE'),
(104, 'Meena Rao', 20, 58000, 'INACTIVE'),
(105, 'Kavya Nair', 30, 52000, 'ACTIVE'),
(106, 'Vijay Singh', 40, 48000, 'ACTIVE');


CREATE TABLE Orders (
    order_id INT PRIMARY KEY,
    customer_name VARCHAR(100),
    amount DECIMAL(10,2),
    order_date DATE,
    status VARCHAR(20)
);


INSERT INTO Orders
(order_id, customer_name, amount, order_date, status)
VALUES
(5001, 'ABC Retail', 45000, '2026-08-02', 'DELIVERED'),
(5002, 'Nova Stores', 78000, '2026-08-05', 'DELIVERED'),
(5003, 'Prime Mart', 32000, '2026-08-09', 'PENDING'),
(5004, 'ABC Retail', 91000, '2026-08-14', 'DELIVERED'),
(5005, 'City Hub', 26000, '2026-08-18', 'CANCELLED');



SELECT * FROM Departments;

SELECT * FROM Employees;

SELECT * FROM Orders;



-- Q1
CREATE VIEW active_employees_view AS
SELECT emp_id, emp_name, dept_id, salary FROM Employees WHERE status = 'ACTIVE';

-- Q2
CREATE VIEW high_salary_employees AS
SELECT emp_id, emp_name, salary FROM Employees WHERE salary >= 70000;

-- Q3
CREATE VIEW delivered_orders_view AS 
SELECT order_id, customer_name, amount, order_date FROM Orders WHERE status = 'DELIVERED';

-- Q4
CREATE VIEW sales_employees_view AS
SELECT emp_id, emp_name, salary, status FROM Employees WHERE dept_id = 20;

-- Q5
CREATE VIEW employee_public_view AS
SELECT emp_id, emp_name, dept_id, status FROM Employees;

-- Q6
CREATE VIEW employee_department_view AS
SELECT e.emp_id, e.emp_name, d.dept_name, d.location 
FROM Employees e JOIN Departments d ON e.dept_id = d.dept_id;

-- Q7
CREATE VIEW active_employee_department_view AS
SELECT e.emp_id, e.emp_name, d.dept_name, d.location, e.salary
FROM Employees e JOIN Departments d ON e.dept_id = d.dept_id
WHERE e.status = 'ACTIVE';

-- Q8
CREATE VIEW department_salary_summary AS
SELECT d.dept_name,
       COUNT(e.emp_id) AS employee_count,
       ROUND(AVG(e.salary),2) AS average_salary
FROM Departments d
LEFT JOIN Employees e
ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name;

-- Q9
CREATE VIEW customer_order_summary AS
SELECT customer_name,
       COUNT(order_id) AS total_orders,
       SUM(amount) AS total_order_amount
FROM Orders
GROUP BY customer_name;

-- Q10
CREATE VIEW delivered_customer_summary AS
SELECT customer_name,
       COUNT(order_id) AS delivered_order_count,
       SUM(amount) AS delivered_total
FROM Orders
WHERE status = 'DELIVERED'
GROUP BY customer_name;

-- Q11
CREATE VIEW engineering_employees AS
SELECT emp_id, emp_name, dept_id, salary, status
FROM Employees
WHERE dept_id = 10;

UPDATE engineering_employees
SET salary = salary + 5000
WHERE emp_id = 101;

-- Q12
CREATE VIEW active_employee_editor AS
SELECT emp_id, emp_name, dept_id, salary, status
FROM Employees
WHERE status = 'ACTIVE'
WITH CHECK OPTION;

UPDATE active_employee_editor
SET status = 'INACTIVE'
WHERE emp_id = 101;

-- Q13
CREATE VIEW premium_delivered_orders AS
SELECT order_id, customer_name, amount, order_date, status
FROM Orders
WHERE status = 'DELIVERED'
AND amount >= 50000
WITH CHECK OPTION;

-- Accepted
UPDATE premium_delivered_orders
SET amount = 80000
WHERE order_id = 5002;

-- Rejected
UPDATE premium_delivered_orders
SET amount = 45000
WHERE order_id = 5002;

-- Q14
CREATE VIEW department_active_salary_report AS
SELECT d.dept_name,
       d.location,
       COUNT(CASE WHEN e.status='ACTIVE' THEN e.emp_id END)
           AS active_employee_count,
       COALESCE(SUM(CASE WHEN e.status='ACTIVE' THEN e.salary END),0)
           AS total_active_salary,
       ROUND(AVG(CASE WHEN e.status='ACTIVE' THEN e.salary END),2)
           AS avg_active_salary
FROM Departments d
LEFT JOIN Employees e
ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name, d.location;

-- Q15
CREATE VIEW delivered_customer_statistics AS
SELECT customer_name,
       COUNT(order_id) AS delivered_orders,
       SUM(amount) AS total_delivered_amount,
       ROUND(AVG(amount),2) AS average_delivered_amount,
       MAX(amount) AS highest_delivered_order
FROM Orders
WHERE status = 'DELIVERED'
GROUP BY customer_name;

SELECT * FROM delivered_customer_statistics WHERE total_delivered_amount >
(
    SELECT AVG(amount) FROM Orders WHERE status = 'DELIVERED'
);