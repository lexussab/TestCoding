package files;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class Program {
	
	//Задаем имя и путь к начальному файлу
	public static final String fName1 = ".."+ File.separator +"test1.txt";
	
	//Задаем имя и путь к конечному файлу
	public static final String fName2 = ".."+ File.separator +"test2.txt";
	public static final String fNameGZIP = ".."+ File.separator +"test2.gzip";

	public static void main(String[] args) throws IOException {
		
		File f1 = new File(fName1);
		if (f1.exists()) {
			
			BufferedReader reader = new BufferedReader(new FileReader(f1));
			FileWriter fw = new FileWriter(fName2);
			String s;
			
			//Проверяем размер файла
			if (f1.length() <= 10485760){
				
				//Чтение данных и перекодирование в новый файл
				while ((s = reader.readLine()) != null){
					byte[] b_s = s.getBytes("cp1251");
					String str = new String(b_s, "UTF8");
					fw.write(str + "\r\n");
				}
				reader.close();
				fw.close();
			}else {
				
		        GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(fNameGZIP));
		        
				//Чтение данных и перекодирование в новый файл
				while ((s = reader.readLine()) != null){
					byte[] b_s = s.getBytes("cp1251");
					String str = new String(b_s, "UTF8");
					byte[] buf = str.getBytes("UTF8");
					out.write(buf);	
				}
				out.finish();
		        out.close();
				reader.close();
				fw.close();
			}

		}else
			System.out.println("File not found");
	
	}
}
