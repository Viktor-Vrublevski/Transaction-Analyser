import com.test.bank.service.ExecuteService;
import com.test.bank.service.MainService;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.*;


public class ServiceTest {

    @Test
    public void testDate(){
        ExecuteService service = new ExecuteService();
        Date date1 = new Date(118, Calendar.AUGUST,20,13,12,22);
        Date date2 = service.getDate("20/08/2018 13:12:22");
        assertEquals(date1.getTime(), date2.getTime());
    }

    @Test
    public void testIsMatcher() {
        MainService service = new MainService();
        assertTrue(service.isMatcher("10/12/2015 10:00:00"));
    }

    @Test
    public void testGetArray(){
        ExecuteService service = new ExecuteService();
        String line = "ID, Date, Amount, Merchant, Type, Related Transaction";
        String[] array = service.getArray(line);
        assertNull(array);
    }

    @Test
    public void tesIsDate(){
        MainService service = new MainService();
        ExecuteService execute = new ExecuteService();
        Date current = execute.getDate("20/08/2018 12:45:33");
        assertTrue(service.isDate(current, "20/08/2018 10:00:00",
                "20/08/2018 13:00:00"));
    }

}
