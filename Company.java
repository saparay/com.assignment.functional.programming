package services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import entity.Employee;

public class Company {

	public List<Employee> empList;
	public static Scanner sc = new Scanner(System.in);
	public boolean opt = true;
	public Company() {
		empList = supplyMethod();
	}
	
	/*
	 * This method is used to add employee to the list
	 * It takes a list and supplier as arguments 
	 */
	/*
	public static void addEmp(List<Employee> list, Supplier<Employee> supply) {
		
		list.add(supply.get());
	}
	*/
	public static List<Employee> supplyMethod() {
			return Arrays.asList(new Employee(1, "Mani Chandra", "saparay@gmail.com","Digital Engineer", 28000),
								new Employee(2, "A Akhil", "angerakhil@gmail.com","Associate", 21000),
								new Employee(3, "V Mohan", "krishnamohan@gmail.com","SDET", 24000),
								new Employee(4, "M Vishnu", "mulintivishnu@gmail.com","System Engineer", 24000),
								new Employee(5, "S Taher", "shaiktaher@gmail.com","SAP Engineer", 17000),
								new Employee(6, "K Narasimha", "knarasimha@gmail.com","AIR Engineer", 2000));
		
	}
	/*
	 * This method is use switch and do-while for 
	 * choosing the particular operation to execute and loop through
	 */
	public void mainMethod() {
		sc = new Scanner(System.in);
		
		do {
			Company.menu();
			System.out.print("Enter your input: ");
			int key = sc.nextInt();
			switch (key) {
			case 1:
				empList.forEach(System.out::println);
				break;
			case 2:
				/*
				System.out.print("Id: ");
				int id = sc.nextInt();
				System.out.print("Name: ");
				String name = sc.next();
				System.out.print("Email: ");
				String email = sc.next();
				System.out.print("Role: ");
				String role = sc.next();
				System.out.print("Salary: ");
				double salary = sc.nextDouble();
				Company.addEmp(empList, supply);
				*/
				Company.supplyMethod();
				break;
			case 3:
				System.out.print("Enter a Employee Id to Update: ");
				int empId = sc.nextInt();
				Company.update(empList, empId-1);
				break;
			case 4:
				/*
				 * It delete the employee details on the basis of index
				 */
				System.out.print("Enter a Id to Delete: "); 
				int delId = sc.nextInt();
//				
//				if(delId >=1) {
//					empList.remove(delId-1);
//				}else {
//					System.out.println("Invalid input");
//				}
				
				Company.deleteEmp(empList, delId);
				
				break;
			case 5:
				Company.sortedByName(empList).forEach(System.out::println);
				System.out.println("----------------------------------------------------");
				Company.sortedBySalary(empList).forEach(System.out::println);
				break;
			case 6:
				System.out.print("Search By Name: ");
				String sName = sc.next();
				Company.searchByName(empList, sName);
				System.out.println("----------------------------------------------------");
				System.out.print("Search By ID: ");
				int sId = sc.nextInt();
				Company.searchById(empList, sId);
				
				break;
			case 7:
				/*
				 * This function is used to group the list on the basis of roles
				 */
				Map<String, List<Employee>> group = empList.stream().collect(Collectors.groupingBy(Employee::getRole));
				System.out.println(group);
				break;
			case 8:
				opt = false;
				System.out.println("THANK YOU :-)");
				break;
			default:
				System.out.println("*****Invalid Input*****");
				break;
			}
			
		}while(opt);
		
			
	}
	/*
	 * This is a simple static method to display what options are available
	 */
	private static void menu() {
		System.out.println("----------WelCome to the Company---------");
		System.out.println("1. Get All Employee");
		System.out.println("2. Add New Employee");
		System.out.println("3. Update Employee Details");
		System.out.println("4. Delete A Employee");
		System.out.println("5. Sort Employee List");
		System.out.println("6. Search");
		System.out.println("7. GroupBy Role");
		System.out.println("8. Exit the Portal");
	}

	/*
	 * This method is used to sort the list on the basis of Name
	 * this method returns a stream of employee 
	 */
	public static Stream<Employee> sortedByName(List<Employee> list){
		return list.stream().sorted((a, b) -> a.getName().compareTo(b.getName()));
	}
	/*
	 * This method is used to sort the list on the basis of salary
	 * this method returns a stream of employee 
	 */
	public static Stream<Employee> sortedBySalary(List<Employee> list){
		return list.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed());
	}
	/*
	 *This method is used to update employee details based on index passed 
	 */
	public static void update(List<Employee> list, int i) {
		System.out.println(list.get(i));
		System.out.print("Id: ");
		int id = sc.nextInt();
		System.out.print("Name: ");
		String name = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Role: ");
		String role = sc.next();
		System.out.print("Salary: ");
		double salary = sc.nextDouble();
		list.set(i, new Employee(id , name, email, role, salary));
	}
	
	/*
	 * This method take list and string as arguments
	 * and filter on the basis of given string argument
	 * this method search on the basis of name
	 */
	public static  void searchByName(List<Employee> list, String t) {
		list.stream()
			.filter(e -> e.getName().equalsIgnoreCase(t))
			.forEach(System.out::println);;
	}
	/*
	 * This method take list and int as arguments
	 * and filter on the basis of given int argument
	 * this method search on the basis of ID
	 */
	public static void searchById(List<Employee> list, int t) {
		list.stream()
			.filter(e -> e.getId()==t)
			.forEach(System.out::println);
	}
	
	public static void deleteEmp(List<Employee> list, int empId) {
//		int temp =0;
//		for(Employee emp : list) {
//			if(emp.getId() ==empId) {
//				temp = list.indexOf(emp);
//			}
//		}
//		System.out.println(list.get(temp));
//		System.out.println("Deleted successfully!");
//		list.remove(temp);
		
		AtomicReference<Integer> eid = new AtomicReference<>();
		list.stream()
				.filter(id -> (id.getId()==empId)).findFirst()
				.ifPresent(emp -> eid.set(list.indexOf(emp)));
		System.out.println(eid);
		System.out.println(list.get(eid.get()));
		System.out.println("Deleted successfully!");
		int i = eid.get();
		list.remove(i);
		list.forEach(System.out::println);
		
	}
	
}
