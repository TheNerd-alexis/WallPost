package Model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {
	public static void main(String[] args) throws ParseException {
		DateFormat df = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.US);
		Date writetime = df.parse("Wed Jun 07 11:59:48 2016");
		System.out.println(writetime.toString());
	}
}
