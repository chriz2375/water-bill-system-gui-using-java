import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {

	public static void main(String[] args) {
		Date date = new Date();
		String fmt = "yy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		
		String dt = sdf.format(date);
		char ch = dt.charAt(4);
		
		System.out.println(sdf.format(date));
		System.out.println(ch);
		System.out.println(sdf.format(date));
		System.out.println(date.toString()); //  CST - Central Standard Time
		System.out.println("Day:"+date.getDay());		
		System.out.println("Date:"+date.getDate());		
		System.out.println("Hours:"+date.getHours());
		System.out.println("Minutes:"+date.getMinutes());
		System.out.println("Seconds:"+date.getSeconds());		
	}
}
