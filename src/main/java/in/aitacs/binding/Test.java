package in.aitacs.binding;

import java.util.Random;

public class Test {

	public static void main(String[] args) {

		System.out.println(generateRandomPwd());
		
		Integer i = new Integer(2);
		
		if(i % 2 == 0) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}
	}

	public static String generateRandomPwd() {

		int leftLimit = 48;
		int rightLimit = 122;
		int targetLimit = 6;

		Random random = new Random();

		String genratedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetLimit)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return genratedString;
		
		
	}

}
