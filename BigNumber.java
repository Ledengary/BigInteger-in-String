import java.util.Scanner;

import javax.sound.midi.SysexMessage;

public class BigNumber {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		String commandLine = inp.nextLine();
		String[] commands = commandLine.split("_");
		String betweenBrackets = "";
		String innerAnswer = "";
		for (int i = 0; i < commands.length; i++) {
			betweenBrackets = commands[i].substring(commands[i].indexOf("(") + 1, commands[i].indexOf(")"));
			// i have the betweenBrckets, not if there is a ( in it, it means that it's
			// "nomre mosbat dar" :) , and if not, then it's just a simple command
			if (indexOf(betweenBrackets, "(") == -1) {
				System.out.println(mainSecond(commands[i]));
			} else {
				betweenBrackets = commands[i].substring(commands[i].indexOf("(") + 1, commands[i].length() - 1);
				// in order to handle them better i use a new var called betweenBracketsWithStar
				// that has * after each ), ====> refer to line 52
				String betweenBracketsWithStar = "";
				for (int j = 0; j < betweenBrackets.length(); j++) {
					if (betweenBrackets.charAt(j) == ')') {
						betweenBracketsWithStar += Character.toString(betweenBrackets.charAt(j)) + "*";
					} else {
						betweenBracketsWithStar += Character.toString(betweenBrackets.charAt(j));
					}
				}
				// let's use my split function to devide it to a string with underlines :)
				String splitByUnderline = split(betweenBrackets, ",");
				// let's count how many kamas do we have !
				int kamaCount = 0;
				for (int j = 0; j < betweenBrackets.length(); j++) {
					if (betweenBrackets.charAt(j) == ',') {
						kamaCount++;
					}
				}
				// this upcoming if is because sum of digits aint got no kama, so imma set it to
				// default 1
				if (kamaCount == 0) {
					kamaCount = 1;
				}
				String[] commanders = new String[kamaCount];
				String firstCommand = "", secondCommand = "";
				// lets put the broken part all together,
				// if there is 2 kamas ==> we have 3 parts ==> code gives out first an second
				// command
				// if there is 3 kamas ==> we have 4 parts ==> code gives out first an second
				// command
				// if there is 1 kamas ==> we have 1 part ==> code gives out first command
				if (kamaCount == 2) {
					commanders = splitByUnderline.split("_");
					boolean hitFirstOrNot = false;
					// this next if else checks if it's got a problem of nextPerm or not !
					if (indexOf(commanders[0], "(") != -1 && indexOf(commanders[2], ")") != -1) {
						if (indexOf(commanders[0], "(") != -1 && indexOf(commanders[0], ")") != -1) {
							commanders[1] += "," + commanders[2];
							commanders[2] = commanders[1];
							hitFirstOrNot = true;
						}
						if (indexOf(commanders[2], "(") != -1 && indexOf(commanders[2], ")") != -1 && !hitFirstOrNot) {
							commanders[0] += "," + commanders[1];
						}
					} else {
						// now by having *s which is a unique character in the whole betweenBrackets
						// String, imma see weather the * is at the end or, cause if it is it is
						// refering to secondCommand and if not it's firstcommand
						if (indexOf(betweenBracketsWithStar, "*") != betweenBracketsWithStar.length() - 1) {
							commanders[0] += "," + commanders[1];
						}
						if (indexOf(betweenBracketsWithStar, "*") == betweenBracketsWithStar.length() - 1) {
							commanders[1] += "," + commanders[2];
							commanders[2] = commanders[1];

						}
					}
				} else if (kamaCount == 3) {
					commanders = splitByUnderline.split("_");
					commanders[0] += "," + commanders[1];
					commanders[2] += "," + commanders[3];
				} else if (kamaCount == 1) {
					// if it is one it means that there aint no other argument to be calculated so
					// imma stick to the firstcommand so imma calculate it and put it in commands[i]
					firstCommand = mainSecond(betweenBrackets);
					commands[i] = commands[i].substring(0, commands[i].indexOf("(")) + "(" + firstCommand + ")";
					System.out.println(mainSecond(commands[i]));
				}
				// if it aint one, so it has either two or three kamas so it'l be put in
				// commands[i] from here
				if (kamaCount != 1) {
					firstCommand = commanders[0];
					secondCommand = commanders[2];
					if (indexOf(commanders[0], "(") != -1) {
						// if firstcommand is not a number which means that it should be calculated,
						// imma calculate it here
						firstCommand = mainSecond(firstCommand);
					}
					if (indexOf(commanders[2], "(") != -1) {
						// if secondcommand is not a number which means that it should be calculated,
						// imma calculate it here
						secondCommand = mainSecond(secondCommand);
					}
					commands[i] = commands[i].substring(0, commands[i].indexOf("(")) + "(" + firstCommand + ","
							+ secondCommand + ")";
					System.out.println(mainSecond(commands[i]));
					// to here
				}
			}
		}
	}

	static String mainSecond(String command) {
		// mainSecond is only made to get the commands and refer to functions in order
		// to get the answers, nothing less, nothing more.
		String commandItself = command.substring(0, command.indexOf("("));
		// get the commands between the ()s
		String betweenBrackets = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
		if (betweenBrackets.indexOf(",") == -1) {
			if ("nextPerm".equals(commandItself)) {
				return nextPerm(betweenBrackets);
			} else if ("fact".equals(commandItself)) {
				return fact(betweenBrackets);
			} else if ("sumOfDigits".equals(commandItself)) {
				return sumOfDigits(betweenBrackets);
			} else if ("sort".equals(commandItself)) {
				return sort(betweenBrackets);
			}
		} else {
			String[] commander = betweenBrackets.split(",");
			if ("add".equals(commandItself)) {
				return add(commander[0], commander[1]);
			} else if ("sub".equals(commandItself)) {
				return sub(commander[0], commander[1]);
			} else if ("pow".equals(commandItself)) {
				return pow(commander[0], commander[1]);
			} else if ("mod".equals(commandItself)) {
				return mod(commander[0], commander[1]);
			} else if ("rep".equals(commandItself)) {
				return rep(commander[0], commander[1]);
			} else if ("isPalindromes".equals(commandItself)) {
				if (!",".equals(betweenBrackets)) {
					return isPalindromes(commander[0], commander[1]);
				} else {
					return "True";
				}
			} else if ("rotate".equals(commandItself)) {
				return rotate(commander[0], commander[1]);
			} else if ("indexOf".equals(commandItself)) {
				return Integer.toString(indexOf(commander[1], commander[0]));
			} else if ("subNumber".equals(commandItself)) {
				String[] subNumbersCommanders = betweenBrackets.split(",");
				return subNumber(subNumbersCommanders[0], Integer.parseInt(subNumbersCommanders[1]),
						Integer.parseInt(subNumbersCommanders[2]));
			} else if ("split".equals(commandItself)) {
				return split(commander[0], commander[1]);
			}
		}
		return "";
	}

	static String add(String x, String y) {
		// az inja ta akhar in if tamami condition haye lazem baraye check kardan ine ke
		// nakone yeki az input ha manfi bashe...
		if (x.charAt(0) == '-' || y.charAt(0) == '-') {
			if (x.charAt(0) == '-' && y.charAt(0) != '-') {
				if (x.charAt(1) == y.charAt(0)) {
					return "0";
				} else {
					if (checkBigger(x.substring(1, x.length()), y)) {
						return "-" + sub(x.substring(1, x.length()), y);
					} else if (!checkBigger(x.substring(1, x.length()), y)) {
						return sub(y, x.substring(1, x.length()));
					}
				}
			} else if (x.charAt(0) != '-' && y.charAt(0) == '-') {
				if (y.charAt(1) == x.charAt(0)) {
					return "0";
				} else {
					if (checkBigger(x, y.substring(1, y.length()))) {
						return sub(x, y.substring(1, y.length()));
					} else if (!checkBigger(x, y.substring(1, y.length()))) {
						return "-" + sub(y.substring(1, y.length()), x);
					}
				}
			} else if (x.charAt(0) == '-' && y.charAt(0) == '-') {
				return "-" + add(x.substring(1,x.length()), y.substring(1,y.length()));
			}
		}
		// ta inja :)
		// next if makes x to be higher number and y the lower number
		if (y.length() > x.length()) {
			String tmp = y;
			y = x;
			x = tmp;
		}
		// tempNum = when x is 1234 and y is 56, tempNum is 12 !
		String tempNum = "";
		if (x.length() != y.length()) {
			if (x.length() > y.length()) {
				for (int i = 0; i < Math.abs(x.length() - y.length()); i++) {
					tempNum += Character.toString(x.charAt(i));
				}
			} else {
				for (int i = 0; i < Math.abs(x.length() - y.length()); i++) {
					tempNum += Character.toString(y.charAt(i));
				}
			}
		}
		int carry = 0;
		int yCharToInt = 0;
		int xCharToInt = 0;
		// moveOn = is the param that saves the counter of i in the adding peocess;
		// refer to lines 188 and 189
		int moveOn = x.length() - 1;
		StringBuilder answer = new StringBuilder("");
		for (int j = y.length() - 1; j >= 0; j--) {
			for (int i = moveOn; i >= 0; i--) {
				yCharToInt = y.charAt(j) - '0';
				xCharToInt = x.charAt(i) - '0';
				if (xCharToInt + yCharToInt + carry >= 10) {
					// if it's the last part of adding, the number should be writen with its carry
					if (i != 0) {
						answer = answer.insert(0, Integer.toString((xCharToInt + yCharToInt + carry) - 10));
					} else {
						answer = answer.insert(0, Integer.toString((xCharToInt + yCharToInt + carry)));
					}
					carry = 1;
				} else {
					answer = answer.insert(0, Integer.toString((xCharToInt + yCharToInt + carry)));
					carry = 0;
				}
				moveOn = i - 1;
				break;
			}
		}
		// the adding peocess is finished

		// the answer that we have till here is without our tempNum, not let's add
		// tempNum to the answer
		if (x.length() != y.length()) {
			if (carry != 0) {
				tempNum = Integer.toString(Integer.parseInt(tempNum) + carry);
			}
			answer = answer.insert(0, tempNum);
		}
		return answer.toString();
	}

	static String multiply(String x, String y) {
		// next if makes x to be higher number and y the lower number
		if (y.length() > x.length()) {
			String tmp = y;
			y = x;
			x = tmp;
		}
		// next if returns 0 if one of the inouts is 0 !
		if (x.equals("0") || y.equals("0")) {
			return "0";
		}
		int carry = 0;
		int yCharToInt = 0;
		int xCharToInt = 0;
		int overflowness = 0;
		// mulparts hamoon motaghayeri e ke vaghti zarb mikonim javabo pishesh ye sefr
		// mizarim, dobare zarb mikonim ye sefr dg mizari, in hamoone
		StringBuilder[] mulParts = new StringBuilder[y.length()];
		int mulPartsCounter = 0;
		// tempsum hamoon mulparts e vali type string dare ke dar akhar kar be add(x,y)
		// pass midimesh
		String[] tempSum = new String[y.length()];
		String finalAnswer = "";
		StringBuilder answer = new StringBuilder("");
		for (int j = y.length() - 1; j >= 0; j--) {
			for (int i = x.length() - 1; i >= 0; i--) {
				yCharToInt = y.charAt(j) - '0';
				xCharToInt = x.charAt(i) - '0';
				if ((xCharToInt * yCharToInt) + carry >= 10) {
					if (i != 0) {
						// if it's the last part of adding, the number should be writen with its
						// overflowness
						overflowness = (((xCharToInt * yCharToInt) + carry)
								- (((xCharToInt * yCharToInt) + carry) % 10)) / 10;
						answer = answer.insert(0,
								Integer.toString(((xCharToInt * yCharToInt) + carry) - (overflowness * 10)));
					} else {
						answer = answer.insert(0, Integer.toString(((xCharToInt * yCharToInt) + carry)));
					}
					carry = overflowness;
				} else {
					answer = answer.insert(0, Integer.toString(((xCharToInt * yCharToInt) + carry)));
					carry = 0;
				}
			}
			carry = 0;
			// the next if else baraye ine ke masalan ba do ta adad 123 va 45 agar avalin
			// bare dari 5(y) o dar 123(x) zarb mikoni javabesh o bedoon safr samte rastesh
			// bezar too mulpart aval. vali age avalin bar nist bebin mulpartscounter et
			// chande, bad be tedad oon samte rastesh sefr bezar ;)
			if (mulPartsCounter == 0) {
				mulParts[mulPartsCounter] = answer;
			} else {
				mulParts[mulPartsCounter] = answer;
				for (int i = 0; i < mulPartsCounter; i++) {
					mulParts[mulPartsCounter] = mulParts[mulPartsCounter].insert(mulParts[mulPartsCounter].length(),
							"0");
				}
			}
			// az oonjai ke tempsum hamoon mulparts e, mulparts o be string tabdil mikonim
			// bad mirizimesh to tempsum
			tempSum[mulPartsCounter] = mulParts[mulPartsCounter].toString();
			mulPartsCounter++;
			answer.setLength(0);
		}
		// hala tamam tempsum haro ba ham jam mikonim
		finalAnswer = tempSum[0];
		for (int i = 1; i < tempSum.length; i++) {
			finalAnswer = add(finalAnswer, tempSum[i]);
		}
		return finalAnswer;
	}

	static String sub(String x, String y) {
		// az inja ta akhar in if tamami condition haye lazem baraye check kardan ine ke
		// nakone yeki az input ha manfi bashe...
		if (x.charAt(0) == '-' || y.charAt(0) == '-') {
			if (x.charAt(0) == '-' && y.charAt(0) != '-') {
				return "-" + add(x.substring(1, x.length()), y);
			} else if (x.charAt(0) != '-' && y.charAt(0) == '-') {
				return "-" + add(x, y.substring(1,y.length()));
			} else if (x.charAt(0) == '-' && y.charAt(0) == '-') {
				return add(x, y.substring(1,y.length()));
			}
		}
		// ta inja :)
		// normalornot is a variable to use as if the first number is bigger than the
		// second one or not, if not, then math library should be used as abs
		boolean normalSubOrNot = true;
		int xCharToInt = 0;
		int yCharToInt = 0;
		StringBuilder answer = new StringBuilder();
		StringBuilder yBuilder = new StringBuilder();
		String finalAnswer = "";
		// giveOrNot = hamoon moghei e ke 3 ro be 13 tabdil mikonim va yedoone az
		// baghali samte chapesh migirim
		boolean gotOrNot = false;
		// next if = check mikone ke aya adad aval bozorgtare az adad dovom ya na, age
		// are is normal age na is not normal
		if (x.length() < y.length()) {
			normalSubOrNot = false;
		}
		if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				xCharToInt = x.charAt(i) - '0';
				yCharToInt = y.charAt(i) - '0';
				if (yCharToInt > xCharToInt) {
					normalSubOrNot = false;
					break;
				} else if (xCharToInt > yCharToInt) {
					break;
				}
			}
		}
		if (normalSubOrNot) {
			// this part of the code makes the y with the same digits of the x, for example
			// x is 33512 and y is 12, it makes y to 00012;
			yBuilder = yBuilder.insert(0, y);
			for (int i = 0; i < x.length() - y.length(); i++) {
				yBuilder = yBuilder.insert(0, 0);
			}
			y = yBuilder.toString();
			for (int i = x.length() - 1; i >= 0; i--) {
				xCharToInt = x.charAt(i) - '0';
				if (gotOrNot) {
					xCharToInt--;
					if (xCharToInt == -1) {
						xCharToInt = 9;
					} else {
						gotOrNot = false;
					}
				}
				yCharToInt = y.charAt(i) - '0';
				if (xCharToInt >= yCharToInt) {
					// it checks to avoid having a number like 0999 when you want to sub 1000 and 1
					// ! so it prints 999 instead of 0999
					if (xCharToInt - yCharToInt == 0) {
						if (i != 0) {
							answer = answer.insert(0, xCharToInt - yCharToInt);
						}
					} else {
						answer = answer.insert(0, xCharToInt - yCharToInt);
					}
				} else {
					xCharToInt += 10;
					if (!gotOrNot) {
						gotOrNot = true;
					}
					answer = answer.insert(0, xCharToInt - yCharToInt);
				}
			}
		} else {
			answer = answer.insert(0, sub(y, x));
			answer = answer.insert(0, "-");
		}
		// this part of the code avoids printing zeroes before, like printing 2 instead
		// of 002 when substracting 1025 from 1023
		int subFrom = 0;
		for (int i = 0; i < answer.toString().length(); i++) {
			if (answer.toString().charAt(i) != '0') {
				subFrom = i;
				break;
			}
			if (i == answer.toString().length() - 1) {
				answer = answer.replace(0, answer.toString().length(), "0");
			}
		}
		return answer.toString().substring(subFrom, answer.length());
	}

	static String fact(String x) {
		if (checkBigger(x, "1"))
			return multiply(x, fact(sub(x, "1")));
		// return n * fact(n - 1)
		else
			return "1";
	}

	static boolean checkBigger(String x, String y) {
		int xCharInt = 0;
		int yCharInt = 0;
		if (x.length() > y.length()) {
			return true;
		} else if (x.length() < y.length()) {
			return false;
		} else if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				xCharInt = x.charAt(i) - '0';
				yCharInt = y.charAt(i) - '0';
				if (xCharInt > yCharInt) {
					return true;
				} else if (xCharInt < yCharInt) {
					return false;
				}
			}
		}
		return false;
	}

	static String pow(String x, String y) {
		StringBuilder temp = new StringBuilder();
		temp = temp.append(x);
		StringBuilder i = new StringBuilder();
		String tempBack = "";
		for (i = i.insert(0, "1"); !checkBigger(i.toString(), y); i = i.replace(0, i.length(),
				add(i.toString(), "1"))) {
			tempBack = temp.toString();
			temp = temp.replace(0, temp.length(), multiply(temp.toString(), x));
		}
		return tempBack;
	}

	static String dividePanel(String x, String y) {
		if (x.length() > y.length()) {
			return divideXisBigger(x, y, true);
		} else if (x.length() < y.length()) {
			return divideYisBigger(x, y);
		} else if (x.length() == y.length()) {
			for (int i = 0; i < x.length(); i++) {
				if (x.charAt(i) - '0' > y.charAt(i) - '0') {
					return divideXisBigger(x, y, true);
				} else if (x.charAt(i) - '0' < y.charAt(i) - '0') {
					return divideYisBigger(x, y);
				}
			}
		}
		return "";
	}

	static String divideXisBigger(String x, String y, boolean xIsBigOrNot) {
		// code generates the two logarithms of the two numbers given, and subtracts
		// them, and then the antilog of that subtraction is the answer !
		double logX = Math.log10(Double.parseDouble(x));
		double logY = Math.log10(Double.parseDouble(y));
		String answer = Double.toString(Math.pow(10, logX - logY));
		StringBuilder finalAnswer = new StringBuilder(answer);
		String[] answers = new String[2];
		// let's convert exponential to number ! (1e*11 == 1 * 10^11) :)
		if (answer.indexOf("E") != -1) {
			answers = answer.split("E");
			answers[0] = answer.substring(0, answer.indexOf("."))
					+ answer.substring(answer.indexOf(".") + 1, answer.indexOf("E"));
			finalAnswer = finalAnswer.replace(0, finalAnswer.length(), answers[0]);
			if (Integer.parseInt(answers[1]) + 1 != answers[0].length()) {
				finalAnswer = finalAnswer.insert(Integer.parseInt(answers[1]) + 1, ".");
			}
		}
		// xIsBigOrNot is a boolean that if it is true it means that devide panel had
		// send us here directly, if not it means that y was bigger and we were sent
		// here from the function divideYisBigger !
		if (xIsBigOrNot) {
			return roundUp(finalAnswer);
		} else {
			return finalAnswer.toString();
		}
	}

	static String roundUp(StringBuilder x) {
		String finalAnswer = x.toString();
		String beforeDot = "";
		String afterDot = "";
		boolean hitDot = false;
		// split couldnt work :/ so i just splited them by myself
		for (int i = 0; i < finalAnswer.length(); i++) {
			if (finalAnswer.charAt(i) != '.' && !hitDot) {
				beforeDot += Character.toString(finalAnswer.charAt(i));
			} else {
				hitDot = true;
				if (finalAnswer.charAt(i) == '.') {
					i++;
				}
				afterDot += Character.toString(finalAnswer.charAt(i));
			}
		}
		// till here
		// let's round up the afterDot to two digits
		if (afterDot.length() > 1) {
			afterDot = Character.toString(afterDot.charAt(0)) + Character.toString(afterDot.charAt(1));
		} else {
			afterDot = Character.toString(afterDot.charAt(0)) + "0";
		}
		// rounded up
		// now let's round up the whole number
		if (Integer.parseInt(afterDot) > 50) {
			finalAnswer = add(beforeDot, "1");
		} else if (Integer.parseInt(afterDot) < 50) {
			finalAnswer = beforeDot;
		}
		// till here
		return finalAnswer;
	}

	static String divideYisBigger(String x, String y) {
		return divideXisBigger(x, y, false);
	}

	static String nextPerm(String x) {
		// this algorithm consists of two main params called pivotpoint and
		// pivotpointplus, pivotpoint if the first number that breaks the ascending of
		// the number given when we read it from right, and pivotpointplus is the first
		// number that is bigger than the pivotpoint that we found,then what the
		// algorithm does is to exchange the places of pivotpoint and pivotpoint plus,
		// and then sort all the digits after pivotpoint's index. what we have now is
		// called the next permutated number of the number given ! :)

		// converts all the characters of the string to an int array
		int[] digit = new int[x.length()];
		for (int i = 0; i < digit.length; i++) {
			digit[i] = x.charAt(i) - '0';
		}
		int tempInt = digit[digit.length - 1];
		int pivotPoint = -1;
		int pivotPointIndex = -1;
		int pivotPointPlus = -1;
		int pivotPointPlusIndex = -1;
		// let's find the pivotPoint and it's index
		for (int i = digit.length - 2; i >= 0; i--) {
			if (digit[i] < tempInt) {
				pivotPoint = digit[i];
				pivotPointIndex = i;
				break;
			}
			tempInt = digit[i];
		}
		// let's find the pivotPointPlus and it's index
		for (int i = digit.length - 1; i >= 0; i--) {
			if (digit[i] > pivotPoint) {
				pivotPointPlus = digit[i];
				pivotPointPlusIndex = i;
				break;
			}
		}
		// let's check if the algorithm couldn't change the pivotPoint or the
		// pivotPointPlus, cause if it couldnt then that means that the number does not
		// have any next permutation number !
		if (pivotPoint == -1 || pivotPointPlus == -1) {
			return "-1";
		}
		// and now code rotates pivotPoint and pivotPointPlus
		int tempRotation = pivotPoint;
		digit[pivotPointIndex] = pivotPointPlus;
		digit[pivotPointPlusIndex] = tempRotation;
		// till here we have the number that has only rotated pivotPoint and
		// pivotPointPlus
		// so let's put em all in a string
		String answer = "";
		for (int i = 0; i < digit.length; i++) {
			answer += Integer.toString(digit[i]);
		}
		// now we devide the answer string to two parts, the first one is from the
		// beginning to pivotPointIndex and the second one is from pivotPointIndex + 1
		// till the end
		String answerFirst = "";
		String answerSecond = "";
		for (int i = 0; i <= pivotPointIndex; i++) {
			answerFirst += Character.toString(answer.charAt(i));
		}
		for (int i = pivotPointIndex + 1; i < x.length(); i++) {
			answerSecond += Character.toString(answer.charAt(i));
		}
		// and now let's sort the second part and put it back to the first part and
		// peint the answer ! :)
		answer = answerFirst + sort(answerSecond);
		return answer;
		// DONE :D
	}

	static String mod(String x, String y) {
		String devideAnswer = "";
		// now lets round the devision answer to the number below

		// we want the number without any aeshar
		for (int i = 0; i < divideXisBigger(x, y, false).length(); i++) {
			if (divideXisBigger(x, y, false).charAt(i) != '.') {
				devideAnswer += Character.toString(divideXisBigger(x, y, false).charAt(i));
			} else {
				break;
			}
		}
		return sub(x, multiply(y, devideAnswer));
		// return maghsoom - (maghsoom alayh * kharej ghesmat)
	}

	static String rep(String x, String y) {
		// this one adds x, y times to a string pre made by myself
		String tempRep = "";
		StringBuilder i = new StringBuilder();
		for (i = i.insert(0, "1"); checkBigger(y, i.toString()); i = i.replace(0, i.length(), add(i.toString(), "1"))) {
			tempRep += x;
		}
		// this is next plus string is because of checkBigger ! :(
		tempRep += x;
		return tempRep;
	}

	static String isPalindromes(String x, String y) {
		// the algorithm checks the two numbers like an X
		int j = y.length() - 1;
		for (int i = 0; i < x.length(); i++) {
			while (j >= 0) {
				if (x.charAt(i) == y.charAt(j)) {
					j = j - 1;
					break;
				} else {
					return "False";
				}
			}
		}
		return "True";
	}

	static String sumOfDigits(String number) {
		// the input is String, i get the character and then by - '0' i convert the
		// answer to int to be returned
		String sum = "0";
		int digit = 0;
		for (int i = 0; i < number.length(); i++) {
			digit = number.charAt(i) - '0';
			sum = add(sum, Integer.toString(digit));
		}
		return sum;
	}

	static String sort(String str) {
		// the one and only BUBBLE SORT !
		int[] number = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			number[i] = str.charAt(i) - '0';
		}
		boolean changed = true;
		int j = 0;
		int temp = 0;
		while (changed) {
			changed = false;
			j++;
			for (int i = 0; i < str.length() - j; i++) {
				if (number[i] > number[i + 1]) {
					temp = number[i];
					number[i] = number[i + 1];
					number[i + 1] = temp;
					changed = true;
				}
			}
		}
		String answer = "";
		for (int i = 0; i < str.length(); i++) {
			answer += Integer.toString(number[i]);
		}
		return answer;
	}

	static String rotate(String x, String y) {
		// this one's algorithm uses arrays and it just checks if the array counter has
		// overflowed, if yes the code sets it to the beginning which is 0
		int[] number = new int[x.length()];
		for (int i = 0; i < x.length(); i++) {
			number[i] = x.charAt(i) - '0';
		}
		int[] fakeNumber = new int[x.length()];
		for (int i = 0; i < number.length; i++) {
			fakeNumber[i] = 0;
		}
		int fakeIndex = 0;
		if (y.charAt(0) != '-') {
			y = mod(y, Integer.toString(x.length()));
			// abs is my way to check if a number is negative :D
			StringBuilder yTd = new StringBuilder();
			for (yTd = yTd.insert(0, "1"); checkBigger(add(y, "1"),
					yTd.toString()); yTd = yTd.replace(0, yTd.length(), add(yTd.toString(), "1"))) {
				for (int i = 0; i < number.length; i++) {
					fakeIndex = i + 1;
					if (fakeIndex == number.length) {
						fakeIndex = 0;
					}
					fakeNumber[fakeIndex] = number[i];
				}
				for (int i = 0; i < number.length; i++) {
					number[i] = fakeNumber[i];
				}
			}
		} else {
			y = y.substring(1, y.length());
			y = mod(y, Integer.toString(x.length()));
			StringBuilder yTd = new StringBuilder();
			for (yTd = yTd.insert(0, "1"); checkBigger(add(y, "1"),
					yTd.toString()); yTd = yTd.replace(0, yTd.length(), add(yTd.toString(), "1"))) {
				for (int i = 0; i < number.length; i++) {
					fakeIndex = i - 1;
					if (fakeIndex == -1) {
						fakeIndex = number.length - 1;
					}
					fakeNumber[fakeIndex] = number[i];
				}
				for (int i = 0; i < number.length; i++) {
					number[i] = fakeNumber[i];
				}
			}
		}
		String answer = "";
		String fakeNumberStr;
		// lets read the fake arrays that the code has made and put em all together and
		// print it as a string called answer !
		for (int i = 0; i < fakeNumber.length; i++) {
			fakeNumberStr = Integer.toString(fakeNumber[i]);
			answer += fakeNumberStr;
		}
		return answer;
	}

	static int indexOf(String x, String y) {
		// this indexOf command find the one and only pregiven string weather connected
		// to an another word or not
		int tempPosition = -1;
		for (int i = 0; i < x.length(); i++) {
			if (y.charAt(0) == x.charAt(i)) {
				tempPosition = i;
				for (int j = 1; j < y.length(); j++) {
					if (i != x.length() - 1) {
						i++;
					} else {
						return -1;
					}
					if (y.charAt(j) == x.charAt(i)) {
						if (j == y.length() - 1) {
							return tempPosition;
						}
					} else {
						break;
					}
				}
			}
		}
		return tempPosition;
	}

	static String subNumber(String x, int y, int z) {
		String answer = "";
		for (int i = y; i < z; i++) {
			answer += Character.toString(x.charAt(i));
		}
		return answer;
	}

	static String split(String x, String y) {
		while (indexOf(x, y) != -1) {
			int position = indexOf(x, y);
			x = subNumber(x, 0, position) + "_" + subNumber(x, position + y.length(), x.length());
		}
		return x;
	}
}
