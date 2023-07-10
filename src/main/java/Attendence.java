import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.time.DayOfWeek;

public class Attendence {
    public static void main(String[] args) {
    	
    	int year = 2023;
        int month = 10;
        int day = 9;
        
        // current time
        //LocalDate currentDate = LocalDate.now();

        // Create a LocalDate instance representing the first day of the desired month
        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        LocalDate customDate = LocalDate.of(year, month, day);
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = customDate.format(dateFormatter);

        // Get the starting day of the month (Sunday = 7, Monday = 1, etc.)
        int startingDay = firstDayOfMonth.getDayOfWeek().getValue();
       // Create a YearMonth instance representing the desired month
        YearMonth yearMonth = YearMonth.of(year, month);

        // Get the number of days in the month
        int daysInMonth = yearMonth.lengthOfMonth();
        
        // Count the number of Saturdays and Sundays
        int countSaturdays = 0;
        int countSundays = 0;
        for (int days = 1; days <= daysInMonth; days++) {
            LocalDate date = yearMonth.atDay(days);
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY) {
                countSaturdays++;
            } else if (dayOfWeek == DayOfWeek.SUNDAY) {
                countSundays++;
            }
        }
        System.out.println("Start Day: " + startingDay);
        System.out.println("Number of Saturdays in the month: " + countSaturdays);
        System.out.println("Number of Sundays in the month: " + countSundays);
    

    	
        try {
        	
            // Create a new Document
            Document document = new Document(PageSize.A4.rotate());

            // Create a new PdfWriter
            PdfWriter.getInstance(document, new FileOutputStream("C:\\\\Users\\\\anike\\\\Desktop\\\\student_activity\\\\Attendence_report.pdf"));

            document.open();
            
            Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Font bodyFont = FontFactory.getFont(FontFactory.TIMES_BOLD, 10);

            // Adding the heading
            Paragraph heading = new Paragraph("Attendence Report", headingFont);
            heading.setAlignment(Element.ALIGN_CENTER);
            heading.setSpacingAfter(15);
            document.add(heading);
            

            // Create a Paragraph element with the formatted date and time
            Paragraph dateParagraph = new Paragraph("Date : " + formattedDate);

            // Add the Paragraph to the Document
            document.add(dateParagraph);
            
            // Set font properties
            BaseFont baseFont = BaseFont.createFont("Helvetica", BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, 5, Font.NORMAL);
            
            
            // for loop for creating table
            for(int i = 1; i<=12; i++) {
            	// Add the class
            	String cl = Integer.toString(getClass(i));
                Paragraph classx = new Paragraph("class : " +cl , bodyFont);
                classx.setAlignment(Element.ALIGN_LEFT);
                classx.setSpacingAfter(10);
                document.add(classx);
                
                // Create the table with columns = numbers of days + 2 - (number of saturday + sunday)
                int noOfCol = daysInMonth - (countSaturdays+countSundays);
                PdfPTable table = new PdfPTable(noOfCol+2);
                table.setWidthPercentage(100);


                // Add table headers
                addCell(table, "Name:", font, Element.ALIGN_CENTER);
                addCell(table, "Roll Number ", font, Element.ALIGN_CENTER);
                
                int k;
                int start = 1;
                if(startingDay == 6) {
                	k = 0;
                	start = 3;
                } else if(startingDay == 7){
                	k = 0;
                	start = 2;
                } else {
                	k = startingDay-1;
                	start = startingDay-1;
                }
                
                
                String[] weekdays = {"mon", "tues", "wed", "thurs", "fri"};
                
                
                for(int j = 1; j<=(daysInMonth-(countSaturdays+countSundays)); j++) {
                	addCell(table, weekdays[k] + "("+start+")", font, Element.ALIGN_CENTER);
                	
                    k++;
                    if(k == 5) {
                    	k = 0;
                    	start = start+2;;
                    }
                    start++;
                    
                }

                // Add sample data rows
                String[] studentsName = {"Aniket", "Atul", "Rakesh", "Supriya", "Suraj", "Chandan", "Niranjan", "Rakesh", "Krishna", "Amit", "Dinanath", "Rohan", "Nayan", "Alok", "Aman", "Ayan", "Sami", "Sayan", "Karan"};
                String[] studentsRoll = {"123", "124", "125","126", "127","128","129", "130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "140", "141"};
                
                Map<String, String[]> record = new HashMap<>();
                record.put("123", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","A"});
                record.put("124", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","P"});
                record.put("125", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","A"});
                record.put("126", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","P"});
                record.put("127", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","A"});
                record.put("128", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","P"});
                record.put("129", new String[] {"P","A","P","A","P","A","P","A","P","A","P","A","P"});
                for (int s = 0; s < studentsName.length; s++) {
                    addCell(table, studentsName[s], font, Element.ALIGN_LEFT);
                    addCell(table, studentsRoll[s], font, Element.ALIGN_LEFT);
                    String key = studentsRoll[s];
                    String[] values = record.get(key);
                    if(values != null) {
                    	for(String value : values) {
                    		addCell(table, value, font, Element.ALIGN_CENTER);
                    	}
                    }
                    table.completeRow();
                }

                // Adding table to the document
                document.add(table);   
                // adding a new page
                document.newPage();
                
            }
            
            document.newPage();
            

            // Closing the Document
            document.close();

            System.out.println("Attendance sheet created successfully.");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    
private static void addCell(PdfPTable table, String text, Font font, int alignment) {
    PdfPCell cell = new PdfPCell(new Phrase(text, font));
    cell.setHorizontalAlignment(alignment);
    cell.setVerticalAlignment(Element.ALIGN_LEFT);
    table.addCell(cell);
}

    
public static int getClass(int i) {
	return i;
}
    
}
