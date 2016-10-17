import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
		System.out.println("Google'i otsitulemuse positsiooni leidmine");
		System.out.println("Autor: Marie");
		System.out.println("Aasta: 2016");

		Scanner sc = new Scanner(System.in);

		System.out.println("Sisesta märksõna, mida otsid:");
		String input1 = sc.nextLine();
		System.out.println("Sisesta piirkond, kust otsid:");
		String input2 = sc.nextLine();
		// Programm guugeldab sisestatud parameetritele vastavaid tulemusi
		System.out.println("Sisesta domeen:");
		String input3 = sc.nextLine();
		// Programm otsib Google'i tulemustest sobiva tulemuse välja ning kuvab selle positsiooni

    }
}
