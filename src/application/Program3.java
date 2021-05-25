package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program3 {

	public static void main(String[] args) {
        Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter full file path : ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		double salary = sc.nextDouble();
		sc.nextLine();
		System.out.printf("Email of people whose salary is more than %.2f%n", salary);
		
		List<Employee> list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
		  String line = br.readLine();
		  while (line != null) {
			  String fields[] = line.split(",");
			  list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
			  line = br.readLine();
		  }
		  Comparator<String> emp = (s1 , s2) -> s1.toUpperCase().compareTo(s2.toUpperCase()); 
		  List<String> mailing = list.stream()
				    .filter(e -> e.getSalary() > salary)
					.map(e -> e.getEmail())
					.sorted().collect(Collectors.toList());	  
				  
		  mailing.forEach(System.out::println);
		  
		  double sum = list.stream()
				  .filter(e -> e.getName().charAt(0) == 'M')
				  .map(e -> e.getSalary())
				  .reduce(0.0, (x,y) -> x + y);
		  System.out.println("Sum of salary from people whose name starts with 'M': "+ String.format("%.2f", sum));  
		}
		catch (IOException e) {
			System.out.println("Error : "+ e.getMessage());
		}
		sc.close();
	}
}
