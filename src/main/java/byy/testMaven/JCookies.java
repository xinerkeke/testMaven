package main.java.byy.testMaven;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class JCookies {
	private WebDriver driver;

	public JCookies(WebDriver driver) {
		this.driver = driver;
	}

	public void writeCookies() {

		File file = new File("broswer.data");

		try

		{
			// delete file if exists
			file.delete();

			file.createNewFile();

			FileWriter fw = new FileWriter(file);

			BufferedWriter bw = new BufferedWriter(fw);

			for (Cookie ck : driver.manage().getCookies())

			{

				Date tmp = ck.getExpiry();
				Date expiry = null;
				if (tmp != null) {
					expiry = getNextDate(tmp);
				}
				bw.write(ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() + ";" + expiry
						+ ";" + ck.isSecure());

				bw.newLine();

			}

			bw.flush();

			bw.close();

			fw.close();
		}

		catch (Exception e)

		{

			e.printStackTrace();

		}

		finally {
			System.out.println("cookie write to file");

		}

	}
	

	public Cookie readCookies() {
		Cookie ck = null;
		try {

			File file = new File("broswer.data");

			FileReader fr = new FileReader(file);

			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null)

			{

				StringTokenizer str = new StringTokenizer(line, ";");

				while (str.hasMoreTokens())

				{


					String name = str.nextToken();

					String value = "";
					if (!name.equals("imoiaDqmLoginName")) {
						value = str.nextToken();
					}

					String domain = str.nextToken();

					String path = str.nextToken();

					Date expiry = null;
					String dt = str.nextToken();

					if(!(dt.equals("null"))){
						Date tmp = new Date(dt);
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
						String tmp2 = sf.format(tmp);
						expiry = sf.parse(tmp2);
					}
					
					
					boolean isSecure = new Boolean(str.nextToken()).booleanValue();
					if((expiry != null)&&(isExpiry(expiry)))
					{
						return null;
					}


					ck = new Cookie(name, value, domain, path, expiry, isSecure);

					driver.manage().addCookie(ck);
				
				}

			}

		}

		catch (Exception e)

		{

			e.printStackTrace();

		}
		return ck;
	}

	public Date getNextDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 1);// ����+1��

		Date nextDay = c.getTime();
		return nextDay;
	}

	public boolean isExpiry(Date DATE1) {

		if (DATE1 == null) {
			return false;
		}
		try {
			Date local = new Date();
			System.out.println(local.toString());
			if (DATE1.getTime() > local.getTime()) {
				System.out.println("dt1 ��dt2ǰ");
				return false;
			} else if (DATE1.getTime() < local.getTime()) {
				System.out.println("dt1��dt2��");
				return true;
			} else {
				return true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return true;
	}
}
