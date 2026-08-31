package Lab02;

import java.util.Arrays;
import java.util.List;
public class Main {

	public static void main(String[] args) {
		String[] names = {"Sulaiman", "Ahmed", "Saud"};
		PrintableList<String> list = new PrintableList<>(names);
		list.printItems();
		NumberBox<Integer> integerBox = new NumberBox<>();
		integerBox.setItem(10);;
		System.out.println(integerBox.getItem());
		
		NumberBox<Double> doubleBox = new NumberBox<>();
		doubleBox.setItem(15.5);
		System.out.println(doubleBox.getItem());
		
		
		System.out.println(integerBox.sumNumbers(Arrays.asList(10, 20, 30)
				));
		
		
		Pipeline<String, String> pipeline = new Pipeline<>();
		Pipeline<String, String> upperCasePipeline = pipeline.addTransformer(input -> input.toUpperCase());
		Pipeline<String , Integer> lengthPipeline = upperCasePipeline.addTransformer(input -> input.length());
		
		Integer result = lengthPipeline.execute("Hello");
		System.out.println(result);
		
		printList(Arrays.asList("Apple", "Banana", "Orange"));
		
		double total = sumNumbers(Arrays.asList(10, 20, 30));
		System.out.println(total);
		
	}
	public static void printList(List<?> List) {
		for (Object item : List) {
			System.out.println(item);
		}
	}
public static double sumNumbers(List<? extends Number> numbers) {
	double sum = 0;
	
	for (Number number : numbers) {
		sum += number.doubleValue();
	}
	return sum;
}
}
